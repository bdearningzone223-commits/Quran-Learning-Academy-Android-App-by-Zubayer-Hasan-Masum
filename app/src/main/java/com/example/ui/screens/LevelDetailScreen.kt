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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioPlaybackState
import com.example.data.content.CurriculumCatalog
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
fun LevelDetailScreen(
    levelNumber: Int,
    uiState: AcademyUiState,
    audioState: AudioPlaybackState,
    onBack: () -> Unit,
    onMarkLessonComplete: (Int, String) -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit,
    onStartQuiz: (Int) -> Unit
) {
    val level = CurriculumCatalog.getLevel(levelNumber)
    val teacherLevelContent = uiState.teacherUploads.filter { it.levelNumber == levelNumber }
    var selectedTab by remember { mutableIntStateOf(0) }
    var videoSlideIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Level ${level.number}: ${level.title}",
                subtitle = level.stageName,
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
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Lessons (${level.lessons.size})") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Video Class") }
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Practice") }
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Level Banner Header
                item {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, GoldBorder)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Brush.horizontalGradient(listOf(EmeraldDeep, EmeraldPrimary)))
                        ) {
                            IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
                            Column(
                                modifier = Modifier.padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                ArabicTextDisplay(
                                    text = level.arabicTitle,
                                    fontSize = 30.sp,
                                    color = GoldSoft,
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = level.summary,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = PureWhite.copy(alpha = 0.92f)
                                )
                                Text(
                                    text = "Badge Reward: ${level.badgeTitle} • Reviewed by Qualified Quran Teachers",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldBright
                                )
                            }
                        }
                    }
                }

                when (selectedTab) {
                    0 -> {
                        items(level.lessons, key = { it.id }) { lesson ->
                            val isDone = lesson.id in uiState.completedLessonIds
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(1.dp, if (isDone) GoldBright else MaterialTheme.colorScheme.outline)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(lesson.title, style = MaterialTheme.typography.titleMedium)
                                            Text(
                                                lesson.subtitle,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                        if (isDone) {
                                            Icon(
                                                imageVector = Icons.Filled.CheckCircle,
                                                contentDescription = "Lesson Completed",
                                                tint = MaterialTheme.colorScheme.primary
                                            )
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
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            ArabicTextDisplay(
                                                text = lesson.arabicShowcase,
                                                fontSize = 34.sp,
                                                color = EmeraldDeep
                                            )
                                            Text(
                                                text = lesson.transliteration,
                                                style = MaterialTheme.typography.labelLarge,
                                                color = EmeraldDeep.copy(alpha = 0.85f),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }

                                    Text(
                                        text = lesson.explanation,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        AudioPronounceButton(
                                            isSpeaking = audioState.isSpeaking && audioState.activeTag == lesson.id,
                                            label = "Listen Audio",
                                            onClick = {
                                                onSpeakArabic(lesson.audioSpeakText, lesson.transliteration, lesson.id)
                                            },
                                            modifier = Modifier.weight(1f)
                                        )
                                        OutlinedButton(
                                            onClick = { onMarkLessonComplete(level.number, lesson.id) },
                                            shape = RoundedCornerShape(50),
                                            modifier = Modifier
                                                .weight(1f)
                                                .testTag("complete_lesson_${lesson.id}")
                                        ) {
                                            Icon(Icons.Filled.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                                            Spacer(Modifier.width(6.dp))
                                            Text(if (isDone) "Completed" else "Mark Done")
                                        }
                                    }
                                }
                            }
                        }

                        if (teacherLevelContent.isNotEmpty()) {
                            item {
                                Text(
                                    text = "Teacher Added Resources",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            items(teacherLevelContent) { upload ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = EmeraldSoft)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(14.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(Icons.Filled.School, contentDescription = null, tint = EmeraldDeep, modifier = Modifier.size(16.dp))
                                            Text(
                                                text = "${upload.contentType} • by ${upload.teacherName}",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = EmeraldDeep
                                            )
                                        }
                                        Text(upload.title, style = MaterialTheme.typography.titleSmall, color = EmeraldDeep)
                                        if (upload.arabicContent.isNotBlank()) {
                                            ArabicTextDisplay(text = upload.arabicContent, fontSize = 26.sp, color = EmeraldDeep)
                                        }
                                        Text(upload.description, style = MaterialTheme.typography.bodySmall, color = EmeraldDeep)
                                        if (upload.arabicContent.isNotBlank()) {
                                            AudioPronounceButton(
                                                isSpeaking = audioState.isSpeaking && audioState.activeTag == "upload_${upload.id}",
                                                label = "Play Recitation",
                                                onClick = { onSpeakArabic(upload.arabicContent, upload.title, "upload_${upload.id}") }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    1 -> {
                        // Interactive Guided Video Explanation Player
                        item {
                            val slides = level.videoLesson.slides
                            val activeSlide = slides[videoSlideIndex.coerceIn(0, slides.lastIndex)]
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(22.dp),
                                border = BorderStroke(1.2.dp, GoldBorder)
                            ) {
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Brush.verticalGradient(listOf(EmeraldDeep, EmeraldPrimary)))
                                            .padding(22.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(12.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(
                                                    text = "VIDEO LESSON • ${level.videoLesson.durationLabel}",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = GoldBright
                                                )
                                                Text(
                                                    text = "Chapter ${videoSlideIndex + 1}/${slides.size}",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = PureWhite
                                                )
                                            }
                                            Text(
                                                text = activeSlide.title,
                                                style = MaterialTheme.typography.titleLarge,
                                                color = PureWhite,
                                                textAlign = TextAlign.Center
                                            )
                                            ArabicTextDisplay(
                                                text = activeSlide.arabicFocus,
                                                fontSize = 42.sp,
                                                color = GoldSoft
                                            )
                                            Text(
                                                text = activeSlide.caption,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = PureWhite.copy(alpha = 0.92f),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }

                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Text(
                                            text = level.videoLesson.title,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            text = "Instructor: ${level.videoLesson.instructor}",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            OutlinedButton(
                                                onClick = { if (videoSlideIndex > 0) videoSlideIndex-- },
                                                enabled = videoSlideIndex > 0
                                            ) {
                                                Icon(Icons.Filled.SkipPrevious, contentDescription = "Previous slide")
                                                Text("Prev")
                                            }
                                            Button(
                                                onClick = {
                                                    onSpeakArabic(
                                                        activeSlide.narration,
                                                        activeSlide.caption,
                                                        "video_${level.number}_$videoSlideIndex"
                                                    )
                                                },
                                                shape = RoundedCornerShape(50)
                                            ) {
                                                Icon(Icons.Filled.PlayCircle, contentDescription = null)
                                                Spacer(Modifier.width(6.dp))
                                                Text("Play Chapter")
                                            }
                                            OutlinedButton(
                                                onClick = { if (videoSlideIndex < slides.lastIndex) videoSlideIndex++ },
                                                enabled = videoSlideIndex < slides.lastIndex
                                            ) {
                                                Text("Next")
                                                Icon(Icons.Filled.SkipNext, contentDescription = "Next slide")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    else -> {
                        // Interactive Practice Drills
                        val allPractice = level.lessons.flatMap { it.practiceItems }
                        items(allPractice) { word ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onSpeakArabic(word.speakText, word.transliteration, "drill_${word.arabic}")
                                    },
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(word.transliteration, style = MaterialTheme.typography.titleMedium)
                                        if (word.meaning.isNotBlank()) {
                                            Text(
                                                word.meaning,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                Icons.AutoMirrored.Filled.VolumeUp,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(Modifier.width(4.dp))
                                            Text(
                                                "Tap to listen & repeat",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }
                                    ArabicTextDisplay(
                                        text = word.arabic,
                                        fontSize = 32.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }

                // Bottom Level Assessment CTA
                item {
                    Button(
                        onClick = { onStartQuiz(level.number) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("start_level_exam_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldBright,
                            contentColor = EmeraldDeep
                        )
                    ) {
                        Icon(Icons.Filled.Quiz, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Take Level ${level.number} Exam & Unlock Next Level",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}
