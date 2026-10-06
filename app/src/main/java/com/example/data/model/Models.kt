package com.example.data.model

data class Course(
    val id: String,
    val title: String,
    val category: String, // Loksewa, Banking, TSC, Medical, Engineering, Skills
    val instructor: String,
    val instructorTitle: String,
    val rating: Double,
    val reviewsCount: Int,
    val studentsCount: Int,
    val priceNpr: Int,
    val originalPriceNpr: Int,
    val badge: String,
    val totalVideos: Int,
    val totalHours: Int,
    val totalNotes: Int,
    val totalTests: Int,
    val overview: String,
    val curriculum: List<CurriculumChapter>
)

data class CurriculumChapter(
    val chapterNumber: Int,
    val title: String,
    val lessons: List<Lesson>
)

data class Lesson(
    val id: String,
    val title: String,
    val duration: String,
    val isFreePreview: Boolean = false,
    val notesCount: Int = 1,
    val hasQuiz: Boolean = true
)

data class LiveClass(
    val id: String,
    val title: String,
    val subject: String,
    val instructorName: String,
    val instructorRole: String,
    val scheduledTime: String,
    val isLiveNow: Boolean,
    val attendeesCount: Int,
    val examCategory: String,
    val timeLabel: String = "Upcoming",
    val durationMinutes: Int = 60,
    val syllabusTopics: List<String> = emptyList()
)

data class ExamQuestion(
    val id: Int,
    val subject: String,
    val questionText: String,
    val questionTextNepali: String,
    val options: List<String>,
    val optionsNepali: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val explanationNepali: String
)

data class StudyMaterial(
    val id: String,
    val title: String,
    val category: String,
    val pages: Int,
    val fileSize: String,
    val isFree: Boolean,
    val description: String,
    val tags: List<String>,
    val readSnippet: String
)

data class LeaderboardItem(
    val rank: Int,
    val name: String,
    val score: Int,
    val district: String,
    val badge: String,
    val targetExam: String,
    val isCurrentUser: Boolean = false
)

data class CommunityDiscussion(
    val id: String,
    val author: String,
    val authorBadge: String,
    val timeAgo: String,
    val topic: String,
    val question: String,
    val upvotes: Int,
    val repliesCount: Int,
    val solved: Boolean
)

data class ChatMessage(
    val id: String,
    val isFromUser: Boolean,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class ExamCategoryEntry(
    val id: String,
    val categoryName: String,
    val nepaliName: String,
    val tagLine: String,
    val tagLineNepali: String,
    val examsList: List<String>,
    val coursesCount: Int,
    val liveBatchesCount: Int,
    val mockTestsCount: Int,
    val badge: String,
    val accentColorHex: Long
)

