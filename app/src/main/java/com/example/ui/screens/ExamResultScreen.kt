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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GuruAmber
import com.example.ui.theme.GuruAmberDark
import com.example.ui.theme.GuruEmerald
import com.example.ui.theme.GuruNavyBorder
import com.example.ui.theme.GuruNavyCard
import com.example.ui.theme.GuruNavyDark
import com.example.ui.theme.GuruNavySurface
import com.example.ui.theme.GuruRose
import com.example.ui.theme.GuruSky
import com.example.ui.theme.GuruTextMuted
import com.example.ui.theme.GuruTextPrimary
import com.example.ui.theme.GuruTextSecondary
import com.example.ui.viewmodel.GuruViewModel
import com.example.ui.viewmodel.Screen

@Composable
fun ExamResultScreen(
    viewModel: GuruViewModel,
    isNepali: Boolean,
    modifier: Modifier = Modifier
) {
    val result by viewModel.examResult.collectAsState()
    val questions by viewModel.examQuestions.collectAsState()
    val userAnswers by viewModel.userAnswers.collectAsState()
    val bookmarks by viewModel.bookmarkedQuestions.collectAsState()

    val res = result ?: return

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("exam_result_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Scorecard Hero Card
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                border = BorderStroke(1.5.dp, GuruAmber),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(GuruAmber.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = GuruAmber,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isNepali) "गुरु परीक्षा नतिजा विश्लेषण" else "Exam Performance Scorecard",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = GuruTextPrimary
                    )

                    Text(
                        text = res.title,
                        style = MaterialTheme.typography.bodySmall,
                        color = GuruTextSecondary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Final Score Display
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = String.format("%.2f", res.score),
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 38.sp
                            ),
                            color = if (res.score > 0) GuruEmerald else GuruRose
                        )
                        Text(
                            text = " / ${res.maxScore.toInt()}",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = GuruTextMuted,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Percentile Badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(GuruSky.copy(alpha = 0.2f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "All Nepal Rank: Top ${(100 - res.rankPercentile).toInt()}% (${String.format("%.1f", res.rankPercentile)}th Percentile)",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = GuruSky
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Breakdown stats
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(GuruNavySurface)
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ScoreStatItem(
                            label = "Correct",
                            value = "${res.correct}",
                            color = GuruEmerald
                        )
                        ScoreStatItem(
                            label = "Wrong (-20%)",
                            value = "${res.incorrect}",
                            color = GuruRose
                        )
                        ScoreStatItem(
                            label = "Unanswered",
                            value = "${res.unanswered}",
                            color = GuruTextMuted
                        )
                        ScoreStatItem(
                            label = "Accuracy",
                            value = "${res.accuracy.toInt()}%",
                            color = GuruAmber
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.startMockExam() },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, GuruAmber),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = GuruAmber),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(imageVector = Icons.Default.Replay, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Re-take Test")
                        }

                        Button(
                            onClick = { viewModel.navigateTo(Screen.HOME) },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GuruAmber, contentColor = GuruNavyDark),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(imageVector = Icons.Default.Home, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Dashboard", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Section Title: Question Review
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isNepali) "विस्तृत प्रश्नोत्तर विश्लेषण (Guru Solutions)" else "Detailed Question Analysis",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = GuruTextPrimary
                )
                Text(
                    text = "${questions.size} Questions",
                    style = MaterialTheme.typography.labelSmall,
                    color = GuruTextMuted
                )
            }
        }

        // Questions review list
        itemsIndexed(questions) { idx, q ->
            val userAns = userAnswers[q.id]
            val isCorrect = userAns == q.correctIndex
            val isSkipped = userAns == null
            val isBookmarked = bookmarks.any { it.questionText == q.questionText }

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                border = BorderStroke(
                    1.dp,
                    when {
                        isCorrect -> GuruEmerald.copy(alpha = 0.6f)
                        isSkipped -> GuruNavyBorder
                        else -> GuruRose.copy(alpha = 0.6f)
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // Header: Status tag & Bookmark
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(
                                        when {
                                            isCorrect -> GuruEmerald.copy(alpha = 0.2f)
                                            isSkipped -> GuruNavySurface
                                            else -> GuruRose.copy(alpha = 0.2f)
                                        }
                                    )
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = when {
                                        isCorrect -> "+1.00 CORRECT"
                                        isSkipped -> "UNATTEMPTED"
                                        else -> "-0.20 INCORRECT"
                                    },
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = when {
                                        isCorrect -> GuruEmerald
                                        isSkipped -> GuruTextMuted
                                        else -> GuruRose
                                    }
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = q.subject,
                                style = MaterialTheme.typography.labelSmall,
                                color = GuruSky
                            )
                        }

                        IconButton(onClick = { viewModel.toggleBookmarkCurrentQuestion(q) }) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Bookmark",
                                tint = if (isBookmarked) GuruAmber else GuruTextMuted
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Question Text
                    Text(
                        text = "${idx + 1}. ${if (isNepali) q.questionTextNepali else q.questionText}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = GuruTextPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Option comparison
                    val options = if (isNepali) q.optionsNepali else q.options
                    options.forEachIndexed { optIdx, optText ->
                        val isUserPick = userAns == optIdx
                        val isCorrectOption = q.correctIndex == optIdx

                        val optColor = when {
                            isCorrectOption -> GuruEmerald
                            isUserPick -> GuruRose
                            else -> GuruTextSecondary
                        }

                        val optBg = when {
                            isCorrectOption -> GuruEmerald.copy(alpha = 0.15f)
                            isUserPick -> GuruRose.copy(alpha = 0.15f)
                            else -> Color.Transparent
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(optBg)
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isCorrectOption) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Correct",
                                    tint = GuruEmerald,
                                    modifier = Modifier.size(16.dp)
                                )
                            } else if (isUserPick) {
                                Icon(
                                    imageVector = Icons.Default.Cancel,
                                    contentDescription = "Your wrong pick",
                                    tint = GuruRose,
                                    modifier = Modifier.size(16.dp)
                                )
                            } else {
                                Spacer(modifier = Modifier.width(16.dp))
                            }

                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${('A'.code + optIdx).toChar()}) $optText",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = if (isCorrectOption || isUserPick) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = optColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Explanation Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(GuruNavySurface)
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = GuruAmber,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isNepali) "गुरु स्पष्टीकरण (Guru Insight):" else "Guru Explanation:",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = GuruAmber
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isNepali) q.explanationNepali else q.explanation,
                                style = MaterialTheme.typography.bodySmall,
                                color = GuruTextSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreStatItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = color
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = GuruTextMuted
        )
    }
}
