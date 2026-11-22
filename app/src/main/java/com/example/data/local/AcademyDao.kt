package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AcademyDao {

    // Accounts
    @Query("SELECT * FROM user_accounts ORDER BY id ASC")
    fun observeAllAccounts(): Flow<List<UserAccountEntity>>

    @Query("SELECT * FROM user_accounts WHERE isActiveSession = 1 LIMIT 1")
    fun observeActiveAccount(): Flow<UserAccountEntity?>

    @Query("SELECT * FROM user_accounts WHERE email = :email LIMIT 1")
    suspend fun getAccountByEmail(email: String): UserAccountEntity?

    @Query("SELECT * FROM user_accounts WHERE role = :role LIMIT 1")
    suspend fun getFirstAccountByRole(role: String): UserAccountEntity?

    @Query("SELECT COUNT(*) FROM user_accounts")
    suspend fun getAccountCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: UserAccountEntity): Long

    @Query("UPDATE user_accounts SET isActiveSession = 0")
    suspend fun clearActiveSessions()

    @Query("UPDATE user_accounts SET isActiveSession = 1 WHERE id = :accountId")
    suspend fun setActiveSession(accountId: Int)

    @Query("UPDATE user_accounts SET parentSupervised = :enabled WHERE id = :accountId")
    suspend fun updateParentSupervision(accountId: Int, enabled: Boolean)

    // Lesson Progress
    @Query("SELECT * FROM lesson_completions WHERE studentId = :studentId ORDER BY completedAt DESC")
    fun observeLessonCompletions(studentId: Int): Flow<List<LessonCompletionEntity>>

    @Query("SELECT * FROM lesson_completions ORDER BY completedAt DESC")
    fun observeAllLessonCompletions(): Flow<List<LessonCompletionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLessonCompletion(entity: LessonCompletionEntity)

    // Letter Practice
    @Query("SELECT * FROM letter_practice WHERE studentId = :studentId ORDER BY letterNumber ASC")
    fun observeLetterPractice(studentId: Int): Flow<List<LetterPracticeEntity>>

    @Query("SELECT * FROM letter_practice WHERE compositeId = :compositeId LIMIT 1")
    suspend fun getLetterPractice(compositeId: String): LetterPracticeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLetterPractice(entity: LetterPracticeEntity)

    // Quiz Attempts
    @Query("SELECT * FROM quiz_attempts WHERE studentId = :studentId ORDER BY completedAt DESC")
    fun observeQuizAttempts(studentId: Int): Flow<List<QuizAttemptEntity>>

    @Query("SELECT * FROM quiz_attempts ORDER BY completedAt DESC")
    fun observeAllQuizAttempts(): Flow<List<QuizAttemptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizAttempt(attempt: QuizAttemptEntity)

    // Certificates
    @Query("SELECT * FROM certificates WHERE studentId = :studentId ORDER BY stageId ASC")
    fun observeCertificates(studentId: Int): Flow<List<CertificateEntity>>

    @Query("SELECT * FROM certificates ORDER BY stageId ASC")
    fun observeAllCertificates(): Flow<List<CertificateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCertificate(certificate: CertificateEntity)

    // Teacher Uploads
    @Query("SELECT * FROM teacher_uploads ORDER BY createdAt DESC")
    fun observeTeacherUploads(): Flow<List<TeacherUploadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacherUpload(upload: TeacherUploadEntity)

    @androidx.room.Update
    suspend fun updateTeacherUpload(upload: TeacherUploadEntity)

    @Query("DELETE FROM teacher_uploads WHERE id = :id")
    suspend fun deleteTeacherUpload(id: Int)

    // Homework
    @Query("SELECT * FROM homework_items ORDER BY createdAt DESC")
    fun observeHomework(): Flow<List<HomeworkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomework(homework: HomeworkEntity)

    @Query("UPDATE homework_items SET completed = :completed WHERE id = :id")
    suspend fun setHomeworkCompleted(id: Int, completed: Boolean)

    // Contact Messages
    @Query("SELECT * FROM contact_messages ORDER BY createdAt DESC")
    fun observeContactMessages(): Flow<List<ContactMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContactMessage(message: ContactMessageEntity)

    // Admin App Settings
    @Query("SELECT * FROM app_settings WHERE id = 1 LIMIT 1")
    fun observeAppSettings(): Flow<AppSettingsEntity?>

    @Query("SELECT COUNT(*) FROM app_settings")
    suspend fun getSettingsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAppSettings(settings: AppSettingsEntity)

    // Admin Custom Dynamic Items (Hadith, Dua, Quran Verses, Courses, Notices)
    @Query("SELECT * FROM custom_academy_items ORDER BY createdAt DESC")
    fun observeCustomItems(): Flow<List<CustomAcademyItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomItem(item: CustomAcademyItemEntity)

    @androidx.room.Update
    suspend fun updateCustomItem(item: CustomAcademyItemEntity)

    @Query("DELETE FROM custom_academy_items WHERE id = :id")
    suspend fun deleteCustomItem(id: Int)
}
