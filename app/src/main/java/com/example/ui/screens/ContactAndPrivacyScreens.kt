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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.AcademyUiState
import com.example.ui.components.AcademyOfficialFooter
import com.example.ui.components.AcademySubTopBar
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder

@Composable
fun ContactUsScreen(
    uiState: AcademyUiState,
    onBack: () -> Unit,
    onSubmitContact: (String, String, String, String, () -> Unit) -> Unit,
    onOpenFounder: () -> Unit,
    onOpenPrivacy: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Contact Us",
                subtitle = "Quran Learning Academy Support",
                onBack = onBack
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                            Icon(Icons.Filled.Email, contentDescription = null, tint = EmeraldDeep)
                            Text("Get in Touch with Our Academy", style = MaterialTheme.typography.titleMedium, color = EmeraldDeep)
                        }
                        Text(
                            text = "Questions about our 13-level Quran curriculum, Tajweed teacher review, or parent supervision? Send a message directly below.",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmeraldDeep
                        )
                        Text(
                            text = "Founder & Director: Zubayer Hasan Masum",
                            style = MaterialTheme.typography.labelLarge,
                            color = EmeraldDeep,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Send a Message", style = MaterialTheme.typography.titleMedium)
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Your Name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth().testTag("contact_name_input")
                        )
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email Address") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth().testTag("contact_email_input")
                        )
                        OutlinedTextField(
                            value = subject,
                            onValueChange = { subject = it },
                            label = { Text("Subject (e.g. Student Enrollment, Teacher Review)") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            label = { Text("Message") },
                            minLines = 3,
                            modifier = Modifier.fillMaxWidth().testTag("contact_message_input")
                        )
                        Button(
                            onClick = {
                                onSubmitContact(name, email, subject, message) {
                                    name = ""
                                    email = ""
                                    subject = ""
                                    message = ""
                                }
                            },
                            modifier = Modifier.fillMaxWidth().height(50.dp).testTag("contact_submit_button"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Send Message", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            if (uiState.contactMessages.isNotEmpty()) {
                item {
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
                            Text("Recent Inquiries Sent (${uiState.contactMessages.size})", style = MaterialTheme.typography.titleSmall)
                            uiState.contactMessages.take(3).forEach { msg ->
                                Text("• ${msg.subject} (${msg.senderName})", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                                Text("  ${msg.message}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }

            item {
                AcademyOfficialFooter(
                    onAboutFounderClick = onOpenFounder,
                    onPrivacyClick = onOpenPrivacy
                )
            }
        }
    }
}

@Composable
fun PrivacyPolicyScreen(
    onBack: () -> Unit,
    onOpenFounder: () -> Unit,
    onOpenContact: () -> Unit
) {
    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Privacy Policy",
                subtitle = "Child Safety & Data Protection",
                onBack = onBack
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
                PolicyCard(
                    title = "1. Protecting Children's Personal Information",
                    body = "Quran Learning Academy is engineered for children's safety. We display zero third-party ads, perform zero behavioral tracking, and never sell or share children's names, progress, or email addresses with external parties.",
                    icon = Icons.Filled.Shield
                )
            }
            item {
                PolicyCard(
                    title = "2. Local-First Encrypted Storage",
                    body = "Student profiles, quiz results, certificates, and parent preferences are stored privately on the user's device using Android Room SQLite storage with SHA-256 salted password protection.",
                    icon = Icons.Filled.Lock
                )
            }
            item {
                PolicyCard(
                    title = "3. Parent-Supervised Learning Features",
                    body = "Parents can switch to the Parent Dashboard at any time to monitor completed lessons, inspect examination scores, verify homework, and enable Safe Parent-Supervised Mode.",
                    icon = Icons.Filled.VerifiedUser
                )
            }
            item {
                PolicyCard(
                    title = "4. Scholar-Reviewed Quranic Accuracy",
                    body = "All Quranic verses, Noorani Qaida vowel marks (Harakat), Makharijul Huruf, and Tajweed guidelines are structured for review by qualified Quran teachers prior to publication to uphold the highest fidelity to the Holy Quran.",
                    icon = Icons.Filled.VerifiedUser
                )
            }
            item {
                AcademyOfficialFooter(
                    onAboutFounderClick = onOpenFounder,
                    onContactClick = onOpenContact
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun PolicyCard(
    title: String,
    body: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
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
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            }
            Text(body, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
