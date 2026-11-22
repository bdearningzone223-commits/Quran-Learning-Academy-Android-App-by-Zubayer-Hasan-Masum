package com.example.data.content

import com.example.data.model.CurriculumLevel
import com.example.data.model.LessonItem
import com.example.data.model.PracticeWord
import com.example.data.model.QuizQuestion
import com.example.data.model.VideoLessonData
import com.example.data.model.VideoSlide

object CurriculumLevelsPart2 {
    val levels: List<CurriculumLevel> = listOf(
        CurriculumLevel(
            number = 8,
            title = "Complete Noorani Qaida",
            arabicTitle = "القَاعِدَةُ النُّورَانِيَّةُ الكَامِلَة",
            stageName = "Stage 3 • Noorani Qaida",
            summary = "Combine all Qaida foundations—Huroof Muqatta'at, Leen letters, Hamzatul Wasl, and stopping signs.",
            badgeTitle = "Qaida Champion",
            estimatedMinutes = 30,
            lessons = listOf(
                LessonItem(
                    id = "l8_1", levelNumber = 8,
                    title = "Leen Letters & Hamzatul Wasl",
                    subtitle = "Soft diphthongs (ـَوْ / ـَيْ) & connecting Alif",
                    arabicShowcase = "خَوْفٍ   •   بَيْتِ   •   بِسْمِ اللَّهِ",
                    transliteration = "Khaw-fin • Bay-ti • Bismillaahi",
                    explanation = "When Waw Sukoon or Ya Sukoon comes after Fatha, glide softly (aw / ay). With Hamzatul Wasl (ٱ), connect straight into the next pronounced letter.",
                    audioSpeakText = "خَوْفٍ، بَيْتِ، قُرَيْشٍ، صَيْفِ، بِسْمِ اللَّهِ",
                    practiceItems = listOf(
                        PracticeWord("خَوْفٍ", "Khaw-fin", "Waw Leen — soft 'aw'"),
                        PracticeWord("قُرَيْشٍ", "Qu-ray-shin", "Ya Leen — soft 'ay'"),
                        PracticeWord("الْحَمْدُ لِلَّهِ", "Al-hamdu lillaahi", "Smooth Al- connection"),
                        PracticeWord("الم", "Alif-Laaaam-Meeeem", "Huroof Muqatta'at")
                    )
                ),
                LessonItem(
                    id = "l8_2", levelNumber = 8,
                    title = "Waqf (Stopping Rules)",
                    subtitle = "How to pause gracefully at verse endings",
                    arabicShowcase = "الرَّحِيمِ ⟵ الرَّحِيمْ   •   رَحْمَةٌ ⟵ رَحْمَهْ",
                    transliteration = "Ar-Raheem • Rahmah",
                    explanation = "When stopping at the end of an Ayah, turn the final vowel into a quiet Sukoon. A round Ta Marbutah (ة) softens into a breathy Ha (هْ).",
                    audioSpeakText = "الرَّحِيمْ، العَالَمِينْ، رَحْمَهْ، الصَّلَاهْ",
                    practiceItems = listOf(
                        PracticeWord("العَالَمِينَ ⟵ العَالَمِينْ", "Al-'aalameen", "Stop with Sukoon"),
                        PracticeWord("قِيَامَةٌ ⟵ قِيَامَهْ", "Qiyaamah", "Ta Marbutah becomes Ha"),
                        PracticeWord("عَلِيمًا ⟵ عَلِيمَا", "'Aleemaa", "Fathatain stops as Alif Madd"),
                        PracticeWord("أَحَدٌ ⟵ أَحَدْ", "Ahad", "Stop with Qalqalah bounce")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_8", levelNumber = 8,
                title = "Completing the Noorani Qaida",
                category = "Noorani Qaida",
                durationLabel = "07:15",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Master Leen letters, Mushaf stopping marks (م ط ج لا), and Huroof Muqatta'at to graduate from Noorani Qaida.",
                slides = listOf(
                    VideoSlide("Soft Leen Glide", "خَوْفٍ  •  بَيْتِ", "Glide smoothly on Waw and Ya Sukoon after Fatha.", "خَوْفٍ، بَيْتِ، يَوْمِ"),
                    VideoSlide("Stopping Gracefully", "ة ⟵ هْ", "Ta Marbutah softens into a gentle 'h' when you pause at the end of a verse.", "جَنَّهْ، رَحْمَهْ، زَكَاهْ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q8_1", "What happens to Ta Marbutah (ة) when you stop on it?", "رَحْمَةٌ", listOf("Read as heavy T", "Softens into a breathy Ha (هْ)", "Stretched 6 counts", "Skipped completely"), 1, "When stopping, Ta Marbutah becomes a quiet Ha (هْ)."),
                QuizQuestion("q8_2", "Which word contains a Ya Leen (ـَيْ)?", "بَيْتِ", listOf("قِيلَ", "بَيْتِ", "كُتِبَ", "نُورٌ"), 1, "بَيْتِ has Ya Sukoon preceded by Fatha."),
                QuizQuestion("q8_3", "How do we stop on a word ending with Fathatain like عَلِيمًا?", "عَلِيمًا", listOf("Stop with Sukoon 'm'", "Stop with 2-count Alif Madd ('Aleemaa)", "Stop with 'n'", "Cannot stop"), 1, "Fathatain transforms into a 2-count Alif Madd (Madd Iwad) upon stopping."),
                QuizQuestion("q8_4", "What are letters like الم (Alif-Lam-Meem) at Surah openings called?", "الم", listOf("Harakat", "Huroof Muqatta'at", "Tanween", "Qalqalah"), 1, "They are the Disconnected Letters (Huroof Muqatta'at), recited by their letter names.")
            )
        ),
        CurriculumLevel(
            number = 9,
            title = "Arabic Word Reading",
            arabicTitle = "قِرَاءَةُ الكَلِمَاتِ العَرَبِيَّة",
            stageName = "Stage 3 • Noorani Qaida",
            summary = "Build confident fluency reading multi-syllable Quranic words and short meaningful phrases.",
            badgeTitle = "Fluent Reader",
            estimatedMinutes = 25,
            lessons = listOf(
                LessonItem(
                    id = "l9_1", levelNumber = 9,
                    title = "Multi-Syllable Quranic Vocabulary",
                    subtitle = "Blending vowels, Sukoon, Shaddah & Madd seamlessly",
                    arabicShowcase = "المُسْتَقِيمَ   •   المُتَّقِينَ   •   يَسْتَعِينُ",
                    transliteration = "Al-Mustaqeema • Al-Muttaqeena • Yasta'eenu",
                    explanation = "Break longer words into small rhythmic syllables: Al + Mus + ta + qee + ma. Soon your eyes will read the whole word effortlessly!",
                    audioSpeakText = "المُسْتَقِيمَ، المُتَّقِينَ، يَسْتَعِينُ، المُفْلِحُونَ",
                    practiceItems = listOf(
                        PracticeWord("المُسْتَقِيمَ", "Al-Mus-ta-qee-ma", "The Straight (Path)"),
                        PracticeWord("المُتَّقِينَ", "Al-Mut-ta-qee-na", "The God-Conscious"),
                        PracticeWord("المُفْلِحُونَ", "Al-Muf-li-hoo-na", "The Successful"),
                        PracticeWord("نَسْتَعِينُ", "Nas-ta-'ee-nu", "We seek help")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_9", levelNumber = 9,
                title = "Reading Full Quranic Words Smoothly",
                category = "Quran Reading",
                durationLabel = "06:15",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "A syllable-by-syllable technique that helps young learners conquer long Quranic words without hesitation.",
                slides = listOf(
                    VideoSlide("Syllable Stepping Stones", "الْ + مُسْ + تَ + قِي + مَ", "Connect one resting syllable at a time for clear, confident flow.", "الصِّرَاطَ المُسْتَقِيمَ"),
                    VideoSlide("Connected Phrases", "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "Blend words together without choppy pauses.", "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q9_1", "Read the word المُسْتَقِيمَ accurately:", "المُسْتَقِيمَ", listOf("Al-Mas-ta-qa-ma", "Al-Mus-ta-qee-ma", "Al-Mis-ti-qoo-ma", "Al-Mus-ta-qim"), 1, "Meem has Damma, Seen has Sukoon, Qaf has Ya Madd: Al-Mustaqeema."),
                QuizQuestion("q9_2", "Which word means 'We seek help' from Surah Al-Fatihah?", "نَسْتَعِينُ", listOf("نَعْبُدُ", "نَسْتَعِينُ", "اهْدِنَا", "العَالَمِينَ"), 1, "نَسْتَعِينُ (Nasta'eenu) means 'we ask for help'."),
                QuizQuestion("q9_3", "How is المُفْلِحُونَ pronounced?", "المُفْلِحُونَ", listOf("Al-Muf-li-hoo-na", "Al-Maf-la-ha-na", "Al-Mif-li-hee-na", "Al-Muf-lu-hoon"), 0, "Al-Muf-li-hoo-na with Waw Madd on Ha."),
                QuizQuestion("q9_4", "What is the best strategy for reading a long Arabic word?", "المُتَّقِينَ", listOf("Guess from memory", "Break into syllables around Sukoon & Madd", "Skip the middle letters", "Ignore vowel marks"), 1, "Breaking words into vowel, Sukoon, and Madd syllables builds fluent accuracy.")
            )
        ),
        CurriculumLevel(
            number = 10,
            title = "Basic Tajweed Rules",
            arabicTitle = "أَحْكَامُ التَّجْوِيدِ الأَسَاسِيَّة",
            stageName = "Stage 4 • Tajweed Mastery",
            summary = "Beautify your recitation with Ghunnah, Qalqalah, Idgham, Ikhfa, Iqlab, and Izhar.",
            badgeTitle = "Tajweed Guardian",
            estimatedMinutes = 30,
            lessons = listOf(
                LessonItem(
                    id = "l10_1", levelNumber = 10,
                    title = "Ghunnah & Qalqalah Essentials",
                    subtitle = "Nasal humming & crisp echoing bounce",
                    arabicShowcase = "إِنَّ   •   ثُمَّ   •   قُلْ هُوَ اللَّهُ أَحَدٌ",
                    transliteration = "Inna • Thumma • Qul Huwallahu Ahad",
                    explanation = "Apply 2-count nasal Ghunnah on نّ and مّ, and give a crisp Qalqalah bounce to ق ط ب ج د when they carry Sukoon.",
                    audioSpeakText = "إِنَّ، ثُمَّ، قُلْ هُوَ اللَّهُ أَحَدْ، قُلْ أَعُوذُ بِرَبِّ الفَلَقْ",
                    practiceItems = listOf(
                        PracticeWord("إِنَّا", "In-naa", "Ghunnah on Noon Shaddah"),
                        PracticeWord("أَحَدْ", "Aha-d", "Qalqalah on Dal"),
                        PracticeWord("الفَلَقْ", "Al-Fala-q", "Qalqalah on Qaf"),
                        PracticeWord("خَلَقْ", "Khala-q", "Qalqalah on Qaf")
                    )
                ),
                LessonItem(
                    id = "l10_2", levelNumber = 10,
                    title = "Rules of Noon Sakinah & Tanween",
                    subtitle = "Izhar, Idgham, Iqlab, and Ikhfa",
                    arabicShowcase = "مِنْ خَوْفٍ  •  مَنْ يَعْمَلْ  •  مِنْ بَعْدِ  •  مِنْ شَرِّ",
                    transliteration = "Izhar • Idgham • Iqlab • Ikhfa",
                    explanation = "Noon Sakinah (نْ) changes its blend depending on the next letter: Clear (Izhar) before throat letters, Merge (Idgham) into ي ر م ل و ن, Convert (Iqlab) before ب, and Conceal (Ikhfa) before the remaining 15 letters.",
                    audioSpeakText = "مِنْ خَوْفٍ. مَنْ يَعْمَلْ. مِنْ بَعْدِ. مِنْ شَرِّ مَا خَلَقَ",
                    practiceItems = listOf(
                        PracticeWord("مِنْ خَوْفٍ", "Min khawfin", "Izhar — clear N before Kha"),
                        PracticeWord("مَنْ يَعْمَلْ", "May-ya'mal", "Idgham — merge into Ya"),
                        PracticeWord("مِنْ بَعْدِ", "Mim-ba'di", "Iqlab — turn into Meem"),
                        PracticeWord("مِنْ شَرِّ", "Min(ng) sharri", "Ikhfa — soft nasal concealment")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_10", levelNumber = 10,
                title = "Core Tajweed Rules for Young Reciters",
                category = "Tajweed Learning",
                durationLabel = "08:00",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Color-coded Tajweed lessons showing children exactly where to echo (Qalqalah), hum (Ghunnah), and merge (Idgham).",
                slides = listOf(
                    VideoSlide("The 5 Bouncing Letters", "قُطْبُ جَدٍّ", "Remember the fun phrase 'Qutbu Jaddin' for Qalqalah!", "قاف، طاء، باء، جيم، دال"),
                    VideoSlide("Four Rules of Noon Sakinah", "إظهار • إدغام • إقلاب • إخفاء", "Look one letter ahead to know whether to read Noon clearly, merge it, flip it to Meem, or hide it softly.", "مِنْ خَوْفٍ، مِنْ بَعْدِ، مِنْ شَرِّ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q10_1", "Which 5 letters are the Qalqalah (echoing) letters?", "قُطْبُ جَدٍّ", listOf("ق ط ب ج د", "ا و ي ن م", "س ش ص ض ط", "ء ه ع ح غ"), 0, "Qaf, Taa, Ba, Jeem, and Dal (قُطْبُ جَدٍّ) are the 5 Qalqalah letters."),
                QuizQuestion("q10_2", "What rule applies in مِنْ بَعْدِ (Noon Sakinah before Ba)?", "مِنْ بَعْدِ", listOf("Izhar", "Iqlab", "Qalqalah", "Madd"), 1, "Iqlab converts Noon Sakinah into a hidden Meem sound before Ba (ب)."),
                QuizQuestion("q10_3", "How long is Ghunnah held on نّ or مّ?", "إِنَّ", listOf("0 counts", "2 counts", "6 counts", "10 counts"), 1, "Ghunnah is held gently for 2 counts."),
                QuizQuestion("q10_4", "Which Tajweed rule applies in مِنْ شَرِّ (Noon Sakinah before Sheen)?", "مِنْ شَرِّ", listOf("Ikhfa", "Iqlab", "Izhar", "Qalqalah"), 0, "Sheen is one of the 15 Ikhfa letters.")
            )
        ),
        CurriculumLevel(
            number = 11,
            title = "Makharijul Huruf",
            arabicTitle = "مَخَارِجُ الحُرُوف",
            stageName = "Stage 4 • Tajweed Mastery",
            summary = "Pronounce every Arabic letter from its authentic origin point across the throat, tongue, lips, and nose.",
            badgeTitle = "Pure Articulation",
            estimatedMinutes = 25,
            lessons = listOf(
                LessonItem(
                    id = "l11_1", levelNumber = 11,
                    title = "The 5 Major Articulation Regions",
                    subtitle = "Al-Jawf, Al-Halq, Al-Lisan, Ash-Shafatan, Al-Khayshoom",
                    arabicShowcase = "الحَلْق   •   اللِّسَان   •   الشَّفَتَان",
                    transliteration = "Throat • Tongue • Lips",
                    explanation = "Every Arabic letter has a home (Makhraj). Pronouncing Ha (ح) from the middle throat and Ha (ه) from the deepest throat keeps meanings pure.",
                    audioSpeakText = "حَمْدٌ، هُدًى. قَلْبٌ، كَلْبٌ. صَبْرٌ، سَلَامٌ",
                    practiceItems = listOf(
                        PracticeWord("ح / ه", "Middle vs Deep Throat", "حَمْدٌ vs هُدًى"),
                        PracticeWord("ق / ك", "Deep vs Front Back-Tongue", "قَلْبٌ vs كِتَابٌ"),
                        PracticeWord("ث / س / ص", "Teeth Tip vs Light vs Heavy", "ثَوْبٌ • سَلَامٌ • صَبْرٌ"),
                        PracticeWord("ب / م / ف / و", "The 4 Lip Letters", "Ash-Shafatan")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_11", levelNumber = 11,
                title = "Makharijul Huruf: Where Every Sound Lives",
                category = "Tajweed Learning",
                durationLabel = "07:40",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Interactive guide to the 5 articulation regions so children speak authentic, crystal-clear Quranic Arabic.",
                slides = listOf(
                    VideoSlide("The 3 Throat Levels", "ء ه  |  ع ح  |  غ خ", "Deepest throat (ء ه), middle throat (ع ح), upper throat (غ خ).", "أَهْلٌ، عِلْمٌ، خَيْرٌ"),
                    VideoSlide("Lips & Nasal Cavity", "ف ب م و  •  نّ مّ", "Feel your lips gently touch for Ba and Meem, and hum through your nose for Ghunnah.", "بِسْمِ، مَسْجِدٌ، إِنَّ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q11_1", "Which region produces the letters ع (Ayn) and ح (Ha)?", "ع  ح", listOf("The Lips", "Middle of the Throat", "Tip of the Tongue", "Nasal Passage"), 1, "Ayn and Ha originate from the middle of the throat (Wasat al-Halq)."),
                QuizQuestion("q11_2", "How many major Makharij regions exist in Tajweed?", "٥", listOf("3 regions", "5 regions", "8 regions", "10 regions"), 1, "The 5 major regions are Al-Jawf, Al-Halq, Al-Lisan, Ash-Shafatan, and Al-Khayshoom."),
                QuizQuestion("q11_3", "Which letters come from Ash-Shafatan (the lips)?", "ف ب م و", listOf("ق ك ل ن", "ف ب م و", "ع ح غ خ", "د ذ ر ز"), 1, "Fa, Ba, Meem, and Waw are articulated using the lips."),
                QuizQuestion("q11_4", "Where does the nasal resonance (Ghunnah) originate?", "الخَيْشُوم", listOf("Al-Khayshoom (Nasal Passage)", "Al-Jawf", "Upper Molars", "Deepest Throat"), 0, "Ghunnah flows from Al-Khayshoom, the nasal cavity.")
            )
        ),
        CurriculumLevel(
            number = 12,
            title = "Short Surahs",
            arabicTitle = "قِصَارُ السُّوَر",
            stageName = "Stage 5 • Holy Quran",
            summary = "Recite and memorize Surah Al-Fatihah, Al-Ikhlas, Al-Falaq, An-Nas, Al-Kawthar, and Al-Asr with Tajweed.",
            badgeTitle = "Surah Reciter",
            estimatedMinutes = 35,
            lessons = listOf(
                LessonItem(
                    id = "l12_1", levelNumber = 12,
                    title = "Surah Al-Fatihah — The Opening",
                    subtitle = "7 blessed verses recited in every prayer",
                    arabicShowcase = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ ۝ الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
                    transliteration = "Bismillaahir Rahmaanir Raheem • Alhamdu lillaahi Rabbil 'aalameen",
                    explanation = "Practice crisp Ha (ح) in Al-Hamdu and Ar-Rahman, clear Ayn (ع) in Al-'Aalameen, and 6-count Madd in Wa lad-daalleen.",
                    audioSpeakText = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ. الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ. الرَّحْمَٰنِ الرَّحِيمِ. مَالِكِ يَوْمِ الدِّينِ",
                    practiceItems = listOf(
                        PracticeWord("بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "Ayah 1", "In the name of Allah..."),
                        PracticeWord("الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "Ayah 2", "All praise is for Allah..."),
                        PracticeWord("إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "Ayah 5", "You alone we worship..."),
                        PracticeWord("اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Ayah 6", "Guide us to the Straight Path")
                    )
                ),
                LessonItem(
                    id = "l12_2", levelNumber = 12,
                    title = "The Three Quls (Al-Ikhlas, Al-Falaq, An-Nas)",
                    subtitle = "Daily protection Surahs for children",
                    arabicShowcase = "قُلْ هُوَ اللَّهُ أَحَدٌ ۝ اللَّهُ الصَّمَدُ",
                    transliteration = "Qul Huwa Allaahu Ahad • Allaahus Samad",
                    explanation = "Notice the crisp Qalqalah bounce at the end of every verse in Surah Al-Ikhlas (أَحَدْ، الصَّمَدْ، يُولَدْ) and Al-Falaq (الفَلَقْ، خَلَقْ).",
                    audioSpeakText = "قُلْ هُوَ اللَّهُ أَحَدٌ. اللَّهُ الصَّمَدُ. لَمْ يَلِدْ وَلَمْ يُولَدْ. وَلَمْ يَكُنْ لَهُ كُفُوًا أَحَدٌ",
                    practiceItems = listOf(
                        PracticeWord("قُلْ هُوَ اللَّهُ أَحَدٌ", "Al-Ikhlas 1", "Qalqalah on Dal"),
                        PracticeWord("قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "Al-Falaq 1", "Qalqalah on Qaf"),
                        PracticeWord("قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "An-Nas 1", "Ghunnah on Noon Shaddah"),
                        PracticeWord("إِنَّا أَعْطَيْنَاكَ الْكَوْثَرَ", "Al-Kawthar 1", "Ghunnah + Madd")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_12", levelNumber = 12,
                title = "Beautiful Recitation of Short Surahs",
                category = "Quran Learning",
                durationLabel = "08:30",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Follow along verse-by-verse with Tajweed reminders for Surah Al-Fatihah and the Three Quls.",
                slides = listOf(
                    VideoSlide("Surah Al-Fatihah", "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "Recite calmly, pausing gracefully at each verse marker.", "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ"),
                    VideoSlide("Surah Al-Ikhlas", "قُلْ هُوَ اللَّهُ أَحَدٌ", "Listen for the echoing Qalqalah on every ending Dal (دْ).", "قُلْ هُوَ اللَّهُ أَحَدٌ، اللَّهُ الصَّمَدُ")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q12_1", "How many Ayahs are in Surah Al-Fatihah?", "الفَاتِحَة", listOf("4 Ayahs", "5 Ayahs", "7 Ayahs", "10 Ayahs"), 2, "Surah Al-Fatihah consists of 7 blessed Ayahs."),
                QuizQuestion("q12_2", "Which Surah begins with قُلْ هُوَ اللَّهُ أَحَدٌ?", "قُلْ هُوَ اللَّهُ أَحَدٌ", listOf("Surah An-Nas", "Surah Al-Ikhlas", "Surah Al-Asr", "Surah Al-Kawthar"), 1, "That is the opening verse of Surah Al-Ikhlas (Chapter 112)."),
                QuizQuestion("q12_3", "What Tajweed rule shines at the verse endings of Surah Al-Ikhlas?", "أَحَدْ • الصَّمَدْ", listOf("Qalqalah on Dal", "Iqlab", "Idgham", "Izhar"), 0, "Stopping on Dal (دْ) produces a crisp Qalqalah bounce."),
                QuizQuestion("q12_4", "Which Surah teaches faith, good deeds, truth, and patience?", "وَالْعَصْرِ", listOf("Surah Al-Asr", "Surah Al-Falaq", "Surah An-Nas", "Surah Al-Kawthar"), 0, "Surah Al-Asr emphasizes faith, righteous deeds, truth, and patience.")
            )
        ),
        CurriculumLevel(
            number = 13,
            title = "Complete Quran Reading Practice",
            arabicTitle = "تَدْرِيبُ قِرَاءَةِ القُرْآنِ الكَرِيم",
            stageName = "Stage 5 • Holy Quran",
            summary = "Apply every Qaida, Madd, Makharij, and Tajweed skill to recite full Quranic passages with confidence and reverence.",
            badgeTitle = "Quran Academy Graduate",
            estimatedMinutes = 40,
            lessons = listOf(
                LessonItem(
                    id = "l13_1", levelNumber = 13,
                    title = "Ayat al-Kursi & Opening of Al-Baqarah",
                    subtitle = "Fluent Mushaf reading with full Tajweed harmony",
                    arabicShowcase = "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ",
                    transliteration = "Allaahu laa ilaaha illaa Huwal Hayyul Qayyoom",
                    explanation = "Combine steady 2-count Madd, clear throat letters, firm Shaddah, and mindful stopping signs as you transition to reading the complete Mushaf.",
                    audioSpeakText = "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ. لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ",
                    practiceItems = listOf(
                        PracticeWord("الم ۝ ذَٰلِكَ الْكِتَابُ لَا رَيْبَ فِيهِ", "Al-Baqarah 1–2", "Huroof Muqatta'at + Leen"),
                        PracticeWord("هُدًى لِلْمُتَّقِينَ", "Al-Baqarah 2", "Idgham without Ghunnah"),
                        PracticeWord("اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ", "Ayat al-Kursi", "Madd + Shaddah clarity"),
                        PracticeWord("رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً", "Al-Baqarah 201", "Beautiful Quranic Dua")
                    )
                )
            ),
            videoLesson = VideoLessonData(
                id = "vid_13", levelNumber = 13,
                title = "Graduation Recitation: Reading the Holy Quran",
                category = "Quran Learning",
                durationLabel = "09:10",
                instructor = "Ustadh Zubayer Hasan Masum",
                description = "Final capstone guidance by Founder Zubayer Hasan Masum on daily Quran recitation habits, adab, and Tajweed fluency.",
                slides = listOf(
                    VideoSlide("Adab of Recitation", "أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيمِ", "Always begin with Isti'adhah and Basmalah with a calm heart.", "أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيمِ، بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ"),
                    VideoSlide("Lifelong Quran Companion", "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً", "Recite even a few verses every day with reflection and Tajweed.", "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً")
                )
            ),
            quizQuestions = listOf(
                QuizQuestion("q13_1", "What should a student recite before starting Quran reading?", "أَعُوذُ بِاللَّهِ", listOf("Only a Salam", "Isti'adhah (A'oodhu billaah) & Basmalah", "Nothing", "Start from the end"), 1, "Beginning with Isti'adhah and Basmalah follows the prophetic etiquette of recitation."),
                QuizQuestion("q13_2", "In هُدًى لِلْمُتَّقِينَ, what Tajweed rule joins Tanween into Lam?", "هُدًى لِلْمُتَّقِينَ", listOf("Idgham without Ghunnah", "Iqlab", "Qalqalah", "Izhar"), 0, "Tanween followed by Lam (ل) merges completely via Idgham without Ghunnah."),
                QuizQuestion("q13_3", "Which majestic verse begins with اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ?", "آيَةُ الكُرْسِي", listOf("Surah Al-Fatihah 1", "Ayat al-Kursi (Al-Baqarah 255)", "Surah Al-Asr 1", "Surah An-Nas 1"), 1, "This is the opening of Ayat al-Kursi, the greatest verse in the Holy Quran."),
                QuizQuestion("q13_4", "What is the key to keeping Quran recitation fluent after Level 13?", "تِلَاوَة", listOf("Read once a year", "Consistent daily practice with Tajweed", "Skip vowel marks", "Rush through verses"), 1, "Consistent, mindful daily recitation builds lifelong mastery and love for the Quran.")
            )
        )
    )
}

object CurriculumCatalog {
    val allLevels: List<CurriculumLevel> = CurriculumLevelsPart1.levels + CurriculumLevelsPart2.levels
    val totalLessonsCount: Int = allLevels.sumOf { it.lessons.size }
    val allVideos: List<VideoLessonData> = allLevels.map { it.videoLesson }

    fun getLevel(levelNumber: Int): CurriculumLevel =
        allLevels.firstOrNull { it.number == levelNumber } ?: allLevels.first()
}
