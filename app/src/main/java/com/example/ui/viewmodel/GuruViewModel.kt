package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.GuruDatabase
import com.example.data.local.entity.BookmarkedQuestion
import com.example.data.local.entity.ClassReminder
import com.example.data.local.entity.DownloadedNote
import com.example.data.local.entity.QuizAttempt
import com.example.data.local.entity.UserProfile
import com.example.data.model.ChatMessage
import com.example.data.model.Course
import com.example.data.model.ExamQuestion
import com.example.data.model.Lesson
import com.example.data.model.LiveClass
import com.example.data.model.StudyMaterial
import com.example.data.repository.GuruRepository
import com.example.data.sample.SampleData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class Screen {
    HOME,
    COURSES,
    COURSE_DETAIL,
    MOCK_TEST_INTRO,
    MOCK_TEST_ACTIVE,
    MOCK_TEST_RESULT,
    LIBRARY,
    NOTE_READER,
    ASK_GURU,
    LEADERBOARD,
    PROFILE,
    LIVE_CLASSROOM,
    LIVE_CLASSES_LIST
}

data class ExamResultData(
    val title: String,
    val score: Double,
    val maxScore: Double,
    val correct: Int,
    val incorrect: Int,
    val unanswered: Int,
    val accuracy: Double,
    val timeTakenSeconds: Int,
    val rankPercentile: Double
)

class GuruViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuruRepository

    init {
        val db = GuruDatabase.getInstance(application)
        repository = GuruRepository(db.guruDao())
    }

    // Room DB Flows
    val bookmarkedQuestions: StateFlow<List<BookmarkedQuestion>> = repository.bookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val pastAttempts: StateFlow<List<QuizAttempt>> = repository.attempts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val downloadedNotes: StateFlow<List<DownloadedNote>> = repository.downloadedNotes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userProfile: StateFlow<UserProfile?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserProfile())

    val classReminders: StateFlow<List<ClassReminder>> = repository.reminders
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleClassReminder(liveClass: LiveClass) {
        viewModelScope.launch {
            val isCurrentlySet = classReminders.value.any { it.classId == liveClass.id }
            val reminder = ClassReminder(
                classId = liveClass.id,
                title = liveClass.title,
                instructorName = liveClass.instructorName,
                scheduledTime = liveClass.scheduledTime,
                examCategory = liveClass.examCategory
            )
            repository.toggleReminder(reminder, isCurrentlySet)
        }
    }

    // Navigation and screen management
    private val _currentScreen = MutableStateFlow(Screen.HOME)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _previousScreen = MutableStateFlow(Screen.HOME)
    val previousScreen: StateFlow<Screen> = _previousScreen.asStateFlow()

    // Language setting: true = Nepali, false = English
    private val _isNepaliLanguage = MutableStateFlow(false)
    val isNepaliLanguage: StateFlow<Boolean> = _isNepaliLanguage.asStateFlow()

    fun toggleLanguage() {
        _isNepaliLanguage.value = !_isNepaliLanguage.value
    }

    // Category filter
    private val _selectedCategory = MutableStateFlow("All Exams")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    fun setSelectedCategory(cat: String) {
        _selectedCategory.value = cat
    }

    // Course Detail & Video Player
    private val _selectedCourse = MutableStateFlow<Course?>(SampleData.courses.first())
    val selectedCourse: StateFlow<Course?> = _selectedCourse.asStateFlow()

    private val _selectedLesson = MutableStateFlow<Lesson?>(
        SampleData.courses.first().curriculum.first().lessons.first()
    )
    val selectedLesson: StateFlow<Lesson?> = _selectedLesson.asStateFlow()

    private val _isPlayingVideo = MutableStateFlow(false)
    val isPlayingVideo: StateFlow<Boolean> = _isPlayingVideo.asStateFlow()

    private val _videoProgress = MutableStateFlow(0.35f)
    val videoProgress: StateFlow<Float> = _videoProgress.asStateFlow()

    private val _playbackSpeed = MutableStateFlow(1.0f)
    val playbackSpeed: StateFlow<Float> = _playbackSpeed.asStateFlow()

    private val _courseActiveTab = MutableStateFlow(0) // 0: Syllabus, 1: Notes, 2: Q&A
    val courseActiveTab: StateFlow<Int> = _courseActiveTab.asStateFlow()

    fun setCourseActiveTab(tab: Int) {
        _courseActiveTab.value = tab
    }

    fun toggleVideoPlay() {
        _isPlayingVideo.value = !_isPlayingVideo.value
    }

    fun setPlaybackSpeed(speed: Float) {
        _playbackSpeed.value = speed
    }

    fun setVideoProgress(progress: Float) {
        _videoProgress.value = progress
    }

    fun selectCourse(course: Course) {
        _selectedCourse.value = course
        _selectedLesson.value = course.curriculum.firstOrNull()?.lessons?.firstOrNull()
        navigateTo(Screen.COURSE_DETAIL)
    }

    fun selectLesson(lesson: Lesson) {
        _selectedLesson.value = lesson
        _isPlayingVideo.value = true
        _videoProgress.value = 0.05f
    }

    // Live Classroom
    private val _activeLiveClass = MutableStateFlow<LiveClass?>(SampleData.liveClasses.first())
    val activeLiveClass: StateFlow<LiveClass?> = _activeLiveClass.asStateFlow()

    private val _liveMessages = MutableStateFlow(
        listOf(
            "Santosh Sir namaste! Fundamental rights ko trick dami chha!",
            "Can you repeat Article 25 (Right to Property) once again sir?",
            "Voice and audio are crystal clear from Pokhara!",
            "Sir, does Article 17 allow strikes in essential public utilities?"
        )
    )
    val liveMessages: StateFlow<List<String>> = _liveMessages.asStateFlow()

    fun openLiveClass(liveClass: LiveClass) {
        _activeLiveClass.value = liveClass
        navigateTo(Screen.LIVE_CLASSROOM)
    }

    fun sendLiveComment(comment: String) {
        if (comment.isNotBlank()) {
            _liveMessages.value = _liveMessages.value + "You: $comment"
        }
    }

    // Mock Exam / Guru Pariksha State
    private val _examQuestions = MutableStateFlow(SampleData.mockExamQuestions)
    val examQuestions: StateFlow<List<ExamQuestion>> = _examQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    // Map of questionId -> selected Option Index (0..3)
    private val _userAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val userAnswers: StateFlow<Map<Int, Int>> = _userAnswers.asStateFlow()

    // Set of marked for review question IDs
    private val _reviewQuestionIds = MutableStateFlow<Set<Int>>(emptySet())
    val reviewQuestionIds: StateFlow<Set<Int>> = _reviewQuestionIds.asStateFlow()

    private val _examTimeRemainingSeconds = MutableStateFlow(900) // 15 minutes
    val examTimeRemainingSeconds: StateFlow<Int> = _examTimeRemainingSeconds.asStateFlow()

    private var examTimerJob: Job? = null

    private val _examResult = MutableStateFlow<ExamResultData?>(null)
    val examResult: StateFlow<ExamResultData?> = _examResult.asStateFlow()

    fun startMockExam() {
        _currentQuestionIndex.value = 0
        _userAnswers.value = emptyMap()
        _reviewQuestionIds.value = emptySet()
        _examTimeRemainingSeconds.value = 900
        startExamTimer()
        navigateTo(Screen.MOCK_TEST_ACTIVE)
    }

    private fun startExamTimer() {
        examTimerJob?.cancel()
        examTimerJob = viewModelScope.launch {
            while (_examTimeRemainingSeconds.value > 0) {
                delay(1000)
                _examTimeRemainingSeconds.value -= 1
            }
            if (_currentScreen.value == Screen.MOCK_TEST_ACTIVE) {
                submitExam()
            }
        }
    }

    fun selectOption(questionId: Int, optionIndex: Int) {
        val current = _userAnswers.value.toMutableMap()
        if (current[questionId] == optionIndex) {
            current.remove(questionId)
        } else {
            current[questionId] = optionIndex
        }
        _userAnswers.value = current
    }

    fun toggleMarkForReview(questionId: Int) {
        val current = _reviewQuestionIds.value.toMutableSet()
        if (current.contains(questionId)) {
            current.remove(questionId)
        } else {
            current.add(questionId)
        }
        _reviewQuestionIds.value = current
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < _examQuestions.value.size - 1) {
            _currentQuestionIndex.value += 1
        }
    }

    fun previousQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value -= 1
        }
    }

    fun jumpToQuestion(index: Int) {
        if (index in _examQuestions.value.indices) {
            _currentQuestionIndex.value = index
        }
    }

    fun submitExam() {
        examTimerJob?.cancel()
        val questions = _examQuestions.value
        val answers = _userAnswers.value

        var correct = 0
        var incorrect = 0
        var unanswered = 0

        for (q in questions) {
            val selected = answers[q.id]
            if (selected == null) {
                unanswered++
            } else if (selected == q.correctIndex) {
                correct++
            } else {
                incorrect++
            }
        }

        // Loksewa marking: +1 for correct, -0.20 for incorrect
        val score = (correct * 1.0) - (incorrect * 0.20)
        val maxScore = questions.size * 1.0
        val attempted = correct + incorrect
        val accuracy = if (attempted > 0) (correct.toDouble() / attempted) * 100.0 else 0.0
        val timeTaken = 900 - _examTimeRemainingSeconds.value
        val rankPercentile = (85.0 + (score / maxScore * 14.5)).coerceIn(50.0, 99.8)

        val resultData = ExamResultData(
            title = "All Nepal Hamro Sapana - Loksewa Model Set 04",
            score = score,
            maxScore = maxScore,
            correct = correct,
            incorrect = incorrect,
            unanswered = unanswered,
            accuracy = accuracy,
            timeTakenSeconds = timeTaken,
            rankPercentile = rankPercentile
        )
        _examResult.value = resultData

        // Save attempt to Room
        viewModelScope.launch {
            repository.recordQuizAttempt(
                QuizAttempt(
                    examTitle = resultData.title,
                    category = "Loksewa Section Officer",
                    score = score,
                    maxScore = maxScore,
                    correctCount = correct,
                    incorrectCount = incorrect,
                    unansweredCount = unanswered,
                    accuracyPercentage = accuracy,
                    timeTakenSeconds = timeTaken
                )
            )

            // Update user profile solved count
            val currentProf = userProfile.value ?: UserProfile()
            repository.updateUserProfile(
                currentProf.copy(
                    questionsSolved = currentProf.questionsSolved + attempted,
                    gems = currentProf.gems + (correct * 5)
                )
            )
        }

        navigateTo(Screen.MOCK_TEST_RESULT)
    }

    fun toggleBookmarkCurrentQuestion(question: ExamQuestion) {
        viewModelScope.launch {
            val isCurrent = bookmarkedQuestions.value.any { it.questionText == question.questionText }
            val item = BookmarkedQuestion(
                questionText = question.questionText,
                optionA = question.options.getOrElse(0) { "" },
                optionB = question.options.getOrElse(1) { "" },
                optionC = question.options.getOrElse(2) { "" },
                optionD = question.options.getOrElse(3) { "" },
                correctOptionIndex = question.correctIndex,
                explanation = question.explanation,
                subject = question.subject
            )
            repository.toggleBookmark(item, isCurrent)
        }
    }

    // Study Material & Note Reader
    private val _selectedNote = MutableStateFlow<StudyMaterial?>(SampleData.studyMaterials.first())
    val selectedNote: StateFlow<StudyMaterial?> = _selectedNote.asStateFlow()

    fun openNote(note: StudyMaterial) {
        _selectedNote.value = note
        navigateTo(Screen.NOTE_READER)
    }

    fun downloadNote(note: StudyMaterial) {
        viewModelScope.launch {
            val item = DownloadedNote(
                title = note.title,
                category = note.category,
                author = "Hamro Sapana Faculty",
                fileSize = note.fileSize,
                pagesCount = note.pages,
                contentSnippet = note.readSnippet
            )
            repository.saveNoteDownload(item)
        }
    }

    fun deleteDownloadedNote(title: String) {
        viewModelScope.launch {
            repository.removeDownloadedNote(title)
        }
    }

    // Ask Guru (AI Tutor with Gemini API)
    private val _chatMessages = MutableStateFlow(
        listOf(
            ChatMessage(
                id = "m1",
                isFromUser = false,
                message = "Namaste! I am Hamro Sapana AI, powered by Google Gemini. Ask me any doubt, syllabus topic, mathematical shortcut, or legal article for Loksewa, Banking, TSC, Medical (CEE), or Engineering (IOE) examinations!"
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _isGuruTyping = MutableStateFlow(false)
    val isGuruTyping: StateFlow<Boolean> = _isGuruTyping.asStateFlow()

    fun clearChat() {
        _chatMessages.value = listOf(
            ChatMessage(
                id = "m_reset_${System.currentTimeMillis()}",
                isFromUser = false,
                message = "Chat refreshed. What exam topic or question would you like to solve next?"
            )
        )
    }

    fun askGuru(query: String) {
        if (query.isBlank()) return
        val userMsg = ChatMessage(id = "user_${System.currentTimeMillis()}", isFromUser = true, message = query.trim())
        _chatMessages.value = _chatMessages.value + userMsg
        _isGuruTyping.value = true

        viewModelScope.launch {
            // Attempt Gemini API call via GeminiService
            val category = _selectedCategory.value
            val geminiResult = com.example.data.remote.GeminiService.askGemini(query.trim(), category)

            val answerText = geminiResult.getOrElse { error ->
                // Fallback to offline Ambition Guru expert syllabus repository if API key is not configured or network fails
                val lower = query.lowercase()
                val offlineInsight = when {
                    lower.contains("negative") || lower.contains("marking") || lower.contains("mark") ->
                        SampleData.askGuruSampleReplies["negative_marking"] ?: ""
                    lower.contains("constitution") || lower.contains("right") || lower.contains("samvidhan") || lower.contains("dhara") ->
                        SampleData.askGuruSampleReplies["constitution"] ?: ""
                    lower.contains("nrb") || lower.contains("bank") || lower.contains("bafia") || lower.contains("monetary") ->
                        SampleData.askGuruSampleReplies["nrb_banking"] ?: ""
                    lower.contains("clock") || lower.contains("angle") || lower.contains("trick") || lower.contains("iq") ->
                        SampleData.askGuruSampleReplies["iq_clock"] ?: ""
                    else -> {
                        "**Hamro Sapana Study Recommendation:**\n" +
                                "• Key Focus: Master core definitions, constitutional amendments, and past 5-year question trends in $category.\n" +
                                "• Time Management: Practice speed solving with 45-second per question targets.\n" +
                                "• Tip: Combine theory with daily Hamro Sapana mock tests for retention."
                    }
                }

                if (error.message?.contains("GEMINI_API_KEY") == true) {
                    offlineInsight + "\n\n*(Note: To connect live Gemini AI responses, configure your GEMINI_API_KEY in the Secrets panel.)*"
                } else {
                    offlineInsight
                }
            }

            val botMsg = ChatMessage(id = "guru_${System.currentTimeMillis()}", isFromUser = false, message = answerText)
            _chatMessages.value = _chatMessages.value + botMsg
            _isGuruTyping.value = false
        }
    }

    // Community Upvote
    private val _discussions = MutableStateFlow(SampleData.communityDiscussions)
    val discussions: StateFlow<List<com.example.data.model.CommunityDiscussion>> = _discussions.asStateFlow()

    fun upvoteDiscussion(id: String) {
        _discussions.value = _discussions.value.map {
            if (it.id == id) it.copy(upvotes = it.upvotes + 1) else it
        }
    }

    // Navigation helper
    fun navigateTo(screen: Screen) {
        _previousScreen.value = _currentScreen.value
        _currentScreen.value = screen
    }

    fun goBack() {
        _currentScreen.value = when (_currentScreen.value) {
            Screen.COURSE_DETAIL -> Screen.COURSES
            Screen.MOCK_TEST_ACTIVE -> Screen.MOCK_TEST_INTRO
            Screen.MOCK_TEST_RESULT -> Screen.HOME
            Screen.NOTE_READER -> Screen.LIBRARY
            Screen.LIVE_CLASSROOM -> Screen.LIVE_CLASSES_LIST
            Screen.LIVE_CLASSES_LIST -> Screen.HOME
            else -> _previousScreen.value
        }
    }
}
