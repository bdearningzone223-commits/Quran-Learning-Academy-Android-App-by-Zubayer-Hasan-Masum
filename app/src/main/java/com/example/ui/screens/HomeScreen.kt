package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.audio.AudioPlaybackState
import com.example.data.content.ArabicAlphabetData
import com.example.data.content.CurriculumCatalog
import com.example.ui.AcademyUiState
import com.example.ui.components.AcademyOfficialFooter
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.components.DownloadApkPromoCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft
import com.example.ui.theme.PureWhite

@Composable
fun HomeScreen(
    uiState: AcademyUiState,
    audioState: AudioPlaybackState,
    onToggleTheme: () -> Unit,
    onDownloadApkClick: () -> Unit,
    onContinueLearning: (Int) -> Unit,
    onOpenAlphabet: () -> Unit,
    onOpenNooraniQaida: () -> Unit,
    onOpenTajweed: () -> Unit,
    onOpenQuranLearning: () -> Unit,
    onOpenHadith: () -> Unit,
    onOpenDuaAzkar: () -> Unit,
    onOpenIslamicCourses: () -> Unit,
    onOpenVideoLessons: () -> Unit,
    onOpenCurriculum: () -> Unit,
    onOpenDashboards: () -> Unit,
    onOpenAboutFounder: () -> Unit,
    onOpenAboutUs: () -> Unit,
    onOpenPrivacyPolicy: () -> Unit,
    onOpenContactUs: () -> Unit,
    onOpenAdminPanel: () -> Unit,
    onSpeakArabic: (String, String?) -> Unit
) {
    val featuredLetters = ArabicAlphabetData.letters.take(8)
    val currentLevel = CurriculumCatalog.getLevel(uiState.highestUnlockedLevel)
    val settings = uiState.appSettings
    val notices = uiState.customItems.filter { it.itemType == "NOTICE" }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Branded Academy Top Header with Founder Name & Download APK Pill
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_academy_logo),
                        contentDescription = "Quran Learning Academy Logo",
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column {
                        Text(
                            text = settings.brandTitle.ifBlank { stringResource(R.string.brand_title) },
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "By ${settings.founderName.ifBlank { "ZUBAYER HASAN MASUM" }}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onOpenAdminPanel,
                        modifier = Modifier.testTag("home_admin_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AdminPanelSettings,
                            contentDescription = "Open Admin Panel",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Surface(
                        onClick = onDownloadApkClick,
                        shape = RoundedCornerShape(50),
                        color = GoldSoft,
                        border = BorderStroke(1.dp, GoldBright),
                        modifier = Modifier.testTag("header_download_apk_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Download,
                                contentDescription = "Download APK",
                                tint = EmeraldDeep,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = "APK",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldDeep
                            )
                        }
                    }
                    IconButton(
                        onClick = onToggleTheme,
                        modifier = Modifier.testTag("theme_toggle_button")
                    ) {
                        Icon(
                            imageVector = if (uiState.isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                            contentDescription = "Toggle theme",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Live Admin Announcement Banner & Custom Notices
        if ((settings.showAnnouncement && settings.announcementText.isNotBlank()) || notices.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (settings.showAnnouncement && settings.announcementText.isNotBlank()) {
                        Card(
                            modifier = Modifier.fillMaxWidth().testTag("live_announcement_banner"),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = GoldSoft),
                            border = BorderStroke(1.2.dp, GoldBright)
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Icon(Icons.Filled.Campaign, contentDescription = null, tint = EmeraldDeep)
                                Text(
                                    text = settings.announcementText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = EmeraldDeep,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                    notices.take(3).forEach { notice ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
                            border = BorderStroke(1.dp, GoldBorder)
                        ) {
                            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                if (notice.titleArabic.isNotBlank()) {
                                    ArabicTextDisplay(
                                        text = notice.titleArabic,
                                        fontSize = 20.sp,
                                        color = EmeraldDeep,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                if (notice.bodyArabic.isNotBlank()) {
                                    ArabicTextDisplay(
                                        text = notice.bodyArabic,
                                        fontSize = 18.sp,
                                        color = EmeraldPrimary,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                if (notice.meaning.isNotBlank()) {
                                    Text(notice.meaning, style = MaterialTheme.typography.bodySmall, color = EmeraldDeep)
                                }
                            }
                        }
                    }
                }
            }
        }

        // Hero Banner Card with Illustrated Islamic Arch & Continue Learning
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(R.drawable.img_hero_banner),
                        contentDescription = "Quran Learning Academy Banner",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(255.dp),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(255.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        EmeraldDeep.copy(alpha = 0.35f),
                                        EmeraldDeep.copy(alpha = 0.88f)
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            color = GoldBright.copy(alpha = 0.22f),
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(1.dp, GoldBright)
                        ) {
                            Text(
                                text = "Founded & Created by ${settings.founderName}",
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldSoft,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = settings.brandTitle,
                            style = MaterialTheme.typography.headlineLarge,
                            color = PureWhite
                        )
                        Text(
                            text = settings.officialTagline.ifBlank { stringResource(R.string.official_tagline) },
                            style = MaterialTheme.typography.bodyMedium,
                            color = PureWhite.copy(alpha = 0.92f)
                        )
                        Spacer(Modifier.height(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { onContinueLearning(currentLevel.number) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .testTag("hero_continue_learning_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GoldBright,
                                    contentColor = EmeraldDeep
                                )
                            ) {
                                Text(
                                    text = "Continue Level ${currentLevel.number}",
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            OutlinedButton(
                                onClick = onOpenAlphabet,
                                modifier = Modifier
                                    .height(48.dp)
                                    .testTag("hero_alphabet_button"),
                                shape = RoundedCornerShape(14.dp),
                                border = BorderStroke(1.2.dp, PureWhite.copy(alpha = 0.8f)),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = PureWhite)
                            ) {
                                Text("29 Letters")
                            }
                        }
                    }
                }
            }
        }

        // Salam Welcome Card ("السلام عليكم" + "Learn Quran • Hadith • Dua • Islamic Courses")
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
                border = BorderStroke(1.dp, GoldBorder)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ArabicTextDisplay(
                        text = settings.welcomeArabic.ifBlank { "السَّلَامُ عَلَيْكُمْ" },
                        fontSize = 32.sp,
                        color = EmeraldDeep
                    )
                    Text(
                        text = settings.welcomeTitle.ifBlank { "Welcome to Quran Learning Academy" },
                        style = MaterialTheme.typography.titleLarge,
                        color = EmeraldDeep,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = settings.welcomeSubtitle.ifBlank { "Learn Quran • Hadith • Dua • Islamic Courses" },
                        style = MaterialTheme.typography.bodySmall,
                        color = EmeraldDeep.copy(alpha = 0.85f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onOpenQuranLearning,
                            modifier = Modifier.weight(1f).height(48.dp).testTag("quick_quran_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary, contentColor = PureWhite)
                        ) {
                            Icon(Icons.Filled.AutoStories, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Quran Learning", maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                        Button(
                            onClick = onOpenHadith,
                            modifier = Modifier.weight(1f).height(48.dp).testTag("quick_hadith_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldDeep, contentColor = PureWhite)
                        ) {
                            Icon(Icons.Filled.MenuBook, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Hadith", maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onOpenDuaAzkar,
                            modifier = Modifier.weight(1f).height(48.dp).testTag("quick_dua_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldDeep, contentColor = PureWhite)
                        ) {
                            Icon(Icons.Filled.VolunteerActivism, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Dua & Azkar", maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                        Button(
                            onClick = onOpenIslamicCourses,
                            modifier = Modifier.weight(1f).height(48.dp).testTag("quick_courses_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldBright, contentColor = EmeraldDeep)
                        ) {
                            Icon(Icons.Filled.School, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Islamic Courses", fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }
        }

        // Student Live Progress Strip
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenDashboards() }
                    .testTag("home_progress_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Assalamu Alaikum, ${uiState.primaryStudentName}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Current Level ${uiState.highestUnlockedLevel}: ${currentLevel.title}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Surface(
                            color = GoldSoft,
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(1.dp, GoldBorder)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Filled.Star, contentDescription = null, tint = GoldBright, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = "${uiState.totalStars} Stars",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDeep
                                )
                            }
                        }
                    }
                    LinearProgressIndicator(
                        progress = { uiState.progressFraction.coerceAtLeast(0.04f) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(50)),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.primaryContainer
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${uiState.progressPercent}% Complete • ${uiState.completedLessonIds.size}/${CurriculumCatalog.totalLessonsCount} Lessons",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${uiState.certificates.size}/5 Certificates",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }

        // Prominent Download APK Card
        item {
            DownloadApkPromoCard(onDownloadClick = onDownloadApkClick)
        }

        // Quick Learning Portals (All Major Academy Sections)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionHeader(
                    title = "Learning Portals",
                    subtitle = "Explore every step of your Islamic learning journey"
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PortalTile(
                        title = "Arabic Alphabet",
                        subtitle = "29 Interactive Letters",
                        icon = Icons.Filled.Abc,
                        accent = EmeraldPrimary,
                        modifier = Modifier.weight(1f).testTag("portal_alphabet"),
                        onClick = onOpenAlphabet
                    )
                    PortalTile(
                        title = "Noorani Qaida",
                        subtitle = "Harakat to Words",
                        icon = Icons.AutoMirrored.Filled.MenuBook,
                        accent = Color(0xFF157A6E),
                        modifier = Modifier.weight(1f).testTag("portal_qaida"),
                        onClick = onOpenNooraniQaida
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PortalTile(
                        title = "Tajweed Learning",
                        subtitle = "Rules & Makharij",
                        icon = Icons.Filled.RecordVoiceOver,
                        accent = Color(0xFFB38218),
                        modifier = Modifier.weight(1f).testTag("portal_tajweed"),
                        onClick = onOpenTajweed
                    )
                    PortalTile(
                        title = "Quran Learning",
                        subtitle = "Short Surahs & Audio",
                        icon = Icons.Filled.AutoStories,
                        accent = EmeraldDeep,
                        modifier = Modifier.weight(1f).testTag("portal_quran"),
                        onClick = onOpenQuranLearning
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PortalTile(
                        title = "Hadith (الأحاديث)",
                        subtitle = "Prophetic Sayings",
                        icon = Icons.Filled.MenuBook,
                        accent = Color(0xFF0E6B53),
                        modifier = Modifier.weight(1f).testTag("portal_hadith"),
                        onClick = onOpenHadith
                    )
                    PortalTile(
                        title = "Dua & Azkar",
                        subtitle = "Daily Arabic Duas",
                        icon = Icons.Filled.VolunteerActivism,
                        accent = Color(0xFF9C7214),
                        modifier = Modifier.weight(1f).testTag("portal_dua"),
                        onClick = onOpenDuaAzkar
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PortalTile(
                        title = "Video Lessons",
                        subtitle = "13 Guided Classes",
                        icon = Icons.Filled.PlayCircle,
                        accent = Color(0xFF0E6B53),
                        modifier = Modifier.weight(1f).testTag("portal_videos"),
                        onClick = onOpenVideoLessons
                    )
                    PortalTile(
                        title = "3 Dashboards",
                        subtitle = "Student • Teacher • Parent",
                        icon = Icons.Filled.School,
                        accent = Color(0xFF8A6510),
                        modifier = Modifier.weight(1f).testTag("portal_dashboards"),
                        onClick = onOpenDashboards
                    )
                }
            }
        }

        // Interactive Arabic Alphabet Quick Practice Strip
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionHeader(
                    title = "Tap to Hear Arabic Letters",
                    subtitle = "Interactive audio pronunciation for young learners",
                    actionLabel = "All 29",
                    onActionClick = onOpenAlphabet
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(featuredLetters) { letter ->
                        val isActive = audioState.activeTag == "home_letter_${letter.number}" && audioState.isSpeaking
                        Card(
                            modifier = Modifier
                                .width(84.dp)
                                .clickable {
                                    onSpeakArabic(letter.speakText, letter.englishName)
                                }
                                .testTag("home_letter_${letter.number}"),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isActive) GoldSoft else MaterialTheme.colorScheme.surface
                            ),
                            border = BorderStroke(
                                width = if (isActive) 1.5.dp else 1.dp,
                                color = if (isActive) GoldBright else MaterialTheme.colorScheme.outline
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp, horizontal = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ArabicTextDisplay(
                                    text = letter.letter,
                                    fontSize = 36.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = letter.englishName,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "Hear ${letter.englishName}",
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // 13-Level Curriculum Preview
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionHeader(
                    title = "13-Level Structured Path",
                    subtitle = "Lessons, audio, video, exercises & exams at every level",
                    actionLabel = "View All",
                    onActionClick = onOpenCurriculum
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(CurriculumCatalog.allLevels.take(6)) { level ->
                        val unlocked = level.number <= uiState.highestUnlockedLevel
                        val passed = level.number in uiState.passedLevels
                        Card(
                            modifier = Modifier
                                .width(220.dp)
                                .clickable { onContinueLearning(level.number) },
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, if (passed) GoldBright else MaterialTheme.colorScheme.outline)
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        color = if (unlocked) EmeraldSoft else MaterialTheme.colorScheme.surfaceVariant,
                                        shape = RoundedCornerShape(50)
                                    ) {
                                        Text(
                                            text = "Level ${level.number}",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = if (unlocked) EmeraldDeep else MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }
                                    if (passed) {
                                        Icon(
                                            imageVector = Icons.Filled.EmojiEvents,
                                            contentDescription = "Completed",
                                            tint = GoldBright,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                                ArabicTextDisplay(
                                    text = level.arabicTitle,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = level.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = level.summary,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }

        // About the Founder Spotlight Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenAboutFounder() }
                    .testTag("home_founder_card"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.2.dp, GoldBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_founder_portrait),
                        contentDescription = "Founder Zubayer Hasan Masum",
                        modifier = Modifier
                            .size(84.dp)
                            .clip(RoundedCornerShape(18.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "FOUNDER & CREATOR",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = settings.founderName.ifBlank { "ZUBAYER HASAN MASUM" },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "\"Making Quran Learning Easier for Every Child.\"",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Read Founder's Story →",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Teacher-Reviewed Accuracy & Child-Safe Trust Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldSoft)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(Icons.Filled.VerifiedUser, contentDescription = null, tint = EmeraldDeep)
                        Text(
                            text = "Scholar-Reviewed & Parent-Supervised",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDeep
                        )
                    }
                    Text(
                        text = "Every Quranic text, Noorani Qaida drill, and Tajweed lesson is structured for review by qualified Quran teachers. Children's personal data stays protected locally with parent-supervised dashboards.",
                        style = MaterialTheme.typography.bodySmall,
                        color = EmeraldDeep.copy(alpha = 0.88f)
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedButton(
                            onClick = onOpenAboutUs,
                            border = BorderStroke(1.dp, EmeraldDeep)
                        ) {
                            Text("About Us", color = EmeraldDeep)
                        }
                        OutlinedButton(
                            onClick = onOpenPrivacyPolicy,
                            border = BorderStroke(1.dp, EmeraldDeep)
                        ) {
                            Icon(Icons.Filled.Shield, contentDescription = null, tint = EmeraldDeep, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Child Safety", color = EmeraldDeep)
                        }
                    }
                }
            }
        }

        // Official Branded Footer
        item {
            AcademyOfficialFooter(
                brandTitle = settings.brandTitle,
                founderName = settings.founderName,
                footerText = settings.footerText,
                onAboutFounderClick = onOpenAboutFounder,
                onPrivacyClick = onOpenPrivacyPolicy,
                onContactClick = onOpenContactUs
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun PortalTile(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accent: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                color = accent.copy(alpha = 0.14f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = accent,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
