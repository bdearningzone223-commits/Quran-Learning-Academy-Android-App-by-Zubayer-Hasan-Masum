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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioPlaybackState
import com.example.data.content.CurriculumCatalog
import com.example.ui.components.AcademySubTopBar
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldSoft

@Composable
fun NooraniQaidaScreen(
    audioState: AudioPlaybackState,
    onBack: () -> Unit,
    onOpenLevel: (Int) -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit
) {
    val qaidaLevels = CurriculumCatalog.allLevels.filter { it.number in 2..9 }

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Noorani Qaida",
                subtitle = "Levels 2–9 • Harakat, Tanween, Sukoon, Madd & Word Reading",
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
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
                    border = BorderStroke(1.dp, GoldBorder)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        ArabicTextDisplay(
                            text = "القَاعِدَةُ النُّورَانِيَّةُ المُيَسَّرَة",
                            fontSize = 28.sp,
                            color = EmeraldDeep
                        )
                        Text(
                            text = "Tap any Arabic board below to hear authentic pronunciation, or open the full level for guided video explanations and exams.",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmeraldDeep
                        )
                    }
                }
            }

            items(qaidaLevels, key = { it.number }) { level ->
                val mainLesson = level.lessons.first()
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
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Qaida Module • Level ${level.number}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(level.title, style = MaterialTheme.typography.titleMedium)
                            }
                            ArabicTextDisplay(
                                text = level.arabicTitle,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        // Tap-to-Hear Qaida Practice Grid
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            mainLesson.practiceItems.take(3).forEach { item ->
                                val active = audioState.isSpeaking && audioState.activeTag == "qaida_${level.number}_${item.arabic}"
                                Surface(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable {
                                            onSpeakArabic(item.speakText, item.transliteration, "qaida_${level.number}_${item.arabic}")
                                        },
                                    shape = RoundedCornerShape(14.dp),
                                    color = if (active) GoldSoft else EmeraldSoft,
                                    border = BorderStroke(1.dp, if (active) GoldBorder else EmeraldDeep.copy(alpha = 0.16f))
                                ) {
                                    Column(
                                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 6.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        ArabicTextDisplay(
                                            text = item.arabic,
                                            fontSize = 24.sp,
                                            color = EmeraldDeep
                                        )
                                        Text(
                                            text = item.transliteration,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = EmeraldDeep,
                                            maxLines = 1
                                        )
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                            contentDescription = "Listen",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Text(
                            text = mainLesson.explanation,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Button(
                            onClick = { onOpenLevel(level.number) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Open Level ${level.number} Lessons & Exam")
                            Spacer(Modifier.width(6.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
