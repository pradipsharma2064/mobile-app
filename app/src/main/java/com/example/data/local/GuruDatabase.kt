package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.GuruDao
import com.example.data.local.entity.BookmarkedQuestion
import com.example.data.local.entity.ClassReminder
import com.example.data.local.entity.DownloadedNote
import com.example.data.local.entity.QuizAttempt
import com.example.data.local.entity.UserProfile

@Database(
    entities = [
        BookmarkedQuestion::class,
        QuizAttempt::class,
        DownloadedNote::class,
        UserProfile::class,
        ClassReminder::class
    ],
    version = 2,
    exportSchema = false
)
abstract class GuruDatabase : RoomDatabase() {
    abstract fun guruDao(): GuruDao

    companion object {
        @Volatile
        private var INSTANCE: GuruDatabase? = null

        fun getInstance(context: Context): GuruDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuruDatabase::class.java,
                    "ambition_guru.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
