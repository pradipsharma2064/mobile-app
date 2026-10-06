package com.example.data.sample

import com.example.data.model.CommunityDiscussion
import com.example.data.model.Course
import com.example.data.model.CurriculumChapter
import com.example.data.model.ExamCategoryEntry
import com.example.data.model.ExamQuestion
import com.example.data.model.LeaderboardItem
import com.example.data.model.Lesson
import com.example.data.model.LiveClass
import com.example.data.model.StudyMaterial

object SampleData {

    val examCategories = listOf(
        "All Exams",
        "Loksewa Aayog",
        "Banking & Finance",
        "Shikshak Sewa (TSC)",
        "Medical (CEE/MECEE)",
        "Engineering (IOE)",
        "Management (CMAT)",
        "Language & Skills"
    )

    val examCategoryEntries = listOf(
        ExamCategoryEntry(
            id = "cat_loksewa",
            categoryName = "Loksewa Aayog",
            nepaliName = "लोकसेवा आयोग",
            tagLine = "Civil Service (Nijamati), Sansthan & Security Forces",
            tagLineNepali = "निजामती सेवा, संगठित संस्था तथा सुरक्षा निकाय",
            examsList = listOf("शाखा अधिकृत (Section Officer)", "नायब सुब्बा (Nasu)", "खरिदार (Kharidar)", "संस्थान (Corporations)"),
            coursesCount = 24,
            liveBatchesCount = 8,
            mockTestsCount = 45,
            badge = "Most Popular",
            accentColorHex = 0xFF0ABAB5L
        ),
        ExamCategoryEntry(
            id = "cat_banking",
            categoryName = "Banking & Finance",
            nepaliName = "बैंकिङ तथा वित्तीय संस्था",
            tagLine = "NRB, RBB, NBL & ADBL Assistant & Officer Levels",
            tagLineNepali = "नेपाल राष्ट्र बैंक, रा.बा.बैंक, नेपाल बैंक र कृषि विकास बैंक",
            examsList = listOf("नेपाल राष्ट्र बैंक (Level 4 & 6)", "राष्ट्रिय वाणिज्य बैंक (RBB)", "नेपाल बैंक (NBL)", "कृषि विकास बैंक (ADBL)"),
            coursesCount = 16,
            liveBatchesCount = 5,
            mockTestsCount = 30,
            badge = "High Demand",
            accentColorHex = 0xFF008B87L
        ),
        ExamCategoryEntry(
            id = "cat_tsc",
            categoryName = "Shikshak Sewa (TSC)",
            nepaliName = "शिक्षक सेवा आयोग (TSC)",
            tagLine = "Permanent Teacher Service & Teaching License",
            tagLineNepali = "स्थायी शिक्षक सेवा तथा अध्यापन अनुमतिपत्र",
            examsList = listOf("माध्यमिक तह (Secondary)", "निम्न माध्यमिक (Lower Sec)", "प्राथमिक तह (Primary)", "शिक्षण लाइसेन्स (License)"),
            coursesCount = 14,
            liveBatchesCount = 4,
            mockTestsCount = 25,
            badge = "Annual Vacancy",
            accentColorHex = 0xFF0D9488L
        ),
        ExamCategoryEntry(
            id = "cat_medical",
            categoryName = "Medical (CEE/MECEE)",
            nepaliName = "चिकित्सा शिक्षा (CEE/MECEE)",
            tagLine = "Common Medical Entrance for MBBS, BDS, Nursing",
            tagLineNepali = "एम.बि.बि.एस, बि.डि.एस, बि.एस.सि नर्सिङ प्रवेश परीक्षा",
            examsList = listOf("MBBS / BDS Entrance", "BSc Nursing & Midwifery", "B.Pharmacy / BPH", "Paramedical Disciplines"),
            coursesCount = 18,
            liveBatchesCount = 6,
            mockTestsCount = 60,
            badge = "Intensive Drill",
            accentColorHex = 0xFF0284C7L
        ),
        ExamCategoryEntry(
            id = "cat_engineering",
            categoryName = "Engineering (IOE)",
            nepaliName = "इन्जिनियरिङ प्रवेश परीक्षा (IOE)",
            tagLine = "Pulchowk, Thapathali & TU Engineering Entrance",
            tagLineNepali = "पुलचोक क्याम्पस तथा सम्बद्ध इन्जिनियरिङ कलेजहरू",
            examsList = listOf("IOE Pulchowk Entrance", "Civil & Computer Eng.", "Electrical & Mechanical", "Architecture (B.Arch)"),
            coursesCount = 12,
            liveBatchesCount = 3,
            mockTestsCount = 40,
            badge = "Rank Booster",
            accentColorHex = 0xFFD97706L
        )
    )


    val liveClasses = listOf(
        LiveClass(
            id = "live_1",
            title = "Constitution of Nepal - Fundamental Rights & Directive Principles",
            subject = "Loksewa General Studies",
            instructorName = "Guru Santosh Khadka",
            instructorRole = "Under Secretary / Loksewa Guru",
            scheduledTime = "Started 15 mins ago",
            isLiveNow = true,
            attendeesCount = 1420,
            examCategory = "Loksewa Aayog",
            timeLabel = "Live Now",
            durationMinutes = 75,
            syllabusTopics = listOf("Article 16-48 Fundamental Rights", "Supreme Court Precedents", "Case Study Solution")
        ),
        LiveClass(
            id = "live_2",
            title = "NRB Act 2058 & BAFIA 2073 Complete Analytical Session",
            subject = "Banking Law & Regulations",
            instructorName = "Guru Ramesh Dhakal",
            instructorRole = "Ex-Director Nepal Rastra Bank",
            scheduledTime = "Today, 6:00 PM (Starts in 2h)",
            isLiveNow = false,
            attendeesCount = 890,
            examCategory = "Banking & Finance",
            timeLabel = "Today",
            durationMinutes = 90,
            syllabusTopics = listOf("Board Composition", "Credit Controls", "AML/CFT Compliance")
        ),
        LiveClass(
            id = "live_3",
            title = "IQ Masterclass: Visual & Matrix Reasoning Speed Tricks",
            subject = "Intelligence Quotient (IQ)",
            instructorName = "Guru Madhav Pandey",
            instructorRole = "IQ National Trainer",
            scheduledTime = "Today, 7:30 PM",
            isLiveNow = false,
            attendeesCount = 2150,
            examCategory = "Loksewa Aayog",
            timeLabel = "Today",
            durationMinutes = 60,
            syllabusTopics = listOf("Venn Diagrams", "Paper Folding", "Non-Verbal Series")
        ),
        LiveClass(
            id = "live_4",
            title = "TSC Secondary Level: Curriculum, Evaluation & Child Psychology",
            subject = "Teaching Pedagogy",
            instructorName = "Guru Dr. Harihar Sharma",
            instructorRole = "Senior Education Specialist",
            scheduledTime = "Tomorrow, 7:00 AM",
            isLiveNow = false,
            attendeesCount = 670,
            examCategory = "Shikshak Sewa (TSC)",
            timeLabel = "Tomorrow",
            durationMinutes = 60,
            syllabusTopics = listOf("Piaget & Vygotsky Theories", "Continuous Assessment (CAS)", "ICT in Classroom")
        ),
        LiveClass(
            id = "live_5",
            title = "MECEE High-Yield Biology: Human Physiology & Genetics Drills",
            subject = "Medical Entrance (CEE)",
            instructorName = "Dr. Subash Adhikari",
            instructorRole = "MBBS AIIMS / IOM Medalist",
            scheduledTime = "Tomorrow, 5:30 PM",
            isLiveNow = false,
            attendeesCount = 1120,
            examCategory = "Medical (CEE/MECEE)",
            timeLabel = "Tomorrow",
            durationMinutes = 90,
            syllabusTopics = listOf("Cardiac Cycle & ECG", "Endocrine Hormones", "Mendelian Genetics MCQs")
        ),
        LiveClass(
            id = "live_6",
            title = "IOE Pulchowk Calculus: Definite Integrals Rapid Elimination Tricks",
            subject = "Engineering Mathematics",
            instructorName = "Er. Pradeep Silwal",
            instructorRole = "Pulchowk Campus Gold Medalist",
            scheduledTime = "Friday, 6:30 PM",
            isLiveNow = false,
            attendeesCount = 940,
            examCategory = "Engineering (IOE)",
            timeLabel = "This Week",
            durationMinutes = 75,
            syllabusTopics = listOf("Leibnitz Rule", "Area between Curves", "Differential Equations")
        ),
        LiveClass(
            id = "live_7",
            title = "Commercial Banking Financial Accounting & Balance Sheet Analysis",
            subject = "Accounting & Auditing",
            instructorName = "Guru Ramesh Dhakal & CA Panel",
            instructorRole = "Chartered Accountant / Banking Expert",
            scheduledTime = "Saturday, 8:00 AM",
            isLiveNow = false,
            attendeesCount = 780,
            examCategory = "Banking & Finance",
            timeLabel = "This Week",
            durationMinutes = 90,
            syllabusTopics = listOf("NFRS Standards", "Cash Flow Statements", "Ratio Analysis")
        )
    )

    val courses = listOf(
        Course(
            id = "course_loksewa_officer",
            title = "Loksewa Section Officer (शाखा अधिकृत) Complete Master Package 2082/83",
            category = "Loksewa Aayog",
            instructor = "Guru Santosh Khadka & Panel",
            instructorTitle = "Joint Secretary & Expert Faculty",
            rating = 4.9,
            reviewsCount = 3840,
            studentsCount = 18450,
            priceNpr = 6500,
            originalPriceNpr = 12000,
            badge = "Bestseller",
            totalVideos = 220,
            totalHours = 180,
            totalNotes = 95,
            totalTests = 45,
            overview = "The most comprehensive online preparation course for Loksewa Section Officer (शाखा अधिकृत) 1st, 2nd, 3rd, and 4th papers with daily live sessions, full video library, e-books, handwritten notes, and Guru Pariksha mock tests with 20% negative marking simulation.",
            curriculum = listOf(
                CurriculumChapter(
                    chapterNumber = 1,
                    title = "General Knowledge (GK) - Geography & History of Nepal",
                    lessons = listOf(
                        Lesson("l1_1", "Physical Geography of Nepal: Himal, Pahad, Terai", "42:15", isFreePreview = true),
                        Lesson("l1_2", "Rivers, Lakes, and Water Resources of Nepal", "38:40"),
                        Lesson("l1_3", "History of Nepal: Ancient, Medieval & Modern Eras", "55:10"),
                        Lesson("l1_4", "Religious Sites, World Heritage & Protected Parks", "34:25")
                    )
                ),
                CurriculumChapter(
                    chapterNumber = 2,
                    title = "Governance, Constitution & Public Administration",
                    lessons = listOf(
                        Lesson("l2_1", "Salient Features of Constitution of Nepal 2072", "48:00", isFreePreview = true),
                        Lesson("l2_2", "Fundamental Rights (Articles 16-48) & Duties", "51:20"),
                        Lesson("l2_3", "Civil Service Act 2049 & Good Governance Act 2064", "44:10"),
                        Lesson("l2_4", "Public Financial Management & Budget Formulation", "40:30")
                    )
                ),
                CurriculumChapter(
                    chapterNumber = 3,
                    title = "General Mental Ability (IQ & Quantitative Reasoning)",
                    lessons = listOf(
                        Lesson("l3_1", "Number Series, Coding-Decoding & Analogies", "45:00", isFreePreview = true),
                        Lesson("l3_2", "Blood Relations, Directions & Clock Angles", "50:15"),
                        Lesson("l3_3", "Data Interpretation & Logical Venn Diagrams", "39:50")
                    )
                )
            )
        ),
        Course(
            id = "course_banking_nrb",
            title = "Nepal Rastra Bank (NRB) Level 4 Assistant & Level 6 Officer Crash Course",
            category = "Banking & Finance",
            instructor = "Guru Ramesh Dhakal & CA Experts",
            instructorTitle = "Ex-Director NRB & Top Chartered Accountants",
            rating = 4.8,
            reviewsCount = 2190,
            studentsCount = 12300,
            priceNpr = 5500,
            originalPriceNpr = 9500,
            badge = "Trending",
            totalVideos = 160,
            totalHours = 135,
            totalNotes = 72,
            totalTests = 30,
            overview = "Complete coverage of Nepal Rastra Bank recruitment syllabus: Monetary Policy, Banking Laws (NRB Act, BAFIA, AML/CFT, Foreign Exchange Act), Financial Accounting, Mathematics, and Economics.",
            curriculum = listOf(
                CurriculumChapter(
                    chapterNumber = 1,
                    title = "Banking Laws, Directives & Nepal Rastra Bank Act",
                    lessons = listOf(
                        Lesson("lb_1", "NRB Act 2058: Objectives, Structure & Functions", "46:30", isFreePreview = true),
                        Lesson("lb_2", "BAFIA 2073: Key Provisions & Banking Classification", "52:10"),
                        Lesson("lb_3", "Anti-Money Laundering (AML/CFT) Act & Directives", "37:45")
                    )
                ),
                CurriculumChapter(
                    chapterNumber = 2,
                    title = "Economics & Monetary Policy of Nepal",
                    lessons = listOf(
                        Lesson("lb_4", "Current Monetary Policy Targets & Instruments", "44:00"),
                        Lesson("lb_5", "Inflation, GDP, Trade Deficit & Forex Reserves in Nepal", "49:20")
                    )
                )
            )
        ),
        Course(
            id = "course_tsc_secondary",
            title = "Shikshak Sewa Aayog (TSC) Secondary & Lower-Secondary General Exam 2082",
            category = "Shikshak Sewa (TSC)",
            instructor = "Guru Dr. Harihar Sharma & Team",
            instructorTitle = "Senior Educationists & TSC Paper Setters",
            rating = 4.9,
            reviewsCount = 1750,
            studentsCount = 9800,
            priceNpr = 4500,
            originalPriceNpr = 8000,
            badge = "High Success Rate",
            totalVideos = 140,
            totalHours = 110,
            totalNotes = 60,
            totalTests = 25,
            overview = "Comprehensive syllabus for Teacher Service Commission covering Educational Governance, ICT in Education, Child Psychology, and Subject Pedagogies with weekly mock exams.",
            curriculum = listOf(
                CurriculumChapter(
                    chapterNumber = 1,
                    title = "Education System & Acts of Nepal",
                    lessons = listOf(
                        Lesson("lt_1", "Education Act 2028 & Regulations 2059", "41:15", isFreePreview = true),
                        Lesson("lt_2", "National Education Policy 2076 & School Sector Plan", "36:50")
                    )
                )
            )
        ),
        Course(
            id = "course_medical_cee",
            title = "MECEE-BL (Common Medical Entrance) MBBS/BDS/BSc Nursing High-Yield Course",
            category = "Medical (CEE/MECEE)",
            instructor = "Dr. Subash Adhikari (AIIMS/IOM)",
            instructorTitle = "Medical Entrance Gold Medalist",
            rating = 4.9,
            reviewsCount = 1420,
            studentsCount = 8200,
            priceNpr = 7900,
            originalPriceNpr = 15000,
            badge = "Intensive Prep",
            totalVideos = 280,
            totalHours = 240,
            totalNotes = 110,
            totalTests = 60,
            overview = "200-question full pattern mock series with in-depth Physics, Chemistry, Biology and Mental Agility modules mapped strictly to MEC syllabus.",
            curriculum = listOf(
                CurriculumChapter(
                    chapterNumber = 1,
                    title = "Zoology & Human Physiology High-Yield",
                    lessons = listOf(
                        Lesson("lm_1", "Cardiovascular & Respiratory Mechanisms", "58:00", isFreePreview = true),
                        Lesson("lm_2", "Endocrine System & Hormonal Feedback Loops", "45:30")
                    )
                )
            )
        ),
        Course(
            id = "course_engineering_ioe",
            title = "IOE Pulchowk Entrance Rank 1 Booster: Physics, Chemistry & Math Formula Master",
            category = "Engineering (IOE)",
            instructor = "Er. Pradeep Silwal & IOE Faculty",
            instructorTitle = "Pulchowk Campus Gold Medalist & IOE Trainer",
            rating = 4.9,
            reviewsCount = 1890,
            studentsCount = 7600,
            priceNpr = 6200,
            originalPriceNpr = 11000,
            badge = "Rank Booster",
            totalVideos = 210,
            totalHours = 175,
            totalNotes = 88,
            totalTests = 40,
            overview = "Complete IOE Pulchowk BE Entrance Masterclass: Shortcut tricks in Mechanics, Electromagnetism, Calculus, Coordinate Geometry, Organic Chemistry and English Aptitude with full timer mock exams.",
            curriculum = listOf(
                CurriculumChapter(
                    chapterNumber = 1,
                    title = "Mathematics & Calculus Shortcuts",
                    lessons = listOf(
                        Lesson("le_1", "Definite Integrals & Area under Curves 10-sec Tricks", "52:00", isFreePreview = true),
                        Lesson("le_2", "Coordinate Geometry & Conic Sections", "44:30")
                    )
                ),
                CurriculumChapter(
                    chapterNumber = 2,
                    title = "Physics & Applied Mechanics Drills",
                    lessons = listOf(
                        Lesson("le_3", "Rotational Dynamics & Moment of Inertia Shortcuts", "48:15", isFreePreview = true)
                    )
                )
            )
        )
    )

    val mockExamQuestions = listOf(
        ExamQuestion(
            id = 1,
            subject = "Nepal Geography & GK",
            questionText = "Which is the highest waterfall in Nepal according to the latest survey?",
            questionTextNepali = "नेपालको पछिल्लो सर्वेक्षण अनुसार सबैभन्दा अग्लो झरना कुन हो?",
            options = listOf(
                "Hyatung Waterfall (Tehrathum)",
                "Pachal Waterfall (Kalikot)",
                "Tupche Waterfall (Nuwakot)",
                "Rupse Waterfall (Myagdi)"
            ),
            optionsNepali = listOf(
                "ह्यातुङ झरना (तेह्रथुम)",
                "पचाल झरना (कालिकोट)",
                "तुप्चे झरना (नुवाकोट)",
                "रुप्से झरना (म्याग्दी)"
            ),
            correctIndex = 1,
            explanation = "Pachal waterfall in Kalikot district with a height of 381 meters is officially recognized as the highest waterfall in Nepal, surpassing Hyatung (365m).",
            explanationNepali = "कालिकोट जिल्लामा अवस्थित ३८१ मिटर अग्लो पचाल झरना नेपालको सबैभन्दा अग्लो झरनाका रूपमा प्रमाणित भएको छ (ह्यातुङ झरना ३६५ मिटर)।"
        ),
        ExamQuestion(
            id = 2,
            subject = "Constitution of Nepal",
            questionText = "Under Article 17 of the Constitution of Nepal, how many specific freedoms are guaranteed to citizens?",
            questionTextNepali = "नेपालको संविधानको धारा १७ अन्तर्गत नागरिकलाई कतिवटा स्वतन्त्रताको हक प्रदान गरिएको छ?",
            options = listOf(
                "4 Freedoms",
                "5 Freedoms",
                "6 Freedoms",
                "7 Freedoms"
            ),
            optionsNepali = listOf(
                "४ वटा स्वतन्त्रता",
                "५ वटा स्वतन्त्रता",
                "६ वटा स्वतन्त्रता",
                "७ वटा स्वतन्त्रता"
            ),
            correctIndex = 2,
            explanation = "Article 17(2) guarantees 6 fundamental freedoms: Freedom of opinion/expression, peaceful assembly without arms, forming unions/associations, movement/residence across Nepal, practicing any profession/trade.",
            explanationNepali = "धारा १७(२) बमोजिम ६ वटा स्वतन्त्रता प्रदान गरिएको छ: विचार र अभिव्यक्ति, शान्तिपूर्वक भेला हुने, राजनीतिक दल/संघसंस्था खोल्ने, आवतजावत, पेसा रोजगार गर्ने।"
        ),
        ExamQuestion(
            id = 3,
            subject = "IQ & Mental Ability",
            questionText = "Complete the series: 3, 7, 15, 31, 63, ?",
            questionTextNepali = "तल दिइएको श्रेणी क्रम पूरा गर्नुहोस्: ३, ७, १५, ३१, ६३, ?",
            options = listOf(
                "125",
                "127",
                "129",
                "131"
            ),
            optionsNepali = listOf(
                "१२५",
                "१२७",
                "१२९",
                "१३१"
            ),
            correctIndex = 1,
            explanation = "The pattern is (x * 2) + 1: (3*2)+1=7, (7*2)+1=15, (15*2)+1=31, (31*2)+1=63, (63*2)+1=127.",
            explanationNepali = "यसको ढाँचा (x × २) + १ हो: (३×२)+१=७, (७×२)+१=१५, (१५×२)+१=३१, (३१×२)+१=६३, (६३×२)+१=१२७।"
        ),
        ExamQuestion(
            id = 4,
            subject = "Banking & Economy",
            questionText = "What is the minimum paid-up capital required for a 'Class A' Commercial Bank in Nepal?",
            questionTextNepali = "नेपालमा 'क' वर्गको वाणिज्य बैंक स्थापना गर्न न्यूनतम चुक्ता पुँजी कति हुनुपर्छ?",
            options = listOf(
                "NPR 2 Billion",
                "NPR 4 Billion",
                "NPR 8 Billion",
                "NPR 10 Billion"
            ),
            optionsNepali = listOf(
                "२ अर्ब रुपैयाँ",
                "४ अर्ब रुपैयाँ",
                "८ अर्ब रुपैयाँ",
                "१० अर्ब रुपैयाँ"
            ),
            correctIndex = 2,
            explanation = "As per NRB licensing directives, Class 'A' Commercial Banks are required to maintain a minimum paid-up capital of NPR 8 Billion (८ अर्ब).",
            explanationNepali = "नेपाल राष्ट्र बैंकको निर्देशिका अनुसार 'क' वर्गका वाणिज्य बैंकहरूको न्यूनतम चुक्ता पुँजी ८ अर्ब रुपैयाँ तोकिएको छ।"
        ),
        ExamQuestion(
            id = 5,
            subject = "Science & Ecology",
            questionText = "Which national park of Nepal is famously inscribed as a UNESCO Natural World Heritage Site?",
            questionTextNepali = "नेपालको कुन राष्ट्रिय निकुञ्ज युनेस्कोको प्राकृतिक विश्व सम्पदा सूचीमा सूचीकृत छ?",
            options = listOf(
                "Bardiya National Park",
                "Chitwan National Park & Sagarmatha National Park",
                "Rara National Park",
                "Shey Phoksundo National Park"
            ),
            optionsNepali = listOf(
                "बर्दिया राष्ट्रिय निकुञ्ज",
                "चितवन राष्ट्रिय निकुञ्ज र सगरमाथा राष्ट्रिय निकुञ्ज",
                "रारा राष्ट्रिय निकुञ्ज",
                "शे-फोक्सुण्डो राष्ट्रिय निकुञ्ज"
            ),
            correctIndex = 1,
            explanation = "Sagarmatha National Park (inscribed in 1979) and Chitwan National Park (inscribed in 1984) are the two UNESCO Natural World Heritage Sites in Nepal.",
            explanationNepali = "सगरमाथा राष्ट्रिय निकुञ्ज (सन् १९७९) र चितवन राष्ट्रिय निकुञ्ज (सन् १९८४) युनेस्कोको प्राकृतिक सम्पदा सूचीमा छन्।"
        ),
        ExamQuestion(
            id = 6,
            subject = "International Affairs",
            questionText = "Where is the permanent headquarters of SAARC located?",
            questionTextNepali = "सार्क (SAARC) को स्थायी सचिवालय कहाँ अवस्थित छ?",
            options = listOf(
                "New Delhi, India",
                "Dhaka, Bangladesh",
                "Kathmandu, Nepal",
                "Colombo, Sri Lanka"
            ),
            optionsNepali = listOf(
                "नयाँ दिल्ली, भारत",
                "ढाका, बंगलादेश",
                "काठमाडौँ, नेपाल",
                "कोलम्बो, श्रीलंका"
            ),
            correctIndex = 2,
            explanation = "The SAARC Secretariat was established in Kathmandu on 16 January 1987 and inaugurated by King Birendra Bir Bikram Shah.",
            explanationNepali = "सार्क सचिवालय सन् १९८७ जनवरी १६ मा नेपालको राजधानी काठमाडौँमा स्थापना भएको हो।"
        ),
        ExamQuestion(
            id = 7,
            subject = "History of Nepal",
            questionText = "Who was the first elected Prime Minister of Nepal?",
            questionTextNepali = "नेपालको प्रथम जननिर्वाचित प्रधानमन्त्री को हुन्?",
            options = listOf(
                "Matrika Prasad Koirala",
                "B.P. Koirala (Bishweshwar Prasad Koirala)",
                "Tanka Prasad Acharya",
                "Subarna Shamsher Rana"
            ),
            optionsNepali = listOf(
                "मातृका प्रसाद कोइराला",
                "बी.पी. कोइराला (विश्वेश्वर प्रसाद कोइराला)",
                "टंकप्रसाद आचार्य",
                "सुवर्ण शमशेर राणा"
            ),
            correctIndex = 1,
            explanation = "Bishweshwar Prasad (B.P.) Koirala took office on 27 May 1959 after the Nepali Congress won a two-thirds majority in the 2015 BS general elections.",
            explanationNepali = "२०१५ सालको प्रथम आम निर्वाचनमा नेपाली कांग्रेसले दुई तिहाइ बहुमत ल्याएपछि २०१६ जेठ १३ मा बी.पी. कोइराला प्रथम जननिर्वाचित प्रधानमन्त्री बनेका थिए।"
        ),
        ExamQuestion(
            id = 8,
            subject = "IQ & Logical Sequence",
            questionText = "If LOKSEWA is coded as MPLOFTXB, how will GURU be coded?",
            questionTextNepali = "यदि LOKSEWA लाई MPLOFTXB लेखिन्छ भने, GURU लाई के लेखिन्छ?",
            options = listOf(
                "HVSV",
                "HVST",
                "HVSV",
                "HWTV"
            ),
            optionsNepali = listOf(
                "HVSV",
                "HVST",
                "HVTV",
                "HWTV"
            ),
            correctIndex = 0,
            explanation = "Each letter is shifted by +1 in the alphabetical sequence: G(+1)=H, U(+1)=V, R(+1)=S, U(+1)=V -> HVSV.",
            explanationNepali = "प्रत्येक वर्णमालामा १ अक्षर थप गरिएको छ: G->H, U->V, R->S, U->V -> HVSV।"
        )
    )

    val studyMaterials = listOf(
        StudyMaterial(
            id = "note_1",
            title = "Monthly Current Affairs (समसामयिक सार) - Falgun/Chaitra 2082",
            category = "Current Affairs",
            pages = 48,
            fileSize = "4.2 MB",
            isFree = true,
            description = "Crucial national and international events, appointments, economic indices, and sports awards compiled specifically for Loksewa and TSC.",
            tags = listOf("Loksewa", "Current Affairs", "High-Yield", "Monthly Digest"),
            readSnippet = "१. आर्थिक परिसूचकहरू: नेपालको आर्थिक वृद्धिदर, मुद्रास्फीति दर ५.२% मा सीमित।\n२. पछिल्ला नियुक्तिहरू: राष्ट्रिय मानव अधिकार आयोग तथा अख्तियार दुरुपयोग अनुसन्धान आयोगका प्रतिवेदनहरू।\n३. अन्तराष्ट्रिय सम्मेलनहरू: जलवायु सम्मेलन (COP) र नेपालको पर्वतीय एजेन्डा।"
        ),
        StudyMaterial(
            id = "note_2",
            title = "Constitution of Nepal 2072 Complete Flowcharts & Article Mnemonics",
            category = "Constitution & Law",
            pages = 65,
            fileSize = "6.8 MB",
            isFree = true,
            description = "Visual mnemonics to memorize 35 Parts, 308 Articles, and 9 Schedules of the Constitution in under 2 hours.",
            tags = listOf("Constitution", "Loksewa Officer", "Nayab Subba", "Law"),
            readSnippet = "नेपालको संविधान २०७२: ३५ भाग, ३०८ धारा, ९ अनुसूची।\n- भाग ३: मौलिक हक र कर्तव्य (धारा १६ देखि ४८ सम्म ३१ वटा मौलिक हक)।\n- भाग ४: राज्यका निर्देशक सिद्धान्त, नीति तथा दायित्व (धारा ४९ देखि ५५)।"
        ),
        StudyMaterial(
            id = "note_3",
            title = "NRB & Banking Laws Compendium (ऐन तथा नियम संग्रह)",
            category = "Banking & Finance",
            pages = 112,
            fileSize = "9.5 MB",
            isFree = false,
            description = "Detailed breakdown with sample subjective questions on BAFIA 2073, NRB Act 2058, Negotiable Instruments Act, and AML Act.",
            tags = listOf("Banking", "NRB", "RBB", "BAFIA"),
            readSnippet = "बैंक तथा वित्तीय संस्था सम्बन्धी ऐन (BAFIA) २०७३ का मुख्य विशेषताहरू:\n- वर्गिकरण: 'क' वर्ग (वाणिज्य बैंक), 'ख' वर्ग (विकास बैंक), 'ग' वर्ग (वित्त कम्पनी), 'घ' वर्ग (लघुवित्त)।\n- संचालक समितिको गठन र योग्यता सम्बन्धी प्रावधानहरू।"
        ),
        StudyMaterial(
            id = "note_4",
            title = "1000 Past Loksewa Kharidar & Nasu GK/IQ Question Bank with Detailed Solutions",
            category = "Question Bank",
            pages = 180,
            fileSize = "14.1 MB",
            isFree = false,
            description = "Past 10 years question collections categorized topic-wise with updated corrections and Guru notes.",
            tags = listOf("Past Papers", "Kharidar", "Nasu", "1000 Questions"),
            readSnippet = "विगत १० वर्षका शाखा अधिकृत तथा नायब सुब्बा प्रथम पत्रका प्रश्नहरू:\n- भूगोल सम्बन्धी सोधिएका ४५० प्रश्नोत्तर\n- इतिहास, धर्म, संस्कृति सम्बन्धी ३०० प्रश्नोत्तर\n- समसामयिक घटनाक्रम सम्बन्धी २५० प्रश्नोत्तर।"
        )
    )

    val leaderboard = listOf(
        LeaderboardItem(1, "Bikram Karki", 2890, "Jhapa", "Loksewa Gold Medalist", "Section Officer"),
        LeaderboardItem(2, "Sunita Shrestha", 2740, "Kathmandu", "Banking Maestro", "NRB Level 6"),
        LeaderboardItem(3, "Pradeep Thapa", 2690, "Kaski", "TSC Topper", "Secondary English"),
        LeaderboardItem(4, "Deepak Joshi", 2580, "Kailali", "IQ Wizard", "Section Officer"),
        LeaderboardItem(5, "Aashish Sharma (You)", 2450, "Lalitpur", "Sapana Scholar", "Section Officer", isCurrentUser = true),
        LeaderboardItem(6, "Puja Dahal", 2410, "Morang", "Consistent Achiever", "RBB Assistant"),
        LeaderboardItem(7, "Rabin Gurung", 2320, "Chitwan", "Quiz Champ", "Section Officer"),
        LeaderboardItem(8, "Anjali Mahato", 2280, "Parsa", "Rising Star", "NRB Assistant")
    )

    val communityDiscussions = listOf(
        CommunityDiscussion(
            id = "d1",
            author = "Santosh Adhikari",
            authorBadge = "Nasu Rank 3",
            timeAgo = "2h ago",
            topic = "Loksewa GK Trick",
            question = "What is the best mnemonic (सूत्र) to easily remember the 7 highest mountains of Nepal above 8000 meters in descending order?",
            upvotes = 42,
            repliesCount = 18,
            solved = true
        ),
        CommunityDiscussion(
            id = "d2",
            author = "Pooja Poudel",
            authorBadge = "Banking Aspirant",
            timeAgo = "4h ago",
            topic = "NRB Level 4 Math",
            question = "Can someone explain the shortcut formula for compound interest half-yearly compounding problems without using calculator?",
            upvotes = 29,
            repliesCount = 12,
            solved = true
        ),
        CommunityDiscussion(
            id = "d3",
            author = "Karan Bhattarai",
            authorBadge = "TSC Aspirant",
            timeAgo = "6h ago",
            topic = "Shikshak Sewa Aayog",
            question = "In the latest TSC syllabus, how much weightage is given to ICT in Education and Computer Fundamentals?",
            upvotes = 15,
            repliesCount = 7,
            solved = false
        )
    )

    val examCountdowns = listOf(
        Pair("Loksewa Section Officer Paper 1", "18 Days Left"),
        Pair("Nepal Rastra Bank Assistant (Level 4)", "34 Days Left"),
        Pair("TSC Secondary Level Written", "42 Days Left"),
        Pair("MECEE Common Medical Entrance", "56 Days Left")
    )

    val askGuruSampleReplies = mapOf(
        "negative_marking" to """
            **Loksewa Negative Marking Formula:**
            In Loksewa Aayog multiple choice examinations:
            - **Correct Answer:** +1.00 Mark
            - **Incorrect Answer:** -0.20 Mark (20% negative deduction)
            - **Unanswered:** 0 Mark (No deduction)

            **Guru Strategy Tip:**
            Only attempt a 50-50 guess if you can eliminate at least two options. The mathematical expectation is positive when you have a 1 in 2 probability (+1 vs -0.20 gives +0.40 expected mark per guess!).
        """.trimIndent(),
        "constitution" to """
            **Summary of Part 3: Fundamental Rights (Articles 16-48):**
            Nepal's Constitution 2072 guarantees **31 Fundamental Rights**.

            **Guru Mnemonic Trick:**
            * "सस्वसनि, याशोधासू, गोशोस्वाशि, रोश्रआखा, जेदलबाजे, संमवैस"
            - Art 16: Right to Live with Dignity (सम्मानपूर्वक बाँच्न पाउने हक)
            - Art 17: Right to Freedom (स्वतन्त्रताको हक)
            - Art 18: Right to Equality (समानताको हक)
            - Art 19: Right to Communication (सञ्चारको हक)
            - Art 20: Rights relating to Justice (न्याय सम्बन्धी हक)
            - Art 27: Right to Information (सूचनाको हक)
            - Art 31: Right relating to Education (शिक्षा सम्बन्धी हक)
            - Art 33: Right to Employment (रोजगारीको हक)
            - Art 46: Constitutional Remedies (संवैधानिक उपचारको हक)
        """.trimIndent(),
        "nrb_banking" to """
            **Key Focus Areas for NRB Level 4 & 6:**
            1. **Nepal Rastra Bank Act 2058:** Structure of Board of Directors (7 members headed by Governor), Monetary Policy formulation authority, Lender of Last Resort.
            2. **BAFIA 2073:** Licensing requirements, corporate governance, fit and proper test for directors.
            3. **Foreign Exchange Act 2019 & AML/CFT Act 2064:** Financial Intelligence Unit (FIU), KYC norms, Suspicious Transaction Reports (STR) threshold.
            4. **Monetary Instruments:** CRR (4%), SLR (10% & 12%), Policy Rate, Reverse Repo, Standing Liquidity Facility (SLF).
        """.trimIndent(),
        "iq_clock" to """
            **Guru IQ Short Trick: Clock Angle Formula:**
            To find angle θ between hour hand and minute hand:
            Angle θ = |30H - (11/2)M|
            
            *Example:* Find angle at 4:20:
            H = 4, M = 20
            θ = |30(4) - (11/2)(20)| = |120 - 110| = 10°!
            Solved in 3 seconds without drawing a clock!
        """.trimIndent()
    )
}
