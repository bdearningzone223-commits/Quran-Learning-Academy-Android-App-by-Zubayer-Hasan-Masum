package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioPlaybackState
import com.example.data.content.ArabicAlphabetData
import com.example.data.model.ArabicLetter
import com.example.ui.AcademyUiState
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
fun ArabicAlphabetScreen(
    uiState: AcademyUiState,
    audioState: AudioPlaybackState,
    onSpeakArabic: (String, String?, String) -> Unit,
    onToggleSlowMode: () -> Unit,
    onPracticeLetter: (Int) -> Unit,
    onTakeLevel1Quiz: () -> Unit
) {
    val letters = ArabicAlphabetData.letters
    var selectedIndex by remember { mutableIntStateOf(0) }
    val selectedLetter = letters[selectedIndex]
    val practiceCount = uiState.letterPracticeMap[selectedLetter.number] ?: 0
    val practicedTotal = uiState.letterPracticeMap.size

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header & Speed Controls
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Interactive Arabic Alphabet",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "29 Letters • Practiced $practicedTotal/29 • Right-to-Left",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                FilterChip(
                    selected = audioState.slowMode,
                    onClick = onToggleSlowMode,
                    label = { Text(if (audioState.slowMode) "Slow Kid Audio" else "Normal Speed") },
                    leadingIcon = {
                        Icon(Icons.Filled.Speed, contentDescription = null, modifier = Modifier.size(16.dp))
                    },
                    modifier = Modifier.testTag("slow_audio_toggle")
                )
            }
        }

        // Interactive Large Arabic Letter Stage
        item {
            AnimatedContent(targetState = selectedLetter, label = "letter_stage") { letter ->
                LetterInteractiveStage(
                    letter = letter,
                    practiceCount = practiceCount,
                    isSpeaking = audioState.isSpeaking && audioState.activeTag == "letter_${letter.number}",
                    onPlayMain = {
                        onPracticeLetter(letter.number)
                        onSpeakArabic(letter.speakText, letter.englishName, "letter_${letter.number}")
                    },
                    onPlayVowel = { arabic, phonetic ->
                        onPracticeLetter(letter.number)
                        onSpeakArabic(arabic, phonetic, "vowel_${letter.number}_$phonetic")
                    },
                    onPracticeAgain = {
                        onPracticeLetter(letter.number)
                        onSpeakArabic(
                            "${letter.arabicName}. ${letter.withFatha}. ${letter.withKasra}. ${letter.withDamma}. ${letter.exampleArabic}",
                            "${letter.englishName}, ${letter.exampleTransliteration}",
                            "letter_${letter.number}"
                        )
                    }
                )
            }
        }

        // Right-to-Left 29-Letter Interactive Grid
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Tap Any Letter (Right → Left)",
                    style = MaterialTheme.typography.titleMedium
                )
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        letters.chunked(5).forEach { rowLetters ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                rowLetters.forEach { letter ->
                                    val isSelected = letter.number == selectedLetter.number
                                    val practiced = (uiState.letterPracticeMap[letter.number] ?: 0) > 0
                                    Card(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(74.dp)
                                            .clickable {
                                                selectedIndex = letter.number - 1
                                                onPracticeLetter(letter.number)
                                                onSpeakArabic(
                                                    letter.speakText,
                                                    letter.englishName,
                                                    "letter_${letter.number}"
                                                )
                                            }
                                            .testTag("alphabet_tile_${letter.number}"),
                                        shape = RoundedCornerShape(14.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = when {
                                                isSelected -> MaterialTheme.colorScheme.primary
                                                practiced -> EmeraldSoft
                                                else -> MaterialTheme.colorScheme.surface
                                            }
                                        ),
                                        border = BorderStroke(
                                            width = if (isSelected) 2.dp else 1.dp,
                                            color = if (isSelected) GoldBright else MaterialTheme.colorScheme.outline
                                        )
                                    ) {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                            if (practiced) {
                                                Icon(
                                                    imageVector = Icons.Filled.CheckCircle,
                                                    contentDescription = "Practiced",
                                                    tint = if (isSelected) GoldBright else EmeraldPrimary,
                                                    modifier = Modifier
                                                        .align(Alignment.TopStart)
                                                        .padding(4.dp)
                                                        .size(13.dp)
                                                )
                                            }
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(4.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                ArabicTextDisplay(
                                                    text = letter.letter,
                                                    fontSize = 30.sp,
                                                    color = if (isSelected) PureWhite else EmeraldDeep
                                                )
                                                Text(
                                                    text = letter.englishName,
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = if (isSelected) GoldSoft else MaterialTheme.colorScheme.onSurfaceVariant,
                                                    maxLines = 1
                                                )
                                            }
                                        }
                                    }
                                }
                                repeat(5 - rowLetters.size) {
                                    Spacer(Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }

        // Level 1 Assessment CTA
        item {
            Button(
                onClick = onTakeLevel1Quiz,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("alphabet_take_quiz_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldBright,
                    contentColor = EmeraldDeep
                )
            ) {
                Text(
                    text = "Take Level 1 Arabic Alphabet Quiz",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun LetterInteractiveStage(
    letter: ArabicLetter,
    practiceCount: Int,
    isSpeaking: Boolean,
    onPlayMain: () -> Unit,
    onPlayVowel: (String, String) -> Unit,
    onPracticeAgain: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.5.dp, GoldBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Emerald Upper Showcase
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(listOf(EmeraldDeep, EmeraldPrimary))
                    )
            ) {
                IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = GoldBright.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(1.dp, GoldBright)
                        ) {
                            Text(
                                text = "Letter ${letter.number} of 29",
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldSoft,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Surface(
                            color = PureWhite.copy(alpha = 0.16f),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = "Practiced ${practiceCount}×",
                                style = MaterialTheme.typography.labelSmall,
                                color = PureWhite,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    ArabicTextDisplay(
                        text = letter.letter,
                        fontSize = 92.sp,
                        color = GoldSoft
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = letter.englishName,
                            style = MaterialTheme.typography.headlineMedium,
                            color = PureWhite
                        )
                        ArabicTextDisplay(
                            text = "(${letter.arabicName})",
                            fontSize = 24.sp,
                            color = GoldBright
                        )
                    }

                    Text(
                        text = letter.pronunciationHint,
                        style = MaterialTheme.typography.bodySmall,
                        color = PureWhite.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(top = 6.dp)
                    ) {
                        AudioPronounceButton(
                            isSpeaking = isSpeaking,
                            label = "Play Pronunciation",
                            onClick = onPlayMain,
                            modifier = Modifier.testTag("letter_play_audio_button")
                        )
                        OutlinedButton(
                            onClick = onPracticeAgain,
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(1.dp, GoldBright),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldSoft),
                            modifier = Modifier.testTag("letter_repeat_button")
                        ) {
                            Icon(Icons.Filled.Repeat, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Repeat Drill")
                        }
                    }
                }
            }

            // Lower Details: Harakat Vowels, Connected Forms, Makhraj, Example Word
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Tap to Practice Vowels (Harakat)",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    VowelChipCard(
                        arabic = letter.withFatha,
                        label = "Fatha (a)",
                        modifier = Modifier.weight(1f),
                        onClick = { onPlayVowel(letter.withFatha, "${letter.englishName} with Fatha") }
                    )
                    VowelChipCard(
                        arabic = letter.withKasra,
                        label = "Kasra (i)",
                        modifier = Modifier.weight(1f),
                        onClick = { onPlayVowel(letter.withKasra, "${letter.englishName} with Kasra") }
                    )
                    VowelChipCard(
                        arabic = letter.withDamma,
                        label = "Damma (u)",
                        modifier = Modifier.weight(1f),
                        onClick = { onPlayVowel(letter.withDamma, "${letter.englishName} with Damma") }
                    )
                }

                // Connected Forms
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FormPill("Initial", letter.initialForm, Modifier.weight(1f))
                    FormPill("Medial", letter.medialForm, Modifier.weight(1f))
                    FormPill("Final", letter.finalForm, Modifier.weight(1f))
                }

                // Example Word
                Surface(
                    color = GoldSoft,
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, GoldBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onPlayVowel(letter.exampleArabic, letter.exampleTransliteration)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Quranic / Arabic Example",
                                style = MaterialTheme.typography.labelSmall,
                                color = EmeraldDeep
                            )
                            Text(
                                text = "${letter.exampleTransliteration} — ${letter.exampleMeaning}",
                                style = MaterialTheme.typography.titleSmall,
                                color = EmeraldDeep
                            )
                            Text(
                                text = "Makhraj: ${letter.makhraj}",
                                style = MaterialTheme.typography.bodySmall,
                                color = EmeraldDeep.copy(alpha = 0.8f)
                            )
                        }
                        ArabicTextDisplay(
                            text = letter.exampleArabic,
                            fontSize = 32.sp,
                            color = EmeraldDeep
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun VowelChipCard(
    arabic: String,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = EmeraldSoft,
        border = BorderStroke(1.dp, EmeraldPrimary.copy(alpha = 0.25f))
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArabicTextDisplay(text = arabic, fontSize = 30.sp, color = EmeraldDeep)
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = EmeraldDeep)
        }
    }
}

@Composable
private fun FormPill(title: String, form: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArabicTextDisplay(text = form, fontSize = 24.sp, color = MaterialTheme.colorScheme.onSurface)
            Text(text = title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
