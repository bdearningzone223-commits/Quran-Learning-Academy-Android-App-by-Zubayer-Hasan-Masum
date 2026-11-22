package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.PureWhite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Locale

@Composable
fun DownloadApkDialog(
    apkDownloadUrl: String = "",
    appVersionLabel: String = "v1.0",
    onDismiss: () -> Unit,
    onExportFeedback: (String) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var exportStatus by remember { mutableStateOf<String?>(null) }
    var isExporting by remember { mutableStateOf(false) }
    val cleanVersion = appVersionLabel.ifBlank { "v1.0" }

    val apkSizeMb = remember {
        val file = File(context.applicationInfo.sourceDir)
        if (file.exists()) String.format(Locale.US, "%.1f MB", file.length() / (1024.0 * 1024.0))
        else "Android APK"
    }

    // Zero-permission Storage Access Framework exporter copies the real APK to user-selected Downloads
    val createApkLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/vnd.android.package-archive")
    ) { targetUri ->
        if (targetUri != null) {
            isExporting = true
            scope.launch {
                val result = withContext(Dispatchers.IO) {
                    runCatching {
                        val sourceApk = File(context.applicationInfo.sourceDir)
                        context.contentResolver.openOutputStream(targetUri)?.use { out ->
                            sourceApk.inputStream().use { input ->
                                input.copyTo(out)
                            }
                        } ?: error("Unable to open destination file.")
                        sourceApk.length()
                    }
                }
                isExporting = false
                result.onSuccess { bytes ->
                    val mb = String.format(Locale.US, "%.1f MB", bytes / (1024.0 * 1024.0))
                    val msg = "APK saved successfully ($mb)! Open it from your Downloads folder to install."
                    exportStatus = msg
                    onExportFeedback(msg)
                }.onFailure { err ->
                    val msg = "Could not export APK: ${err.localizedMessage ?: "Please try again."}"
                    exportStatus = msg
                    onExportFeedback(msg)
                }
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.2.dp, GoldBorder),
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_academy_logo),
                        contentDescription = "Quran Learning Academy Icon",
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Download Android APK",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Quran Learning Academy • $cleanVersion ($apkSizeMb)",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Security,
                            contentDescription = null,
                            tint = EmeraldDeep,
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = "Safe, child-friendly, zero ads, and offline-ready. Founded & Created by Zubayer Hasan Masum.",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmeraldDeep
                        )
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Quick Installation Steps",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    InstallStepRow("1", "Tap 'Download & Save APK' to save QuranLearningAcademy-v1.0.apk.")
                    InstallStepRow("2", "Open the saved file in your device's Downloads / Files app.")
                    InstallStepRow("3", "Tap Install and begin learning from Arabic Alphabet to Holy Quran!")
                }

                if (exportStatus != null) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = exportStatus!!,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }

                if (apkDownloadUrl.isNotBlank()) {
                    Button(
                        onClick = {
                            val normalizedUrl = if (apkDownloadUrl.startsWith("http://") || apkDownloadUrl.startsWith("https://")) {
                                apkDownloadUrl
                            } else {
                                "https://$apkDownloadUrl"
                            }
                            runCatching {
                                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(normalizedUrl)))
                            }.onFailure { err ->
                                val msg = "Could not open APK URL: ${err.localizedMessage ?: "Invalid link"}"
                                exportStatus = msg
                                onExportFeedback(msg)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("open_official_apk_url_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldBright,
                            contentColor = EmeraldDeep
                        )
                    ) {
                        Icon(Icons.Filled.OpenInBrowser, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Open Official APK Link ($cleanVersion)",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Button(
                    onClick = { createApkLauncher.launch("QuranLearningAcademy-${cleanVersion}.apk") },
                    enabled = !isExporting,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("save_apk_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = PureWhite
                    )
                ) {
                    Icon(Icons.Filled.Download, contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = if (isExporting) "Saving APK..." else "Download & Save APK",
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, "Quran Learning Academy — Android App")
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Learn Arabic Alphabet, Noorani Qaida, Tajweed, and the Holy Quran step by step with QURAN LEARNING ACADEMY (Founded & Created by Zubayer Hasan Masum).\n\"From Arabic Alphabet to the Holy Quran – Learn Step by Step.\""
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share Quran Learning Academy"))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("share_app_button"),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, GoldBright)
                ) {
                    Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Share Academy App with Family")
                }

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
private fun InstallStepRow(step: String, text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            color = GoldBright.copy(alpha = 0.22f),
            shape = CircleShape,
            modifier = Modifier.size(22.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = step,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldDeep
                )
            }
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
    }
}
