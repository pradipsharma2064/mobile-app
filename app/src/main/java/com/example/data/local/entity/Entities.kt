package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_questions")
data class BookmarkedQuestion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val questionText: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctOptionIndex: Int,
    val explanation: String,
    val subject: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_attempts")
data class QuizAttempt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val examTitle: String,
    val category: String,
    val score: Double,
    val maxScore: Double,
    val correctCount: Int,
    val incorrectCount: Int,
    val unansweredCount: Int,
    val accuracyPercentage: Double,
    val timeTakenSeconds: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "downloaded_notes")
data class DownloadedNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val category: String,
    val author: String,
    val fileSize: String,
    val pagesCount: Int,
    val contentSnippet: String,
    val downloadedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey
    val id: Int = 1,
    val name: String = "Aashish Sharma",
    val email: String = "aashish.loksewa@gmail.com",
    val targetExam: String = "Loksewa Section Officer (शाखा अधिकृत)",
    val streakDays: Int = 7,
    val gems: Int = 450,
    val studyMinutes: Int = 340,
    val questionsSolved: Int = 285
)

@Entity(tableName = "class_reminders")
data class ClassReminder(
    @PrimaryKey
    val classId: String,
    val title: String,
    val instructorName: String,
    val scheduledTime: String,
    val examCategory: String,
    val setTimestamp: Long = System.currentTimeMillis()
)

