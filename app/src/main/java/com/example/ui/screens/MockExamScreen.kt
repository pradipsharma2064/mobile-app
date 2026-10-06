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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ExamQuestion
import com.example.ui.theme.GuruAmber
import com.example.ui.theme.GuruAmberDark
import com.example.ui.theme.GuruEmerald
import com.example.ui.theme.GuruNavyBorder
import com.example.ui.theme.GuruNavyCard
import com.example.ui.theme.GuruNavyDark
import com.example.ui.theme.GuruNavySurface
import com.example.ui.theme.GuruOrange
import com.example.ui.theme.GuruRose
import com.example.ui.theme.GuruSky
import com.example.ui.theme.GuruTextMuted
import com.example.ui.theme.GuruTextPrimary
import com.example.ui.theme.GuruTextSecondary
import com.example.ui.theme.GuruViolet
import com.example.ui.viewmodel.GuruViewModel
import com.example.ui.viewmodel.Screen

@Composable
fun MockExamIntroScreen(
    viewModel: GuruViewModel,
    isNepali: Boolean,
    modifier: Modifier = Modifier
) {
    val pastAttempts by viewModel.pastAttempts.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .padding(16.dp)
            .testTag("mock_exam_intro"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = if (isNepali) "हाम्रो सपना परीक्षा - वास्तविक परीक्षा अभ्यास" else "Hamro Sapana Pariksha - Mock Exam",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = GuruTextPrimary
                )
                Text(
                    text = if (isNepali) "लोकसेवा, शिक्षक सेवा तथा बैंकिङ परीक्षाको वास्तविक ढाँचा" else "Real Loksewa, TSC & Banking pattern with timer & negative marks",
                    style = MaterialTheme.typography.bodySmall,
                    color = GuruTextSecondary
                )
            }
        }

        // Live Test Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                border = BorderStroke(1.dp, GuruAmber),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(GuruAmber.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "LIVE ALL-NEPAL TEST",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = GuruAmber
                            )
                        }

                        Text(
                            text = "Ends in 2 Days",
                            style = MaterialTheme.typography.labelSmall,
                            color = GuruRose
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Loksewa Section Officer Paper 1 (GK + IQ) Mega Model Set 04",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = GuruTextPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Rules Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ExamRuleBadge(title = "Questions", value = "8 Questions")
                        ExamRuleBadge(title = "Duration", value = "15 Mins")
                        ExamRuleBadge(title = "Correct", value = "+1.0 Mark")
                        ExamRuleBadge(title = "Negative", value = "-20% (-0.20)")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.startMockExam() },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GuruAmber,
                            contentColor = GuruNavyDark
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("start_mock_exam_btn")
                    ) {
                        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isNepali) "परीक्षा सुरु गर्नुहोस्" else "Start Mock Examination",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }

        // Instructions
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = GuruNavySurface),
                border = BorderStroke(1.dp, GuruNavyBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = GuruSky,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isNepali) "परीक्षार्थीका लागि महत्त्वपूर्ण नियमहरू:" else "Exam Rules & Instructions:",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = GuruTextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "1. Each question has 4 options with only ONE correct answer.\n" +
                                "2. Real Loksewa rule applies: 20% mark (0.20) is deducted for each incorrect answer.\n" +
                                "3. Use the bilingual toggle to switch between English and Nepali text at any time.\n" +
                                "4. Questions can be marked for review and changed anytime before submitting.\n" +
                                "5. Once submitted, comprehensive percentile ranking and Guru explanations will be displayed.",
                        style = MaterialTheme.typography.bodySmall,
                        color = GuruTextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Past Attempts History
        if (pastAttempts.isNotEmpty()) {
            item {
                Text(
                    text = if (isNepali) "तपाईंको अघिल्लो नतिजाहरू" else "Your Past Exam Attempts",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = GuruTextPrimary
                )
            }

            items(pastAttempts.size) { index ->
                val attempt = pastAttempts[index]
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                    border = BorderStroke(1.dp, GuruNavyBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = attempt.examTitle,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                color = GuruTextPrimary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${attempt.correctCount} Correct • ${attempt.incorrectCount} Wrong • Accuracy: ${attempt.accuracyPercentage.toInt()}%",
                                style = MaterialTheme.typography.labelSmall,
                                color = GuruTextMuted
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = String.format("%.2f / %.0f", attempt.score, attempt.maxScore),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                                color = if (attempt.score > (attempt.maxScore * 0.5)) GuruEmerald else GuruRose
                            )
                            Text(
                                text = "Score",
                                style = MaterialTheme.typography.labelSmall,
                                color = GuruTextMuted
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExamRuleBadge(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = GuruAmber
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = GuruTextMuted
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MockExamActiveScreen(
    viewModel: GuruViewModel,
    isNepali: Boolean,
    modifier: Modifier = Modifier
) {
    val questions by viewModel.examQuestions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val userAnswers by viewModel.userAnswers.collectAsState()
    val reviewIds by viewModel.reviewQuestionIds.collectAsState()
    val timeRemaining by viewModel.examTimeRemainingSeconds.collectAsState()
    val bookmarks by viewModel.bookmarkedQuestions.collectAsState()

    var showPaletteSheet by remember { mutableStateOf(false) }
    var showSubmitDialog by remember { mutableStateOf(false) }
    var useNepaliTranslation by remember { mutableStateOf(isNepali) }

    val currentQ = questions.getOrNull(currentIndex) ?: return
    val selectedOption = userAnswers[currentQ.id]
    val isMarkedForReview = reviewIds.contains(currentQ.id)
    val isBookmarked = bookmarks.any { it.questionText == currentQ.questionText }

    val minutes = timeRemaining / 60
    val seconds = timeRemaining % 60
    val timerString = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("mock_exam_active_screen")
    ) {
        // Exam Header: Timer, Question index, Palette button, Submit
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(GuruNavySurface)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Live Timer
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (timeRemaining < 120) GuruRose.copy(alpha = 0.2f) else GuruNavyDark)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "Timer",
                    tint = if (timeRemaining < 120) GuruRose else GuruAmber,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = timerString,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (timeRemaining < 120) GuruRose else GuruAmber
                )
            }

            // Question counter
            Text(
                text = "Q ${currentIndex + 1} of ${questions.size}",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = GuruTextPrimary
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Language switcher
                IconButton(onClick = { useNepaliTranslation = !useNepaliTranslation }) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Language",
                        tint = if (useNepaliTranslation) GuruAmber else GuruTextMuted
                    )
                }

                // Question Palette button
                IconButton(
                    onClick = { showPaletteSheet = true },
                    modifier = Modifier.testTag("open_palette_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.GridOn,
                        contentDescription = "Palette",
                        tint = GuruSky
                    )
                }

                // Submit button
                Button(
                    onClick = { showSubmitDialog = true },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GuruEmerald,
                        contentColor = GuruNavyDark
                    ),
                    modifier = Modifier
                        .height(34.dp)
                        .testTag("submit_exam_top_btn")
                ) {
                    Text(
                        text = "Submit",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        // Active Question Card & Options
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(GuruNavySurface)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = currentQ.subject,
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = GuruSky
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { viewModel.toggleBookmarkCurrentQuestion(currentQ) }) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Bookmark",
                                tint = if (isBookmarked) GuruAmber else GuruTextMuted
                            )
                        }

                        // Mark for Review chip
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isMarkedForReview) GuruViolet else GuruNavySurface)
                                .clickable { viewModel.toggleMarkForReview(currentQ.id) }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                                .testTag("mark_for_review_btn")
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Flag,
                                    contentDescription = null,
                                    tint = if (isMarkedForReview) Color.White else GuruTextMuted,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isMarkedForReview) "Reviewing" else "Review",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = if (isMarkedForReview) Color.White else GuruTextMuted
                                )
                            }
                        }
                    }
                }
            }

            // Question Text
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                    border = BorderStroke(1.dp, GuruNavyBorder)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = if (useNepaliTranslation) currentQ.questionTextNepali else currentQ.questionText,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                lineHeight = 24.sp
                            ),
                            color = GuruTextPrimary
                        )
                    }
                }
            }

            // 4 Options
            val optionsList = if (useNepaliTranslation) currentQ.optionsNepali else currentQ.options
            items(optionsList.size) { optIndex ->
                val isSelected = selectedOption == optIndex
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) GuruAmber.copy(alpha = 0.15f) else GuruNavyCard
                    ),
                    border = BorderStroke(
                        1.5.dp,
                        if (isSelected) GuruAmber else GuruNavyBorder
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.selectOption(currentQ.id, optIndex) }
                        .testTag("option_${currentQ.id}_$optIndex")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { viewModel.selectOption(currentQ.id, optIndex) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = GuruAmber,
                                unselectedColor = GuruTextMuted
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${('A'.code + optIndex).toChar()}) ${optionsList[optIndex]}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            ),
                            color = if (isSelected) GuruAmber else GuruTextPrimary
                        )
                    }
                }
            }
        }

        // Bottom Navigation Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(GuruNavySurface)
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { viewModel.previousQuestion() },
                enabled = currentIndex > 0,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = GuruTextPrimary),
                border = BorderStroke(1.dp, GuruNavyBorder)
            ) {
                Text("Previous")
            }

            // Clear response
            if (selectedOption != null) {
                TextButton(onClick = { viewModel.selectOption(currentQ.id, selectedOption) }) {
                    Text("Clear Choice", color = GuruRose, fontSize = 12.sp)
                }
            }

            Button(
                onClick = {
                    if (currentIndex < questions.size - 1) {
                        viewModel.nextQuestion()
                    } else {
                        showSubmitDialog = true
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GuruAmber,
                    contentColor = GuruNavyDark
                ),
                modifier = Modifier.testTag("next_or_finish_btn")
            ) {
                Text(
                    text = if (currentIndex < questions.size - 1) "Next" else "Finish",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    // Submit Confirmation Dialog
    if (showSubmitDialog) {
        val answeredCount = userAnswers.size
        val unansweredCount = questions.size - answeredCount
        val reviewedCount = reviewIds.size

        AlertDialog(
            onDismissRequest = { showSubmitDialog = false },
            title = {
                Text("Submit Guru Pariksha?", fontWeight = FontWeight.Bold, color = GuruTextPrimary)
            },
            text = {
                Column {
                    Text(
                        "Are you sure you want to finalize and submit your answers?",
                        color = GuruTextSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("• Answered: $answeredCount", color = GuruEmerald, fontWeight = FontWeight.Bold)
                    Text("• Unanswered: $unansweredCount", color = GuruRose, fontWeight = FontWeight.Bold)
                    Text("• Marked for Review: $reviewedCount", color = GuruViolet, fontWeight = FontWeight.Bold)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSubmitDialog = false
                        viewModel.submitExam()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = GuruAmber, contentColor = GuruNavyDark)
                ) {
                    Text("Yes, Submit Exam", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSubmitDialog = false }) {
                    Text("Continue Test", color = GuruTextMuted)
                }
            },
            containerColor = GuruNavyCard
        )
    }

    // Question Palette Bottom Sheet
    if (showPaletteSheet) {
        ModalBottomSheet(
            onDismissRequest = { showPaletteSheet = false },
            containerColor = GuruNavyCard
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Question Palette",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = GuruTextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Legend
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    LegendItem(color = GuruEmerald, label = "Answered")
                    LegendItem(color = GuruViolet, label = "Review")
                    LegendItem(color = GuruNavySurface, label = "Unvisited")
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(questions) { idx, q ->
                        val isAns = userAnswers.containsKey(q.id)
                        val isRev = reviewIds.contains(q.id)
                        val isCur = idx == currentIndex

                        val bgColor = when {
                            isCur -> GuruAmber
                            isRev -> GuruViolet
                            isAns -> GuruEmerald
                            else -> GuruNavySurface
                        }

                        val textColor = when {
                            isCur -> GuruNavyDark
                            isRev || isAns -> Color.White
                            else -> GuruTextSecondary
                        }

                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(bgColor)
                                .clickable {
                                    viewModel.jumpToQuestion(idx)
                                    showPaletteSheet = false
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${idx + 1}",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = textColor
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = GuruTextSecondary)
    }
}
