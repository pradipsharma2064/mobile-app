package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import com.example.data.sample.SampleData
import com.example.ui.components.CourseCard
import com.example.ui.theme.GuruAmber
import com.example.ui.theme.GuruNavyBorder
import com.example.ui.theme.GuruNavyDark
import com.example.ui.theme.GuruNavySurface
import com.example.ui.theme.GuruTextMuted
import com.example.ui.theme.GuruTextPrimary
import com.example.ui.theme.GuruTextSecondary
import com.example.ui.viewmodel.GuruViewModel

@Composable
fun CoursesScreen(
    viewModel: GuruViewModel,
    isNepali: Boolean,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    val selectedCat by viewModel.selectedCategory.collectAsState()

    val filteredCourses = remember(searchQuery, selectedCat) {
        SampleData.courses.filter { course ->
            val matchesCategory = selectedCat == "All Exams" || course.category == selectedCat
            val matchesSearch = searchQuery.isBlank() ||
                    course.title.contains(searchQuery, ignoreCase = true) ||
                    course.instructor.contains(searchQuery, ignoreCase = true) ||
                    course.category.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("courses_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Search & Filter header
        item {
            Column {
                Text(
                    text = if (isNepali) "गुरु कक्षाहरू र पाठ्यक्रम" else "Guru Courses & Classrooms",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = GuruTextPrimary
                )
                Text(
                    text = if (isNepali) "नेपालका उत्कृष्ट प्रशिक्षकहरूद्वारा सञ्चालित" else "Prepared by top civil servants, doctors, bankers & Gurus",
                    style = MaterialTheme.typography.bodySmall,
                    color = GuruTextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            text = if (isNepali) "पाठ्यक्रम वा प्रशिक्षक खोज्नुहोस्..." else "Search Loksewa, Banking, TSC...",
                            color = GuruTextMuted
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = GuruAmber
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotBlank()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear",
                                    tint = GuruTextMuted
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = GuruNavySurface,
                        unfocusedContainerColor = GuruNavySurface,
                        focusedBorderColor = GuruAmber,
                        unfocusedBorderColor = GuruNavyBorder,
                        focusedTextColor = GuruTextPrimary,
                        unfocusedTextColor = GuruTextPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("courses_search_bar")
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Categories Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SampleData.examCategories.forEach { category ->
                        val isSelected = category == selectedCat
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (isSelected) GuruAmber else GuruNavySurface)
                                .clickable { viewModel.setSelectedCategory(category) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .testTag("cat_filter_$category")
                        ) {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = if (isSelected) GuruNavyDark else GuruTextPrimary
                            )
                        }
                    }
                }
            }
        }

        if (filteredCourses.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isNepali) "कुनै पाठ्यक्रम भेटिएन" else "No courses found matching your search.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = GuruTextMuted
                    )
                }
            }
        } else {
            items(filteredCourses) { course ->
                CourseCard(
                    course = course,
                    onClick = { viewModel.selectCourse(course) }
                )
            }
        }
    }
}
