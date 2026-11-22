package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.local.AppSettingsEntity
import com.example.ui.components.AcademyOfficialFooter
import com.example.ui.components.DownloadApkPromoCard

@Composable
fun AcademyHubScreen(
    appSettings: AppSettingsEntity = AppSettingsEntity(),
    onDownloadApkClick: () -> Unit,
    onOpenAdminPanel: () -> Unit,
    onOpenNooraniQaida: () -> Unit,
    onOpenTajweed: () -> Unit,
    onOpenQuranLearning: () -> Unit,
    onOpenHadith: () -> Unit,
    onOpenDuaAzkar: () -> Unit,
    onOpenIslamicCourses: () -> Unit,
    onOpenVideoLessons: () -> Unit,
    onOpenAboutUs: () -> Unit,
    onOpenAboutFounder: () -> Unit,
    onOpenContactUs: () -> Unit,
    onOpenPrivacyPolicy: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Academy Directory", style = MaterialTheme.typography.headlineLarge)
                Text(
                    "Explore all 13 Quran Learning Academy modules, Hadith, Dua & Azkar, and APK download.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            DownloadApkPromoCard(onDownloadClick = onDownloadApkClick)
        }

        item {
            DirectoryRow(
                title = "Admin Control Panel (Founder Controls)",
                subtitle = "Update branding, live announcements, APK link & add any Arabic content",
                icon = Icons.Filled.AdminPanelSettings,
                tag = "menu_admin_panel",
                onClick = onOpenAdminPanel
            )
        }

        item {
            Text("Learning Modules", style = MaterialTheme.typography.titleMedium)
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                DirectoryRow(
                    title = "Islamic Courses (الدَّوْرَاتُ الإِسْلَامِيَّةُ)",
                    subtitle = "All structured Quran, Arabic, Hadith & Dua courses",
                    icon = Icons.Filled.School,
                    tag = "menu_islamic_courses",
                    onClick = onOpenIslamicCourses
                )
                DirectoryRow(
                    title = "Noorani Qaida (الْقَاعِدَةُ النُّورَانِيَّةُ)",
                    subtitle = "Levels 2–9 • Harakat, Tanween, Sukoon, Madd & Word Reading",
                    icon = Icons.AutoMirrored.Filled.MenuBook,
                    tag = "menu_noorani_qaida",
                    onClick = onOpenNooraniQaida
                )
                DirectoryRow(
                    title = "Tajweed Learning (أَحْكَامُ التَّجْوِيدِ)",
                    subtitle = "Levels 10–11 • Ghunnah, Qalqalah, Noon Sakinah & Makharij",
                    icon = Icons.Filled.RecordVoiceOver,
                    tag = "menu_tajweed",
                    onClick = onOpenTajweed
                )
                DirectoryRow(
                    title = "Quran Learning (الْقُرْآنُ الْكَرِيمُ)",
                    subtitle = "Levels 12–13 • Authentic Short Surahs & Verse-by-Verse Audio",
                    icon = Icons.Filled.AutoStories,
                    tag = "menu_quran_learning",
                    onClick = onOpenQuranLearning
                )
                DirectoryRow(
                    title = "Hadith (الأَحَادِيثُ النَّبَوِيَّةُ)",
                    subtitle = "Authentic Arabic Prophetic sayings with audio",
                    icon = Icons.Filled.MenuBook,
                    tag = "menu_hadith",
                    onClick = onOpenHadith
                )
                DirectoryRow(
                    title = "Dua & Azkar (الأَدْعِيَةُ وَالأَذْكَارُ)",
                    subtitle = "Daily Arabic duas and interactive Tasbeeh counter",
                    icon = Icons.Filled.VolunteerActivism,
                    tag = "menu_dua_azkar",
                    onClick = onOpenDuaAzkar
                )
                DirectoryRow(
                    title = "Video Lessons (الدُّرُوسُ الْمَرْئِيَّةُ)",
                    subtitle = "Interactive whiteboard classes across all 13 curriculum levels",
                    icon = Icons.Filled.PlayCircle,
                    tag = "menu_video_lessons",
                    onClick = onOpenVideoLessons
                )
            }
        }

        item {
            Text("Academy & Founder", style = MaterialTheme.typography.titleMedium)
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                DirectoryRow(
                    title = "About the Founder",
                    subtitle = "Zubayer Hasan Masum — Vision, Bio & Mission",
                    icon = Icons.Filled.Person,
                    tag = "menu_about_founder",
                    onClick = onOpenAboutFounder
                )
                DirectoryRow(
                    title = "About Us",
                    subtitle = "Our Islamic educational mission & qualified teacher review",
                    icon = Icons.Filled.Info,
                    tag = "menu_about_us",
                    onClick = onOpenAboutUs
                )
                DirectoryRow(
                    title = "Contact Us",
                    subtitle = "Reach the Academy team for support & guidance",
                    icon = Icons.Filled.ContactMail,
                    tag = "menu_contact_us",
                    onClick = onOpenContactUs
                )
                DirectoryRow(
                    title = "Privacy Policy & Child Safety",
                    subtitle = "Safe, parent-supervised learning & data protection",
                    icon = Icons.Filled.PrivacyTip,
                    tag = "menu_privacy_policy",
                    onClick = onOpenPrivacyPolicy
                )
            }
        }

        item {
            AcademyOfficialFooter(
                brandTitle = appSettings.brandTitle,
                founderName = appSettings.founderName,
                footerText = appSettings.footerText,
                onAboutFounderClick = onOpenAboutFounder,
                onPrivacyClick = onOpenPrivacyPolicy,
                onContactClick = onOpenContactUs
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun DirectoryRow(
    title: String,
    subtitle: String,
    icon: ImageVector,
    tag: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag(tag),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(42.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleSmall)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
