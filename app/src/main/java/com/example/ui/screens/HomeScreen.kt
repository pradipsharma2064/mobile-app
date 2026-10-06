package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.entity.UserProfile
import com.example.data.sample.SampleData
import com.example.ui.components.CourseCard
import com.example.ui.components.ExamCategoryCard
import com.example.ui.components.LiveClassCard
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
import com.example.ui.viewmodel.Screen

@Composable
fun HomeScreen(
    viewModel: GuruViewModel,
    profile: UserProfile?,
    isNepali: Boolean,
    modifier: Modifier = Modifier
) {
    val selectedCat by viewModel.selectedCategory.collectAsState()
    val liveClasses = SampleData.liveClasses
    val courses = SampleData.courses

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("home_screen_content"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Welcome & Target Goal Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isNepali) "नमस्ते, ${profile?.name ?: "साथी"}!" else "Namaste, ${profile?.name ?: "Learner"}! 👋",
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = GuruTextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = profile?.targetExam ?: "Loksewa Section Officer Prep",
                            style = MaterialTheme.typography.bodySmall,
                            color = GuruAmber
                        )
                    }

                    // Target Change Button
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(GuruNavySurface)
                            .clickable { viewModel.navigateTo(Screen.PROFILE) }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("change_exam_btn")
                    ) {
                        Text(
                            text = if (isNepali) "लक्ष्य परिवर्तन" else "Change Goal",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = GuruSky
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Streak & Daily Study Tracker with Tiffany Blue & White Gradient
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                    border = BorderStroke(1.5.dp, GuruAmber),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("daily_streak_card")
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFE0F7F5),
                                        Color(0xFFFFFFFF),
                                        Color(0xFFF2FBF9)
                                    )
                                )
                            )
                            .padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(GuruOrange.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocalFireDepartment,
                                        contentDescription = "Streak",
                                        tint = GuruOrange,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = if (isNepali) "${profile?.streakDays ?: 7} दिनको लगातार अध्ययन" else "${profile?.streakDays ?: 7}-Day Study Streak!",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                        color = GuruTextPrimary
                                    )
                                    Text(
                                        text = if (isNepali) "आजको लक्ष्य: ४५ मिनेट र २० प्रश्न" else "Today's Target: 45 mins & 20 questions",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = GuruTextMuted
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(GuruEmerald.copy(alpha = 0.2f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = if (isNepali) "सक्रिय" else "ACTIVE",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                    color = GuruEmerald
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Progress bar
                        LinearProgressIndicator(
                            progress = { 0.65f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(7.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = GuruAmber,
                            trackColor = GuruNavyDark
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "⏱️ ${profile?.studyMinutes ?: 340} mins studied",
                                style = MaterialTheme.typography.bodySmall,
                                color = GuruTextSecondary
                            )
                            Text(
                                text = "🎯 ${profile?.questionsSolved ?: 285} questions solved",
                                style = MaterialTheme.typography.bodySmall,
                                color = GuruTextSecondary
                            )
                        }
                    }
                }
            }
        }

        // Hero Banners Carousel (Visual assets)
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Banner 1: Loksewa Masterclass
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                            border = BorderStroke(1.dp, GuruAmber.copy(alpha = 0.4f)),
                            modifier = Modifier
                                .width(310.dp)
                                .height(160.dp)
                                .clickable {
                                    val course = courses.first()
                                    viewModel.selectCourse(course)
                                }
                                .testTag("hero_banner_loksewa")
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(id = R.drawable.banner_loksewa),
                                    contentDescription = "Loksewa Masterclass",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                // Gradient Overlay for photo legibility
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    Color(0xFF042B29).copy(alpha = 0.92f)
                                                ),
                                                startY = 50f
                                            )
                                        )
                                )
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(14.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(GuruAmber)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "ADMISSION OPEN 2082/83",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                            color = Color.White
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Loksewa Section Officer Live Masterclass",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                        color = Color.White
                                    )
                                    Text(
                                        text = "With Under Secretary Santosh Khadka & Panel",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF81D8D0)
                                    )
                                }
                            }
                        }
                    }

                    // Banner 2: Hamro Sapana Pariksha Championship
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                            border = BorderStroke(1.dp, GuruAmberLight.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .width(310.dp)
                                .height(160.dp)
                                .clickable { viewModel.startMockExam() }
                                .testTag("hero_banner_pariksha")
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(id = R.drawable.banner_pariksha),
                                    contentDescription = "Hamro Sapana Mock Exam",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    Color(0xFF042B29).copy(alpha = 0.92f)
                                                ),
                                                startY = 50f
                                            )
                                        )
                                )
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(14.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(GuruAmber)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "ALL NEPAL MOCK EXAM",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                            color = Color.White
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Hamro Sapana Championship 2026",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Real -20% Loksewa marking • Win Scholarship",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF81D8D0)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Primary Structured Entry Point: Exam Category Clickable Cards
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isNepali) "तयारी विधा छनोट (Exam Pathways)" else "Select Exam Category",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 19.sp
                                ),
                                color = GuruTextPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(GuruAmber)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "5 PATHWAYS",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 9.sp
                                    ),
                                    color = GuruNavyDark
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = if (isNepali) "आफ्नो लक्ष्य अनुसारको पाठ्यक्रम, प्रत्यक्ष कक्षा र परीक्षा सेट" else "Structured entry point for Loksewa, Banking, TSC, Medical & Engineering",
                            style = MaterialTheme.typography.bodySmall,
                            color = GuruTextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // 5 Clickable Cards: Loksewa, Banking, TSC, Medical, Engineering
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("exam_categories_list"),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SampleData.examCategoryEntries.forEach { catEntry ->
                        val isSelected = selectedCat == catEntry.categoryName
                        ExamCategoryCard(
                            category = catEntry,
                            isNepali = isNepali,
                            isSelected = isSelected,
                            onClick = {
                                viewModel.setSelectedCategory(catEntry.categoryName)
                                viewModel.navigateTo(Screen.COURSES)
                            }
                        )
                    }
                }
            }
        }

        // Quick Actions 6-Grid
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = if (isNepali) "हाम्रो सपना अध्ययन कक्ष" else "Hamro Sapana Learning Suite",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = GuruTextPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionTile(
                        title = if (isNepali) "प्रत्यक्ष कक्षा" else "Live Classes",
                        subtitle = "🔴 1 Live Now",
                        icon = Icons.Default.Videocam,
                        iconTint = GuruRose,
                        onClick = {
                            viewModel.navigateTo(Screen.LIVE_CLASSES_LIST)
                        },
                        modifier = Modifier.weight(1f),
                        testTag = "quick_live_classes"
                    )
                    QuickActionTile(
                        title = if (isNepali) "सपना परीक्षा" else "Sapana Pariksha",
                        subtitle = "Mock Test",
                        icon = Icons.Default.Quiz,
                        iconTint = GuruAmber,
                        onClick = { viewModel.navigateTo(Screen.MOCK_TEST_INTRO) },
                        modifier = Modifier.weight(1f),
                        testTag = "quick_guru_pariksha"
                    )
                    QuickActionTile(
                        title = if (isNepali) "दैनिक प्रश्न" else "Daily Quiz",
                        subtitle = "10 Questions",
                        icon = Icons.Default.AutoAwesome,
                        iconTint = GuruEmerald,
                        onClick = { viewModel.startMockExam() },
                        modifier = Modifier.weight(1f),
                        testTag = "quick_daily_quiz"
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionTile(
                        title = if (isNepali) "ई-पुस्तकालय" else "E-Library",
                        subtitle = "Notes & Acts",
                        icon = Icons.Default.AutoStories,
                        iconTint = GuruSky,
                        onClick = { viewModel.navigateTo(Screen.LIBRARY) },
                        modifier = Modifier.weight(1f),
                        testTag = "quick_library"
                    )
                    QuickActionTile(
                        title = if (isNepali) "सपना ए.आई." else "Sapana AI",
                        subtitle = "Doubt Solver",
                        icon = Icons.Default.Psychology,
                        iconTint = GuruAmberLight,
                        onClick = { viewModel.navigateTo(Screen.ASK_GURU) },
                        modifier = Modifier.weight(1f),
                        testTag = "quick_ask_guru"
                    )
                    QuickActionTile(
                        title = if (isNepali) "उत्कृष्ट श्रेणी" else "Leaderboard",
                        subtitle = "Rankings",
                        icon = Icons.Default.Leaderboard,
                        iconTint = GuruOrange,
                        onClick = { viewModel.navigateTo(Screen.LEADERBOARD) },
                        modifier = Modifier.weight(1f),
                        testTag = "quick_leaderboard"
                    )
                }
            }
        }

        // Daily Free Quiz Banner
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GuruNavySurface),
                    border = BorderStroke(1.dp, GuruEmerald.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.startMockExam() }
                        .testTag("daily_quiz_card")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(GuruEmerald.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = GuruEmerald,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = if (isNepali) "आजको हाम्रो सपना दैनिक अभ्यास" else "Today's Hamro Sapana Quiz",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = GuruTextPrimary
                                )
                                Text(
                                    text = if (isNepali) "नेपालको भूगोल, संविधान र IQ • १० प्रश्नहरू" else "Nepal GK, Constitution & IQ • 10 Qs",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = GuruTextSecondary
                                )
                            }
                        }

                        Button(
                            onClick = { viewModel.startMockExam() },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GuruEmerald,
                                contentColor = GuruNavyDark
                            ),
                            modifier = Modifier.height(38.dp)
                        ) {
                            Text(
                                text = if (isNepali) "सुरु गर्नुहोस्" else "Start",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }

        // Live Now Section
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isNepali) "प्रत्यक्ष कक्षाहरू" else "Ongoing Live Classes",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = GuruTextPrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(GuruRose)
                        )
                    }

                    Text(
                        text = if (isNepali) "सबै हेर्नुहोस्" else "View All",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = GuruSky,
                        modifier = Modifier.clickable { viewModel.navigateTo(Screen.LIVE_CLASSES_LIST) }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                val live = liveClasses.first()
                LiveClassCard(
                    liveClass = live,
                    onJoinLive = { viewModel.openLiveClass(live) }
                )
            }
        }

        // Exam Countdown Timers
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = if (isNepali) "आगामी परीक्षा तालिका" else "Upcoming Exam Deadlines",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = GuruTextPrimary
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SampleData.examCountdowns.forEach { (exam, daysLeft) ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                            border = BorderStroke(1.dp, GuruNavyBorder),
                            modifier = Modifier.width(220.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = exam,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                    color = GuruTextPrimary,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(GuruAmber.copy(alpha = 0.15f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AccessTime,
                                        contentDescription = null,
                                        tint = GuruAmber,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = daysLeft,
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                        color = GuruAmber
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Recommended Master Courses
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedCat == "All Exams") {
                            if (isNepali) "लोकप्रिय हाम्रो सपना प्याकेजहरू" else "Popular Hamro Sapana Packages"
                        } else {
                            if (isNepali) "$selectedCat पाठ्यक्रमहरू" else "$selectedCat Packages"
                        },
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = GuruTextPrimary
                    )
                    Text(
                        text = if (isNepali) "सबै हेर्नुहोस्" else "View All",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = GuruSky,
                        modifier = Modifier.clickable { viewModel.navigateTo(Screen.COURSES) }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        val displayedCourses = if (selectedCat == "All Exams") courses
            else courses.filter { it.category == selectedCat }.ifEmpty { courses }

        items(displayedCourses) { course ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                CourseCard(
                    course = course,
                    onClick = { viewModel.selectCourse(course) }
                )
            }
        }
    }
}

@Composable
fun QuickActionTile(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = ""
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
        border = BorderStroke(1.dp, GuruNavyBorder),
        modifier = modifier
            .clickable { onClick() }
            .testTag(testTag)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconTint.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                ),
                color = GuruTextPrimary,
                maxLines = 1
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = GuruTextMuted,
                maxLines = 1
            )
        }
    }
}
