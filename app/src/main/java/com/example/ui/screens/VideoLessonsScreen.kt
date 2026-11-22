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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioPlaybackState
import com.example.data.content.CurriculumCatalog
import com.example.data.model.VideoLessonData
import com.example.data.model.VideoSlide
import com.example.ui.AcademyUiState
import com.example.ui.components.AcademySubTopBar
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.components.IslamicGeometricOverlay
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft
import com.example.ui.theme.PureWhite

@Composable
fun VideoLessonsScreen(
    uiState: AcademyUiState,
    audioState: AudioPlaybackState,
    onBack: () -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit,
    onStopAudio: () -> Unit
) {
    val teacherVideos = remember(uiState.teacherUploads) {
        uiState.teacherUploads
            .filter { it.contentType == "VIDEO" }
            .map { upload ->
                VideoLessonData(
                    id = "teacher_vid_${upload.id}",
                    levelNumber = upload.levelNumber,
                    title = upload.title,
                    category = "Teacher Upload • Level ${upload.levelNumber}",
                    durationLabel = upload.durationLabel,
                    instructor = upload.teacherName,
                    description = upload.description,
                    slides = listOf(
                        VideoSlide(
                            title = upload.title,
                            arabicFocus = upload.arabicContent.ifBlank { "القُرْآنُ الكَرِيم" },
                            caption = upload.description,
                            narration = upload.arabicContent.ifBlank { upload.description }
                        )
                    )
                )
            }
    }
    val allVideos = remember(teacherVideos) { teacherVideos + CurriculumCatalog.allVideos }
    var activeVideo by remember(allVideos) { mutableStateOf(allVideos.first()) }
    var slideIdx by remember(activeVideo.id) { mutableIntStateOf(0) }
    val activeSlide = activeVideo.slides[slideIdx.coerceIn(0, activeVideo.slides.lastIndex)]
    val isPlayingCurrent = audioState.isSpeaking && audioState.activeTag == "vid_player_${activeVideo.id}_$slideIdx"

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Video Lessons",
                subtitle = "${allVideos.size} Interactive Guided Islamic Classes",
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
            // Interactive Guided Video Theater
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    border = BorderStroke(1.5.dp, GoldBorder)
                ) {
                    Column {
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
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "LEVEL ${activeVideo.levelNumber} • ${activeVideo.durationLabel}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = GoldBright,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Slide ${slideIdx + 1}/${activeVideo.slides.size}",
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
                                    fontSize = 38.sp,
                                    color = GoldSoft
                                )
                                Text(
                                    text = activeSlide.caption,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = PureWhite.copy(alpha = 0.92f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(activeVideo.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = "Instructor: ${activeVideo.instructor}",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedButton(
                                    onClick = { if (slideIdx > 0) slideIdx-- },
                                    enabled = slideIdx > 0
                                ) {
                                    Icon(Icons.Filled.SkipPrevious, contentDescription = "Previous")
                                    Text("Prev")
                                }
                                Button(
                                    onClick = {
                                        if (isPlayingCurrent) {
                                            onStopAudio()
                                        } else {
                                            onSpeakArabic(
                                                activeSlide.narration,
                                                activeSlide.caption,
                                                "vid_player_${activeVideo.id}_$slideIdx"
                                            )
                                        }
                                    },
                                    shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isPlayingCurrent) GoldBright else MaterialTheme.colorScheme.primary,
                                        contentColor = if (isPlayingCurrent) EmeraldDeep else PureWhite
                                    )
                                ) {
                                    Icon(
                                        imageVector = if (isPlayingCurrent) Icons.Filled.Stop else Icons.Filled.PlayArrow,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Text(if (isPlayingCurrent) "Stop" else "Play Lesson")
                                }
                                OutlinedButton(
                                    onClick = { if (slideIdx < activeVideo.slides.lastIndex) slideIdx++ },
                                    enabled = slideIdx < activeVideo.slides.lastIndex
                                ) {
                                    Text("Next")
                                    Icon(Icons.Filled.SkipNext, contentDescription = "Next")
                                }
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "All Academy Video Lessons",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            items(allVideos, key = { it.id }) { video ->
                val selected = video.id == activeVideo.id
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            activeVideo = video
                            slideIdx = 0
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected) GoldSoft else MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, if (selected) GoldBright else MaterialTheme.colorScheme.outline)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.PlayCircle,
                                    contentDescription = "Play ${video.title}",
                                    tint = PureWhite,
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Level ${video.levelNumber} • ${video.category} • ${video.durationLabel}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(video.title, style = MaterialTheme.typography.titleSmall)
                            Text(
                                text = video.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 2
                            )
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
