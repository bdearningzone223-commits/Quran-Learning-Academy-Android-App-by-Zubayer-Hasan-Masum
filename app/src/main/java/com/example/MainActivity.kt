package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.Abc
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.AcademyViewModel
import com.example.ui.components.DownloadApkDialog
import com.example.ui.navigation.AcademyRoutes
import com.example.ui.screens.AboutFounderScreen
import com.example.ui.screens.AboutUsScreen
import com.example.ui.screens.AcademyHubScreen
import com.example.ui.screens.AdminPanelScreen
import com.example.ui.screens.ArabicAlphabetScreen
import com.example.ui.screens.ContactUsScreen
import com.example.ui.screens.CurriculumScreen
import com.example.ui.screens.DashboardsScreen
import com.example.ui.screens.DuaAzkarScreen
import com.example.ui.screens.HadithScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.IslamicCoursesScreen
import com.example.ui.screens.LevelDetailScreen
import com.example.ui.screens.LevelQuizScreen
import com.example.ui.screens.NooraniQaidaScreen
import com.example.ui.screens.PrivacyPolicyScreen
import com.example.ui.screens.QuranLearningScreen
import com.example.ui.screens.TajweedLearningScreen
import com.example.ui.screens.VideoLessonsScreen
import com.example.ui.theme.QuranAcademyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: AcademyViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            QuranAcademyTheme(darkTheme = uiState.isDarkTheme) {
                QuranLearningAcademyApp(viewModel = viewModel)
            }
        }
    }
}

private data class TopNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val mainNavItems = listOf(
    TopNavItem(AcademyRoutes.ROUTE_HOME, "Home", Icons.Filled.Home, Icons.Outlined.Home),
    TopNavItem(AcademyRoutes.ROUTE_CURRICULUM, "Levels", Icons.AutoMirrored.Filled.MenuBook, Icons.AutoMirrored.Outlined.MenuBook),
    TopNavItem(AcademyRoutes.ROUTE_ALPHABET, "Alphabet", Icons.Filled.Abc, Icons.Outlined.Abc),
    TopNavItem(AcademyRoutes.ROUTE_DASHBOARD, "Portal", Icons.Filled.School, Icons.Outlined.School),
    TopNavItem(AcademyRoutes.ROUTE_ACADEMY_HUB, "More", Icons.Filled.GridView, Icons.Outlined.GridView)
)

@Composable
fun QuranLearningAcademyApp(viewModel: AcademyViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val audioState by viewModel.audioState.collectAsStateWithLifecycle()
    val toastMessage by viewModel.toastMessage.collectAsStateWithLifecycle()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showMainChrome = currentRoute in mainNavItems.map { it.route }

    val snackbarHostState = remember { SnackbarHostState() }
    var showDownloadApkDialog by remember { mutableStateOf(false) }

    LaunchedEffect(toastMessage) {
        val msg = toastMessage
        if (msg != null) {
            snackbarHostState.showSnackbar(msg)
            viewModel.clearToast()
        }
    }

    fun navigateTopLevel(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useNavRail = maxWidth >= 600.dp

        Row(modifier = Modifier.fillMaxSize()) {
            if (showMainChrome && useNavRail) {
                NavigationRail(
                    modifier = Modifier.fillMaxHeight(),
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    mainNavItems.forEach { item ->
                        val selected = currentRoute == item.route
                        NavigationRailItem(
                            selected = selected,
                            onClick = { navigateTopLevel(item.route) },
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label) },
                            modifier = Modifier.testTag("nav_${item.route}")
                        )
                    }
                }
            }

            Scaffold(
                modifier = Modifier.weight(1f),
                contentWindowInsets = WindowInsets.safeDrawing,
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = {
                    if (showMainChrome && !useNavRail) {
                        NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                            mainNavItems.forEach { item ->
                                val selected = currentRoute == item.route
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { navigateTopLevel(item.route) },
                                    icon = {
                                        Icon(
                                            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.label
                                        )
                                    },
                                    label = { Text(item.label) },
                                    modifier = Modifier.testTag("nav_${item.route}")
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.TopCenter
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = AcademyRoutes.ROUTE_HOME,
                        modifier = Modifier
                            .fillMaxSize()
                            .widthIn(max = 760.dp)
                    ) {
                        composable(AcademyRoutes.ROUTE_HOME) {
                            HomeScreen(
                                uiState = uiState,
                                audioState = audioState,
                                onToggleTheme = viewModel::toggleTheme,
                                onDownloadApkClick = { showDownloadApkDialog = true },
                                onContinueLearning = { lvl -> navController.navigate(AcademyRoutes.levelDetail(lvl)) },
                                onOpenAlphabet = { navigateTopLevel(AcademyRoutes.ROUTE_ALPHABET) },
                                onOpenNooraniQaida = { navController.navigate(AcademyRoutes.ROUTE_NOORANI_QAIDA) },
                                onOpenTajweed = { navController.navigate(AcademyRoutes.ROUTE_TAJWEED) },
                                onOpenQuranLearning = { navController.navigate(AcademyRoutes.ROUTE_QURAN_LEARNING) },
                                onOpenHadith = { navController.navigate(AcademyRoutes.ROUTE_HADITH) },
                                onOpenDuaAzkar = { navController.navigate(AcademyRoutes.ROUTE_DUA_AZKAR) },
                                onOpenIslamicCourses = { navController.navigate(AcademyRoutes.ROUTE_ISLAMIC_COURSES) },
                                onOpenVideoLessons = { navController.navigate(AcademyRoutes.ROUTE_VIDEO_LESSONS) },
                                onOpenCurriculum = { navigateTopLevel(AcademyRoutes.ROUTE_CURRICULUM) },
                                onOpenDashboards = { navigateTopLevel(AcademyRoutes.ROUTE_DASHBOARD) },
                                onOpenAboutFounder = { navController.navigate(AcademyRoutes.ROUTE_ABOUT_FOUNDER) },
                                onOpenAboutUs = { navController.navigate(AcademyRoutes.ROUTE_ABOUT_US) },
                                onOpenPrivacyPolicy = { navController.navigate(AcademyRoutes.ROUTE_PRIVACY_POLICY) },
                                onOpenContactUs = { navController.navigate(AcademyRoutes.ROUTE_CONTACT_US) },
                                onOpenAdminPanel = { navController.navigate(AcademyRoutes.ROUTE_ADMIN_PANEL) },
                                onSpeakArabic = { arabic, phonetic ->
                                    viewModel.speakArabic(arabic, phonetic)
                                }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_CURRICULUM) {
                            CurriculumScreen(
                                uiState = uiState,
                                onOpenLevel = { lvl -> navController.navigate(AcademyRoutes.levelDetail(lvl)) },
                                onStartQuiz = { lvl -> navController.navigate(AcademyRoutes.levelQuiz(lvl)) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_ALPHABET) {
                            ArabicAlphabetScreen(
                                uiState = uiState,
                                audioState = audioState,
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                },
                                onToggleSlowMode = viewModel::toggleSlowAudio,
                                onPracticeLetter = viewModel::practiceLetter,
                                onTakeLevel1Quiz = { navController.navigate(AcademyRoutes.levelQuiz(1)) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_DASHBOARD) {
                            DashboardsScreen(
                                uiState = uiState,
                                onSwitchRole = viewModel::switchRole,
                                onRegisterAccount = viewModel::registerAccount,
                                onLoginAccount = viewModel::loginAccount,
                                onSetParentSupervision = viewModel::setParentSupervision,
                                onContinueLearning = { lvl -> navController.navigate(AcademyRoutes.levelDetail(lvl)) },
                                onToggleHomework = viewModel::toggleHomework,
                                onPublishTeacherContent = viewModel::publishTeacherContent,
                                onDeleteTeacherUpload = viewModel::deleteTeacherUpload,
                                onAssignHomework = viewModel::assignHomework,
                                onOpenAdminPanel = { navController.navigate(AcademyRoutes.ROUTE_ADMIN_PANEL) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_ACADEMY_HUB) {
                            AcademyHubScreen(
                                appSettings = uiState.appSettings,
                                onDownloadApkClick = { showDownloadApkDialog = true },
                                onOpenAdminPanel = { navController.navigate(AcademyRoutes.ROUTE_ADMIN_PANEL) },
                                onOpenNooraniQaida = { navController.navigate(AcademyRoutes.ROUTE_NOORANI_QAIDA) },
                                onOpenTajweed = { navController.navigate(AcademyRoutes.ROUTE_TAJWEED) },
                                onOpenQuranLearning = { navController.navigate(AcademyRoutes.ROUTE_QURAN_LEARNING) },
                                onOpenHadith = { navController.navigate(AcademyRoutes.ROUTE_HADITH) },
                                onOpenDuaAzkar = { navController.navigate(AcademyRoutes.ROUTE_DUA_AZKAR) },
                                onOpenIslamicCourses = { navController.navigate(AcademyRoutes.ROUTE_ISLAMIC_COURSES) },
                                onOpenVideoLessons = { navController.navigate(AcademyRoutes.ROUTE_VIDEO_LESSONS) },
                                onOpenAboutUs = { navController.navigate(AcademyRoutes.ROUTE_ABOUT_US) },
                                onOpenAboutFounder = { navController.navigate(AcademyRoutes.ROUTE_ABOUT_FOUNDER) },
                                onOpenContactUs = { navController.navigate(AcademyRoutes.ROUTE_CONTACT_US) },
                                onOpenPrivacyPolicy = { navController.navigate(AcademyRoutes.ROUTE_PRIVACY_POLICY) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_NOORANI_QAIDA) {
                            NooraniQaidaScreen(
                                audioState = audioState,
                                onBack = { navController.popBackStack() },
                                onOpenLevel = { lvl -> navController.navigate(AcademyRoutes.levelDetail(lvl)) },
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_TAJWEED) {
                            TajweedLearningScreen(
                                audioState = audioState,
                                onBack = { navController.popBackStack() },
                                onOpenLevel = { lvl -> navController.navigate(AcademyRoutes.levelDetail(lvl)) },
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_QURAN_LEARNING) {
                            QuranLearningScreen(
                                customQuranVerses = uiState.customItems.filter { it.itemType == "QURAN_AYAH" },
                                audioState = audioState,
                                onBack = { navController.popBackStack() },
                                onToggleSlowMode = viewModel::toggleSlowAudio,
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                },
                                onOpenLevel = { lvl -> navController.navigate(AcademyRoutes.levelDetail(lvl)) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_HADITH) {
                            HadithScreen(
                                customItems = uiState.customItems,
                                audioState = audioState,
                                onBack = { navController.popBackStack() },
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_DUA_AZKAR) {
                            DuaAzkarScreen(
                                customItems = uiState.customItems,
                                audioState = audioState,
                                onBack = { navController.popBackStack() },
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_ISLAMIC_COURSES) {
                            IslamicCoursesScreen(
                                uiState = uiState,
                                onBack = { navController.popBackStack() },
                                onOpenAlphabet = { navigateTopLevel(AcademyRoutes.ROUTE_ALPHABET) },
                                onOpenNooraniQaida = { navController.navigate(AcademyRoutes.ROUTE_NOORANI_QAIDA) },
                                onOpenTajweed = { navController.navigate(AcademyRoutes.ROUTE_TAJWEED) },
                                onOpenQuranLearning = { navController.navigate(AcademyRoutes.ROUTE_QURAN_LEARNING) },
                                onOpenHadith = { navController.navigate(AcademyRoutes.ROUTE_HADITH) },
                                onOpenDuaAzkar = { navController.navigate(AcademyRoutes.ROUTE_DUA_AZKAR) },
                                onOpenVideoLessons = { navController.navigate(AcademyRoutes.ROUTE_VIDEO_LESSONS) },
                                onOpenCurriculum = { navigateTopLevel(AcademyRoutes.ROUTE_CURRICULUM) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_VIDEO_LESSONS) {
                            VideoLessonsScreen(
                                uiState = uiState,
                                audioState = audioState,
                                onBack = { navController.popBackStack() },
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                },
                                onStopAudio = viewModel::stopAudio
                            )
                        }

                        composable(AcademyRoutes.ROUTE_ABOUT_US) {
                            AboutUsScreen(
                                onBack = { navController.popBackStack() },
                                onOpenFounder = { navController.navigate(AcademyRoutes.ROUTE_ABOUT_FOUNDER) },
                                onOpenPrivacy = { navController.navigate(AcademyRoutes.ROUTE_PRIVACY_POLICY) },
                                onOpenContact = { navController.navigate(AcademyRoutes.ROUTE_CONTACT_US) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_ABOUT_FOUNDER) {
                            AboutFounderScreen(
                                onBack = { navController.popBackStack() },
                                onOpenPrivacy = { navController.navigate(AcademyRoutes.ROUTE_PRIVACY_POLICY) },
                                onOpenContact = { navController.navigate(AcademyRoutes.ROUTE_CONTACT_US) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_CONTACT_US) {
                            ContactUsScreen(
                                uiState = uiState,
                                onBack = { navController.popBackStack() },
                                onSubmitContact = viewModel::submitContactForm,
                                onOpenFounder = { navController.navigate(AcademyRoutes.ROUTE_ABOUT_FOUNDER) },
                                onOpenPrivacy = { navController.navigate(AcademyRoutes.ROUTE_PRIVACY_POLICY) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_PRIVACY_POLICY) {
                            PrivacyPolicyScreen(
                                onBack = { navController.popBackStack() },
                                onOpenFounder = { navController.navigate(AcademyRoutes.ROUTE_ABOUT_FOUNDER) },
                                onOpenContact = { navController.navigate(AcademyRoutes.ROUTE_CONTACT_US) }
                            )
                        }

                        composable(AcademyRoutes.ROUTE_ADMIN_PANEL) {
                            AdminPanelScreen(
                                uiState = uiState,
                                onBack = { navController.popBackStack() },
                                onSaveSettings = viewModel::saveAppSettings,
                                onResetSettings = viewModel::resetAppSettings,
                                onSaveCustomItem = viewModel::saveCustomItem,
                                onDeleteCustomItem = viewModel::deleteCustomItem,
                                onPublishTeacherContent = viewModel::publishTeacherContent,
                                onUpdateTeacherUpload = viewModel::updateTeacherUpload,
                                onDeleteTeacherUpload = viewModel::deleteTeacherUpload,
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                }
                            )
                        }

                        composable(
                            route = AcademyRoutes.ROUTE_LEVEL_DETAIL,
                            arguments = listOf(navArgument("levelNumber") { type = NavType.IntType })
                        ) { entry ->
                            val lvl = entry.arguments?.getInt("levelNumber") ?: 1
                            LevelDetailScreen(
                                levelNumber = lvl,
                                uiState = uiState,
                                audioState = audioState,
                                onBack = { navController.popBackStack() },
                                onMarkLessonComplete = viewModel::markLessonCompleted,
                                onSpeakArabic = { arabic, phonetic, tag ->
                                    viewModel.speakArabic(arabic, phonetic, tag)
                                },
                                onStartQuiz = { qLvl -> navController.navigate(AcademyRoutes.levelQuiz(qLvl)) }
                            )
                        }

                        composable(
                            route = AcademyRoutes.ROUTE_LEVEL_QUIZ,
                            arguments = listOf(navArgument("levelNumber") { type = NavType.IntType })
                        ) { entry ->
                            val lvl = entry.arguments?.getInt("levelNumber") ?: 1
                            LevelQuizScreen(
                                levelNumber = lvl,
                                uiState = uiState,
                                onBack = { navController.popBackStack() },
                                onSubmitQuiz = viewModel::submitQuiz,
                                onContinueNextLevel = { nextLvl ->
                                    navController.navigate(AcademyRoutes.levelDetail(nextLvl)) {
                                        popUpTo(AcademyRoutes.ROUTE_CURRICULUM)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDownloadApkDialog) {
        DownloadApkDialog(
            apkDownloadUrl = uiState.appSettings.apkDownloadUrl,
            appVersionLabel = uiState.appSettings.appVersionLabel,
            onDismiss = { showDownloadApkDialog = false },
            onExportFeedback = viewModel::showMessage
        )
    }
}
