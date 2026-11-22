package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.ArabicAudioEngine
import com.example.audio.AudioPlaybackState
import com.example.data.AcademyRepository
import com.example.data.content.CurriculumCatalog
import com.example.data.local.AcademyDatabase
import com.example.data.local.AppSettingsEntity
import com.example.data.local.CertificateEntity
import com.example.data.local.ContactMessageEntity
import com.example.data.local.CustomAcademyItemEntity
import com.example.data.local.HomeworkEntity
import com.example.data.local.LessonCompletionEntity
import com.example.data.local.LetterPracticeEntity
import com.example.data.local.QuizAttemptEntity
import com.example.data.local.TeacherUploadEntity
import com.example.data.local.UserAccountEntity
import com.example.data.model.UserRole
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class AcademyUiState(
    val activeAccount: UserAccountEntity? = null,
    val allAccounts: List<UserAccountEntity> = emptyList(),
    val primaryStudentId: Int = 1,
    val primaryStudentName: String = "Amina Rahman",
    val completedLessons: List<LessonCompletionEntity> = emptyList(),
    val letterPracticeMap: Map<Int, Int> = emptyMap(),
    val quizAttempts: List<QuizAttemptEntity> = emptyList(),
    val certificates: List<CertificateEntity> = emptyList(),
    val teacherUploads: List<TeacherUploadEntity> = emptyList(),
    val homeworkItems: List<HomeworkEntity> = emptyList(),
    val contactMessages: List<ContactMessageEntity> = emptyList(),
    val appSettings: AppSettingsEntity = AppSettingsEntity(),
    val customItems: List<CustomAcademyItemEntity> = emptyList(),
    val isDarkTheme: Boolean = false
) {
    val activeRole: UserRole
        get() = UserRole.entries.firstOrNull { it.name == activeAccount?.role } ?: UserRole.STUDENT

    val passedLevels: Set<Int>
        get() = quizAttempts.filter { it.passed }.map { it.levelNumber }.toSet()

    val highestUnlockedLevel: Int
        get() = ((passedLevels.maxOrNull() ?: 0) + 1).coerceAtMost(13)

    val completedLessonIds: Set<String>
        get() = completedLessons.map { it.lessonId }.toSet()

    val totalStars: Int
        get() = quizAttempts
            .groupBy { it.levelNumber }
            .values
            .sumOf { attempts -> attempts.maxOfOrNull { it.starsEarned } ?: 0 }

    val earnedBadges: List<String>
        get() = quizAttempts
            .filter { it.passed }
            .distinctBy { it.levelNumber }
            .map { it.badgeTitle }

    val progressFraction: Float
        get() {
            val totalUnits = CurriculumCatalog.totalLessonsCount + 13
            val doneUnits = completedLessonIds.size + passedLevels.size
            return (doneUnits.toFloat() / totalUnits.toFloat()).coerceIn(0f, 1f)
        }

    val progressPercent: Int
        get() = (progressFraction * 100).toInt()
}

@OptIn(ExperimentalCoroutinesApi::class)
class AcademyViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AcademyDatabase.getInstance(application)
    private val repository = AcademyRepository(database.academyDao())
    private val audioEngine = ArabicAudioEngine(application)

    val audioState: StateFlow<AudioPlaybackState> = audioEngine.state

    private val _isDarkTheme = MutableStateFlow(false)
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    private val primaryStudentFlow = repository.allAccounts.flatMapLatest { accounts ->
        val active = accounts.firstOrNull { it.isActiveSession }
        val student = if (active?.role == UserRole.STUDENT.name) {
            active
        } else {
            accounts.firstOrNull { it.role == UserRole.STUDENT.name }
        }
        flowOf(student)
    }

    private val letterPracticeFlow = primaryStudentFlow.flatMapLatest { student ->
        if (student != null) repository.observeLetterPractice(student.id) else flowOf(emptyList())
    }

    private val baseDataFlow = combine(
        repository.activeAccount,
        repository.allAccounts,
        repository.allLessonCompletions,
        repository.allQuizAttempts,
        repository.allCertificates
    ) { active, all, lessons, quizzes, certs ->
        BaseDataBundle(active, all, lessons, quizzes, certs)
    }

    private val extraDataFlow = combine(
        repository.teacherUploads,
        repository.homeworkItems,
        repository.contactMessages,
        letterPracticeFlow,
        _isDarkTheme
    ) { uploads, homework, contacts, letters, dark ->
        ExtraDataBundle(uploads, homework, contacts, letters, dark)
    }

    private val adminDataFlow = combine(
        repository.appSettings,
        repository.customItems
    ) { settings, custom ->
        (settings ?: AppSettingsEntity()) to custom
    }

    val uiState: StateFlow<AcademyUiState> = combine(baseDataFlow, extraDataFlow, adminDataFlow) { base, extra, admin ->
        val student = if (base.active?.role == UserRole.STUDENT.name) {
            base.active
        } else {
            base.allAccounts.firstOrNull { it.role == UserRole.STUDENT.name }
        }
        val studentId = student?.id ?: 1
        AcademyUiState(
            activeAccount = base.active,
            allAccounts = base.allAccounts,
            primaryStudentId = studentId,
            primaryStudentName = student?.fullName ?: "Amina Rahman",
            completedLessons = base.lessons.filter { it.studentId == studentId },
            letterPracticeMap = extra.letters.associate { it.letterNumber to it.repeatCount },
            quizAttempts = base.quizzes.filter { it.studentId == studentId },
            certificates = base.certs.filter { it.studentId == studentId },
            teacherUploads = extra.uploads,
            homeworkItems = extra.homework,
            contactMessages = extra.contacts,
            appSettings = admin.first,
            customItems = admin.second,
            isDarkTheme = extra.isDark
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = AcademyUiState()
    )

    init {
        viewModelScope.launch {
            repository.ensureSeeded()
        }
    }

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }

    fun clearToast() {
        _toastMessage.value = null
    }

    fun showMessage(msg: String) {
        _toastMessage.value = msg
    }

    // Audio Controls
    fun speakArabic(arabicText: String, fallbackPhonetic: String? = null, tag: String = arabicText) {
        audioEngine.speak(arabicText, fallbackPhonetic, tag)
    }

    fun stopAudio() = audioEngine.stop()

    fun toggleSlowAudio() = audioEngine.toggleSlowMode()

    // Role & Auth Actions
    fun switchRole(role: UserRole) {
        viewModelScope.launch {
            repository.switchToRole(role)
            _toastMessage.value = "Switched to ${role.label} Dashboard"
        }
    }

    fun registerAccount(
        fullName: String,
        email: String,
        password: String,
        role: UserRole,
        ageOrNote: String,
        onResult: (Result<UserAccountEntity>) -> Unit
    ) {
        viewModelScope.launch {
            val res = repository.registerAccount(fullName, email, password, role, ageOrNote)
            res.onSuccess {
                _toastMessage.value = "Welcome to Quran Learning Academy, ${it.fullName}!"
            }
            onResult(res)
        }
    }

    fun loginAccount(
        email: String,
        password: String,
        onResult: (Result<UserAccountEntity>) -> Unit
    ) {
        viewModelScope.launch {
            val res = repository.loginAccount(email, password)
            res.onSuccess {
                _toastMessage.value = "Signed in as ${it.fullName}"
            }
            onResult(res)
        }
    }

    fun setParentSupervision(enabled: Boolean) {
        val account = uiState.value.activeAccount ?: return
        viewModelScope.launch {
            repository.setParentSupervision(account.id, enabled)
            _toastMessage.value = if (enabled) {
                "Parent Supervision Mode Enabled"
            } else {
                "Parent Supervision Mode Paused"
            }
        }
    }

    // Student Learning Actions
    fun markLessonCompleted(levelNumber: Int, lessonId: String) {
        val studentId = uiState.value.primaryStudentId
        viewModelScope.launch {
            repository.markLessonCompleted(studentId, levelNumber, lessonId)
            _toastMessage.value = "MashaAllah! Lesson marked complete."
        }
    }

    fun practiceLetter(letterNumber: Int) {
        val studentId = uiState.value.primaryStudentId
        viewModelScope.launch {
            repository.recordLetterPractice(studentId, letterNumber)
        }
    }

    fun submitQuiz(
        levelNumber: Int,
        score: Int,
        totalQuestions: Int,
        badgeTitle: String,
        onEvaluated: (QuizAttemptEntity) -> Unit
    ) {
        val state = uiState.value
        viewModelScope.launch {
            val attempt = repository.recordQuizResult(
                studentId = state.primaryStudentId,
                studentName = state.primaryStudentName,
                levelNumber = levelNumber,
                score = score,
                totalQuestions = totalQuestions,
                badgeTitle = badgeTitle,
                passedLevelsSoFar = state.passedLevels
            )
            onEvaluated(attempt)
        }
    }

    // Teacher Actions
    fun publishTeacherContent(
        contentType: String,
        levelNumber: Int,
        title: String,
        arabicContent: String,
        description: String,
        durationLabel: String,
        quizOptions: List<String> = emptyList(),
        correctIndex: Int = 0
    ) {
        if (title.isBlank() || description.isBlank()) {
            _toastMessage.value = "Please fill in a title and description."
            return
        }
        val teacherName = uiState.value.activeAccount?.fullName ?: "Ustadh Zubayer Hasan Masum"
        viewModelScope.launch {
            repository.addTeacherUpload(
                teacherName = teacherName,
                contentType = contentType,
                levelNumber = levelNumber,
                title = title,
                arabicContent = arabicContent,
                description = description,
                durationLabel = durationLabel,
                quizOptions = quizOptions,
                correctIndex = correctIndex
            )
            _toastMessage.value = "Published $contentType to Level $levelNumber!"
        }
    }

    fun deleteTeacherUpload(id: Int) {
        viewModelScope.launch {
            repository.deleteTeacherUpload(id)
            _toastMessage.value = "Content removed."
        }
    }

    fun assignHomework(
        levelNumber: Int,
        title: String,
        instructions: String,
        dueDateLabel: String
    ) {
        if (title.isBlank() || instructions.isBlank()) {
            _toastMessage.value = "Please enter homework title and instructions."
            return
        }
        val teacherName = uiState.value.activeAccount?.fullName ?: "Ustadh Zubayer Hasan Masum"
        viewModelScope.launch {
            repository.assignHomework(teacherName, levelNumber, title, instructions, dueDateLabel)
            _toastMessage.value = "Homework assigned to students!"
        }
    }

    fun toggleHomework(id: Int, completed: Boolean) {
        viewModelScope.launch {
            repository.toggleHomeworkCompleted(id, completed)
        }
    }

    // Contact Us
    fun submitContactForm(name: String, email: String, subject: String, message: String, onSuccess: () -> Unit) {
        if (name.isBlank() || email.isBlank() || message.isBlank()) {
            _toastMessage.value = "Please complete your name, email, and message."
            return
        }
        viewModelScope.launch {
            repository.submitContactMessage(
                senderName = name,
                email = email,
                subject = subject.ifBlank { "Academy Inquiry" },
                message = message
            )
            _toastMessage.value = "Thank you! Your message has been received."
            onSuccess()
        }
    }

    // Admin Panel Actions
    fun saveAppSettings(settings: AppSettingsEntity) {
        viewModelScope.launch {
            val sanitizedPin = settings.adminPin.trim().ifBlank { "2026" }
            repository.updateAppSettings(
                settings.copy(
                    id = 1,
                    brandTitle = settings.brandTitle.trim().ifBlank { "QURAN LEARNING ACADEMY" },
                    founderName = settings.founderName.trim().ifBlank { "ZUBAYER HASAN MASUM" },
                    appVersionLabel = settings.appVersionLabel.trim().ifBlank { "v1.0" },
                    adminPin = sanitizedPin
                )
            )
            _toastMessage.value = "App settings updated & applied live!"
        }
    }

    fun resetAppSettings() {
        viewModelScope.launch {
            repository.resetAppSettings()
            _toastMessage.value = "All app settings restored to defaults."
        }
    }

    fun saveCustomItem(item: CustomAcademyItemEntity) {
        if (item.titleArabic.isBlank() && item.titleEnglish.isBlank() && item.bodyArabic.isBlank()) {
            _toastMessage.value = "Please provide a title or Arabic text before saving."
            return
        }
        viewModelScope.launch {
            if (item.id == 0) {
                repository.addCustomItem(item)
                _toastMessage.value = "Added new ${item.itemType.lowercase().replace('_', ' ')} to the app!"
            } else {
                repository.updateCustomItem(item)
                _toastMessage.value = "Updated ${item.itemType.lowercase().replace('_', ' ')}!"
            }
        }
    }

    fun deleteCustomItem(id: Int) {
        viewModelScope.launch {
            repository.deleteCustomItem(id)
            _toastMessage.value = "Item deleted."
        }
    }

    fun updateTeacherUpload(upload: TeacherUploadEntity) {
        viewModelScope.launch {
            repository.updateTeacherUpload(upload)
            _toastMessage.value = "Updated ${upload.contentType} in Level ${upload.levelNumber}!"
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioEngine.shutdown()
    }

    private data class BaseDataBundle(
        val active: UserAccountEntity?,
        val allAccounts: List<UserAccountEntity>,
        val lessons: List<LessonCompletionEntity>,
        val quizzes: List<QuizAttemptEntity>,
        val certs: List<CertificateEntity>
    )

    private data class ExtraDataBundle(
        val uploads: List<TeacherUploadEntity>,
        val homework: List<HomeworkEntity>,
        val contacts: List<ContactMessageEntity>,
        val letters: List<LetterPracticeEntity>,
        val isDark: Boolean
    )
}
