package com.example.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayLesson
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayLesson
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GuruAmber
import com.example.ui.theme.GuruNavyDark
import com.example.ui.theme.GuruNavySurface
import com.example.ui.theme.GuruTextMuted
import com.example.ui.theme.GuruTextPrimary
import com.example.ui.viewmodel.Screen

@Composable
fun GuruBottomNav(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = GuruNavyDark,
        tonalElevation = 8.dp,
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .testTag("guru_bottom_nav")
    ) {
        // Home
        NavigationBarItem(
            selected = currentScreen == Screen.HOME,
            onClick = { onNavigate(Screen.HOME) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == Screen.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Home", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GuruNavyDark,
                selectedTextColor = GuruAmber,
                indicatorColor = GuruAmber,
                unselectedIconColor = GuruTextMuted,
                unselectedTextColor = GuruTextMuted
            ),
            modifier = Modifier.testTag("nav_item_home")
        )

        // Courses
        NavigationBarItem(
            selected = currentScreen == Screen.COURSES || currentScreen == Screen.COURSE_DETAIL,
            onClick = { onNavigate(Screen.COURSES) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == Screen.COURSES || currentScreen == Screen.COURSE_DETAIL)
                        Icons.Filled.PlayLesson else Icons.Outlined.PlayLesson,
                    contentDescription = "Courses",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Courses", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GuruNavyDark,
                selectedTextColor = GuruAmber,
                indicatorColor = GuruAmber,
                unselectedIconColor = GuruTextMuted,
                unselectedTextColor = GuruTextMuted
            ),
            modifier = Modifier.testTag("nav_item_courses")
        )

        // Mock Tests (Guru Pariksha)
        NavigationBarItem(
            selected = currentScreen == Screen.MOCK_TEST_INTRO ||
                    currentScreen == Screen.MOCK_TEST_ACTIVE ||
                    currentScreen == Screen.MOCK_TEST_RESULT,
            onClick = { onNavigate(Screen.MOCK_TEST_INTRO) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == Screen.MOCK_TEST_INTRO ||
                        currentScreen == Screen.MOCK_TEST_ACTIVE ||
                        currentScreen == Screen.MOCK_TEST_RESULT
                    ) Icons.Filled.Quiz else Icons.Outlined.Quiz,
                    contentDescription = "Mock Tests",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Pariksha", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GuruNavyDark,
                selectedTextColor = GuruAmber,
                indicatorColor = GuruAmber,
                unselectedIconColor = GuruTextMuted,
                unselectedTextColor = GuruTextMuted
            ),
            modifier = Modifier.testTag("nav_item_pariksha")
        )

        // AI Doubt Solver (Gemini AI Tutor)
        NavigationBarItem(
            selected = currentScreen == Screen.ASK_GURU,
            onClick = { onNavigate(Screen.ASK_GURU) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == Screen.ASK_GURU)
                        Icons.Filled.Psychology else Icons.Outlined.Psychology,
                    contentDescription = "AI Doubt Solver",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("AI Solver", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GuruNavyDark,
                selectedTextColor = GuruAmber,
                indicatorColor = GuruAmber,
                unselectedIconColor = GuruTextMuted,
                unselectedTextColor = GuruTextMuted
            ),
            modifier = Modifier.testTag("nav_item_ai_doubt_solver")
        )

        // Library (E-Notes & PDFs)
        NavigationBarItem(
            selected = currentScreen == Screen.LIBRARY || currentScreen == Screen.NOTE_READER,
            onClick = { onNavigate(Screen.LIBRARY) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == Screen.LIBRARY || currentScreen == Screen.NOTE_READER)
                        Icons.Filled.AutoStories else Icons.Outlined.AutoStories,
                    contentDescription = "Library",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Library", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = GuruNavyDark,
                selectedTextColor = GuruAmber,
                indicatorColor = GuruAmber,
                unselectedIconColor = GuruTextMuted,
                unselectedTextColor = GuruTextMuted
            ),
            modifier = Modifier.testTag("nav_item_library")
        )
    }
}
