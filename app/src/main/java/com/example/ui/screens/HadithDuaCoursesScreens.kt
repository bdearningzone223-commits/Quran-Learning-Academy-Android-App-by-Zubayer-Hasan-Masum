package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioPlaybackState
import com.example.data.content.DuaAzkarItem
import com.example.data.content.HadithAndDuaData
import com.example.data.content.HadithItem
import com.example.data.local.CustomAcademyItemEntity
import com.example.ui.AcademyUiState
import com.example.ui.components.AcademySubTopBar
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.components.AudioPronounceButton
import com.example.ui.components.IslamicGeometricOverlay
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft
import com.example.ui.theme.PureWhite

@Composable
fun HadithScreen(
    customItems: List<CustomAcademyItemEntity> = emptyList(),
    audioState: AudioPlaybackState,
    onBack: () -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit
) {
    var arabicOnlyMode by remember { mutableStateOf(true) }
    val customHadiths = remember(customItems) {
        customItems.filter { it.itemType == "HADITH" }.mapIndexed { idx, item ->
            HadithItem(
                id = "custom_h_${item.id}",
                number = idx + 1,
                category = item.categoryArabic.ifBlank { "حَدِيثٌ شَرِيفٌ" },
                titleArabic = item.titleArabic,
                textArabic = item.bodyArabic.ifBlank { item.titleArabic },
                sourceArabic = item.titleEnglish.ifBlank { "مُضَافٌ مِنَ الْإِدَارَةِ" },
                transliteration = item.transliteration,
                meaning = item.meaning
            )
        }
    }
    val allHadiths = remember(customHadiths) {
        (customHadiths + HadithAndDuaData.hadithList).mapIndexed { index, item ->
            item.copy(number = index + 1)
        }
    }

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "الأَحَادِيثُ النَّبَوِيَّةُ • Hadith",
                subtitle = "Authentic Prophetic Sayings in Arabic",
                onBack = onBack,
                actions = {
                    FilterChip(
                        selected = !arabicOnlyMode,
                        onClick = { arabicOnlyMode = !arabicOnlyMode },
                        label = { Text(if (arabicOnlyMode) "عربي فقط" else "Arabic + Meaning") },
                        leadingIcon = { Icon(Icons.Filled.Translate, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        modifier = Modifier.padding(end = 8.dp).testTag("hadith_language_toggle")
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    border = BorderStroke(1.2.dp, GoldBorder)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.verticalGradient(listOf(EmeraldDeep, EmeraldPrimary)))
                    ) {
                        IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            ArabicTextDisplay(
                                text = "مِنْ كُنُوزِ السُّنَّةِ النَّبَوِيَّةِ",
                                fontSize = 30.sp,
                                color = GoldSoft
                            )
                            ArabicTextDisplay(
                                text = "أَحَادِيثُ صَحِيحَةٌ مُيَسَّرَةٌ لِلْأَطْفَالِ",
                                fontSize = 20.sp,
                                color = PureWhite
                            )
                        }
                    }
                }
            }

            items(allHadiths, key = { it.id }) { hadith ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, GoldBorder)
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
                            Surface(
                                color = EmeraldSoft,
                                shape = RoundedCornerShape(50)
                            ) {
                                ArabicTextDisplay(
                                    text = hadith.category,
                                    fontSize = 16.sp,
                                    color = EmeraldDeep,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                                )
                            }
                            Surface(
                                color = GoldSoft,
                                shape = CircleShape,
                                border = BorderStroke(1.dp, GoldBright),
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${hadith.number}",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = EmeraldDeep
                                    )
                                }
                            }
                        }

                        Surface(
                            color = GoldSoft,
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, GoldBorder),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                ArabicTextDisplay(
                                    text = hadith.textArabic,
                                    fontSize = 26.sp,
                                    color = EmeraldDeep,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                ArabicTextDisplay(
                                    text = hadith.sourceArabic,
                                    fontSize = 16.sp,
                                    color = EmeraldPrimary,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        if (!arabicOnlyMode) {
                            Text(
                                text = hadith.transliteration,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = hadith.meaning,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        AudioPronounceButton(
                            isSpeaking = audioState.isSpeaking && audioState.activeTag == hadith.id,
                            label = "اسْتَمِعْ لِلْحَدِيثِ",
                            onClick = {
                                onSpeakArabic(hadith.textArabic, hadith.transliteration, hadith.id)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun DuaAzkarScreen(
    customItems: List<CustomAcademyItemEntity> = emptyList(),
    audioState: AudioPlaybackState,
    onBack: () -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit
) {
    var arabicOnlyMode by remember { mutableStateOf(true) }
    val zikrCounts = remember { mutableStateMapOf<String, Int>() }
    val allDuas = remember(customItems) {
        val customDuas = customItems.filter { it.itemType == "DUA" }.map { item ->
            DuaAzkarItem(
                id = "custom_d_${item.id}",
                category = item.categoryArabic.ifBlank { "دُعَاءٌ مُبَارَكٌ" },
                titleArabic = item.titleArabic.ifBlank { item.titleEnglish },
                titleEnglish = item.titleEnglish.ifBlank { item.titleArabic },
                textArabic = item.bodyArabic.ifBlank { item.titleArabic },
                transliteration = item.transliteration,
                meaning = item.meaning,
                targetCount = item.extraMeta.toIntOrNull()?.coerceAtLeast(1) ?: 3
            )
        }
        customDuas + HadithAndDuaData.duaAzkarList
    }

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "الأَدْعِيَةُ وَالأَذْكَارُ • Dua & Azkar",
                subtitle = "Daily Supplications in Authentic Arabic",
                onBack = onBack,
                actions = {
                    FilterChip(
                        selected = !arabicOnlyMode,
                        onClick = { arabicOnlyMode = !arabicOnlyMode },
                        label = { Text(if (arabicOnlyMode) "عربي فقط" else "Arabic + Meaning") },
                        leadingIcon = { Icon(Icons.Filled.Translate, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        modifier = Modifier.padding(end = 8.dp).testTag("dua_language_toggle")
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    border = BorderStroke(1.2.dp, GoldBorder)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.verticalGradient(listOf(EmeraldDeep, EmeraldPrimary)))
                    ) {
                        IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            ArabicTextDisplay(
                                text = "أَلَا بِذِكْرِ اللَّهِ تَطْمَئِنُّ الْقُلُوبُ",
                                fontSize = 28.sp,
                                color = GoldSoft
                            )
                            ArabicTextDisplay(
                                text = "حِصْنُ الْمُسْلِمِ الصَّغِيرِ — الأَدْعِيَةُ الْيَوْمِيَّةُ",
                                fontSize = 18.sp,
                                color = PureWhite
                            )
                        }
                    }
                }
            }

            items(allDuas, key = { it.id }) { dua ->
                val count = zikrCounts[dua.id] ?: 0
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, GoldBorder)
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
                            Surface(color = EmeraldSoft, shape = RoundedCornerShape(50)) {
                                ArabicTextDisplay(
                                    text = dua.category,
                                    fontSize = 15.sp,
                                    color = EmeraldDeep,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                                )
                            }
                            ArabicTextDisplay(
                                text = dua.titleArabic,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Surface(
                            color = GoldSoft,
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, GoldBorder),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ArabicTextDisplay(
                                text = dua.textArabic,
                                fontSize = 28.sp,
                                color = EmeraldDeep,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }

                        if (!arabicOnlyMode) {
                            Text(dua.titleEnglish, style = MaterialTheme.typography.titleSmall)
                            Text(
                                text = dua.transliteration,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = dua.meaning,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AudioPronounceButton(
                                isSpeaking = audioState.isSpeaking && audioState.activeTag == dua.id,
                                label = "اسْتَمِعْ",
                                onClick = {
                                    onSpeakArabic(dua.textArabic, dua.transliteration, dua.id)
                                },
                                modifier = Modifier.weight(1f)
                            )
                            Button(
                                onClick = { zikrCounts[dua.id] = count + 1 },
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GoldBright,
                                    contentColor = EmeraldDeep
                                ),
                                modifier = Modifier.weight(1f).testTag("tasbeeh_btn_${dua.id}")
                            ) {
                                Text(
                                    text = "تَسْبِيح  $count / ${dua.targetCount}",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            if (count > 0) {
                                IconButton(onClick = { zikrCounts[dua.id] = 0 }) {
                                    Icon(Icons.Filled.Refresh, contentDescription = "Reset counter")
                                }
                            }
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun IslamicCoursesScreen(
    uiState: AcademyUiState,
    onBack: () -> Unit,
    onOpenAlphabet: () -> Unit,
    onOpenNooraniQaida: () -> Unit,
    onOpenTajweed: () -> Unit,
    onOpenQuranLearning: () -> Unit,
    onOpenHadith: () -> Unit,
    onOpenDuaAzkar: () -> Unit,
    onOpenVideoLessons: () -> Unit,
    onOpenCurriculum: () -> Unit
) {
    val customCourses = remember(uiState.customItems) {
        uiState.customItems.filter { it.itemType == "COURSE" || it.itemType == "NOTICE" }
    }
    var selectedCustomCourse by remember { mutableStateOf<CustomAcademyItemEntity?>(null) }

    val courses = listOf(
        CourseTrack("الْحُرُوفُ الْهِجَائِيَّةُ", "Arabic Alphabet (29 Letters)", "Interactive letter studio, vowel drills & audio", Icons.Filled.Abc, EmeraldPrimary, onOpenAlphabet),
        CourseTrack("الْقَاعِدَةُ النُّورَانِيَّةُ", "Complete Noorani Qaida", "Levels 2–9: Harakat, Tanween, Sukoon, Madd & Word Reading", Icons.AutoMirrored.Filled.MenuBook, Color(0xFF147A5E), onOpenNooraniQaida),
        CourseTrack("أَحْكَامُ التَّجْوِيدِ وَالْمَخَارِجُ", "Tajweed & Makharijul Huruf", "Levels 10–11: Ghunnah, Qalqalah, Noon Sakinah & Articulation", Icons.Filled.RecordVoiceOver, Color(0xFFB38218), onOpenTajweed),
        CourseTrack("تِلَاوَةُ الْقُرْآنِ الْكَرِيمِ", "Quran Learning & Short Surahs", "Levels 12–13: Authentic Mushaf script, audio & practice", Icons.Filled.AutoStories, EmeraldDeep, onOpenQuranLearning),
        CourseTrack("الأَحَادِيثُ النَّبَوِيَّةُ", "Prophetic Hadith Course", "Authentic Arabic Hadith for children & good character", Icons.Filled.MenuBook, Color(0xFF0E6B53), onOpenHadith),
        CourseTrack("الأَدْعِيَةُ وَالأَذْكَارُ", "Daily Dua & Azkar Course", "Authentic Arabic duas with Tasbeeh practice counter", Icons.Filled.VolunteerActivism, Color(0xFF8A6510), onOpenDuaAzkar),
        CourseTrack("الدُّرُوسُ الْمَرْئِيَّةُ", "Video Lessons Library", "13 guided Arabic classes by ${uiState.appSettings.founderName}", Icons.Filled.PlayCircle, EmeraldPrimary, onOpenVideoLessons)
    )

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Islamic Courses",
                subtitle = "الدَّوْرَاتُ الإِسْلَامِيَّةُ • ${uiState.progressPercent}% Mastery",
                onBack = onBack
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
                    border = BorderStroke(1.dp, GoldBorder)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Filled.School, contentDescription = null, tint = EmeraldDeep)
                            Text("Structured Islamic Academy Tracks", style = MaterialTheme.typography.titleMedium, color = EmeraldDeep)
                        }
                        Text(
                            text = "Every Arabic course keeps authentic Arabic script with full Harakat front and center—never replaced by English.",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmeraldDeep
                        )
                    }
                }
            }

            items(courses) { course ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = course.onClick),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Surface(
                            color = course.accent.copy(alpha = 0.14f),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.size(48.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(course.icon, contentDescription = course.titleEnglish, tint = course.accent)
                            }
                        }
                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            ArabicTextDisplay(
                                text = course.titleArabic,
                                fontSize = 20.sp,
                                color = course.accent,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(course.titleEnglish, style = MaterialTheme.typography.titleSmall)
                            Text(course.subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Open ${course.titleEnglish}",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            if (customCourses.isNotEmpty()) {
                item {
                    Text(
                        text = "Admin Added Courses & Announcements (${customCourses.size})",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                items(customCourses, key = { "cc_${it.id}" }) { custom ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedCustomCourse = custom },
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = GoldSoft),
                        border = BorderStroke(1.dp, GoldBright)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(Icons.Filled.School, contentDescription = null, tint = EmeraldDeep)
                            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                if (custom.titleArabic.isNotBlank()) {
                                    ArabicTextDisplay(
                                        text = custom.titleArabic,
                                        fontSize = 20.sp,
                                        color = EmeraldDeep,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                if (custom.titleEnglish.isNotBlank()) {
                                    Text(custom.titleEnglish, style = MaterialTheme.typography.titleSmall, color = EmeraldDeep)
                                }
                                if (custom.meaning.isNotBlank()) {
                                    Text(custom.meaning, style = MaterialTheme.typography.bodySmall, color = EmeraldDeep.copy(alpha = 0.85f))
                                }
                            }
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "View details", tint = EmeraldDeep)
                        }
                    }
                }
            }

            item {
                Button(
                    onClick = onOpenCurriculum,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GoldBright, contentColor = EmeraldDeep)
                ) {
                    Text("Explore Full 13-Level Curriculum Roadmap", fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }

    selectedCustomCourse?.let { item ->
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { selectedCustomCourse = null },
            title = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    if (item.titleArabic.isNotBlank()) {
                        ArabicTextDisplay(
                            text = item.titleArabic,
                            fontSize = 22.sp,
                            color = EmeraldDeep,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (item.titleEnglish.isNotBlank()) {
                        Text(item.titleEnglish, style = MaterialTheme.typography.titleMedium)
                    }
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (item.bodyArabic.isNotBlank()) {
                        ArabicTextDisplay(
                            text = item.bodyArabic,
                            fontSize = 22.sp,
                            color = EmeraldPrimary,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (item.transliteration.isNotBlank()) {
                        Text(item.transliteration, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                    }
                    if (item.meaning.isNotBlank()) {
                        Text(item.meaning, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            },
            confirmButton = {
                androidx.compose.material3.TextButton(onClick = { selectedCustomCourse = null }) {
                    Text("Close")
                }
            }
        )
    }
}

private data class CourseTrack(
    val titleArabic: String,
    val titleEnglish: String,
    val subtitle: String,
    val icon: ImageVector,
    val accent: Color,
    val onClick: () -> Unit
)
