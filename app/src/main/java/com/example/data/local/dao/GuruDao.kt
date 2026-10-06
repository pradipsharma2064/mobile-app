package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.BookmarkedQuestion
import com.example.data.local.entity.ClassReminder
import com.example.data.local.entity.DownloadedNote
import com.example.data.local.entity.QuizAttempt
import com.example.data.local.entity.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface GuruDao {
    // Class Reminders
    @Query("SELECT * FROM class_reminders ORDER BY setTimestamp DESC")
    fun getAllReminders(): Flow<List<ClassReminder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ClassReminder)

    @Query("DELETE FROM class_reminders WHERE classId = :classId")
    suspend fun deleteReminder(classId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM class_reminders WHERE classId = :classId)")
    fun isReminderSet(classId: String): Flow<Boolean>

    // Bookmarks
    @Query("SELECT * FROM bookmarked_questions ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkedQuestion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(question: BookmarkedQuestion)

    @Query("DELETE FROM bookmarked_questions WHERE questionText = :text")
    suspend fun deleteBookmarkByText(text: String)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_questions WHERE questionText = :text)")
    fun isBookmarked(text: String): Flow<Boolean>

    // Quiz Attempts
    @Query("SELECT * FROM quiz_attempts ORDER BY timestamp DESC")
    fun getAllAttempts(): Flow<List<QuizAttempt>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: QuizAttempt)

    // Downloaded Notes
    @Query("SELECT * FROM downloaded_notes ORDER BY downloadedTimestamp DESC")
    fun getAllDownloadedNotes(): Flow<List<DownloadedNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedNote(note: DownloadedNote)

    @Query("DELETE FROM downloaded_notes WHERE title = :title")
    suspend fun deleteDownloadedNote(title: String)

    @Query("SELECT EXISTS(SELECT 1 FROM downloaded_notes WHERE title = :title)")
    fun isNoteDownloaded(title: String): Flow<Boolean>

    // User Profile
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfile?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfile)
}
