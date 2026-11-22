package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.data.local.AppSettingsEntity
import com.example.data.local.CustomAcademyItemEntity
import com.example.data.local.TeacherUploadEntity
import com.example.ui.AcademyUiState
import com.example.ui.components.AcademySubTopBar
import com.example.ui.components.IslamicGeometricOverlay
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft
import com.example.ui.theme.PureWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPanelScreen(
    uiState: AcademyUiState,
    onBack: () -> Unit,
    onSaveSettings: (AppSettingsEntity) -> Unit,
    onResetSettings: () -> Unit,
    onSaveCustomItem: (CustomAcademyItemEntity) -> Unit,
    onDeleteCustomItem: (Int) -> Unit,
    onPublishTeacherContent: (String, Int, String, String, String, String, List<String>, Int) -> Unit,
    onUpdateTeacherUpload: (TeacherUploadEntity) -> Unit,
    onDeleteTeacherUpload: (Int) -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit
) {
    var isUnlocked by rememberSaveable { mutableStateOf(false) }
    var pinInput by remember { mutableStateOf("") }
    var pinError by remember { mutableStateOf<String?>(null) }
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    var editingCustomItem by remember { mutableStateOf<CustomAcademyItemEntity?>(null) }
    var editingTeacherUpload by remember { mutableStateOf<TeacherUploadEntity?>(null) }

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Admin Control Panel",
                subtitle = "Live App Customization & Content Manager",
                onBack = onBack,
                actions = {
                    if (isUnlocked) {
                        TextButton(onClick = { isUnlocked = false; pinInput = "" }) {
                            Icon(Icons.Filled.Lock, contentDescription = "Lock Admin Panel", modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Lock")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        if (!isUnlocked) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(1.5.dp, GoldBorder),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            color = EmeraldSoft,
                            shape = CircleShape,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.AdminPanelSettings,
                                    contentDescription = "Admin Panel",
                                    tint = EmeraldDeep,
                                    modifier = Modifier.size(34.dp)
                                )
                            }
                        }
                        Text(
                            text = "Founder & Admin Panel",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Update branding, announcements, APK link, Hadith, Dua, Quran verses, courses, and curriculum lessons live inside the app.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Surface(
                            color = GoldSoft,
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, GoldBright),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Default Admin PIN: 2026 (You can change it inside Site Settings)",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldDeep,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        OutlinedTextField(
                            value = pinInput,
                            onValueChange = { pinInput = it; pinError = null },
                            label = { Text("Enter Admin PIN") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("admin_pin_input")
                        )
                        if (pinError != null) {
                            Text(
                                text = pinError!!,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        Button(
                            onClick = {
                                val expected = uiState.appSettings.adminPin.ifBlank { "2026" }
                                if (pinInput.trim() == expected) {
                                    isUnlocked = true
                                    pinError = null
                                } else {
                                    pinError = "Incorrect PIN. Try $expected."
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("admin_unlock_button"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Filled.LockOpen, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Unlock Admin Panel", fontWeight = FontWeight.Bold)
                        }
                        TextButton(
                            onClick = {
                                isUnlocked = true
                                pinError = null
                            },
                            modifier = Modifier.testTag("admin_quick_enter_button")
                        ) {
                            Text("Continue as Founder (${uiState.appSettings.founderName})")
                        }
                    }
                }
            }
        } else {
            val tabTitles = listOf("Site & Branding", "Add Anything", "Manage & Edit", "Users & Inbox")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                PrimaryScrollableTabRow(
                    selectedTabIndex = selectedTab,
                    edgePadding = 12.dp
                ) {
                    tabTitles.forEachIndexed { idx, title ->
                        Tab(
                            selected = selectedTab == idx,
                            onClick = { selectedTab = idx },
                            text = { Text(title, fontWeight = if (selectedTab == idx) FontWeight.Bold else FontWeight.Normal) },
                            modifier = Modifier.testTag("admin_tab_$idx")
                        )
                    }
                }

                when (selectedTab) {
                    0 -> AdminSettingsTab(
                        settings = uiState.appSettings,
                        onSaveSettings = onSaveSettings,
                        onResetSettings = onResetSettings
                    )
                    1 -> AdminAddAnythingTab(
                        onSaveCustomItem = onSaveCustomItem,
                        onPublishTeacherContent = onPublishTeacherContent
                    )
                    2 -> AdminManageContentTab(
                        customItems = uiState.customItems,
                        teacherUploads = uiState.teacherUploads,
                        onEditCustomItem = { editingCustomItem = it },
                        onDeleteCustomItem = onDeleteCustomItem,
                        onEditTeacherUpload = { editingTeacherUpload = it },
                        onDeleteTeacherUpload = onDeleteTeacherUpload,
                        onSpeakArabic = onSpeakArabic
                    )
                    3 -> AdminOverviewInboxTab(uiState = uiState)
                }
            }
        }
    }

    editingCustomItem?.let { item ->
        EditCustomItemDialog(
            item = item,
            onDismiss = { editingCustomItem = null },
            onSave = { updated ->
                onSaveCustomItem(updated)
                editingCustomItem = null
            }
        )
    }

    editingTeacherUpload?.let { upload ->
        EditTeacherUploadDialog(
            upload = upload,
            onDismiss = { editingTeacherUpload = null },
            onSave = { updated ->
                onUpdateTeacherUpload(updated)
                editingTeacherUpload = null
            }
        )
    }
}

@Composable
private fun AdminSettingsTab(
    settings: AppSettingsEntity,
    onSaveSettings: (AppSettingsEntity) -> Unit,
    onResetSettings: () -> Unit
) {
    var brandTitle by remember { mutableStateOf(settings.brandTitle) }
    var founderName by remember { mutableStateOf(settings.founderName) }
    var officialTagline by remember { mutableStateOf(settings.officialTagline) }
    var welcomeArabic by remember { mutableStateOf(settings.welcomeArabic) }
    var welcomeTitle by remember { mutableStateOf(settings.welcomeTitle) }
    var welcomeSubtitle by remember { mutableStateOf(settings.welcomeSubtitle) }
    var announcementText by remember { mutableStateOf(settings.announcementText) }
    var showAnnouncement by remember { mutableStateOf(settings.showAnnouncement) }
    var apkDownloadUrl by remember { mutableStateOf(settings.apkDownloadUrl) }
    var appVersionLabel by remember { mutableStateOf(settings.appVersionLabel) }
    var footerText by remember { mutableStateOf(settings.footerText) }
    var adminPin by remember { mutableStateOf(settings.adminPin) }

    LaunchedEffect(settings) {
        brandTitle = settings.brandTitle
        founderName = settings.founderName
        officialTagline = settings.officialTagline
        welcomeArabic = settings.welcomeArabic
        welcomeTitle = settings.welcomeTitle
        welcomeSubtitle = settings.welcomeSubtitle
        announcementText = settings.announcementText
        showAnnouncement = settings.showAnnouncement
        apkDownloadUrl = settings.apkDownloadUrl
        appVersionLabel = settings.appVersionLabel
        footerText = settings.footerText
        adminPin = settings.adminPin
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.dp, GoldBorder)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.horizontalGradient(listOf(EmeraldDeep, EmeraldPrimary)))
                ) {
                    IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Live App Branding & Controls", style = MaterialTheme.typography.titleMedium, color = GoldSoft)
                        Text(
                            "Every change here updates Home Screen, Welcome Card, Footer, and Download APK immediately.",
                            style = MaterialTheme.typography.bodySmall,
                            color = PureWhite.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Identity & Home Banner", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                    OutlinedTextField(value = brandTitle, onValueChange = { brandTitle = it }, label = { Text("Academy Brand Name") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = founderName, onValueChange = { founderName = it }, label = { Text("Founder & Creator Name") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = officialTagline, onValueChange = { officialTagline = it }, label = { Text("Official Tagline") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = welcomeArabic, onValueChange = { welcomeArabic = it }, label = { Text("Arabic Greeting") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = welcomeTitle, onValueChange = { welcomeTitle = it }, label = { Text("Welcome Heading") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = welcomeSubtitle, onValueChange = { welcomeSubtitle = it }, label = { Text("Welcome Subtitle") }, modifier = Modifier.fillMaxWidth())
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Home Announcement Banner", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                            Text("Show a live notice at the top of the Home Screen", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Switch(checked = showAnnouncement, onCheckedChange = { showAnnouncement = it })
                    }
                    OutlinedTextField(
                        value = announcementText,
                        onValueChange = { announcementText = it },
                        label = { Text("Live Announcement Message") },
                        placeholder = { Text("e.g., Ramadan Special Classes Open! New Surahs added.") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("APK Download Link, Footer & Security", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
                    OutlinedTextField(
                        value = apkDownloadUrl,
                        onValueChange = { apkDownloadUrl = it },
                        label = { Text("Custom APK Download URL (Optional)") },
                        placeholder = { Text("https://example.com/QuranLearningAcademy.apk") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(value = appVersionLabel, onValueChange = { appVersionLabel = it }, label = { Text("APK Version Label") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = footerText, onValueChange = { footerText = it }, label = { Text("Official Footer Text") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(
                        value = adminPin,
                        onValueChange = { adminPin = it },
                        label = { Text("Admin PIN") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {
            Button(
                onClick = {
                    onSaveSettings(
                        AppSettingsEntity(
                            id = 1,
                            brandTitle = brandTitle,
                            founderName = founderName,
                            officialTagline = officialTagline,
                            welcomeArabic = welcomeArabic,
                            welcomeTitle = welcomeTitle,
                            welcomeSubtitle = welcomeSubtitle,
                            announcementText = announcementText,
                            showAnnouncement = showAnnouncement,
                            apkDownloadUrl = apkDownloadUrl.trim(),
                            appVersionLabel = appVersionLabel,
                            footerText = footerText,
                            adminPin = adminPin
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("admin_save_settings_button"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Filled.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Save & Apply All Updates Live", fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(
                onClick = onResetSettings,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Filled.RestartAlt, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Restore Default Academy Settings")
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AdminAddAnythingTab(
    onSaveCustomItem: (CustomAcademyItemEntity) -> Unit,
    onPublishTeacherContent: (String, Int, String, String, String, String, List<String>, Int) -> Unit
) {
    val contentTypes = listOf(
        "HADITH" to "Hadith",
        "DUA" to "Dua & Azkar",
        "QURAN_AYAH" to "Quran Verse",
        "COURSE" to "Course",
        "NOTICE" to "Notice",
        "LESSON" to "Level Lesson",
        "AUDIO" to "Level Audio",
        "VIDEO" to "Level Video",
        "QUIZ" to "Level Quiz"
    )
    var selectedType by remember { mutableStateOf("HADITH") }
    var categoryOrLevel by remember { mutableStateOf("") }
    var titleArabic by remember { mutableStateOf("") }
    var titleEnglish by remember { mutableStateOf("") }
    var bodyArabic by remember { mutableStateOf("") }
    var transliteration by remember { mutableStateOf("") }
    var meaning by remember { mutableStateOf("") }
    var extraMeta by remember { mutableStateOf("") }
    var quizOptA by remember { mutableStateOf("") }
    var quizOptB by remember { mutableStateOf("") }

    val isCurriculumType = selectedType in setOf("LESSON", "AUDIO", "VIDEO", "QUIZ")

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Choose What to Add Live", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                contentTypes.forEach { (key, label) ->
                    FilterChip(
                        selected = selectedType == key,
                        onClick = { selectedType = key },
                        label = { Text(label) },
                        modifier = Modifier.testTag("admin_type_$key")
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, GoldBorder)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (isCurriculumType) {
                        OutlinedTextField(
                            value = categoryOrLevel,
                            onValueChange = { categoryOrLevel = it.filter { ch -> ch.isDigit() } },
                            label = { Text("Curriculum Level (1–13)") },
                            placeholder = { Text("1") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = titleEnglish,
                            onValueChange = { titleEnglish = it },
                            label = { Text(if (selectedType == "QUIZ") "Quiz Question" else "Title") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = bodyArabic,
                            onValueChange = { bodyArabic = it },
                            label = { Text("Arabic Content / Recitation Text") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = meaning,
                            onValueChange = { meaning = it },
                            label = { Text("Explanation / Description") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = extraMeta,
                            onValueChange = { extraMeta = it },
                            label = { Text("Duration Label (e.g. 05:00)") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        if (selectedType == "QUIZ") {
                            OutlinedTextField(
                                value = quizOptA,
                                onValueChange = { quizOptA = it },
                                label = { Text("Option A (Correct Answer)") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = quizOptB,
                                onValueChange = { quizOptB = it },
                                label = { Text("Option B (Distractor)") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    } else {
                        OutlinedTextField(
                            value = categoryOrLevel,
                            onValueChange = { categoryOrLevel = it },
                            label = { Text("Category (Arabic or Label)") },
                            placeholder = { Text(if (selectedType == "HADITH") "الأخلاق والآداب" else "دعاء عام") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = titleArabic,
                            onValueChange = { titleArabic = it },
                            label = { Text("Title in Arabic (العنوان بالعربية)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = titleEnglish,
                            onValueChange = { titleEnglish = it },
                            label = {
                                Text(
                                    when (selectedType) {
                                        "HADITH" -> "Source / Reference (e.g. رَوَاهُ الْبُخَارِيُّ)"
                                        "QURAN_AYAH" -> "Surah Name & Ayah No."
                                        else -> "English / Display Title"
                                    }
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = bodyArabic,
                            onValueChange = { bodyArabic = it },
                            label = { Text("Main Arabic Text (النص العربي)") },
                            minLines = 2,
                            modifier = Modifier.fillMaxWidth().testTag("admin_add_body_arabic")
                        )
                        OutlinedTextField(
                            value = transliteration,
                            onValueChange = { transliteration = it },
                            label = { Text("Transliteration / Subtitle (Optional)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = meaning,
                            onValueChange = { meaning = it },
                            label = { Text("Meaning / Details / Course Description") },
                            minLines = 2,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = extraMeta,
                            onValueChange = { extraMeta = it },
                            label = {
                                Text(
                                    when (selectedType) {
                                        "DUA" -> "Tasbeeh Target Count (e.g. 3 or 33)"
                                        "COURSE" -> "Course Duration / Level Tag"
                                        else -> "Extra Reference / Note"
                                    }
                                )
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Button(
                        onClick = {
                            if (isCurriculumType) {
                                val lvl = (categoryOrLevel.toIntOrNull() ?: 1).coerceIn(1, 13)
                                onPublishTeacherContent(
                                    selectedType,
                                    lvl,
                                    titleEnglish,
                                    bodyArabic,
                                    meaning,
                                    extraMeta.ifBlank { "05:00" },
                                    if (selectedType == "QUIZ") listOf(quizOptA.ifBlank { "Correct Answer" }, quizOptB.ifBlank { "Alternative" }) else emptyList(),
                                    0
                                )
                            } else {
                                onSaveCustomItem(
                                    CustomAcademyItemEntity(
                                        itemType = selectedType,
                                        categoryArabic = categoryOrLevel.ifBlank {
                                            when (selectedType) {
                                                "HADITH" -> "حَدِيثٌ شَرِيفٌ"
                                                "DUA" -> "دُعَاءٌ مُبَارَكٌ"
                                                "QURAN_AYAH" -> "الْقُرْآنُ الْكَرِيمُ"
                                                "COURSE" -> "دَوْرَةٌ جَدِيدَةٌ"
                                                else -> "إِعْلَانٌ"
                                            }
                                        },
                                        titleArabic = titleArabic.ifBlank { titleEnglish },
                                        titleEnglish = titleEnglish.ifBlank { titleArabic },
                                        bodyArabic = bodyArabic,
                                        transliteration = transliteration,
                                        meaning = meaning,
                                        extraMeta = extraMeta.ifBlank { if (selectedType == "DUA") "3" else "1" }
                                    )
                                )
                            }
                            categoryOrLevel = ""
                            titleArabic = ""
                            titleEnglish = ""
                            bodyArabic = ""
                            transliteration = ""
                            meaning = ""
                            extraMeta = ""
                            quizOptA = ""
                            quizOptB = ""
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("admin_publish_item_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GoldBright, contentColor = EmeraldDeep)
                    ) {
                        Text("Publish Live to App", fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}
