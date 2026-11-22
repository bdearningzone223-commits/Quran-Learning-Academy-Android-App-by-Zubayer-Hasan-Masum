package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_accounts")
data class UserAccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val email: String,
    val passwordHash: String,
    val role: String, // STUDENT, TEACHER, PARENT
    val studentAgeLabel: String = "Age 8",
    val linkedStudentName: String = "Amina Rahman",
    val parentSupervised: Boolean = true,
    val isActiveSession: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "lesson_completions")
data class LessonCompletionEntity(
    @PrimaryKey val compositeId: String, // "${studentId}_${lessonId}"
    val studentId: Int,
    val levelNumber: Int,
    val lessonId: String,
    val practiceCount: Int = 1,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "letter_practice")
data class LetterPracticeEntity(
    @PrimaryKey val compositeId: String, // "${studentId}_${letterNumber}"
    val studentId: Int,
    val letterNumber: Int,
    val repeatCount: Int = 1,
    val lastPracticedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_attempts")
data class QuizAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val studentId: Int,
    val levelNumber: Int,
    val score: Int,
    val totalQuestions: Int,
    val passed: Boolean,
    val starsEarned: Int,
    val badgeTitle: String,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "certificates")
data class CertificateEntity(
    @PrimaryKey val compositeId: String, // "${studentId}_${stageId}"
    val studentId: Int,
    val stageId: String,
    val stageTitle: String,
    val arabicTitle: String,
    val studentName: String,
    val issuedDateLabel: String,
    val founderName: String = "Zubayer Hasan Masum"
)

@Entity(tableName = "teacher_uploads")
data class TeacherUploadEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val teacherName: String,
    val contentType: String, // LESSON, AUDIO, VIDEO, QUIZ
    val levelNumber: Int,
    val title: String,
    val arabicContent: String,
    val description: String,
    val durationLabel: String = "04:00",
    val quizOptionsDelimited: String = "",
    val correctOptionIndex: Int = 0,
    val reviewedByScholar: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "homework_items")
data class HomeworkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val teacherName: String,
    val levelNumber: Int,
    val title: String,
    val instructions: String,
    val dueDateLabel: String,
    val completed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "contact_messages")
data class ContactMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val senderName: String,
    val email: String,
    val subject: String,
    val message: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "app_settings")
data class AppSettingsEntity(
    @PrimaryKey val id: Int = 1,
    val brandTitle: String = "QURAN LEARNING ACADEMY",
    val founderName: String = "ZUBAYER HASAN MASUM",
    val officialTagline: String = "From Arabic Alphabet to the Holy Quran – Learn Step by Step.",
    val welcomeArabic: String = "السَّلَامُ عَلَيْكُمْ",
    val welcomeTitle: String = "Welcome to Quran Learning Academy",
    val welcomeSubtitle: String = "Learn Quran • Hadith • Dua • Islamic Courses",
    val announcementText: String = "",
    val showAnnouncement: Boolean = false,
    val apkDownloadUrl: String = "",
    val appVersionLabel: String = "v1.0",
    val footerText: String = "© 2026 Quran Learning Academy. All Rights Reserved. Founded & Created by Zubayer Hasan Masum.",
    val adminPin: String = "2026"
)

@Entity(tableName = "custom_academy_items")
data class CustomAcademyItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemType: String, // HADITH, DUA, QURAN_AYAH, COURSE, NOTICE
    val categoryArabic: String,
    val titleArabic: String,
    val titleEnglish: String,
    val bodyArabic: String,
    val transliteration: String = "",
    val meaning: String = "",
    val extraMeta: String = "1",
    val createdAt: Long = System.currentTimeMillis()
)
