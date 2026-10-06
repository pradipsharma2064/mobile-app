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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.TextDecrease
import androidx.compose.material.icons.filled.TextIncrease
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.StudyMaterial
import com.example.data.sample.SampleData
import com.example.ui.theme.GuruAmber
import com.example.ui.theme.GuruAmberDark
import com.example.ui.theme.GuruAmberLight
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
fun LibraryScreen(
    viewModel: GuruViewModel,
    isNepali: Boolean,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: All Materials, 1: Downloaded Offline
    val downloadedNotes by viewModel.downloadedNotes.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("library_screen")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = if (isNepali) "हाम्रो सपना ई-पुस्तकालय र अध्ययन सामग्री" else "Hamro Sapana E-Library & Study Hub",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = GuruTextPrimary
            )
            Text(
                text = if (isNepali) "ऐन, नियम, समसामयिक सार र विगतका प्रश्नोत्तरहरू" else "Nepal Constitution, Acts, Current Affairs & Question Banks",
                style = MaterialTheme.typography.bodySmall,
                color = GuruTextSecondary
            )
        }

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = GuruNavySurface,
            contentColor = GuruAmber,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = GuruAmber
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("E-Books & Acts", fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Offline Saved (${downloadedNotes.size})", fontWeight = FontWeight.Bold) }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            if (selectedTab == 0) {
                items(SampleData.studyMaterials) { material ->
                    val isDownloaded = downloadedNotes.any { it.title == material.title }

                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                        border = BorderStroke(1.dp, GuruNavyBorder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.openNote(material) }
                            .testTag("material_item_${material.id}")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(GuruSky.copy(alpha = 0.2f))
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = material.category,
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                        color = GuruSky
                                    )
                                }

                                if (material.isFree) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(GuruEmerald.copy(alpha = 0.2f))
                                            .padding(horizontal = 8.dp, vertical = 3.dp)
                                    ) {
                                        Text(
                                            text = "FREE ACCESS",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = GuruEmerald
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = material.title,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = GuruTextPrimary
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = material.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = GuruTextSecondary,
                                maxLines = 2
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "📄 ${material.pages} Pages • ${material.fileSize}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GuruTextMuted
                                )

                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    if (isDownloaded) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(GuruEmerald.copy(alpha = 0.2f))
                                                .padding(horizontal = 10.dp, vertical = 6.dp)
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = GuruEmerald,
                                                    modifier = Modifier.size(14.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(
                                                    text = "Saved",
                                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                                    color = GuruEmerald
                                                )
                                            }
                                        }
                                    } else {
                                        Button(
                                            onClick = { viewModel.downloadNote(material) },
                                            shape = RoundedCornerShape(8.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = GuruNavySurface,
                                                contentColor = GuruAmber
                                            ),
                                            modifier = Modifier.height(34.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Download,
                                                contentDescription = null,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "Save Offline",
                                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                                            )
                                        }
                                    }

                                    Button(
                                        onClick = { viewModel.openNote(material) },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = GuruAmber,
                                            contentColor = GuruNavyDark
                                        ),
                                        modifier = Modifier.height(34.dp)
                                    ) {
                                        Text(
                                            text = "Read",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                // Downloaded offline tab
                if (downloadedNotes.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.AutoStories,
                                    contentDescription = null,
                                    tint = GuruTextMuted,
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "No offline notes saved yet",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = GuruTextPrimary
                                )
                                Text(
                                    text = "Tap 'Save Offline' on any study material to read without internet.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = GuruTextMuted
                                )
                            }
                        }
                    }
                } else {
                    items(downloadedNotes) { note ->
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                            border = BorderStroke(1.dp, GuruNavyBorder),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = note.title,
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                        color = GuruTextPrimary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "${note.category} • ${note.pagesCount} Pages • ${note.fileSize}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = GuruTextMuted
                                    )
                                }

                                IconButton(onClick = { viewModel.deleteDownloadedNote(note.title) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Remove",
                                        tint = GuruRose
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

@Composable
fun NoteReaderScreen(
    note: StudyMaterial?,
    viewModel: GuruViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (note == null) return

    var fontSize by remember { mutableStateOf(16.sp) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("note_reader_screen")
    ) {
        // Reader Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(GuruNavySurface)
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = GuruTextPrimary
                )
            }

            Text(
                text = "Guru Reader",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = GuruTextPrimary
            )

            Row {
                IconButton(onClick = {
                    if (fontSize.value > 12) fontSize = (fontSize.value - 2).sp
                }) {
                    Icon(imageVector = Icons.Default.TextDecrease, contentDescription = "Smaller", tint = GuruTextPrimary)
                }
                IconButton(onClick = {
                    if (fontSize.value < 26) fontSize = (fontSize.value + 2).sp
                }) {
                    Icon(imageVector = Icons.Default.TextIncrease, contentDescription = "Larger", tint = GuruTextPrimary)
                }
            }
        }

        // Reading Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = GuruAmber
                )
                Text(
                    text = "${note.category} • Official Study Publication",
                    style = MaterialTheme.typography.labelSmall,
                    color = GuruSky
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                    border = BorderStroke(1.dp, GuruNavyBorder)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = note.readSnippet,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = fontSize,
                                lineHeight = (fontSize.value * 1.5).sp
                            ),
                            color = GuruTextPrimary
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Guru Highlighted Exam Notes:\n" +
                                    "• Remember that for Section Officer paper 2, conceptual clarity and relevant legal sections carry 60% of evaluation marks.\n" +
                                    "• Revise this chapter 24 hours before the mock exam for maximum retention.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = (fontSize.value - 2).sp,
                                lineHeight = (fontSize.value * 1.4).sp
                            ),
                            color = GuruAmberLight
                        )
                    }
                }
            }
        }
    }
}
