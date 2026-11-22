package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.content.CurriculumCatalog
import com.example.data.local.QuizAttemptEntity
import com.example.data.model.QuizQuestion
import com.example.ui.AcademyUiState
import com.example.ui.components.AcademySubTopBar
import com.example.ui.components.ArabicTextDisplay
import com.example.ui.components.StarRatingRow
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft

@Composable
fun LevelQuizScreen(
    levelNumber: Int,
    uiState: AcademyUiState,
    onBack: () -> Unit,
    onSubmitQuiz: (Int, Int, Int, String, (QuizAttemptEntity) -> Unit) -> Unit,
    onContinueNextLevel: (Int) -> Unit
) {
    val level = CurriculumCatalog.getLevel(levelNumber)
    val customTeacherQuestions = remember(uiState.teacherUploads, levelNumber) {
        uiState.teacherUploads
            .filter { it.levelNumber == levelNumber && it.contentType == "QUIZ" && it.quizOptionsDelimited.isNotBlank() }
            .map { upload ->
                val opts = upload.quizOptionsDelimited.split("|").filter { it.isNotBlank() }
                QuizQuestion(
                    id = "teacher_q_${upload.id}",
                    prompt = upload.title,
                    arabicDisplay = upload.arabicContent.ifBlank { null },
                    options = if (opts.size >= 2) opts else listOf("Correct", "Incorrect"),
                    correctIndex = upload.correctOptionIndex.coerceIn(0, (opts.size - 1).coerceAtLeast(0)),
                    explanation = upload.description
                )
            }
    }
    val questions = remember(level, customTeacherQuestions) {
        level.quizQuestions + customTeacherQuestions
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var answeredCurrent by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    var resultAttempt by remember { mutableStateOf<QuizAttemptEntity?>(null) }

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "Level ${level.number} Examination",
                subtitle = level.title,
                onBack = onBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (resultAttempt != null) {
                val attempt = resultAttempt!!
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(1.5.dp, if (attempt.passed) GoldBright else MaterialTheme.colorScheme.outline),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (attempt.passed) {
                            Image(
                                painter = painterResource(R.drawable.img_certificate_seal),
                                contentDescription = "Award Seal",
                                modifier = Modifier.size(86.dp)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.EmojiEvents,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(64.dp)
                            )
                        }

                        Text(
                            text = if (attempt.passed) "MashaAllah! Level ${level.number} Passed!" else "Keep Practicing!",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "You scored ${attempt.score} out of ${attempt.totalQuestions}",
                            style = MaterialTheme.typography.titleLarge
                        )

                        StarRatingRow(stars = attempt.starsEarned)

                        if (attempt.passed) {
                            Surface(
                                color = GoldSoft,
                                shape = RoundedCornerShape(50),
                                border = BorderStroke(1.dp, GoldBorder)
                            ) {
                                Text(
                                    text = "Badge Awarded: ${attempt.badgeTitle}",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = EmeraldDeep,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                                )
                            }
                            if (level.number < 13) {
                                Text(
                                    text = "Level ${level.number + 1} is now unlocked!",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            } else {
                                Text(
                                    text = "Congratulations! You completed all 13 levels of Quran Learning Academy!",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        } else {
                            Text(
                                text = "Score at least 70% to earn stars and unlock the next level.",
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(Modifier.height(6.dp))
                        if (attempt.passed && level.number < 13) {
                            Button(
                                onClick = { onContinueNextLevel(level.number + 1) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("quiz_next_level_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GoldBright,
                                    contentColor = EmeraldDeep
                                )
                            ) {
                                Text("Continue to Level ${level.number + 1}", fontWeight = FontWeight.Bold)
                            }
                        }
                        OutlinedButton(
                            onClick = {
                                currentIndex = 0
                                selectedOption = null
                                answeredCurrent = false
                                score = 0
                                resultAttempt = null
                            },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Filled.Refresh, contentDescription = null)
                            Spacer(Modifier.size(6.dp))
                            Text("Retake Examination")
                        }
                        Button(
                            onClick = onBack,
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Back to Level ${level.number}")
                        }
                    }
                }
            } else {
                val question = questions[currentIndex]
                val progress = (currentIndex + 1).toFloat() / questions.size.toFloat()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Question ${currentIndex + 1} of ${questions.size}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Score: $score",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(50))
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, GoldBorder)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (!question.arabicDisplay.isNullOrBlank()) {
                            Surface(
                                color = EmeraldSoft,
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ArabicTextDisplay(
                                    text = question.arabicDisplay,
                                    fontSize = 44.sp,
                                    color = EmeraldDeep,
                                    modifier = Modifier.padding(vertical = 14.dp, horizontal = 12.dp)
                                )
                            }
                        }
                        Text(
                            text = question.prompt,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    question.options.forEachIndexed { idx, option ->
                        val isSelected = selectedOption == idx
                        val isCorrect = idx == question.correctIndex
                        val cardColor = when {
                            !answeredCurrent && isSelected -> EmeraldSoft
                            answeredCurrent && isCorrect -> EmeraldSoft
                            answeredCurrent && isSelected && !isCorrect -> MaterialTheme.colorScheme.errorContainer
                            else -> MaterialTheme.colorScheme.surface
                        }
                        val borderColor = when {
                            answeredCurrent && isCorrect -> MaterialTheme.colorScheme.primary
                            answeredCurrent && isSelected && !isCorrect -> MaterialTheme.colorScheme.error
                            isSelected -> GoldBright
                            else -> MaterialTheme.colorScheme.outline
                        }

                        Surface(
                            onClick = {
                                if (!answeredCurrent) {
                                    selectedOption = idx
                                    answeredCurrent = true
                                    if (idx == question.correctIndex) score++
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("quiz_option_$idx"),
                            shape = RoundedCornerShape(14.dp),
                            color = cardColor,
                            border = BorderStroke(if (isSelected || (answeredCurrent && isCorrect)) 2.dp else 1.dp, borderColor)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.weight(1f)
                                )
                                if (answeredCurrent && isCorrect) {
                                    Icon(Icons.Filled.CheckCircle, contentDescription = "Correct", tint = MaterialTheme.colorScheme.primary)
                                }
                            }
                        }
                    }
                }

                if (answeredCurrent) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = GoldSoft),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, GoldBorder)
                    ) {
                        Text(
                            text = question.explanation,
                            style = MaterialTheme.typography.bodySmall,
                            color = EmeraldDeep,
                            modifier = Modifier.padding(14.dp)
                        )
                    }

                    Button(
                        onClick = {
                            if (currentIndex < questions.lastIndex) {
                                currentIndex++
                                selectedOption = null
                                answeredCurrent = false
                            } else {
                                onSubmitQuiz(
                                    level.number,
                                    score,
                                    questions.size,
                                    level.badgeTitle
                                ) { evaluated ->
                                    resultAttempt = evaluated
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("quiz_continue_button"),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = if (currentIndex < questions.lastIndex) "Next Question" else "Finish & See Results",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
