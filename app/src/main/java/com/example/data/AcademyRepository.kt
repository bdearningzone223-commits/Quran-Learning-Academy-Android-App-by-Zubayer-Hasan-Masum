package com.example.data

import com.example.data.content.IslamicKnowledgeData
import com.example.data.local.AcademyDao
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
import kotlinx.coroutines.flow.Flow
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AcademyRepository(private val dao: AcademyDao) {

    val allAccounts: Flow<List<UserAccountEntity>> = dao.observeAllAccounts()
    val activeAccount: Flow<UserAccountEntity?> = dao.observeActiveAccount()
    val allLessonCompletions: Flow<List<LessonCompletionEntity>> = dao.observeAllLessonCompletions()
    val allQuizAttempts: Flow<List<QuizAttemptEntity>> = dao.observeAllQuizAttempts()
    val allCertificates: Flow<List<CertificateEntity>> = dao.observeAllCertificates()
    val teacherUploads: Flow<List<TeacherUploadEntity>> = dao.observeTeacherUploads()
    val homeworkItems: Flow<List<HomeworkEntity>> = dao.observeHomework()
    val contactMessages: Flow<List<ContactMessageEntity>> = dao.observeContactMessages()
    val appSettings: Flow<AppSettingsEntity?> = dao.observeAppSettings()
    val customItems: Flow<List<CustomAcademyItemEntity>> = dao.observeCustomItems()

    fun observeLetterPractice(studentId: Int): Flow<List<LetterPracticeEntity>> =
        dao.observeLetterPractice(studentId)

    suspend fun ensureSeeded() {
        if (dao.getSettingsCount() == 0) {
            dao.upsertAppSettings(AppSettingsEntity())
        }
        if (dao.getAccountCount() > 0) return

        val studentId = dao.insertAccount(
            UserAccountEntity(
                fullName = "Amina Rahman",
                email = "amina@quranacademy.edu",
                passwordHash = hashPassword("student123"),
                role = UserRole.STUDENT.name,
                studentAgeLabel = "Age 8 • Level 1 Explorer",
                linkedStudentName = "Amina Rahman",
                parentSupervised = true,
                isActiveSession = true
            )
        ).toInt()

        dao.insertAccount(
            UserAccountEntity(
                fullName = "Ustadh Zubayer Hasan Masum",
                email = "teacher@quranacademy.edu",
                passwordHash = hashPassword("teacher123"),
                role = UserRole.TEACHER.name,
                studentAgeLabel = "Founder & Senior Quran Educator",
                linkedStudentName = "Amina Rahman",
                parentSupervised = true,
                isActiveSession = false
            )
        )

        dao.insertAccount(
            UserAccountEntity(
                fullName = "Tariq Rahman (Parent)",
                email = "parent@quranacademy.edu",
                passwordHash = hashPassword("parent123"),
                role = UserRole.PARENT.name,
                studentAgeLabel = "Guardian of Amina Rahman",
                linkedStudentName = "Amina Rahman",
                parentSupervised = true,
                isActiveSession = false
            )
        )

        // Starter progress so dashboards immediately look alive and informative
        dao.upsertLessonCompletion(
            LessonCompletionEntity(
                compositeId = "${studentId}_l1_1",
                studentId = studentId,
                levelNumber = 1,
                lessonId = "l1_1",
                practiceCount = 3
            )
        )
        dao.upsertLetterPractice(
            LetterPracticeEntity(
                compositeId = "${studentId}_1",
                studentId = studentId,
                letterNumber = 1,
                repeatCount = 4
            )
        )
        dao.upsertLetterPractice(
            LetterPracticeEntity(
                compositeId = "${studentId}_2",
                studentId = studentId,
                letterNumber = 2,
                repeatCount = 3
            )
        )

        // Starter Teacher Content & Homework
        dao.insertTeacherUpload(
            TeacherUploadEntity(
                teacherName = "Ustadh Zubayer Hasan Masum",
                contentType = "AUDIO",
                levelNumber = 1,
                title = "Clear Pronunciation of Throat Letters",
                arabicContent = "حَ  عَ  خَ  غَ  هَ  أَ",
                description = "Listen slowly and repeat each throat letter three times with calm breathing.",
                durationLabel = "03:45",
                reviewedByScholar = true
            )
        )
        dao.insertTeacherUpload(
            TeacherUploadEntity(
                teacherName = "Ustadh Zubayer Hasan Masum",
                contentType = "VIDEO",
                levelNumber = 12,
                title = "Surah Al-Ikhlas Qalqalah Drill",
                arabicContent = "قُلْ هُوَ اللَّهُ أَحَدٌ ۝ اللَّهُ الصَّمَدُ",
                description = "Focus on the crisp bouncing echo at the end of each verse.",
                durationLabel = "04:20",
                reviewedByScholar = true
            )
        )

        dao.insertHomework(
            HomeworkEntity(
                teacherName = "Ustadh Zubayer Hasan Masum",
                levelNumber = 1,
                title = "Practice Alif to Kha (5 Repetitions)",
                instructions = "Open the Interactive Arabic Alphabet, tap ا through خ, and listen 5 times each.",
                dueDateLabel = "Due Friday",
                completed = false
            )
        )
        dao.insertHomework(
            HomeworkEntity(
                teacherName = "Ustadh Zubayer Hasan Masum",
                levelNumber = 12,
                title = "Recite Surah Al-Fatihah with Parent",
                instructions = "Recite all 7 verses aloud slowly with clear Harakat together with a parent.",
                dueDateLabel = "Due Sunday",
                completed = false
            )
        )
    }

    suspend fun switchToRole(role: UserRole) {
        val account = dao.getFirstAccountByRole(role.name) ?: return
        dao.clearActiveSessions()
        dao.setActiveSession(account.id)
    }

    suspend fun registerAccount(
        fullName: String,
        email: String,
        password: String,
        role: UserRole,
        ageOrNote: String
    ): Result<UserAccountEntity> {
        val cleanEmail = email.trim().lowercase(Locale.ROOT)
        if (fullName.isBlank() || cleanEmail.isBlank() || password.length < 4) {
            return Result.failure(IllegalArgumentException("Please enter name, email, and a password (4+ characters)."))
        }
        val existing = dao.getAccountByEmail(cleanEmail)
        if (existing != null) {
            return Result.failure(IllegalArgumentException("An account with this email already exists."))
        }
        dao.clearActiveSessions()
        val entity = UserAccountEntity(
            fullName = fullName.trim(),
            email = cleanEmail,
            passwordHash = hashPassword(password),
            role = role.name,
            studentAgeLabel = ageOrNote.ifBlank { role.label },
            linkedStudentName = if (role == UserRole.STUDENT) fullName.trim() else "Amina Rahman",
            parentSupervised = true,
            isActiveSession = true
        )
        val id = dao.insertAccount(entity).toInt()
        return Result.success(entity.copy(id = id))
    }

    suspend fun loginAccount(email: String, password: String): Result<UserAccountEntity> {
        val cleanEmail = email.trim().lowercase(Locale.ROOT)
        val account = dao.getAccountByEmail(cleanEmail)
            ?: return Result.failure(IllegalArgumentException("Account not found. Please register or try a demo account."))
        if (account.passwordHash != hashPassword(password)) {
            return Result.failure(IllegalArgumentException("Incorrect password. Please try again."))
        }
        dao.clearActiveSessions()
        dao.setActiveSession(account.id)
        return Result.success(account.copy(isActiveSession = true))
    }

    suspend fun setParentSupervision(accountId: Int, enabled: Boolean) {
        dao.updateParentSupervision(accountId, enabled)
    }

    suspend fun markLessonCompleted(studentId: Int, levelNumber: Int, lessonId: String) {
        dao.upsertLessonCompletion(
            LessonCompletionEntity(
                compositeId = "${studentId}_${lessonId}",
                studentId = studentId,
                levelNumber = levelNumber,
                lessonId = lessonId,
                practiceCount = 1,
                completedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun recordLetterPractice(studentId: Int, letterNumber: Int) {
        val key = "${studentId}_${letterNumber}"
        val existing = dao.getLetterPractice(key)
        dao.upsertLetterPractice(
            LetterPracticeEntity(
                compositeId = key,
                studentId = studentId,
                letterNumber = letterNumber,
                repeatCount = (existing?.repeatCount ?: 0) + 1,
                lastPracticedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun recordQuizResult(
        studentId: Int,
        studentName: String,
        levelNumber: Int,
        score: Int,
        totalQuestions: Int,
        badgeTitle: String,
        passedLevelsSoFar: Set<Int>
    ): QuizAttemptEntity {
        val ratio = if (totalQuestions > 0) score.toFloat() / totalQuestions else 0f
        val passed = ratio >= 0.70f
        val stars = when {
            ratio >= 0.95f -> 3
            ratio >= 0.75f -> 2
            passed -> 1
            else -> 0
        }
        val attempt = QuizAttemptEntity(
            studentId = studentId,
            levelNumber = levelNumber,
            score = score,
            totalQuestions = totalQuestions,
            passed = passed,
            starsEarned = stars,
            badgeTitle = if (passed) badgeTitle else "Keep Practicing"
        )
        dao.insertQuizAttempt(attempt)

        if (passed) {
            val updatedPassed = passedLevelsSoFar + levelNumber
            val dateLabel = SimpleDateFormat("MMMM dd, yyyy", Locale.US).format(Date())
            IslamicKnowledgeData.certificateStages.forEach { stage ->
                if (stage.requiredLevels.all { it in updatedPassed }) {
                    dao.upsertCertificate(
                        CertificateEntity(
                            compositeId = "${studentId}_${stage.stageId}",
                            studentId = studentId,
                            stageId = stage.stageId,
                            stageTitle = stage.title,
                            arabicTitle = stage.arabicTitle,
                            studentName = studentName,
                            issuedDateLabel = dateLabel,
                            founderName = "Zubayer Hasan Masum"
                        )
                    )
                }
            }
        }
        return attempt
    }

    suspend fun addTeacherUpload(
        teacherName: String,
        contentType: String,
        levelNumber: Int,
        title: String,
        arabicContent: String,
        description: String,
        durationLabel: String,
        quizOptions: List<String> = emptyList(),
        correctIndex: Int = 0
    ) {
        dao.insertTeacherUpload(
            TeacherUploadEntity(
                teacherName = teacherName,
                contentType = contentType,
                levelNumber = levelNumber,
                title = title.trim(),
                arabicContent = arabicContent.trim(),
                description = description.trim(),
                durationLabel = durationLabel.ifBlank { "04:00" },
                quizOptionsDelimited = quizOptions.joinToString("|"),
                correctOptionIndex = correctIndex,
                reviewedByScholar = true
            )
        )
    }

    suspend fun deleteTeacherUpload(id: Int) = dao.deleteTeacherUpload(id)

    suspend fun assignHomework(
        teacherName: String,
        levelNumber: Int,
        title: String,
        instructions: String,
        dueDateLabel: String
    ) {
        dao.insertHomework(
            HomeworkEntity(
                teacherName = teacherName,
                levelNumber = levelNumber,
                title = title.trim(),
                instructions = instructions.trim(),
                dueDateLabel = dueDateLabel.ifBlank { "This Week" },
                completed = false
            )
        )
    }

    suspend fun toggleHomeworkCompleted(id: Int, completed: Boolean) {
        dao.setHomeworkCompleted(id, completed)
    }

    suspend fun submitContactMessage(
        senderName: String,
        email: String,
        subject: String,
        message: String
    ) {
        dao.insertContactMessage(
            ContactMessageEntity(
                senderName = senderName.trim(),
                email = email.trim(),
                subject = subject.trim(),
                message = message.trim()
            )
        )
    }

    // Admin Settings & Dynamic Content
    suspend fun updateAppSettings(settings: AppSettingsEntity) {
        dao.upsertAppSettings(settings.copy(id = 1))
    }

    suspend fun resetAppSettings() {
        dao.upsertAppSettings(AppSettingsEntity(id = 1))
    }

    suspend fun addCustomItem(item: CustomAcademyItemEntity) {
        dao.insertCustomItem(item)
    }

    suspend fun updateCustomItem(item: CustomAcademyItemEntity) {
        dao.updateCustomItem(item)
    }

    suspend fun deleteCustomItem(id: Int) {
        dao.deleteCustomItem(id)
    }

    suspend fun updateTeacherUpload(upload: TeacherUploadEntity) {
        dao.updateTeacherUpload(upload)
    }

    private fun hashPassword(raw: String): String {
        val salt = "QLA_ZHM_2026_SALT_"
        val bytes = MessageDigest.getInstance("SHA-256").digest((salt + raw).toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
