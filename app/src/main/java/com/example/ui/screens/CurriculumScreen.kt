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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.CurriculumCatalog
import com.example.data.model.UserRole
import com.example.ui.AcademyUiState
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.components.StarRatingRow
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft

@Composable
fun CurriculumScreen(
    uiState: AcademyUiState,
    onOpenLevel: (Int) -> Unit,
    onStartQuiz: (Int) -> Unit
) {
    val stages = listOf(
        "All 13 Levels",
        "Stage 1 • Foundation",
        "Stage 2 • Vowels & Signs",
        "Stage 3 • Noorani Qaida",
        "Stage 4 • Tajweed Mastery",
        "Stage 5 • Holy Quran"
    )
    var selectedStage by remember { mutableStateOf(stages.first()) }
    val filteredLevels = remember(selectedStage) {
        if (selectedStage == stages.first()) CurriculumCatalog.allLevels
        else CurriculumCatalog.allLevels.filter { it.stageName == selectedStage }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Complete 13-Level Curriculum",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "Pass each level quiz (70%+) to earn stars, badges, and unlock the next stage.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Progress Card
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldSoft),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Curriculum Mastery: ${uiState.progressPercent}%",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDeep
                        )
                        Text(
                            text = "${uiState.passedLevels.size}/13 Levels Passed",
                            style = MaterialTheme.typography.labelMedium,
                            color = EmeraldDeep
                        )
                    }
                    LinearProgressIndicator(
                        progress = { uiState.progressFraction.coerceAtLeast(0.03f) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(50)),
                        color = EmeraldDeep,
                        trackColor = EmeraldDeep.copy(alpha = 0.16f)
                    )
                }
            }
        }

        // Stage Filter Chips
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(stages) { stage ->
                    FilterChip(
                        selected = selectedStage == stage,
                        onClick = { selectedStage = stage },
                        label = { Text(stage) }
                    )
                }
            }
        }

        // All 13 Level Cards
        items(filteredLevels, key = { it.number }) { level ->
            val isTeacherOrParent = uiState.activeRole != UserRole.STUDENT
            val isUnlocked = level.number <= uiState.highestUnlockedLevel || isTeacherOrParent
            val isPassed = level.number in uiState.passedLevels
            val bestStars = uiState.quizAttempts
                .filter { it.levelNumber == level.number }
                .maxOfOrNull { it.starsEarned } ?: 0
            val completedInLevel = level.lessons.count { it.id in uiState.completedLessonIds }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = isUnlocked) { onOpenLevel(level.number) }
                    .testTag("curriculum_level_${level.number}"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface
                    else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
                ),
                border = BorderStroke(
                    width = if (isPassed) 1.5.dp else 1.dp,
                    color = when {
                        isPassed -> GoldBright
                        isUnlocked -> MaterialTheme.colorScheme.outline
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    }
                )
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = when {
                                    isPassed -> GoldBright
                                    isUnlocked -> MaterialTheme.colorScheme.primary
                                    else -> MaterialTheme.colorScheme.outline
                                },
                                modifier = Modifier.size(38.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    if (!isUnlocked) {
                                        Icon(
                                            imageVector = Icons.Filled.Lock,
                                            contentDescription = "Locked",
                                            tint = MaterialTheme.colorScheme.surface,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    } else {
                                        Text(
                                            text = "${level.number}",
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isPassed) EmeraldDeep else MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                }
                            }
                            Column {
                                Text(
                                    text = level.stageName,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Text(
                                    text = "Level ${level.number}: ${level.title}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                        if (isPassed) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = "Passed",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    ArabicTextDisplay(
                        text = level.arabicTitle,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = level.summary,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "$completedInLevel/${level.lessons.size} Lessons • ~${level.estimatedMinutes}m",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (bestStars > 0) {
                                StarRatingRow(stars = bestStars)
                            }
                        }

                        if (isUnlocked) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Surface(
                                    onClick = { onOpenLevel(level.number) },
                                    shape = RoundedCornerShape(50),
                                    color = MaterialTheme.colorScheme.primary
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(Icons.Filled.PlayArrow, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(16.dp))
                                        Spacer(Modifier.width(4.dp))
                                        Text("Study", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary)
                                    }
                                }
                                Surface(
                                    onClick = { onStartQuiz(level.number) },
                                    shape = RoundedCornerShape(50),
                                    color = GoldSoft,
                                    border = BorderStroke(1.dp, GoldBorder)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(Icons.Filled.Quiz, contentDescription = null, tint = EmeraldDeep, modifier = Modifier.size(15.dp))
                                        Spacer(Modifier.width(4.dp))
                                        Text("Exam", style = MaterialTheme.typography.labelMedium, color = EmeraldDeep, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "Pass Level ${level.number - 1} Exam to Unlock",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }
    }
}
