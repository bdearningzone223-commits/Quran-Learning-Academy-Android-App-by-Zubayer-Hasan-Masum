package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.local.CustomAcademyItemEntity
import com.example.data.local.TeacherUploadEntity
import com.example.ui.AcademyUiState
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldSoft

@Composable
fun AdminManageContentTab(
    customItems: List<CustomAcademyItemEntity>,
    teacherUploads: List<TeacherUploadEntity>,
    onEditCustomItem: (CustomAcademyItemEntity) -> Unit,
    onDeleteCustomItem: (Int) -> Unit,
    onEditTeacherUpload: (TeacherUploadEntity) -> Unit,
    onDeleteTeacherUpload: (Int) -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Custom Hadith, Dua, Quran Verses, Courses & Notices (${customItems.size})",
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (customItems.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldSoft)
                ) {
                    Text(
                        text = "No custom items added yet. Open 'Add Anything' tab to publish new Hadith, Dua, Quran Verses, Courses, or Notices.",
                        style = MaterialTheme.typography.bodySmall,
                        color = EmeraldDeep,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }
        } else {
            items(customItems, key = { "custom_${it.id}" }) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, GoldBorder)
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(color = GoldSoft, shape = RoundedCornerShape(50)) {
                                Text(
                                    text = "${item.itemType} • ${item.categoryArabic}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDeep,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                            Row {
                                if (item.bodyArabic.isNotBlank()) {
                                    IconButton(onClick = { onSpeakArabic(item.bodyArabic, item.transliteration, "admin_custom_${item.id}") }) {
                                        Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Listen", tint = MaterialTheme.colorScheme.primary)
                                    }
                                }
                                IconButton(onClick = { onEditCustomItem(item) }) {
                                    Icon(Icons.Filled.Edit, contentDescription = "Edit item", tint = MaterialTheme.colorScheme.primary)
                                }
                                IconButton(onClick = { onDeleteCustomItem(item.id) }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Delete item", tint = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                        if (item.titleArabic.isNotBlank()) {
                            ArabicTextDisplay(
                                text = item.titleArabic,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        if (item.titleEnglish.isNotBlank()) {
                            Text(item.titleEnglish, style = MaterialTheme.typography.titleSmall)
                        }
                        if (item.bodyArabic.isNotBlank()) {
                            ArabicTextDisplay(
                                text = item.bodyArabic,
                                fontSize = 22.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        if (item.meaning.isNotBlank()) {
                            Text(item.meaning, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Curriculum Level Lessons, Audio, Video & Quizzes (${teacherUploads.size})",
                style = MaterialTheme.typography.titleMedium
            )
        }

        items(teacherUploads, key = { "upload_${it.id}" }) { upload ->
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
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "${upload.contentType} • Level ${upload.levelNumber} • ${upload.durationLabel}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(upload.title, style = MaterialTheme.typography.titleSmall)
                        if (upload.arabicContent.isNotBlank()) {
                            ArabicTextDisplay(
                                text = upload.arabicContent,
                                fontSize = 20.sp,
                                color = EmeraldDeep,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Text(upload.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Row {
                        IconButton(onClick = { onEditTeacherUpload(upload) }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit upload", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { onDeleteTeacherUpload(upload.id) }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete upload", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }
    }
}

@Composable
fun AdminOverviewInboxTab(uiState: AcademyUiState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
                border = BorderStroke(1.dp, GoldBorder)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Academy Live Summary", style = MaterialTheme.typography.titleMedium, color = EmeraldDeep)
                    Text(
                        text = "Users: ${uiState.allAccounts.size} • Custom Items: ${uiState.customItems.size} • Teacher Materials: ${uiState.teacherUploads.size}",
                        style = MaterialTheme.typography.bodySmall,
                        color = EmeraldDeep
                    )
                    Text(
                        text = "Primary Student: ${uiState.primaryStudentName} (${uiState.progressPercent}% • ${uiState.passedLevels.size}/13 Exams Passed)",
                        style = MaterialTheme.typography.labelMedium,
                        color = EmeraldDeep
                    )
                }
            }
        }

        item {
            Text("Registered Accounts (${uiState.allAccounts.size})", style = MaterialTheme.typography.titleMedium)
        }
        items(uiState.allAccounts, key = { "acc_${it.id}" }) { account ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Column(modifier = Modifier.weight(1f)) {
                        Text(account.fullName, style = MaterialTheme.typography.titleSmall)
                        Text("${account.email} • ${account.studentAgeLabel}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Surface(color = GoldSoft, shape = RoundedCornerShape(50)) {
                        Text(
                            text = account.role,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDeep,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(4.dp))
            Text("Contact Us Inbox (${uiState.contactMessages.size})", style = MaterialTheme.typography.titleMedium)
        }

        if (uiState.contactMessages.isEmpty()) {
            item {
                Text(
                    text = "No contact inquiries received yet.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            items(uiState.contactMessages, key = { "msg_${it.id}" }) { msg ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Filled.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                            Text(msg.subject, style = MaterialTheme.typography.titleSmall)
                        }
                        Text("${msg.senderName} (${msg.email})", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        Text(msg.message, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }
    }
}

@Composable
fun EditCustomItemDialog(
    item: CustomAcademyItemEntity,
    onDismiss: () -> Unit,
    onSave: (CustomAcademyItemEntity) -> Unit
) {
    var category by remember { mutableStateOf(item.categoryArabic) }
    var titleArabic by remember { mutableStateOf(item.titleArabic) }
    var titleEnglish by remember { mutableStateOf(item.titleEnglish) }
    var bodyArabic by remember { mutableStateOf(item.bodyArabic) }
    var transliteration by remember { mutableStateOf(item.transliteration) }
    var meaning by remember { mutableStateOf(item.meaning) }
    var extraMeta by remember { mutableStateOf(item.extraMeta) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(22.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.2.dp, GoldBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Edit ${item.itemType}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = titleArabic, onValueChange = { titleArabic = it }, label = { Text("Arabic Title") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = titleEnglish, onValueChange = { titleEnglish = it }, label = { Text("English Title / Reference") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = bodyArabic, onValueChange = { bodyArabic = it }, label = { Text("Arabic Body Text") }, minLines = 2, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = transliteration, onValueChange = { transliteration = it }, label = { Text("Transliteration") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = meaning, onValueChange = { meaning = it }, label = { Text("Meaning / Details") }, minLines = 2, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = extraMeta, onValueChange = { extraMeta = it }, label = { Text("Extra Meta") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    Button(
                        onClick = {
                            onSave(
                                item.copy(
                                    categoryArabic = category,
                                    titleArabic = titleArabic,
                                    titleEnglish = titleEnglish,
                                    bodyArabic = bodyArabic,
                                    transliteration = transliteration,
                                    meaning = meaning,
                                    extraMeta = extraMeta
                                )
                            )
                        }
                    ) { Text("Save Changes") }
                }
            }
        }
    }
}

@Composable
fun EditTeacherUploadDialog(
    upload: TeacherUploadEntity,
    onDismiss: () -> Unit,
    onSave: (TeacherUploadEntity) -> Unit
) {
    var levelText by remember { mutableStateOf(upload.levelNumber.toString()) }
    var title by remember { mutableStateOf(upload.title) }
    var arabicContent by remember { mutableStateOf(upload.arabicContent) }
    var description by remember { mutableStateOf(upload.description) }
    var duration by remember { mutableStateOf(upload.durationLabel) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(22.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.2.dp, GoldBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Edit ${upload.contentType}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                OutlinedTextField(value = levelText, onValueChange = { levelText = it.filter { ch -> ch.isDigit() } }, label = { Text("Level (1–13)") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = arabicContent, onValueChange = { arabicContent = it }, label = { Text("Arabic Text") }, minLines = 2, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, minLines = 2, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = duration, onValueChange = { duration = it }, label = { Text("Duration") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    Button(
                        onClick = {
                            onSave(
                                upload.copy(
                                    levelNumber = (levelText.toIntOrNull() ?: upload.levelNumber).coerceIn(1, 13),
                                    title = title,
                                    arabicContent = arabicContent,
                                    description = description,
                                    durationLabel = duration.ifBlank { "04:00" }
                                )
                            )
                        }
                    ) { Text("Save Changes") }
                }
            }
        }
    }
}
