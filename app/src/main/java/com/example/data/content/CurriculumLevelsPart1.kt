package com.example.data.content

import com.example.data.model.CurriculumLevel
import com.example.data.model.LessonItem
import com.example.data.model.PracticeWord
import com.example.data.model.QuizQuestion
import com.example.data.model.VideoLessonData
import com.example.data.model.VideoSlide

object CurriculumLevelsPart1 {
    val levels: List<CurriculumLevel> = listOf(
        CurriculumLevel(
            number = 1,
            title = "Arabic Alphabet (29 Letters)",
            arabicTitle = "الحُرُوفُ الهِجَائِيَّة",
            stageName = "Stage 1 • Foundation",
            summary = "Discover all 29 authentic Arabic letters with joyful pronunciation and right-to-left reading.",
            badgeTitle = "Alphabet Explorer",
            estimatedMinutes = 25,
            lessons = listOf(
                LessonItem(
                    id = "l1_1", levelNumber = 1,
                    title = "First Family: Alif to Tha",
                    subtitle = "Letters 1–4 • ا ب ت ث",
                    arabicShowcase = "ا  ب  ت  ث",
                    transliteration = "Alif • Ba • Ta • Tha",
                    explanation = "Arabic is read from right to left. Notice how Ba, Ta, and Tha share the same boat shape and are distinguished by their dots.",
                    audioSpeakText = "ألف، باء، تاء، ثاء",
                    practiceItems = listOf(
                        PracticeWord("ا", "Alif", "Tall straight stroke"),
                        PracticeWord("ب", "Ba", "1 dot below"),
                        PracticeWord("ت", "Ta", "2 dots above"),
                        PracticeWord("ث", "Tha", "3 dots above")
                    )
                ),
                LessonItem(
                    id = "l1_2", levelNumber = 1,
                    title = "Jeem, Ha, Kha & Dal Family",
                    subtitle = "Letters 5–11 • ج ح خ د ذ ر ز",
                    arabicShowcase = "ج  ح  خ  د  ذ  ر  ز",
                    transliteration = "Jeem • Ha • Kha • Dal • Dhal • Ra • Zay",
                    explanation = "Practice the throat clarity of Ha (ح) versus Kha (خ), and remember Dal (د), Dhal (ذ), Ra (ر), and Zay (ز) never connect to the letter after them.",
                    audioSpeakText = "جيم، حاء، خاء، دال، ذال، راء، زاي",
                    practiceItems = listOf(
                        PracticeWord("ج", "Jeem", "1 dot inside"),
                        PracticeWord("ح", "Ha", "No dots • middle throat"),
                        PracticeWord("خ", "Kha", "1 dot above • upper throat"),
                        PracticeWord("ر", "Ra", "Clear rolled R")
                    )
                ),
                LessonItem(
                    id = "l1_3", levelNumber = 1,
                    title = "Seen to Ya Mastery",
                    subtitle = "Letters 12–29 • س ش ص ض ... ي",
                    arabicShowcase = "س  ش  ص  ض  ط  ظ  ع  غ  ف  ق  ك  ل  م  ن  ه  و  ء  ي",
                    transliteration = "Seen to Ya",
                    explanation = "Learn heavy letters (ص ض ط ظ ق غ خ) with a full, round sound and light letters (س ت ك ل) with a smiling tone.",
                    audioSpeakText = "سين، شين، صاد، ضاد، طاء، ظاء، عين، غين، فاء، قاف، كاف، لام، ميم، نون، هاء، واو، همزة، ياء",
                    practiceItems = listOf(
                        PracticeWord("ص", "Sad", "Heavy full-mouth S"),
                        PracticeWord("ع", "Ayn", "Deep middle throat"),
                        PracticeWord("ق", "Qaf", "Deep heavy Q"),
                        PracticeWord("ي", "Ya", "2 dots below")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_1", levelNumber = 1,
                title = "Journey Through the 29 Arabic Letters",
                category = "Arabic Alphabet",
                durationLabel = "06:20",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "A child-friendly visual walkthrough of all 29 Arabic letters, dot families, and clear right-to-left reading.",
                slides = listOf(
                    VideoSlide("Welcome to Arabic", "ا  ب  ت  ث", "Always read Arabic from RIGHT to LEFT. Count the dots to tell Ba, Ta, and Tha apart!", "ألف، باء، تاء، ثاء"),
                    VideoSlide("Throat & Sister Letters", "ج  ح  خ", "Jeem has a dot in its belly, Ha has no dot, and Kha wears a dot on top.", "جيم، حاء، خاء"),
                    VideoSlide("Heavy vs Light Letters", "ص  ض  ط  ظ  ق", "Round your mouth slightly for heavy letters so your Quran recitation sounds majestic.", "صاد، ضاد، طاء، ظاء، قاف")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q1_1", "Which Arabic letter has ONE dot underneath its boat shape?", "ب", listOf("Ta (ت)", "Ba (ب)", "Tha (ث)", "Noon (ن)"), 1, "Ba (ب) has one dot underneath."),
                QuizQuestion("q1_2", "Which letter is this?", "ح", listOf("Jeem (ج)", "Kha (خ)", "Ha (ح)", "Ayn (ع)"), 2, "Ha (ح) has no dots and comes from the middle throat."),
                QuizQuestion("q1_3", "How many letters are taught in the complete Arabic Alphabet curriculum?", "٢٩", listOf("26 letters", "29 letters", "24 letters", "32 letters"), 1, "The traditional Qaida Arabic alphabet has 29 letters including Hamzah."),
                QuizQuestion("q1_4", "Which direction do we read Arabic and the Holy Quran?", "قُرْآنٌ", listOf("Left to Right", "Top to Bottom", "Right to Left", "Bottom to Top"), 2, "Arabic is always read from Right to Left.")
            )
        ),
        CurriculumLevel(
            number = 2,
            title = "Arabic Letter Recognition",
            arabicTitle = "تَمْيِيزُ الحُرُوف",
            stageName = "Stage 1 • Foundation",
            summary = "Recognize similar-looking letters quickly by dots, curves, and connected word positions.",
            badgeTitle = "Sharp Eye",
            estimatedMinutes = 20,
            lessons = listOf(
                LessonItem(
                    id = "l2_1", levelNumber = 2,
                    title = "Similar Letter Pairs",
                    subtitle = "Distinguishing dot twins",
                    arabicShowcase = "ب ت ث  •  د ذ  •  ر ز  •  س ش",
                    transliteration = "Ba/Ta/Tha • Dal/Dhal • Ra/Zay • Seen/Sheen",
                    explanation = "Look carefully at the dots: adding a single dot transforms Dal (د) into Dhal (ذ), Ra (ر) into Zay (ز), and Sad (ص) into Dad (ض).",
                    audioSpeakText = "دال، ذال. راء، زاي. سين، شين. صاد، ضاد",
                    practiceItems = listOf(
                        PracticeWord("د / ذ", "Dal vs Dhal", "No dot vs 1 dot"),
                        PracticeWord("ص / ض", "Sad vs Dad", "No dot vs 1 dot"),
                        PracticeWord("ط / ظ", "Taa vs Dhaa", "No dot vs 1 dot"),
                        PracticeWord("ع / غ", "Ayn vs Ghayn", "No dot vs 1 dot")
                    )
                ),
                LessonItem(
                    id = "l2_2", levelNumber = 2,
                    title = "Initial, Medial & Final Shapes",
                    subtitle = "How letters dress inside words",
                    arabicShowcase = "بـ  ـبـ  ـب   •   عـ  ـعـ  ـع",
                    transliteration = "Beginning • Middle • End",
                    explanation = "Most Arabic letters stretch their arms to hold hands! Recognize each letter by its head and dots even when joined.",
                    audioSpeakText = "باء في الأول، باء في الوسط، باء في الآخر",
                    practiceItems = listOf(
                        PracticeWord("عـ ـعـ ـع", "Ayn Shapes", "Open head at start, closed triangle in middle"),
                        PracticeWord("هـ ـهـ ـه", "Ha Shapes", "Butterfly shape in the middle"),
                        PracticeWord("كـ ـكـ ـك", "Kaf Shapes", "Slanted top stroke at beginning"),
                        PracticeWord("مـ ـمـ ـم", "Meem Shapes", "Small round loop")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_2", levelNumber = 2,
                title = "Spotting Letters in Any Shape",
                category = "Noorani Qaida",
                durationLabel = "05:45",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Learn the secret dot patterns and head shapes to identify every letter instantly inside Quranic words.",
                slides = listOf(
                    VideoSlide("Dot Detective", "ت  ن  ث  ي", "Ta has 2 dots above, Ya has 2 dots below, Noon has 1 dot above!", "تاء، نون، ثاء، ياء"),
                    VideoSlide("The 6 Non-Connecting Letters", "ا  د  ذ  ر  ز  و", "These 6 letters connect from the right, but never hold hands on the left.", "ألف، دال، ذال، راء، زاي، واو")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q2_1", "Which letter has ONE dot ABOVE and curves like a bowl?", "ن", listOf("Ba (ب)", "Noon (ن)", "Ta (ت)", "Fa (ف)"), 1, "Noon (ن) has one dot above."),
                QuizQuestion("q2_2", "Which letter does NOT connect to the letter after it?", "ر", listOf("Ba (ب)", "Seen (س)", "Ra (ر)", "Meem (م)"), 2, "Ra (ر) is one of the 6 non-connecting letters (ا د ذ ر ز و)."),
                QuizQuestion("q2_3", "What is the middle form of the letter Ayn (ع)?", "ـعـ", listOf("ـعـ", "ـفـ", "ـقـ", "ـمـ"), 0, "Ayn (ع) closes into a small triangle loop in the middle: ـعـ."),
                QuizQuestion("q2_4", "How many dots does Qaf (ق) have compared to Fa (ف)?", "ق  ف", listOf("Qaf has 1, Fa has 2", "Qaf has 2, Fa has 1", "Both have 3", "Neither has dots"), 1, "Qaf (ق) has 2 dots above, while Fa (ف) has 1 dot above.")
            )
        ),
        CurriculumLevel(
            number = 3,
            title = "Fatha, Kasra and Damma",
            arabicTitle = "الحَرَكَاتُ الثَّلَاث",
            stageName = "Stage 2 • Vowels & Signs",
            summary = "Bring Arabic letters to life with the three short vowels (Harakat): Fatha, Kasra, and Damma.",
            badgeTitle = "Vowel Virtuoso",
            estimatedMinutes = 25,
            lessons = listOf(
                LessonItem(
                    id = "l3_1", levelNumber = 3,
                    title = "Fatha (َ) — The Open 'a' Sound",
                    subtitle = "Short diagonal stroke above the letter",
                    arabicShowcase = "بَ  تَ  ثَ  جَ  حَ  خَ  دَ",
                    transliteration = "Ba • Ta • Tha • Ja • Ha • Kha • Da",
                    explanation = "Fatha is a small line ABOVE a letter. Open your mouth gently for 1 crisp count without stretching it.",
                    audioSpeakText = "بَ، تَ، ثَ، جَ، حَ، خَ، دَ. كَتَبَ",
                    practiceItems = listOf(
                        PracticeWord("كَتَبَ", "Ka-ta-ba", "He wrote"),
                        PracticeWord("خَلَقَ", "Kha-la-qa", "He created"),
                        PracticeWord("ذَهَبَ", "Dha-ha-ba", "He went"),
                        PracticeWord("جَعَلَ", "Ja-'a-la", "He made")
                    )
                ),
                LessonItem(
                    id = "l3_2", levelNumber = 3,
                    title = "Kasra (ِ) & Damma (ُ)",
                    subtitle = "Short 'i' below & short 'u' above",
                    arabicShowcase = "بِ  بُ   •   عَلِمَ   •   كُتِبَ",
                    transliteration = "Bi • Bu • 'A-li-ma • Ku-ti-ba",
                    explanation = "Kasra sits BELOW the letter ('i' sound). Damma looks like a tiny Waw ABOVE the letter ('u' sound with rounded lips).",
                    audioSpeakText = "بَ، بِ، بُ. عَلِمَ. كُتِبَ. رُزِقَ",
                    practiceItems = listOf(
                        PracticeWord("عَلِمَ", "'A-li-ma", "He knew"),
                        PracticeWord("سَمِعَ", "Sa-mi-'a", "He heard"),
                        PracticeWord("كُتِبَ", "Ku-ti-ba", "It was written"),
                        PracticeWord("رُسُلُ", "Ru-su-lu", "Messengers")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_3", levelNumber = 3,
                title = "Mastering Fatha, Kasra & Damma",
                category = "Noorani Qaida",
                durationLabel = "06:10",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Hear and practice crisp 1-count Harakat pronunciation with mouth shape cues for children.",
                slides = listOf(
                    VideoSlide("Fatha Above = 'a'", "كَتَبَ", "Keep every Fatha quick and crisp: Ka-ta-ba!", "كَتَبَ، خَلَقَ، فَتَحَ"),
                    VideoSlide("Kasra Below = 'i'", "سَمِعَ", "Lower the jaw gently for Kasra: Sa-mi-'a!", "سَمِعَ، عَلِمَ، شَهِدَ"),
                    VideoSlide("Damma Curled = 'u'", "كُتِبَ", "Round both lips into a circle for Damma: Ku-ti-ba!", "كُتِبَ، خُلِقَ، رُزِقَ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q3_1", "Where is a Kasra (ِ) placed on an Arabic letter?", "بِ", listOf("Above the letter", "Below the letter", "To the left", "Inside the letter"), 1, "Kasra is placed below the letter and makes a short 'i' sound."),
                QuizQuestion("q3_2", "How do you pronounce كَتَبَ?", "كَتَبَ", listOf("Ku-ti-ba", "Ka-ta-ba", "Ki-ta-bi", "Ka-tu-bu"), 1, "All three letters carry Fatha: Ka-ta-ba."),
                QuizQuestion("q3_3", "Which vowel sign looks like a tiny Waw (ُ) above a letter?", "مُ", listOf("Fatha", "Kasra", "Damma", "Sukoon"), 2, "Damma looks like a small Waw above the letter and gives a short 'u' sound."),
                QuizQuestion("q3_4", "Read the word عَلِمَ carefully:", "عَلِمَ", listOf("'A-la-ma", "'U-li-ma", "'A-li-ma", "'I-la-mu"), 2, "Ayn has Fatha, Lam has Kasra, Meem has Fatha: 'A-li-ma.")
            )
        ),
        CurriculumLevel(
            number = 4,
            title = "Tanween",
            arabicTitle = "التَّنْوِين",
            stageName = "Stage 2 • Vowels & Signs",
            summary = "Master double vowels (Fathatain, Kasratain, Dammatain) that add a ringing 'n' sound at word endings.",
            badgeTitle = "Tanween Star",
            estimatedMinutes = 20,
            lessons = listOf(
                LessonItem(
                    id = "l4_1", levelNumber = 4,
                    title = "Fathatain (ً), Kasratain (ٍ), Dammatain (ٌ)",
                    subtitle = "Double vowel = vowel + 'n' sound",
                    arabicShowcase = "بًا   بٍ   بٌ   •   عَلِيمٌ   •   حَكِيمًا",
                    transliteration = "Ban • Bin • Bun • 'Aleemun • Hakeeman",
                    explanation = "Tanween means 'adding a Noon sound' at the end of a noun: ً = 'an', ٍ = 'in', and ٌ = 'un'.",
                    audioSpeakText = "بًا، بٍ، بٌ. عَلِيمٌ، حَكِيمٌ، رَحِيمًا، يَوْمٍ",
                    practiceItems = listOf(
                        PracticeWord("أَحَدٌ", "A-ha-dun", "One (Dammatain)"),
                        PracticeWord("كِتَابًا", "Ki-taa-ban", "A Book (Fathatain)"),
                        PracticeWord("يَوْمٍ", "Yaw-min", "A Day (Kasratain)"),
                        PracticeWord("سَلَامٌ", "Sa-laa-mun", "Peace (Dammatain)")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_4", levelNumber = 4,
                title = "Double Vowels: Learning Tanween",
                category = "Noorani Qaida",
                durationLabel = "05:00",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Understand how two Fathas, two Kasras, or two Dammas create the classic Quranic 'an, in, un' endings.",
                slides = listOf(
                    VideoSlide("The Rule of Two", "بًا  بٍ  بٌ", "Double the stroke, add an 'n'! Ban, Bin, Bun.", "بًا، بٍ، بٌ"),
                    VideoSlide("Quranic Word Practice", "غَفُورٌ رَحِيمٌ", "Spot Dammatain at the end of Allah's beautiful names.", "غَفُورٌ رَحِيمٌ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q4_1", "What sound does Dammatain (ٌ) add to the end of a letter?", "دٌ", listOf("-an", "-in", "-un", "-oo"), 2, "Dammatain (ٌ) produces the '-un' sound, as in Ahadun."),
                QuizQuestion("q4_2", "How do you read كِتَابًا?", "كِتَابًا", listOf("Kitaabun", "Kitaabin", "Kitaaban", "Kitaaba"), 2, "Fathatain (ً) gives the '-an' sound: Kitaaban."),
                QuizQuestion("q4_3", "Which sign represents Kasratain?", "مٍ", listOf("Two lines above (ً)", "Two lines below (ٍ)", "Two Dammas above (ٌ)", "A small circle (ْ)"), 1, "Kasratain is two short strokes below the letter giving '-in'."),
                QuizQuestion("q4_4", "Read the Quranic word سَلَامٌ:", "سَلَامٌ", listOf("Salaaman", "Salaamin", "Salaamun", "Salaamu"), 2, "Meem carries Dammatain: Salaamun.")
            )
        ),
        CurriculumLevel(
            number = 5,
            title = "Sukoon and Shaddah",
            arabicTitle = "السُّكُونُ وَالشَّدَّة",
            stageName = "Stage 2 • Vowels & Signs",
            summary = "Join resting letters cleanly with Sukoon (ْ) and emphasize doubled letters powerfully with Shaddah (ّ).",
            badgeTitle = "Rhythm Keeper",
            estimatedMinutes = 25,
            lessons = listOf(
                LessonItem(
                    id = "l5_1", levelNumber = 5,
                    title = "Sukoon / Jazm (ْ) — Resting Sound",
                    subtitle = "Connecting a vowel letter to a resting letter",
                    arabicShowcase = "أَبْ  أَتْ  أَسْ   •   مِنْ   •   قُلْ",
                    transliteration = "Ab • At • As • Min • Qul",
                    explanation = "A letter with Sukoon has no vowel of its own; it joins smoothly with the vowel letter right before it.",
                    audioSpeakText = "أَبْ، أَتْ، مِنْ، قُلْ، الْحَمْدُ",
                    practiceItems = listOf(
                        PracticeWord("مِنْ", "Min", "From"),
                        PracticeWord("قُلْ", "Qul", "Say"),
                        PracticeWord("الْحَمْدُ", "Al-ham-du", "All praise"),
                        PracticeWord("يَعْلَمُ", "Ya'-la-mu", "He knows")
                    )
                ),
                LessonItem(
                    id = "l5_2", levelNumber = 5,
                    title = "Shaddah / Tashdeed (ّ) — Double Strength",
                    subtitle = "Pressing a letter twice smoothly",
                    arabicShowcase = "رَبِّ   •   إِنَّ   •   ثُمَّ   •   اللَّهُ",
                    transliteration = "Rab-bi • In-na • Thum-ma • Allaahu",
                    explanation = "Shaddah looks like a tiny 'w' above a letter. Read the letter twice—first resting (Sukoon), then with its vowel!",
                    audioSpeakText = "رَبِّ، إِنَّ، ثُمَّ، حَقٌّ، اللَّهُ",
                    practiceItems = listOf(
                        PracticeWord("رَبِّ", "Rab-bi", "My Lord"),
                        PracticeWord("إِنَّ", "In-na", "Indeed (with Ghunnah)"),
                        PracticeWord("حَقٌّ", "Haq-qun", "Truth"),
                        PracticeWord("تَبَّتْ", "Tab-bat", "Perished")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_5", levelNumber = 5,
                title = "Sukoon & Shaddah Made Easy",
                category = "Noorani Qaida",
                durationLabel = "06:30",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Learn how Sukoon stops a syllable cleanly and Shaddah doubles a letter with authentic Quranic rhythm.",
                slides = listOf(
                    VideoSlide("Resting with Sukoon", "قُلْ  •  مِنْ", "Never add an extra 'uh' after a Sukoon—stop cleanly on the consonant.", "قُلْ، مِنْ، لَمْ"),
                    VideoSlide("Power of Shaddah", "رَبِّ  •  إِنَّ", "Shaddah = Sukoon + Vowel combined into one strong letter!", "رَبِّ، إِنَّ، ثُمَّ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q5_1", "What does a Shaddah (ّ) tell the reader to do?", "رَبِّ", listOf("Skip the letter", "Stretch it 4 counts", "Pronounce the letter twice with firmness", "Add an 'n' sound"), 2, "Shaddah doubles the letter: first resting, then with its vowel."),
                QuizQuestion("q5_2", "How do you pronounce قُلْ?", "قُلْ", listOf("Qa-la", "Qul", "Qi-li", "Qul-la"), 1, "Qaf has Damma and Lam has Sukoon: Qul."),
                QuizQuestion("q5_3", "Which sign indicates a resting consonant with no vowel?", "نْ", listOf("Fatha", "Tanween", "Sukoon", "Madd"), 2, "Sukoon (ْ) marks a resting letter."),
                QuizQuestion("q5_4", "In إِنَّ (Inna), what special sound occurs on Noon with Shaddah?", "إِنَّ", listOf("Qalqalah bounce", "2-count nasal Ghunnah", "Silent Noon", "Short Fatha only"), 1, "Noon with Shaddah always carries a 2-count nasal Ghunnah.")
            )
        ),
        CurriculumLevel(
            number = 6,
            title = "Madd Rules",
            arabicTitle = "أَحْكَامُ المَدّ",
            stageName = "Stage 2 • Vowels & Signs",
            summary = "Stretch long vowels gracefully for 2, 4, or 6 counts using Alif, Waw, and Ya Maddah.",
            badgeTitle = "Melodic Reader",
            estimatedMinutes = 25,
            lessons = listOf(
                LessonItem(
                    id = "l6_1", levelNumber = 6,
                    title = "Three Madd Letters (ا و ي)",
                    subtitle = "Natural 2-count elongation",
                    arabicShowcase = "بَا  بُو  بِي   •   نُوحِيهَا",
                    transliteration = "Baa • Boo • Bee • Noo-hee-haa",
                    explanation = "The single Quranic word نُوحِيهَا contains all three Madd letters: Waw after Damma (نُو), Ya after Kasra (حِي), and Alif after Fatha (هَا)!",
                    audioSpeakText = "بَا، بُو، بِي. نُوحِيهَا. قَالَ، يَقُولُ، قِيلَ",
                    practiceItems = listOf(
                        PracticeWord("قَالَ", "Qaa-la", "Alif Madd (2 counts)"),
                        PracticeWord("يَقُولُ", "Ya-qoo-lu", "Waw Madd (2 counts)"),
                        PracticeWord("قِيلَ", "Qee-la", "Ya Madd (2 counts)"),
                        PracticeWord("نُوحِيهَا", "Noo-hee-haa", "All 3 Madds in one word")
                    )
                ),
                LessonItem(
                    id = "l6_2", levelNumber = 6,
                    title = "Long Madd (~) & Standing Vowels",
                    subtitle = "4 to 6 counts of graceful elongation",
                    arabicShowcase = "جَاءَ   •   وَلَا الضَّالِّينَ   •   ذَٰلِكَ",
                    transliteration = "Jaaa'a • Wa lad-daaaalleen • Dhaalika",
                    explanation = "When you see the wavy Madd ribbon (~) above a letter, stretch smoothly for 4–5 counts (before Hamzah) or 6 counts (before Shaddah).",
                    audioSpeakText = "جَاءَ، سَمَاءٌ، ذَٰلِكَ، الرَّحْمَٰنِ",
                    practiceItems = listOf(
                        PracticeWord("جَاءَ", "Jaaaa-a", "4–5 counts before Hamzah"),
                        PracticeWord("الرَّحْمَٰنِ", "Ar-Rahmaani", "Standing Fatha = 2 counts"),
                        PracticeWord("إِلَٰهَ", "Ilaaha", "Standing Alif"),
                        PracticeWord("وَلَا الضَّالِّينَ", "Wa lad-daaaalleen", "6 counts Madd Lazim")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_6", levelNumber = 6,
                title = "The Beauty of Madd Elongation",
                category = "Tajweed & Madd",
                durationLabel = "05:50",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Count beats on your fingers to stretch Natural Madd (2 counts) and Long Madd (4–6 counts) accurately.",
                slides = listOf(
                    VideoSlide("The Magic Word", "نُوحِيهَا", "Noo (2) - Hee (2) - Haa (2). All three Madd letters in one word from the Quran!", "نُوحِيهَا"),
                    VideoSlide("The Wavy Madd Sign", "جَاءَ  •  الضَّالِّينَ", "Spot the wavy ribbon (~) and hold your voice steadily like a calm wave.", "جَاءَ، وَلَا الضَّالِّينَ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q6_1", "Which three letters are the Madd (elongation) letters?", "ا  و  ي", listOf("ب ت ث", "ا و ي", "س ش ص", "ق ك ل"), 1, "Alif, Waw, and Ya are the three Madd letters."),
                QuizQuestion("q6_2", "How many counts do you stretch Natural Madd (Madd Tabee'ee)?", "قَالَ", listOf("1 count", "2 counts", "6 counts", "No stretch"), 1, "Madd Tabee'ee is always stretched for 2 counts."),
                QuizQuestion("q6_3", "What does the wavy sign (~) above a letter indicate?", "جَاءَ", listOf("Stop immediately", "Read faster", "Elongate longer (4–6 counts)", "Whisper the letter"), 2, "The wavy Madd mark signals an extended 4 to 6 count elongation."),
                QuizQuestion("q6_4", "Which Quranic word contains all three Madd letters?", "نُوحِيهَا", listOf("كَتَبَ", "قُلْ", "نُوحِيهَا", "رَبِّ"), 2, "نُوحِيهَا has Waw Madd, Ya Madd, and Alif Madd.")
            )
        ),
        CurriculumLevel(
            number = 7,
            title = "Joining Arabic Letters",
            arabicTitle = "وَصْلُ الحُرُوف",
            stageName = "Stage 3 • Noorani Qaida",
            summary = "Connect isolated letters smoothly into complete Arabic words while respecting non-connecting letters.",
            badgeTitle = "Word Builder",
            estimatedMinutes = 25,
            lessons = listOf(
                LessonItem(
                    id = "l7_1", levelNumber = 7,
                    title = "Building 3-Letter Words",
                    subtitle = "From separate letters to joined script",
                    arabicShowcase = "ك + ت + ب = كَتَبَ   •   س + ج + د = سَجَدَ",
                    transliteration = "Ka + ta + ba = Kataba • Sa + ja + da = Sajada",
                    explanation = "Watch how the tail of each connecting letter opens up to link with the next letter.",
                    audioSpeakText = "كاف، تاء، باء: كَتَبَ. سين، جيم، دال: سَجَدَ. ميم، سين، جيم، دال: مَسْجِدٌ",
                    practiceItems = listOf(
                        PracticeWord("سَجَدَ", "Sa-ja-da", "س + ج + د"),
                        PracticeWord("قَرَأَ", "Qa-ra-a", "ق + ر + أ (Ra doesn't join left)"),
                        PracticeWord("مَسْجِدٌ", "Mas-ji-dun", "م + س + ج + د"),
                        PracticeWord("رَسُولٌ", "Ra-soo-lun", "ر + س + و + ل")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_7", levelNumber = 7,
                title = "Joining Letters Like a Pro",
                category = "Noorani Qaida",
                durationLabel = "06:00",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Step-by-step demonstration of how letters link together and where breaks occur after ا د ذ ر ز و.",
                slides = listOf(
                    VideoSlide("Friendly Connectors", "ب + س + م = بِسْمِ", "Ba, Seen, and Meem all join together seamlessly to form Bismi!", "بِسْمِ اللَّهِ"),
                    VideoSlide("Respecting the 6 Breakers", "ر + س + و + ل = رَسُولٌ", "Ra and Waw connect from the right, leaving a clean space on their left.", "رَسُولٌ كَرِيمٌ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q7_1", "Which word is formed by joining س + ج + د?", "س + ج + د", listOf("كَتَبَ", "سَجَدَ", "حَمِدَ", "قَرَأَ"), 1, "س + ج + د joins to form سَجَدَ (Sajada — He prostrated)."),
                QuizQuestion("q7_2", "Why is there a gap after ر in رَسُولٌ?", "رَسُولٌ", listOf("It is a printing space", "Ra never joins to the letter after it", "Seen cannot connect", "It is two words"), 1, "Ra (ر) is one of the 6 non-connecting letters."),
                QuizQuestion("q7_3", "Which letters make up بِسْمِ?", "بِسْمِ", listOf("ب س م", "ت ش م", "ن س ل", "ب ص ن"), 0, "Bismi is composed of Ba (ب), Seen (س), and Meem (م)."),
                QuizQuestion("q7_4", "How many letters are joined in مَسْجِدٌ?", "مَسْجِدٌ", listOf("3 letters", "4 letters", "5 letters", "2 letters"), 1, "Meem, Seen, Jeem, and Dal — 4 letters.")
            )
        )
    )
}
