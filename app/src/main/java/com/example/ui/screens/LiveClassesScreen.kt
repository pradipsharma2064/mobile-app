package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AlarmAdd
import androidx.compose.material.icons.filled.CastConnected
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LiveClass
import com.example.data.sample.SampleData
import com.example.ui.theme.GuruAmber
import com.example.ui.theme.GuruAmberDark
import com.example.ui.theme.GuruAmberLight
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
import com.example.ui.viewmodel.GuruViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LiveClassesScreen(
    viewModel: GuruViewModel,
    isNepali: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val liveClasses = SampleData.liveClasses
    val reminders by viewModel.classReminders.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    var selectedTimeTab by remember { mutableIntStateOf(0) } // 0: All, 1: Live Now, 2: Today, 3: Tomorrow, 4: My Reminders
    val timeTabs = listOf("All Sessions", "🔴 Live Now", "Today", "Tomorrow", "My Reminders (${reminders.size})")

    val filteredClasses = remember(selectedTimeTab, selectedCategory, reminders) {
        liveClasses.filter { session ->
            val matchesCategory = selectedCategory == "All Exams" || session.examCategory == selectedCategory
            val matchesTime = when (selectedTimeTab) {
                0 -> true
                1 -> session.isLiveNow
                2 -> session.timeLabel == "Today"
                3 -> session.timeLabel == "Tomorrow"
                4 -> reminders.any { it.classId == session.id }
                else -> true
            }
            matchesCategory && matchesTime
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("live_classes_screen")
    ) {
        // Top Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GuruNavySurface)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("live_classes_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = GuruTextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isNepali) "प्रत्यक्ष कक्षाहरू" else "Live Classrooms",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 19.sp
                                ),
                                color = GuruTextPrimary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(GuruRose)
                            )
                        }
                        Text(
                            text = if (isNepali) "आगामी प्रत्यक्ष अन्तरक्रियात्मक सेसनहरू" else "Interactive live lectures with top faculties",
                            style = MaterialTheme.typography.bodySmall,
                            color = GuruTextSecondary
                        )
                    }
                }

                // Active Reminders Counter Chip
                if (reminders.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(GuruAmber.copy(alpha = 0.2f))
                            .clickable { selectedTimeTab = 4 }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("active_reminders_counter_chip")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.NotificationsActive,
                                contentDescription = null,
                                tint = GuruAmber,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${reminders.size} Set",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = GuruAmber
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Category filter chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SampleData.examCategories.forEach { category ->
                    val isSelected = category == selectedCategory
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) GuruAmber else GuruNavyDark)
                            .clickable { viewModel.setSelectedCategory(category) }
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                            .testTag("live_filter_chip_$category")
                    ) {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 11.sp
                            ),
                            color = if (isSelected) GuruNavyDark else GuruTextPrimary
                        )
                    }
                }
            }
        }

        // Time Filter Tabs
        TabRow(
            selectedTabIndex = selectedTimeTab,
            containerColor = GuruNavyDark,
            contentColor = GuruAmber,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTimeTab]),
                    color = GuruAmber
                )
            }
        ) {
            timeTabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTimeTab == index,
                    onClick = { selectedTimeTab = index },
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (selectedTimeTab == index) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    },
                    modifier = Modifier.testTag("live_tab_$index")
                )
            }
        }

        // Live Sessions List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            if (filteredClasses.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = if (selectedTimeTab == 4) Icons.Default.NotificationsNone else Icons.Default.Videocam,
                                contentDescription = null,
                                tint = GuruTextMuted,
                                modifier = Modifier.size(52.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = if (selectedTimeTab == 4) "No reminders set yet" else "No live sessions found",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = GuruTextPrimary
                            )
                            Text(
                                text = if (selectedTimeTab == 4)
                                    "Tap 'Set Reminder' on any upcoming lecture to get notified!"
                                else
                                    "Try choosing 'All Exams' or switching to another tab.",
                                style = MaterialTheme.typography.bodySmall,
                                color = GuruTextMuted
                            )
                        }
                    }
                }
            } else {
                items(filteredClasses) { session ->
                    val isReminderSet = reminders.any { it.classId == session.id }

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (session.isLiveNow) GuruNavySurface else GuruNavyCard
                        ),
                        border = BorderStroke(
                            1.5.dp,
                            if (session.isLiveNow) GuruRose else if (isReminderSet) GuruAmber else GuruNavyBorder
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("live_session_card_${session.id}")
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Header: Status badge & Category
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (session.isLiveNow) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(GuruRose)
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .clip(CircleShape)
                                                .background(Color.White)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "LIVE NOW",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                            color = Color.White
                                        )
                                    }
                                } else {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(GuruNavyDark)
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.AccessTime,
                                            contentDescription = null,
                                            tint = GuruAmber,
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = session.scheduledTime,
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = GuruAmber
                                        )
                                    }
                                }

                                // Exam Category tag
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(GuruNavyDark)
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = session.examCategory,
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                        color = GuruSky
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Lecture Title
                            Text(
                                text = session.title,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = GuruTextPrimary,
                                lineHeight = 22.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Instructor Information
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(GuruAmber.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = GuruAmber,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "${session.instructorName} • ${session.instructorRole}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = GuruTextSecondary
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Topics Covered Pills
                            if (session.syllabusTopics.isNotEmpty()) {
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    session.syllabusTopics.forEach { topic ->
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(GuruNavyDark.copy(alpha = 0.6f))
                                                .padding(horizontal = 7.dp, vertical = 3.dp)
                                        ) {
                                            Text(
                                                text = topic,
                                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                                color = GuruTextSecondary
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                            // Footer info: Attendees + Actions
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Group,
                                        contentDescription = null,
                                        tint = GuruTextMuted,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${session.attendeesCount} Aspirants",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = GuruTextMuted
                                    )
                                }

                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    // Reminder Action Button (Core Requirement)
                                    if (!session.isLiveNow) {
                                        Button(
                                            onClick = {
                                                viewModel.toggleClassReminder(session)
                                                val msg = if (isReminderSet)
                                                    "Reminder removed for ${session.title}"
                                                else
                                                    "🔔 Reminder set! You will be notified 15 mins before ${session.title}."
                                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                            },
                                            shape = RoundedCornerShape(10.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = if (isReminderSet) GuruAmber else GuruNavyDark,
                                                contentColor = if (isReminderSet) GuruNavyDark else GuruAmber
                                            ),
                                            border = if (isReminderSet) null else BorderStroke(1.dp, GuruAmber),
                                            modifier = Modifier
                                                .height(38.dp)
                                                .testTag("reminder_btn_${session.id}")
                                        ) {
                                            Icon(
                                                imageVector = if (isReminderSet) Icons.Default.NotificationsActive else Icons.Default.AlarmAdd,
                                                contentDescription = "Set Reminder",
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = if (isReminderSet) "Reminder Set ✓" else "Set Reminder",
                                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                                            )
                                        }
                                    }

                                    // Join / Enter Live Classroom
                                    Button(
                                        onClick = { viewModel.openLiveClass(session) },
                                        shape = RoundedCornerShape(10.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (session.isLiveNow) GuruRose else GuruNavyDark,
                                            contentColor = if (session.isLiveNow) Color.White else GuruAmber
                                        ),
                                        border = if (session.isLiveNow) null else BorderStroke(1.dp, GuruAmber),
                                        modifier = Modifier
                                            .height(38.dp)
                                            .testTag("join_live_btn_${session.id}")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CastConnected,
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = if (session.isLiveNow) "Join Live" else "View Class",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
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
