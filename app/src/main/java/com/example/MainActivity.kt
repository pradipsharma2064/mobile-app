package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.ui.components.GuruBottomNav
import com.example.ui.components.GuruTopBar
import com.example.ui.screens.AskGuruScreen
import com.example.ui.screens.CourseDetailScreen
import com.example.ui.screens.CoursesScreen
import com.example.ui.screens.ExamResultScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LeaderboardScreen
import com.example.ui.screens.LibraryScreen
import com.example.ui.screens.LiveClassesScreen
import com.example.ui.screens.LiveClassroomScreen
import com.example.ui.screens.MockExamActiveScreen
import com.example.ui.screens.MockExamIntroScreen
import com.example.ui.screens.NoteReaderScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.theme.AmbitionGuruTheme
import com.example.ui.theme.GuruNavyDark
import com.example.ui.viewmodel.GuruViewModel
import com.example.ui.viewmodel.Screen

class MainActivity : ComponentActivity() {
    private val viewModel: GuruViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmbitionGuruTheme(darkTheme = true) {
                MainApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainApp(viewModel: GuruViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    val isNepali by viewModel.isNepaliLanguage.collectAsState()
    val selectedCourse by viewModel.selectedCourse.collectAsState()
    val selectedNote by viewModel.selectedNote.collectAsState()
    val activeLiveClass by viewModel.activeLiveClass.collectAsState()

    val showTopBar = currentScreen in listOf(
        Screen.HOME,
        Screen.COURSES,
        Screen.MOCK_TEST_INTRO,
        Screen.LIBRARY
    )

    val showBottomBar = currentScreen in listOf(
        Screen.HOME,
        Screen.COURSES,
        Screen.MOCK_TEST_INTRO,
        Screen.LIBRARY,
        Screen.ASK_GURU
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("ambition_guru_scaffold"),
        containerColor = GuruNavyDark,
        topBar = {
            if (showTopBar) {
                GuruTopBar(
                    streakDays = userProfile?.streakDays ?: 7,
                    gems = userProfile?.gems ?: 450,
                    isNepali = isNepali,
                    onToggleLanguage = { viewModel.toggleLanguage() },
                    onProfileClick = { viewModel.navigateTo(Screen.PROFILE) }
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                GuruBottomNav(
                    currentScreen = currentScreen,
                    onNavigate = { screen -> viewModel.navigateTo(screen) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                Screen.HOME -> {
                    HomeScreen(
                        viewModel = viewModel,
                        profile = userProfile,
                        isNepali = isNepali
                    )
                }

                Screen.COURSES -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    CoursesScreen(
                        viewModel = viewModel,
                        isNepali = isNepali
                    )
                }

                Screen.COURSE_DETAIL -> {
                    BackHandler { viewModel.goBack() }
                    CourseDetailScreen(
                        course = selectedCourse,
                        viewModel = viewModel,
                        isNepali = isNepali,
                        onBack = { viewModel.goBack() }
                    )
                }

                Screen.MOCK_TEST_INTRO -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    MockExamIntroScreen(
                        viewModel = viewModel,
                        isNepali = isNepali
                    )
                }

                Screen.MOCK_TEST_ACTIVE -> {
                    BackHandler { viewModel.goBack() }
                    MockExamActiveScreen(
                        viewModel = viewModel,
                        isNepali = isNepali
                    )
                }

                Screen.MOCK_TEST_RESULT -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    ExamResultScreen(
                        viewModel = viewModel,
                        isNepali = isNepali
                    )
                }

                Screen.LIBRARY -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    LibraryScreen(
                        viewModel = viewModel,
                        isNepali = isNepali
                    )
                }

                Screen.NOTE_READER -> {
                    BackHandler { viewModel.goBack() }
                    NoteReaderScreen(
                        note = selectedNote,
                        viewModel = viewModel,
                        onBack = { viewModel.goBack() }
                    )
                }

                Screen.ASK_GURU -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    AskGuruScreen(
                        viewModel = viewModel,
                        isNepali = isNepali
                    )
                }

                Screen.LEADERBOARD -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    LeaderboardScreen(
                        viewModel = viewModel,
                        isNepali = isNepali,
                        onBack = { viewModel.navigateTo(Screen.HOME) }
                    )
                }

                Screen.PROFILE -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    ProfileScreen(
                        viewModel = viewModel,
                        profile = userProfile,
                        isNepali = isNepali,
                        onBack = { viewModel.navigateTo(Screen.HOME) }
                    )
                }

                Screen.LIVE_CLASSROOM -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    LiveClassroomScreen(
                        liveClass = activeLiveClass,
                        viewModel = viewModel,
                        onBack = { viewModel.navigateTo(Screen.HOME) }
                    )
                }

                Screen.LIVE_CLASSES_LIST -> {
                    BackHandler { viewModel.navigateTo(Screen.HOME) }
                    LiveClassesScreen(
                        viewModel = viewModel,
                        isNepali = isNepali,
                        onBack = { viewModel.navigateTo(Screen.HOME) }
                    )
                }
            }
        }
    }
}
