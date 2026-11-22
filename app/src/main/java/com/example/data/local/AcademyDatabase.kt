package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        UserAccountEntity::class,
        LessonCompletionEntity::class,
        LetterPracticeEntity::class,
        QuizAttemptEntity::class,
        CertificateEntity::class,
        TeacherUploadEntity::class,
        HomeworkEntity::class,
        ContactMessageEntity::class,
        AppSettingsEntity::class,
        CustomAcademyItemEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AcademyDatabase : RoomDatabase() {
    abstract fun academyDao(): AcademyDao

    companion object {
        @Volatile
        private var INSTANCE: AcademyDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `app_settings` (
                        `id` INTEGER NOT NULL,
                        `brandTitle` TEXT NOT NULL,
                        `founderName` TEXT NOT NULL,
                        `officialTagline` TEXT NOT NULL,
                        `welcomeArabic` TEXT NOT NULL,
                        `welcomeTitle` TEXT NOT NULL,
                        `welcomeSubtitle` TEXT NOT NULL,
                        `announcementText` TEXT NOT NULL,
                        `showAnnouncement` INTEGER NOT NULL,
                        `apkDownloadUrl` TEXT NOT NULL,
                        `appVersionLabel` TEXT NOT NULL,
                        `footerText` TEXT NOT NULL,
                        `adminPin` TEXT NOT NULL,
                        PRIMARY KEY(`id`)
                    )
                    """.trimIndent()
                )
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `custom_academy_items` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `itemType` TEXT NOT NULL,
                        `categoryArabic` TEXT NOT NULL,
                        `titleArabic` TEXT NOT NULL,
                        `titleEnglish` TEXT NOT NULL,
                        `bodyArabic` TEXT NOT NULL,
                        `transliteration` TEXT NOT NULL,
                        `meaning` TEXT NOT NULL,
                        `extraMeta` TEXT NOT NULL,
                        `createdAt` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        fun getInstance(context: Context): AcademyDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AcademyDatabase::class.java,
                    "quran_learning_academy.db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
