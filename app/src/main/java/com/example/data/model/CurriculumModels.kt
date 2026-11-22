package com.example.data.model

import androidx.compose.ui.graphics.Color

enum class UserRole(val label: String) {
    STUDENT("Student"),
    TEACHER("Teacher"),
    PARENT("Parent")
}

data class ArabicLetter(
    val number: Int,
    val letter: String,
    val arabicName: String,
    val englishName: String,
    val pronunciationHint: String,
    val makhraj: String,
    val initialForm: String,
    val medialForm: String,
    val finalForm: String,
    val withFatha: String,
    val withKasra: String,
    val withDamma: String,
    val exampleArabic: String,
    val exampleTransliteration: String,
    val exampleMeaning: String,
    val speakText: String
)

data class LessonItem(
    val id: String,
    val levelNumber: Int,
    val title: String,
    val subtitle: String,
    val arabicShowcase: String,
    val transliteration: String,
    val explanation: String,
    val audioSpeakText: String,
    val practiceItems: List<PracticeWord>
)

data class PracticeWord(
    val arabic: String,
    val transliteration: String,
    val meaning: String = "",
    val speakText: String = arabic
)

data class QuizQuestion(
    val id: String,
    val prompt: String,
    val arabicDisplay: String? = null,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class VideoSlide(
    val title: String,
    val arabicFocus: String,
    val caption: String,
    val narration: String
)

data class VideoLessonData(
    val id: String,
    val levelNumber: Int,
    val title: String,
    val category: String,
    val durationLabel: String,
    val instructor: String,
    val description: String,
    val slides: List<VideoSlide>
)

data class CurriculumLevel(
    val number: Int,
    val title: String,
    val arabicTitle: String,
    val stageName: String,
    val summary: String,
    val badgeTitle: String,
    val estimatedMinutes: Int,
    val lessons: List<LessonItem>,
    val videoLesson: VideoLessonData,
    val quizQuestions: List<QuizQuestion>
)

data class TajweedRuleData(
    val id: String,
    val title: String,
    val arabicTitle: String,
    val category: String,
    val description: String,
    val letters: String,
    val exampleArabic: String,
    val exampleTransliteration: String,
    val exampleRuleNote: String,
    val accentColor: Color
)

data class MakhrajArea(
    val id: String,
    val name: String,
    val arabicName: String,
    val letters: String,
    val explanation: String,
    val practiceWord: String
)

data class SurahAyah(
    val number: Int,
    val arabic: String,
    val transliteration: String,
    val translation: String
)

data class SurahData(
    val number: Int,
    val nameEnglish: String,
    val nameArabic: String,
    val meaning: String,
    val revelationType: String,
    val ayahs: List<SurahAyah>
)

data class CertificateStage(
    val stageId: String,
    val title: String,
    val arabicTitle: String,
    val requiredLevels: List<Int>,
    val description: String
)
