package com.example.data.repository

import com.example.data.local.dao.GuruDao
import com.example.data.local.entity.BookmarkedQuestion
import com.example.data.local.entity.ClassReminder
import com.example.data.local.entity.DownloadedNote
import com.example.data.local.entity.QuizAttempt
import com.example.data.local.entity.UserProfile
import kotlinx.coroutines.flow.Flow

class GuruRepository(private val dao: GuruDao) {

    val bookmarks: Flow<List<BookmarkedQuestion>> = dao.getAllBookmarks()
    val attempts: Flow<List<QuizAttempt>> = dao.getAllAttempts()
    val downloadedNotes: Flow<List<DownloadedNote>> = dao.getAllDownloadedNotes()
    val userProfile: Flow<UserProfile?> = dao.getUserProfile()
    val reminders: Flow<List<ClassReminder>> = dao.getAllReminders()

    suspend fun toggleReminder(reminder: ClassReminder, isCurrentlySet: Boolean) {
        if (isCurrentlySet) {
            dao.deleteReminder(reminder.classId)
        } else {
            dao.insertReminder(reminder)
        }
    }

    suspend fun toggleBookmark(question: BookmarkedQuestion, currentlyBookmarked: Boolean) {
        if (currentlyBookmarked) {
            dao.deleteBookmarkByText(question.questionText)
        } else {
            dao.insertBookmark(question)
        }
    }

    suspend fun recordQuizAttempt(attempt: QuizAttempt) {
        dao.insertAttempt(attempt)
    }

    suspend fun saveNoteDownload(note: DownloadedNote) {
        dao.insertDownloadedNote(note)
    }

    suspend fun removeDownloadedNote(title: String) {
        dao.deleteDownloadedNote(title)
    }

    suspend fun updateUserProfile(profile: UserProfile) {
        dao.saveUserProfile(profile)
    }
}
