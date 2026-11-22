package com.example.ui.navigation

object AcademyRoutes {
    const val ROUTE_HOME = "home"
    const val ROUTE_CURRICULUM = "curriculum"
    const val ROUTE_ALPHABET = "arabic_alphabet"
    const val ROUTE_DASHBOARD = "dashboard"
    const val ROUTE_ACADEMY_HUB = "academy_hub"

    const val ROUTE_NOORANI_QAIDA = "noorani_qaida"
    const val ROUTE_TAJWEED = "tajweed_learning"
    const val ROUTE_QURAN_LEARNING = "quran_learning"
    const val ROUTE_VIDEO_LESSONS = "video_lessons"
    const val ROUTE_HADITH = "hadith"
    const val ROUTE_DUA_AZKAR = "dua_azkar"
    const val ROUTE_ISLAMIC_COURSES = "islamic_courses"
    const val ROUTE_ABOUT_US = "about_us"
    const val ROUTE_ABOUT_FOUNDER = "about_founder"
    const val ROUTE_CONTACT_US = "contact_us"
    const val ROUTE_PRIVACY_POLICY = "privacy_policy"
    const val ROUTE_ADMIN_PANEL = "admin_panel"

    const val ROUTE_LEVEL_DETAIL = "level_detail/{levelNumber}"
    const val ROUTE_LEVEL_QUIZ = "level_quiz/{levelNumber}"

    fun levelDetail(levelNumber: Int) = "level_detail/$levelNumber"
    fun levelQuiz(levelNumber: Int) = "level_quiz/$levelNumber"
}
