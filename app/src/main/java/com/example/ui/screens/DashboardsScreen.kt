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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.data.content.CurriculumCatalog
import com.example.data.content.IslamicKnowledgeData
import com.example.data.local.CertificateEntity
import com.example.data.local.UserAccountEntity
import com.example.data.model.UserRole
import com.example.ui.AcademyUiState
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.components.IslamicGeometricOverlay
import com.example.ui.components.StarRatingRow
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft
import com.example.ui.theme.PureWhite

@Composable
fun DashboardsScreen(
    uiState: AcademyUiState,
    onSwitchRole: (UserRole) -> Unit,
    onRegisterAccount: (String, String, String, UserRole, String, (Result<UserAccountEntity>) -> Unit) -> Unit,
    onLoginAccount: (String, String, (Result<UserAccountEntity>) -> Unit) -> Unit,
    onSetParentSupervision: (Boolean) -> Unit,
    onContinueLearning: (Int) -> Unit,
    onToggleHomework: (Int, Boolean) -> Unit,
    onPublishTeacherContent: (String, Int, String, String, String, String, List<String>, Int) -> Unit,
    onDeleteTeacherUpload: (Int) -> Unit,
    onAssignHomework: (Int, String, String, String) -> Unit,
    onOpenAdminPanel: () -> Unit = {}
) {
    var showAccountDialog by remember { mutableStateOf(false) }
    var viewingCertificate by remember { mutableStateOf<CertificateEntity?>(null) }

    val roles = UserRole.entries
    val activeRole = uiState.activeRole

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header + Account Login/Register Button
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${activeRole.label} Dashboard",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = uiState.activeAccount?.fullName ?: "Quran Learning Academy Account",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onOpenAdminPanel,
                        modifier = Modifier.testTag("dashboard_admin_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AdminPanelSettings,
                            contentDescription = "Open Admin Panel",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    OutlinedButton(
                        onClick = { showAccountDialog = true },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.testTag("account_auth_button")
                    ) {
                        Icon(Icons.Filled.PersonAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Account")
                    }
                }
            }
        }

        // Role Switcher Segmented Control (Student / Teacher / Parent)
        item {
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                roles.forEachIndexed { index, role ->
                    SegmentedButton(
                        selected = activeRole == role,
                        onClick = { onSwitchRole(role) },
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = roles.size),
                        modifier = Modifier.testTag("role_tab_${role.name.lowercase()}")
                    ) {
                        Text(role.label)
                    }
                }
            }
        }

        when (activeRole) {
            UserRole.STUDENT -> {
                // Student Profile & Continue Learning Card
                item {
                    val currentLevel = CurriculumCatalog.getLevel(uiState.highestUnlockedLevel)
                    val remainingLessons = (CurriculumCatalog.totalLessonsCount - uiState.completedLessonIds.size).coerceAtLeast(0)

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        border = BorderStroke(1.2.dp, GoldBorder)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Brush.linearGradient(listOf(EmeraldDeep, EmeraldPrimary)))
                        ) {
                            IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
                            Column(
                                modifier = Modifier.padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = GoldBright,
                                        modifier = Modifier.size(48.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(Icons.Filled.School, contentDescription = null, tint = EmeraldDeep)
                                        }
                                    }
                                    Column {
                                        Text(uiState.primaryStudentName, style = MaterialTheme.typography.titleLarge, color = PureWhite)
                                        Text(
                                            "Current Level ${currentLevel.number}: ${currentLevel.title}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = GoldSoft
                                        )
                                    }
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    StatMiniBox("Progress", "${uiState.progressPercent}%", Modifier.weight(1f))
                                    StatMiniBox("Completed", "${uiState.completedLessonIds.size}", Modifier.weight(1f))
                                    StatMiniBox("Remaining", "$remainingLessons", Modifier.weight(1f))
                                    StatMiniBox("Stars", "${uiState.totalStars}", Modifier.weight(1f))
                                }

                                LinearProgressIndicator(
                                    progress = { uiState.progressFraction.coerceAtLeast(0.04f) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                        .clip(RoundedCornerShape(50)),
                                    color = GoldBright,
                                    trackColor = PureWhite.copy(alpha = 0.25f)
                                )

                                Button(
                                    onClick = { onContinueLearning(currentLevel.number) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp)
                                        .testTag("student_continue_learning_button"),
                                    shape = RoundedCornerShape(14.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = GoldBright,
                                        contentColor = EmeraldDeep
                                    )
                                ) {
                                    Text("Continue Learning — Level ${currentLevel.number}", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                // Certificates Section
                item {
                    CertificatesCardSection(
                        certificates = uiState.certificates,
                        onOpenCertificate = { viewingCertificate = it }
                    )
                }

                // Assigned Homework Checklist
                item {
                    Text("Assigned Homework (${uiState.homeworkItems.size})", style = MaterialTheme.typography.titleMedium)
                }
                items(uiState.homeworkItems, key = { it.id }) { hw ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = hw.completed,
                                onCheckedChange = { onToggleHomework(hw.id, it) }
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(hw.title, style = MaterialTheme.typography.titleSmall)
                                Text(hw.instructions, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(
                                    "Level ${hw.levelNumber} • ${hw.dueDateLabel} • by ${hw.teacherName}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                // Quiz Results
                item {
                    QuizResultsSection(uiState = uiState)
                }
            }

            UserRole.TEACHER -> {
                item {
                    TeacherDashboardContent(
                        uiState = uiState,
                        onPublishTeacherContent = onPublishTeacherContent,
                        onDeleteTeacherUpload = onDeleteTeacherUpload,
                        onAssignHomework = onAssignHomework
                    )
                }
            }

            UserRole.PARENT -> {
                item {
                    ParentDashboardContent(
                        uiState = uiState,
                        onSetParentSupervision = onSetParentSupervision,
                        onOpenCertificate = { viewingCertificate = it }
                    )
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }
    }

    if (showAccountDialog) {
        AccountAuthDialog(
            onDismiss = { showAccountDialog = false },
            onRegister = { name, email, pass, role, note, cb ->
                onRegisterAccount(name, email, pass, role, note) { res ->
                    if (res.isSuccess) showAccountDialog = false
                    cb(res)
                }
            },
            onLogin = { email, pass, cb ->
                onLoginAccount(email, pass) { res ->
                    if (res.isSuccess) showAccountDialog = false
                    cb(res)
                }
            }
        )
    }

    if (viewingCertificate != null) {
        CertificatePreviewDialog(
            certificate = viewingCertificate!!,
            onDismiss = { viewingCertificate = null }
        )
    }
}

@Composable
private fun StatMiniBox(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = PureWhite.copy(alpha = 0.14f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, style = MaterialTheme.typography.titleMedium, color = GoldSoft, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.labelSmall, color = PureWhite.copy(alpha = 0.85f))
        }
    }
}

@Composable
private fun CertificatesCardSection(
    certificates: List<CertificateEntity>,
    onOpenCertificate: (CertificateEntity) -> Unit
) {
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
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Filled.WorkspacePremium, contentDescription = null, tint = GoldBright)
                Text("Academy Certificates (${certificates.size}/${IslamicKnowledgeData.certificateStages.size})", style = MaterialTheme.typography.titleMedium)
            }
            if (certificates.isEmpty()) {
                Text(
                    text = "Pass Level 1 & Level 2 exams to earn your first official certificate signed by Founder Zubayer Hasan Masum!",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                certificates.forEach { cert ->
                    Surface(
                        onClick = { onOpenCertificate(cert) },
                        color = GoldSoft,
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, GoldBright),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(cert.stageTitle, style = MaterialTheme.typography.titleSmall, color = EmeraldDeep)
                                Text("Issued ${cert.issuedDateLabel} • Tap to View", style = MaterialTheme.typography.labelSmall, color = EmeraldDeep.copy(alpha = 0.8f))
                            }
                            Image(
                                painter = painterResource(R.drawable.img_certificate_seal),
                                contentDescription = "Certificate Seal",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizResultsSection(uiState: AcademyUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Filled.EmojiEvents, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Text("Examination Results & Badges", style = MaterialTheme.typography.titleMedium)
            }
            if (uiState.quizAttempts.isEmpty()) {
                Text(
                    text = "No examinations attempted yet. Open any curriculum level and tap 'Exam' to earn stars and badges!",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                uiState.quizAttempts.take(8).forEach { attempt ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Level ${attempt.levelNumber} • ${attempt.badgeTitle}", style = MaterialTheme.typography.titleSmall)
                            Text(
                                "Score: ${attempt.score}/${attempt.totalQuestions} (${if (attempt.passed) "Passed" else "Try Again"})",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (attempt.passed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                            )
                        }
                        StarRatingRow(stars = attempt.starsEarned)
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun TeacherDashboardContent(
    uiState: AcademyUiState,
    onPublishTeacherContent: (String, Int, String, String, String, String, List<String>, Int) -> Unit,
    onDeleteTeacherUpload: (Int) -> Unit,
    onAssignHomework: (Int, String, String, String) -> Unit
) {
    var contentType by remember { mutableStateOf("LESSON") }
    var levelText by remember { mutableStateOf("1") }
    var title by remember { mutableStateOf("") }
    var arabicContent by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var quizOptA by remember { mutableStateOf("") }
    var quizOptB by remember { mutableStateOf("") }

    var hwTitle by remember { mutableStateOf("") }
    var hwInstructions by remember { mutableStateOf("") }
    var hwDue by remember { mutableStateOf("Due Friday") }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Student Monitoring Overview
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
            border = BorderStroke(1.dp, GoldBorder)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Student Progress Monitor", style = MaterialTheme.typography.titleMedium, color = EmeraldDeep)
                Text(
                    text = "Active Student: ${uiState.primaryStudentName} • Level ${uiState.highestUnlockedLevel}/13 • ${uiState.progressPercent}% Complete",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmeraldDeep
                )
                Text(
                    text = "Lessons Completed: ${uiState.completedLessonIds.size} • Exams Passed: ${uiState.passedLevels.size} • Letters Practiced: ${uiState.letterPracticeMap.size}/29",
                    style = MaterialTheme.typography.labelMedium,
                    color = EmeraldDeep
                )
            }
        }

        // Upload Lesson / Audio / Video / Quiz Form
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Filled.AddCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Text("Publish Learning Content", style = MaterialTheme.typography.titleMedium)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("LESSON", "AUDIO", "VIDEO", "QUIZ").forEach { type ->
                        FilterChip(
                            selected = contentType == type,
                            onClick = { contentType = type },
                            label = { Text(type, style = MaterialTheme.typography.labelSmall) }
                        )
                    }
                }
                OutlinedTextField(
                    value = levelText,
                    onValueChange = { levelText = it.filter { ch -> ch.isDigit() } },
                    label = { Text("Curriculum Level (1–13)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(if (contentType == "QUIZ") "Quiz Question Prompt" else "Lesson / Audio / Video Title") },
                    modifier = Modifier.fillMaxWidth().testTag("teacher_content_title_input")
                )
                OutlinedTextField(
                    value = arabicContent,
                    onValueChange = { arabicContent = it },
                    label = { Text("Arabic Text / Recitation Ayah (Optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Explanation / Notes (Teacher-Reviewed)") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (contentType == "QUIZ") {
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
                Button(
                    onClick = {
                        val lvl = (levelText.toIntOrNull() ?: 1).coerceIn(1, 13)
                        onPublishTeacherContent(
                            contentType,
                            lvl,
                            title,
                            arabicContent,
                            description,
                            "05:00",
                            if (contentType == "QUIZ") listOf(quizOptA.ifBlank { "Correct Answer" }, quizOptB.ifBlank { "Alternative" }) else emptyList(),
                            0
                        )
                        title = ""
                        arabicContent = ""
                        description = ""
                        quizOptA = ""
                        quizOptB = ""
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp).testTag("teacher_publish_button"),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Publish $contentType to Academy", fontWeight = FontWeight.Bold)
                }
            }
        }

        // Assign Homework Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.AutoMirrored.Filled.Assignment, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                    Text("Assign Student Homework", style = MaterialTheme.typography.titleMedium)
                }
                OutlinedTextField(
                    value = hwTitle,
                    onValueChange = { hwTitle = it },
                    label = { Text("Homework Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = hwInstructions,
                    onValueChange = { hwInstructions = it },
                    label = { Text("Instructions for Student & Parent") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = hwDue,
                    onValueChange = { hwDue = it },
                    label = { Text("Due Date") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        val lvl = (levelText.toIntOrNull() ?: 1).coerceIn(1, 13)
                        onAssignHomework(lvl, hwTitle, hwInstructions, hwDue)
                        hwTitle = ""
                        hwInstructions = ""
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GoldBright, contentColor = EmeraldDeep)
                ) {
                    Text("Assign Homework", fontWeight = FontWeight.Bold)
                }
            }
        }

        // Published Teacher Uploads List
        if (uiState.teacherUploads.isNotEmpty()) {
            Text("Published Teacher Materials (${uiState.teacherUploads.size})", style = MaterialTheme.typography.titleMedium)
            uiState.teacherUploads.forEach { upload ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "${upload.contentType} • Level ${upload.levelNumber}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(upload.title, style = MaterialTheme.typography.titleSmall)
                            Text(upload.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        IconButton(onClick = { onDeleteTeacherUpload(upload.id) }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete upload", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ParentDashboardContent(
    uiState: AcademyUiState,
    onSetParentSupervision: (Boolean) -> Unit,
    onOpenCertificate: (CertificateEntity) -> Unit
) {
    val supervised = uiState.activeAccount?.parentSupervised ?: true
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Parent Child Summary Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
            border = BorderStroke(1.2.dp, GoldBorder)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(Icons.Filled.FamilyRestroom, contentDescription = null, tint = EmeraldDeep, modifier = Modifier.size(30.dp))
                    Column {
                        Text("Child Progress Report", style = MaterialTheme.typography.titleLarge, color = EmeraldDeep)
                        Text("Monitoring: ${uiState.primaryStudentName}", style = MaterialTheme.typography.bodySmall, color = EmeraldDeep)
                    }
                }
                LinearProgressIndicator(
                    progress = { uiState.progressFraction.coerceAtLeast(0.04f) },
                    modifier = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(50)),
                    color = EmeraldDeep,
                    trackColor = EmeraldDeep.copy(alpha = 0.16f)
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Overall: ${uiState.progressPercent}%", style = MaterialTheme.typography.labelLarge, color = EmeraldDeep)
                    Text("Level ${uiState.highestUnlockedLevel} of 13", style = MaterialTheme.typography.labelLarge, color = EmeraldDeep)
                }
                Text(
                    "Completed Lessons: ${uiState.completedLessonIds.size}/${CurriculumCatalog.totalLessonsCount} • Arabic Letters Practiced: ${uiState.letterPracticeMap.size}/29 • Stars: ${uiState.totalStars}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmeraldDeep
                )
            }
        }

        // Safe Parent Supervision Controls
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(Icons.Filled.Shield, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Column(modifier = Modifier.weight(1f)) {
                    Text("Safe Parent-Supervised Mode", style = MaterialTheme.typography.titleSmall)
                    Text(
                        "Keeps child profile local-only, ad-free, and focused strictly on Quran lessons.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = supervised,
                    onCheckedChange = onSetParentSupervision
                )
            }
        }

        // Homework Monitoring
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Homework Tracker", style = MaterialTheme.typography.titleMedium)
                uiState.homeworkItems.forEach { hw ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(hw.title, style = MaterialTheme.typography.titleSmall)
                            Text("Level ${hw.levelNumber} • ${hw.dueDateLabel}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Surface(
                            color = if (hw.completed) EmeraldSoft else GoldSoft,
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = if (hw.completed) "Done" else "Pending",
                                style = MaterialTheme.typography.labelSmall,
                                color = EmeraldDeep,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        QuizResultsSection(uiState = uiState)
        CertificatesCardSection(certificates = uiState.certificates, onOpenCertificate = onOpenCertificate)
    }
}

@Composable
private fun AccountAuthDialog(
    onDismiss: () -> Unit,
    onRegister: (String, String, String, UserRole, String, (Result<UserAccountEntity>) -> Unit) -> Unit,
    onLogin: (String, String, (Result<UserAccountEntity>) -> Unit) -> Unit
) {
    var isRegister by remember { mutableStateOf(true) }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf(UserRole.STUDENT) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.2.dp, GoldBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = if (isRegister) "Create Academy Account" else "Secure Sign In",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Stored securely on-device with SHA-256 protection for child safety.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (isRegister) {
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Full Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        UserRole.entries.forEach { role ->
                            FilterChip(
                                selected = selectedRole == role,
                                onClick = { selectedRole = role },
                                label = { Text(role.label) }
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMsg != null) {
                    Text(errorMsg!!, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                }

                Button(
                    onClick = {
                        errorMsg = null
                        if (isRegister) {
                            onRegister(fullName, email, password, selectedRole, selectedRole.label) { res ->
                                res.onFailure { errorMsg = it.message }
                            }
                        } else {
                            onLogin(email, password) { res ->
                                res.onFailure { errorMsg = it.message }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(if (isRegister) "Register Account" else "Sign In", fontWeight = FontWeight.Bold)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { isRegister = !isRegister; errorMsg = null }) {
                        Text(if (isRegister) "Already have an account?" else "Create new account")
                    }
                    TextButton(onClick = onDismiss) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@Composable
private fun CertificatePreviewDialog(
    certificate: CertificateEntity,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = GoldSoft,
            border = BorderStroke(3.dp, GoldBright)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.img_certificate_seal),
                    contentDescription = "Official Academy Seal",
                    modifier = Modifier.size(72.dp)
                )
                Text(
                    text = "QURAN LEARNING ACADEMY",
                    style = MaterialTheme.typography.titleSmall,
                    color = EmeraldDeep,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
                ArabicTextDisplay(
                    text = certificate.arabicTitle,
                    fontSize = 26.sp,
                    color = EmeraldDeep
                )
                Text(
                    text = "CERTIFICATE OF ACHIEVEMENT",
                    style = MaterialTheme.typography.labelMedium,
                    color = EmeraldPrimary
                )
                Text(
                    text = "Proudly Presented To",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmeraldDeep.copy(alpha = 0.8f)
                )
                Text(
                    text = certificate.studentName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = EmeraldDeep,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "For successfully completing ${certificate.stageTitle}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = EmeraldDeep,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider(color = GoldBright)
                Text(
                    text = "Issued on ${certificate.issuedDateLabel}",
                    style = MaterialTheme.typography.labelSmall,
                    color = EmeraldDeep
                )
                Text(
                    text = "Signed by Founder & Creator: ${certificate.founderName}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldDeep,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldDeep, contentColor = PureWhite)
                ) {
                    Text("Close Certificate")
                }
            }
        }
    }
}
