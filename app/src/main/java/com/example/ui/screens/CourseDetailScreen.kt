package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Course
import com.example.data.model.Lesson
import com.example.ui.theme.GuruAmber
import com.example.ui.theme.GuruAmberDark
import com.example.ui.theme.GuruEmerald
import com.example.ui.theme.GuruNavyBorder
import com.example.ui.theme.GuruNavyCard
import com.example.ui.theme.GuruNavyDark
import com.example.ui.theme.GuruNavySurface
import com.example.ui.theme.GuruOrange
import com.example.ui.theme.GuruSky
import com.example.ui.theme.GuruTextMuted
import com.example.ui.theme.GuruTextPrimary
import com.example.ui.theme.GuruTextSecondary
import com.example.ui.viewmodel.GuruViewModel

@Composable
fun CourseDetailScreen(
    course: Course?,
    viewModel: GuruViewModel,
    isNepali: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (course == null) return

    val selectedLesson by viewModel.selectedLesson.collectAsState()
    val isPlaying by viewModel.isPlayingVideo.collectAsState()
    val videoProgress by viewModel.videoProgress.collectAsState()
    val playbackSpeed by viewModel.playbackSpeed.collectAsState()
    val activeTab by viewModel.courseActiveTab.collectAsState()

    val expandedChapters = remember { mutableStateMapOf<Int, Boolean>().apply { put(1, true) } }
    var doubtInput by remember { mutableStateOf("") }
    var doubtList by remember {
        mutableStateOf(
            listOf(
                "Santosh Sir, can we cite Supreme Court precedent on Article 20 in 2nd paper?",
                "Is there any difference between Directive Principles and State Obligations?",
                "Sir, which book is recommended for English vocabulary in Section Officer?"
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("course_detail_screen")
    ) {
        // Video Player Simulator
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Color.Black)
                .testTag("video_player_simulator")
        ) {
            // Video background gradient simulation
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF13192B),
                                Color(0xFF060911)
                            )
                        )
                    )
            )

            // Top action bar overlay
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(GuruAmber.copy(alpha = 0.3f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "1080p FHD",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = GuruAmber
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { /* Share */ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }
                }
            }

            // Center Play / Pause & Controls
            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        val newProgress = (videoProgress - 0.05f).coerceAtLeast(0f)
                        viewModel.setVideoProgress(newProgress)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Replay10,
                        contentDescription = "Rewind 10s",
                        tint = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Play / Pause Circle
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(GuruAmber)
                        .clickable { viewModel.toggleVideoPlay() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = GuruNavyDark,
                        modifier = Modifier.size(32.dp)
                    )
                }

                IconButton(
                    onClick = {
                        val newProgress = (videoProgress + 0.05f).coerceAtMost(1f)
                        viewModel.setVideoProgress(newProgress)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Forward10,
                        contentDescription = "Forward 10s",
                        tint = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            // Bottom scrub bar & controls
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))
                        )
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                // Lecture title
                Text(
                    text = selectedLesson?.title ?: "Chapter Introduction",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Slider
                Slider(
                    value = videoProgress,
                    onValueChange = { viewModel.setVideoProgress(it) },
                    colors = SliderDefaults.colors(
                        thumbColor = GuruAmber,
                        activeTrackColor = GuruAmber,
                        inactiveTrackColor = Color.White.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val currentMins = (videoProgress * 42).toInt()
                    val currentSecs = ((videoProgress * 42 * 60) % 60).toInt()
                    Text(
                        text = String.format("%02d:%02d / %s", currentMins, currentSecs, selectedLesson?.duration ?: "42:15"),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Speed Switcher
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color.White.copy(alpha = 0.2f))
                                .clickable {
                                    val nextSpeed = when (playbackSpeed) {
                                        1.0f -> 1.25f
                                        1.25f -> 1.5f
                                        1.5f -> 2.0f
                                        else -> 1.0f
                                    }
                                    viewModel.setPlaybackSpeed(nextSpeed)
                                }
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "${playbackSpeed}x",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.Fullscreen,
                            contentDescription = "Fullscreen",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        // Tab Navigation (0: Syllabus, 1: Notes, 2: Q&A)
        TabRow(
            selectedTabIndex = activeTab,
            containerColor = GuruNavySurface,
            contentColor = GuruAmber,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[activeTab]),
                    color = GuruAmber
                )
            }
        ) {
            Tab(
                selected = activeTab == 0,
                onClick = { viewModel.setCourseActiveTab(0) },
                text = { Text("Syllabus", fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = activeTab == 1,
                onClick = { viewModel.setCourseActiveTab(1) },
                text = { Text("E-Notes (${course.totalNotes})", fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = activeTab == 2,
                onClick = { viewModel.setCourseActiveTab(2) },
                text = { Text("Doubt Q&A", fontWeight = FontWeight.Bold) }
            )
        }

        // Tab Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (activeTab) {
                0 -> {
                    // Course Overview Brief
                    item {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                            border = BorderStroke(1.dp, GuruNavyBorder)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = GuruTextPrimary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = course.overview,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = GuruTextSecondary
                                )
                            }
                        }
                    }

                    // Chapters and Lessons
                    items(course.curriculum) { chapter ->
                        val isExpanded = expandedChapters[chapter.chapterNumber] ?: false
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                            border = BorderStroke(1.dp, GuruNavyBorder)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            expandedChapters[chapter.chapterNumber] = !isExpanded
                                        }
                                        .padding(14.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Chapter ${chapter.chapterNumber}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = GuruAmber
                                        )
                                        Text(
                                            text = chapter.title,
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                            color = GuruTextPrimary
                                        )
                                    }
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                        contentDescription = null,
                                        tint = GuruTextMuted
                                    )
                                }

                                AnimatedVisibility(visible = isExpanded) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(GuruNavyDark.copy(alpha = 0.4f))
                                            .padding(bottom = 8.dp)
                                    ) {
                                        chapter.lessons.forEach { lesson ->
                                            val isSelected = selectedLesson?.id == lesson.id
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clickable { viewModel.selectLesson(lesson) }
                                                    .background(
                                                        if (isSelected) GuruAmber.copy(alpha = 0.15f) else Color.Transparent
                                                    )
                                                    .padding(horizontal = 16.dp, vertical = 10.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.weight(1f)
                                                ) {
                                                    Icon(
                                                        imageVector = if (isSelected) Icons.Default.PlayArrow else Icons.Default.CheckCircle,
                                                        contentDescription = null,
                                                        tint = if (isSelected) GuruAmber else GuruTextMuted,
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(10.dp))
                                                    Column {
                                                        Text(
                                                            text = lesson.title,
                                                            style = MaterialTheme.typography.bodySmall.copy(
                                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                                            ),
                                                            color = if (isSelected) GuruAmber else GuruTextPrimary
                                                        )
                                                        Text(
                                                            text = "${lesson.duration} • 1 Note Included",
                                                            style = MaterialTheme.typography.labelSmall,
                                                            color = GuruTextMuted
                                                        )
                                                    }
                                                }

                                                if (lesson.isFreePreview) {
                                                    Box(
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(4.dp))
                                                            .background(GuruEmerald.copy(alpha = 0.2f))
                                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                                    ) {
                                                        Text(
                                                            text = "FREE",
                                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                                            color = GuruEmerald
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                1 -> {
                    // Notes tab
                    item {
                        Text(
                            text = "Course Study Material & PDF Handouts",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = GuruTextPrimary
                        )
                    }

                    items(course.curriculum.flatMap { it.lessons }) { lesson ->
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
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                    Icon(
                                        imageVector = Icons.Default.MenuBook,
                                        contentDescription = null,
                                        tint = GuruSky,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = "${lesson.title} - Handwritten Notes",
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                            color = GuruTextPrimary
                                        )
                                        Text(
                                            text = "Verified Guru Lecture Summary • 8 Pages",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = GuruTextMuted
                                        )
                                    }
                                }

                                IconButton(onClick = { /* download */ }) {
                                    Icon(
                                        imageVector = Icons.Default.Download,
                                        contentDescription = "Download Note",
                                        tint = GuruAmber
                                    )
                                }
                            }
                        }
                    }
                }
                2 -> {
                    // Q&A / Doubt Forum
                    item {
                        Column {
                            Text(
                                text = "Ask Instructor & Discussion",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = GuruTextPrimary
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = doubtInput,
                                    onValueChange = { doubtInput = it },
                                    placeholder = { Text("Ask a doubt to Guru Santosh Sir...", color = GuruTextMuted) },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedContainerColor = GuruNavySurface,
                                        unfocusedContainerColor = GuruNavySurface,
                                        focusedBorderColor = GuruAmber,
                                        unfocusedBorderColor = GuruNavyBorder,
                                        focusedTextColor = GuruTextPrimary,
                                        unfocusedTextColor = GuruTextPrimary
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        if (doubtInput.isNotBlank()) {
                                            doubtList = listOf("You: $doubtInput") + doubtList
                                            doubtInput = ""
                                        }
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = GuruAmber,
                                        contentColor = GuruNavyDark
                                    )
                                ) {
                                    Icon(imageVector = Icons.Default.Send, contentDescription = "Post Doubt")
                                }
                            }
                        }
                    }

                    items(doubtList) { doubt ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                            border = BorderStroke(1.dp, GuruNavyBorder)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = doubt,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = GuruTextPrimary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(GuruEmerald.copy(alpha = 0.2f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "GURU ANSWERED",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = GuruEmerald
                                        )
                                    }
                                    Text(
                                        text = "Santosh Khadka • 2h ago",
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
    }
}
