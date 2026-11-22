package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.data.content.IslamicKnowledgeData
import com.example.data.local.CustomAcademyItemEntity
import com.example.ui.components.AcademySubTopBar
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.components.AudioPronounceButton
import com.example.ui.components.IslamicGeometricOverlay
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft
import com.example.ui.theme.PureWhite

@Composable
fun QuranLearningScreen(
    customQuranVerses: List<CustomAcademyItemEntity> = emptyList(),
    audioState: AudioPlaybackState,
    onBack: () -> Unit,
    onToggleSlowMode: () -> Unit,
    onSpeakArabic: (String, String?, String) -> Unit,
    onOpenLevel: (Int) -> Unit
) {
    val surahs = IslamicKnowledgeData.shortSurahs
    var selectedSurahIdx by remember { mutableIntStateOf(0) }
    var arabicOnlyMode by remember { mutableStateOf(true) }
    val selectedSurah = surahs[selectedSurahIdx]

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "الْقُرْآنُ الْكَرِيمُ • Quran",
                subtitle = "Short Surahs & Recitation Practice",
                onBack = onBack,
                actions = {
                    FilterChip(
                        selected = !arabicOnlyMode,
                        onClick = { arabicOnlyMode = !arabicOnlyMode },
                        label = { Text(if (arabicOnlyMode) "عربي" else "Meaning") },
                        leadingIcon = { Icon(Icons.Filled.Translate, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    FilterChip(
                        selected = audioState.slowMode,
                        onClick = onToggleSlowMode,
                        label = { Text(if (audioState.slowMode) "Slow" else "Normal") },
                        leadingIcon = { Icon(Icons.Filled.Speed, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
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
            // Surah Selector Chips
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(surahs.indices.toList()) { idx ->
                        val s = surahs[idx]
                        FilterChip(
                            selected = idx == selectedSurahIdx,
                            onClick = { selectedSurahIdx = idx },
                            label = { Text("${s.number}. ${s.nameEnglish.removePrefix("Surah ")}") }
                        )
                    }
                }
            }

            // Surah Ornamental Header Banner
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    border = BorderStroke(1.2.dp, GoldBorder)
                ) {
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
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = selectedSurah.revelationType,
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldBright
                            )
                            ArabicTextDisplay(
                                text = selectedSurah.nameArabic,
                                fontSize = 36.sp,
                                color = GoldSoft
                            )
                            Text(
                                text = "${selectedSurah.nameEnglish} (${selectedSurah.meaning})",
                                style = MaterialTheme.typography.titleMedium,
                                color = PureWhite,
                                textAlign = TextAlign.Center
                            )
                            if (selectedSurah.number != 1) {
                                ArabicTextDisplay(
                                    text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                                    fontSize = 24.sp,
                                    color = PureWhite.copy(alpha = 0.94f)
                                )
                            }
                            AudioPronounceButton(
                                isSpeaking = audioState.isSpeaking && audioState.activeTag == "surah_${selectedSurah.number}",
                                label = "Recite Full Surah",
                                onClick = {
                                    val fullArabic = selectedSurah.ayahs.joinToString(". ") { it.arabic }
                                    val fullPhonetic = selectedSurah.ayahs.joinToString(". ") { it.transliteration }
                                    onSpeakArabic(fullArabic, fullPhonetic, "surah_${selectedSurah.number}")
                                }
                            )
                        }
                    }
                }
            }

            // Ayah-by-Ayah Recitation Cards
            items(selectedSurah.ayahs, key = { "${selectedSurah.number}_${it.number}" }) { ayah ->
                val isPlaying = audioState.isSpeaking && audioState.activeTag == "ayah_${selectedSurah.number}_${ayah.number}"
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isPlaying) GoldSoft else MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, if (isPlaying) GoldBright else MaterialTheme.colorScheme.outline)
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
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${ayah.number}",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                            IconButton(
                                onClick = {
                                    onSpeakArabic(
                                        ayah.arabic,
                                        ayah.transliteration,
                                        "ayah_${selectedSurah.number}_${ayah.number}"
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "Recite Ayah ${ayah.number}",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        ArabicTextDisplay(
                            text = ayah.arabic,
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (!arabicOnlyMode) {
                            Text(
                                text = ayah.transliteration,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = ayah.translation,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            if (customQuranVerses.isNotEmpty()) {
                item {
                    Text(
                        text = "إِضَافَاتُ الْقُرْآنِ • Admin Added Verses (${customQuranVerses.size})",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                items(customQuranVerses, key = { "custom_ayah_${it.id}" }) { verse ->
                    val tag = "custom_ayah_${verse.id}"
                    val isPlaying = audioState.isSpeaking && audioState.activeTag == tag
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = if (isPlaying) GoldSoft else MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, GoldBorder)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = verse.titleEnglish.ifBlank { verse.categoryArabic },
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                IconButton(
                                    onClick = {
                                        onSpeakArabic(
                                            verse.bodyArabic.ifBlank { verse.titleArabic },
                                            verse.transliteration,
                                            tag
                                        )
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                        contentDescription = "Listen",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            ArabicTextDisplay(
                                text = verse.bodyArabic.ifBlank { verse.titleArabic },
                                fontSize = 28.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                            if (!arabicOnlyMode) {
                                if (verse.transliteration.isNotBlank()) {
                                    Text(verse.transliteration, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                                }
                                if (verse.meaning.isNotBlank()) {
                                    Text(verse.meaning, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { onOpenLevel(12) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Level 12 Exam")
                    }
                    Button(
                        onClick = { onOpenLevel(13) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GoldBright, contentColor = EmeraldDeep)
                    ) {
                        Text("Level 13 Practice", fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
