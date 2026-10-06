package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.data.sample.SampleData
import com.example.ui.theme.GuruAmber
import com.example.ui.theme.GuruAmberLight
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
fun AskGuruScreen(
    viewModel: GuruViewModel,
    isNepali: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val messages by viewModel.chatMessages.collectAsState()
    val isTyping by viewModel.isGuruTyping.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size, isTyping) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    val quickQuestions = listOf(
        "Loksewa negative marking formula",
        "Fundamental rights mnemonic (Art 16-48)",
        "NRB Act 2058 key provisions",
        "IQ clock angle shortcut trick",
        "CEE medical physics formula list",
        "IOE integration shortcut"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GuruNavyDark)
            .testTag("ai_doubt_solver_screen")
    ) {
        // Top Header with Gemini AI Badge and Clear action
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GuruNavySurface)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(GuruAmber.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = "AI Doubt Solver",
                            tint = GuruAmber,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isNepali) "हाम्रो सपना ए.आई. शंका समाधान" else "Hamro Sapana AI Solver",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
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
                                    text = "GEMINI 3.5 FLASH",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 9.sp
                                    ),
                                    color = GuruNavyDark
                                )
                            }
                        }
                        Text(
                            text = if (isNepali) "२४/७ गुगल जेमिनाई मार्फत तत्काल उत्तर र सूत्र" else "Live Doubt Solver powered by Google Gemini",
                            style = MaterialTheme.typography.bodySmall,
                            color = GuruTextSecondary
                        )
                    }
                }

                // Reset chat button
                IconButton(
                    onClick = { viewModel.clearChat() },
                    modifier = Modifier.testTag("clear_chat_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reset Conversation",
                        tint = GuruTextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Context Exam Filter selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Context:",
                    style = MaterialTheme.typography.labelSmall,
                    color = GuruTextMuted
                )

                SampleData.examCategories.forEach { category ->
                    val isSelected = selectedCategory == category
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) GuruAmber else GuruNavyDark)
                            .clickable { viewModel.setSelectedCategory(category) }
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .testTag("ai_category_chip_$category")
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

        // Quick Suggestions Horizontal Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            quickQuestions.forEach { prompt ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(GuruNavyCard)
                        .clickable { viewModel.askGuru(prompt) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .testTag("quick_prompt_${prompt.take(10)}")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = GuruAmber,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = prompt,
                            style = MaterialTheme.typography.labelSmall,
                            color = GuruTextPrimary
                        )
                    }
                }
            }
        }

        // Chat Message Stream
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .testTag("ai_chat_message_list"),
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(messages) { msg ->
                val isUser = msg.isFromUser
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
                ) {
                    if (!isUser) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(GuruAmber),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = GuruNavyDark,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Card(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = if (isUser) 16.dp else 4.dp,
                            bottomEnd = if (isUser) 4.dp else 16.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isUser) GuruAmber else GuruNavyCard
                        ),
                        border = if (!isUser) BorderStroke(1.dp, GuruNavyBorder) else null,
                        modifier = Modifier.fillMaxWidth(if (isUser) 0.82f else 0.88f)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            if (!isUser) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Hamro Sapana AI",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = GuruAmberLight
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(GuruEmerald.copy(alpha = 0.2f))
                                                .padding(horizontal = 4.dp, vertical = 1.dp)
                                        ) {
                                            Text(
                                                text = "GEMINI",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 8.sp
                                                ),
                                                color = GuruEmerald
                                            )
                                        }
                                    }

                                    // Copy to clipboard
                                    IconButton(
                                        onClick = {
                                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                            val clip = ClipData.newPlainText("Guru Solution", msg.message)
                                            clipboard.setPrimaryClip(clip)
                                            Toast.makeText(context, "Solution copied to clipboard!", Toast.LENGTH_SHORT).show()
                                        },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ContentCopy,
                                            contentDescription = "Copy Solution",
                                            tint = GuruTextMuted,
                                            modifier = Modifier.size(15.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                            }

                            Text(
                                text = msg.message,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    lineHeight = 22.sp,
                                    fontSize = 14.sp
                                ),
                                color = if (isUser) GuruNavyDark else GuruTextPrimary
                            )
                        }
                    }
                }
            }

            if (isTyping) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(GuruAmber),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = GuruNavyDark,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = GuruNavyCard),
                            border = BorderStroke(1.dp, GuruNavyBorder)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    color = GuruAmber,
                                    strokeWidth = 2.dp
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Gemini AI is analyzing syllabus & formulating response...",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GuruTextMuted
                                )
                            }
                        }
                    }
                }
            }
        }

        // Dedicated Bottom Text Input Field & Send Action
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(GuruNavySurface)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = {
                    Text(
                        text = if (isNepali) "कुनै पनि शंका वा प्रश्न सोध्नुहोस्..." else "Ask any doubt, question, or formula...",
                        color = GuruTextMuted,
                        fontSize = 13.sp
                    )
                },
                trailingIcon = {
                    if (inputText.isNotBlank()) {
                        IconButton(onClick = { inputText = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Input",
                                tint = GuruTextMuted
                            )
                        }
                    }
                },
                maxLines = 3,
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = GuruNavyDark,
                    unfocusedContainerColor = GuruNavyDark,
                    focusedBorderColor = GuruAmber,
                    unfocusedBorderColor = GuruNavyBorder,
                    focusedTextColor = GuruTextPrimary,
                    unfocusedTextColor = GuruTextPrimary
                ),
                modifier = Modifier
                    .weight(1f)
                    .testTag("ai_doubt_input_field")
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (inputText.isNotBlank()) {
                        viewModel.askGuru(inputText)
                        inputText = ""
                    }
                },
                enabled = inputText.isNotBlank() && !isTyping,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = GuruAmber,
                    contentColor = GuruNavyDark,
                    disabledContainerColor = GuruNavySurface,
                    disabledContentColor = GuruTextMuted
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(46.dp)
                    .testTag("ai_doubt_send_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send to Gemini",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
