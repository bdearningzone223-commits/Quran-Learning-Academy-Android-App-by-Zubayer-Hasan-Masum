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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioPlaybackState
import com.example.data.content.IslamicKnowledgeData
import com.example.ui.components.AcademySubTopBar
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.components.AudioPronounceButton
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft

@Composable
fun TajweedLearningScreen(
    audioState: AudioPlaybackState,
    onBack: () -> Unit,
    onOpenLevel: (Int) -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Tajweed Learning",
                subtitle = "Levels 10–11 • Essential Rules & Makharijul Huruf",
                onBack = onBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("Tajweed Rules") })
                Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("Makharijul Huruf") })
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                if (selectedTab == 0) {
                    items(IslamicKnowledgeData.tajweedRules, key = { it.id }) { rule ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.2.dp, rule.accentColor.copy(alpha = 0.45f))
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
                                        Surface(
                                            color = rule.accentColor.copy(alpha = 0.14f),
                                            shape = RoundedCornerShape(50)
                                        ) {
                                            Text(
                                                text = rule.category,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = rule.accentColor,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                            )
                                        }
                                        Spacer(Modifier.height(4.dp))
                                        Text(rule.title, style = MaterialTheme.typography.titleMedium)
                                    }
                                    ArabicTextDisplay(
                                        text = rule.arabicTitle,
                                        fontSize = 26.sp,
                                        color = rule.accentColor
                                    )
                                }

                                Text(
                                    text = rule.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Surface(
                                    color = GoldSoft,
                                    shape = RoundedCornerShape(14.dp),
                                    border = BorderStroke(1.dp, GoldBorder),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier.padding(14.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(
                                            text = "Letters: ${rule.letters}",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = EmeraldDeep,
                                            fontWeight = FontWeight.Bold
                                        )
                                        ArabicTextDisplay(
                                            text = rule.exampleArabic,
                                            fontSize = 28.sp,
                                            color = rule.accentColor
                                        )
                                        Text(
                                            text = rule.exampleTransliteration,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = EmeraldDeep
                                        )
                                        Text(
                                            text = rule.exampleRuleNote,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = EmeraldDeep.copy(alpha = 0.8f)
                                        )
                                    }
                                }

                                AudioPronounceButton(
                                    isSpeaking = audioState.isSpeaking && audioState.activeTag == "tajweed_${rule.id}",
                                    label = "Hear Tajweed Example",
                                    onClick = {
                                        onSpeakArabic(rule.exampleArabic, rule.exampleTransliteration, "tajweed_${rule.id}")
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    item {
                        Button(
                            onClick = { onOpenLevel(10) },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldBright, contentColor = EmeraldDeep)
                        ) {
                            Text("Study Level 10 & Take Tajweed Exam", fontWeight = FontWeight.Bold)
                        }
                    }
                } else {
                    items(IslamicKnowledgeData.makharijAreas, key = { it.id }) { area ->
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
                                    Text(
                                        text = area.name,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.weight(1f)
                                    )
                                    ArabicTextDisplay(
                                        text = area.arabicName,
                                        fontSize = 26.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Surface(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    ArabicTextDisplay(
                                        text = area.letters,
                                        fontSize = 22.sp,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        modifier = Modifier.padding(12.dp)
                                    )
                                }
                                Text(
                                    text = area.explanation,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ArabicTextDisplay(
                                        text = area.practiceWord,
                                        fontSize = 24.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    AudioPronounceButton(
                                        isSpeaking = audioState.isSpeaking && audioState.activeTag == "makhraj_${area.id}",
                                        label = "Practice Sound",
                                        onClick = { onSpeakArabic(area.practiceWord, area.name, "makhraj_${area.id}") }
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Button(
                            onClick = { onOpenLevel(11) },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldBright, contentColor = EmeraldDeep)
                        ) {
                            Text("Study Level 11 & Take Makharij Exam", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                item { Spacer(Modifier.height(16.dp)) }
            }
        }
    }
}
