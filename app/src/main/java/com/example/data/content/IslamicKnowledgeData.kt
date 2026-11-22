package com.example.data.content

import com.example.data.model.CertificateStage
import com.example.data.model.MakhrajArea
import com.example.data.model.SurahAyah
import com.example.data.model.SurahData
import com.example.data.model.TajweedRuleData
import com.example.ui.theme.TajweedGhunnah
import com.example.ui.theme.TajweedIdgham
import com.example.ui.theme.TajweedIkhfa
import com.example.ui.theme.TajweedMadd
import com.example.ui.theme.TajweedQalqalah

object IslamicKnowledgeData {

    val certificateStages: List<CertificateStage> = listOf(
        CertificateStage(
            stageId = "stage_1_alphabet",
            title = "Arabic Alphabet & Recognition Scholar",
            arabicTitle = "شَهَادَةُ الحُرُوفِ العَرَبِيَّة",
            requiredLevels = listOf(1, 2),
            description = "Awarded for mastering all 29 Arabic letters and recognizing them in any shape."
        ),
        CertificateStage(
            stageId = "stage_2_vowels",
            title = "Harakat, Tanween & Madd Achiever",
            arabicTitle = "شَهَادَةُ الحَرَكَاتِ وَالمُدُود",
            requiredLevels = listOf(3, 4, 5, 6),
            description = "Awarded for accurate pronunciation of Fatha, Kasra, Damma, Tanween, Sukoon, Shaddah, and Madd."
        ),
        CertificateStage(
            stageId = "stage_3_qaida",
            title = "Complete Noorani Qaida Graduate",
            arabicTitle = "شَهَادَةُ إِتْمَامِ القَاعِدَةِ النُّورَانِيَّة",
            requiredLevels = listOf(7, 8, 9),
            description = "Awarded for joining Arabic letters fluently and reading full Quranic words."
        ),
        CertificateStage(
            stageId = "stage_4_tajweed",
            title = "Tajweed & Makharijul Huruf Honoree",
            arabicTitle = "شَهَادَةُ التَّجْوِيدِ وَمَخَارِجِ الحُرُوف",
            requiredLevels = listOf(10, 11),
            description = "Awarded for demonstrating authentic articulation points and essential Tajweed rules."
        ),
        CertificateStage(
            stageId = "stage_5_quran",
            title = "Holy Quran Recitation Distinction",
            arabicTitle = "شَهَادَةُ تِلَاوَةِ القُرْآنِ الكَرِيم",
            requiredLevels = listOf(12, 13),
            description = "Highest Academy Honor awarded for completing Short Surahs and Holy Quran Reading Practice."
        )
    )

    val tajweedRules: List<TajweedRuleData> = listOf(
        TajweedRuleData(
            id = "ghunnah",
            title = "Ghunnah (Nasalization)",
            arabicTitle = "الغُنَّة",
            category = "Noon & Meem Mushaddadah",
            description = "Hold a gentle, pleasant nasal humming sound for 2 counts whenever Noon (نّ) or Meem (مّ) carries a Shaddah.",
            letters = "نّ  •  مّ",
            exampleArabic = "إِنَّ  •  ثُمَّ  •  عَمَّ",
            exampleTransliteration = "In-na  •  Thum-ma  •  Am-ma",
            exampleRuleNote = "Hold the nasal resonance smoothly for 2 beats.",
            accentColor = TajweedGhunnah
        ),
        TajweedRuleData(
            id = "qalqalah",
            title = "Qalqalah (Echoing Bounce)",
            arabicTitle = "القَلْقَلَة",
            category = "Echoing Letters",
            description = "Produce a crisp echoing bounce when any of the five Qalqalah letters (قُطْبُ جَدٍّ) has a Sukoon or when stopping on it.",
            letters = "ق  •  ط  •  ب  •  ج  •  د",
            exampleArabic = "قُلْ هُوَ اللَّهُ أَحَدٌ  •  الفَلَقِ",
            exampleTransliteration = "Qul Huwallahu Aha-d  •  Al-Fala-q",
            exampleRuleNote = "Notice the crisp bounce on د (Dal) and ق (Qaf).",
            accentColor = TajweedQalqalah
        ),
        TajweedRuleData(
            id = "madd_tabee",
            title = "Madd Tabee'ee (Natural Elongation)",
            arabicTitle = "المَدُّ الطَّبِيعِي",
            category = "Elongation Rules",
            description = "Stretch the vowel smoothly for 2 counts when Alif follows Fatha, Waw Sukoon follows Damma, or Ya Sukoon follows Kasra.",
            letters = "ـَا  •  ـُو  •  ـِي",
            exampleArabic = "قَالَ  •  يَقُولُ  •  قِيلَ",
            exampleTransliteration = "Qaa-la  •  Ya-qoo-lu  •  Qee-la",
            exampleRuleNote = "Keep the elongation steady and natural—exactly 2 counts.",
            accentColor = TajweedMadd
        ),
        TajweedRuleData(
            id = "idgham",
            title = "Idgham (Merging)",
            arabicTitle = "الإِدْغَام",
            category = "Noon Sakinah & Tanween",
            description = "Merge Noon Sakinah (نْ) or Tanween smoothly into the following letter when it is one of the 6 letters of يَرْمَلُون.",
            letters = "ي  •  ر  •  م  •  ل  •  و  •  ن",
            exampleArabic = "مَنْ يَعْمَلْ  •  مِنْ رَبِّهِمْ",
            exampleTransliteration = "May-ya'mal  •  Mir-rabbihim",
            exampleRuleNote = "Merge with Ghunnah for ي ن م و and without Ghunnah for ل ر.",
            accentColor = TajweedIdgham
        ),
        TajweedRuleData(
            id = "ikhfa",
            title = "Ikhfa (Gentle Concealment)",
            arabicTitle = "الإِخْفَاء",
            category = "Noon Sakinah & Tanween",
            description = "Pronounce Noon Sakinah or Tanween softly between clear pronunciation and merging with 2 counts of nasal Ghunnah before 15 letters.",
            letters = "ت ث ج د ذ ز س ش ص ض ط ظ ف ق ك",
            exampleArabic = "مِنْ شَرِّ  •  كُنْتُمْ",
            exampleTransliteration = "Min(ng) sharri  •  Kun(ng)tum",
            exampleRuleNote = "Keep the tongue ready for the next letter while humming gently.",
            accentColor = TajweedIkhfa
        ),
        TajweedRuleData(
            id = "iqlab",
            title = "Iqlab (Conversion to Meem)",
            arabicTitle = "الإِقْلَاب",
            category = "Noon Sakinah & Tanween",
            description = "When Noon Sakinah or Tanween is followed by Ba (ب), convert the 'n' sound into a hidden Meem (م) with 2 counts of Ghunnah.",
            letters = "ب",
            exampleArabic = "مِنْ بَعْدِ  •  سَمِيعٌ بَصِيرٌ",
            exampleTransliteration = "Mim-ba'di  •  Samee'um-baseer",
            exampleRuleNote = "Look for the small م sign printed above Noon in the Mushaf.",
            accentColor = TajweedGhunnah
        )
    )

    val makharijAreas: List<MakhrajArea> = listOf(
        MakhrajArea(
            id = "jawf",
            name = "Al-Jawf (Oral & Throat Cavity)",
            arabicName = "الجَوْف",
            letters = "ا  •  و  •  ي (Madd Letters)",
            explanation = "Produces the three long vowel elongation sounds (aa, oo, ee) flowing freely through the open mouth and throat.",
            practiceWord = "نُوحِيهَا"
        ),
        MakhrajArea(
            id = "halq",
            name = "Al-Halq (The Throat)",
            arabicName = "الحَلْق",
            letters = "ء ه  |  ع ح  |  غ خ",
            explanation = "Divided into three parts: deepest throat (ء ه), middle throat (ع ح), and upper throat closest to the mouth (غ خ).",
            practiceWord = "أَهْلٌ  •  عِلْمٌ  •  خَيْرٌ"
        ),
        MakhrajArea(
            id = "lisan",
            name = "Al-Lisan (The Tongue)",
            arabicName = "اللِّسَان",
            letters = "ق ك ج ش ي ض ل ن ر ط د ت ص ز س ظ ذ ث",
            explanation = "The most active articulation area with 10 specific points covering 18 Arabic letters from the back, middle, sides, and tip of the tongue.",
            practiceWord = "قُرْآنٌ  •  صِرَاطَ"
        ),
        MakhrajArea(
            id = "shafatan",
            name = "Ash-Shafatan (The Two Lips)",
            arabicName = "الشَّفَتَان",
            letters = "ف  •  ب  •  م  •  و",
            explanation = "Uses the lower lip with upper teeth for Fa (ف), and both lips together for Ba (ب), Meem (م), and rounded Waw (و).",
            practiceWord = "بِسْمِ  •  فَوْزٌ"
        ),
        MakhrajArea(
            id = "khayshoom",
            name = "Al-Khayshoom (Nasal Passage)",
            arabicName = "الخَيْشُوم",
            letters = "نّ  •  مّ (Ghunnah)",
            explanation = "The upper nasal cavity where the gentle humming resonance (Ghunnah) originates for Noon and Meem.",
            practiceWord = "إِنَّ  •  عَمَّ"
        )
    )

    val shortSurahs: List<SurahData> = listOf(
        SurahData(
            number = 1,
            nameEnglish = "Surah Al-Fatihah",
            nameArabic = "سُورَةُ الفَاتِحَة",
            meaning = "The Opening",
            revelationType = "Makkan • 7 Ayahs",
            ayahs = listOf(
                SurahAyah(1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "Bismillaahir Rahmaanir Raheem", "In the name of Allah, the Most Compassionate, Most Merciful."),
                SurahAyah(2, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "Alhamdu lillaahi Rabbil 'aalameen", "All praise is for Allah—Lord of all worlds."),
                SurahAyah(3, "الرَّحْمَٰنِ الرَّحِيمِ", "Ar-Rahmaanir Raheem", "The Most Compassionate, Most Merciful."),
                SurahAyah(4, "مَالِكِ يَوْمِ الدِّينِ", "Maaliki yawmid deen", "Master of the Day of Judgment."),
                SurahAyah(5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "Iyyaaka na'budu wa iyyaaka nasta'een", "You alone we worship and You alone we ask for help."),
                SurahAyah(6, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Ihdinas siraatal mustaqeem", "Guide us along the Straight Path,"),
                SurahAyah(7, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "Siraatal ladheena an'amta 'alayhim ghayril maghdoobi 'alayhim wa lad daalleen", "the Path of those You have blessed—not those You are displeased with, or those who are astray.")
            )
        ),
        SurahData(
            number = 112,
            nameEnglish = "Surah Al-Ikhlas",
            nameArabic = "سُورَةُ الإِخْلَاص",
            meaning = "Sincerity / Purity of Faith",
            revelationType = "Makkan • 4 Ayahs",
            ayahs = listOf(
                SurahAyah(1, "قُلْ هُوَ اللَّهُ أَحَدٌ", "Qul Huwa Allaahu Ahad", "Say, He is Allah—One and Indivisible."),
                SurahAyah(2, "اللَّهُ الصَّمَدُ", "Allaahus Samad", "Allah—the Sustainer needed by all."),
                SurahAyah(3, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "Lam yalid wa lam yoolad", "He has never had offspring, nor was He born."),
                SurahAyah(4, "وَلَمْ يَكُنْ لَهُ كُفُوًا أَحَدٌ", "Wa lam yakul lahoo kufuwan ahad", "And there is none comparable to Him.")
            )
        ),
        SurahData(
            number = 113,
            nameEnglish = "Surah Al-Falaq",
            nameArabic = "سُورَةُ الفَلَق",
            meaning = "The Daybreak",
            revelationType = "Makkan • 5 Ayahs",
            ayahs = listOf(
                SurahAyah(1, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "Qul a'oodhu bi Rabbil falaq", "Say, I seek refuge in the Lord of the daybreak"),
                SurahAyah(2, "مِنْ شَرِّ مَا خَلَقَ", "Min sharri maa khalaq", "from the evil of whatever He has created,"),
                SurahAyah(3, "وَمِنْ شَرِّ غَاسِقٍ إِذَا وَقَبَ", "Wa min sharri ghaasiqin idhaa waqab", "and from the evil of the night when it grows dark,"),
                SurahAyah(4, "وَمِنْ شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "Wa min sharrin naffaathaati fil 'uqad", "and from the evil of those who practice sorcery,"),
                SurahAyah(5, "وَمِنْ شَرِّ حَاسِدٍ إِذَا حَسَدَ", "Wa min sharri haasidin idhaa hasad", "and from the evil of an envier when they envy.")
            )
        ),
        SurahData(
            number = 114,
            nameEnglish = "Surah An-Nas",
            nameArabic = "سُورَةُ النَّاس",
            meaning = "Mankind",
            revelationType = "Makkan • 6 Ayahs",
            ayahs = listOf(
                SurahAyah(1, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "Qul a'oodhu bi Rabbin naas", "Say, I seek refuge in the Lord of humankind,"),
                SurahAyah(2, "مَلِكِ النَّاسِ", "Malikin naas", "the Master of humankind,"),
                SurahAyah(3, "إِلَٰهِ النَّاسِ", "Ilaahin naas", "the God of humankind,"),
                SurahAyah(4, "مِنْ شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "Min sharril waswaasil khannaas", "from the evil of the lurking whisperer"),
                SurahAyah(5, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "Alladhee yuwaswisu fee sudoorin naas", "who whispers into the hearts of humankind,"),
                SurahAyah(6, "مِنَ الْجِنَّةِ وَالنَّاسِ", "Minal jinnati wan naas", "from among jinn and humankind.")
            )
        ),
        SurahData(
            number = 108,
            nameEnglish = "Surah Al-Kawthar",
            nameArabic = "سُورَةُ الكَوْثَر",
            meaning = "Abundant Goodness",
            revelationType = "Makkan • 3 Ayahs",
            ayahs = listOf(
                SurahAyah(1, "إِنَّا أَعْطَيْنَاكَ الْكَوْثَرَ", "Innaa a'taynaakal kawthar", "Indeed, We have granted you abundant goodness."),
                SurahAyah(2, "فَصَلِّ لِرَبِّكَ وَانْحَرْ", "Fasalli li Rabbika wanhar", "So pray and sacrifice to your Lord alone."),
                SurahAyah(3, "إِنَّ شَانِئَكَ هُوَ الْأَبْتَرُ", "Inna shaani'aka huwal abtar", "Indeed, your enemy is the one cut off.")
            )
        ),
        SurahData(
            number = 103,
            nameEnglish = "Surah Al-Asr",
            nameArabic = "سُورَةُ العَصْر",
            meaning = "The Declining Day / Time",
            revelationType = "Makkan • 3 Ayahs",
            ayahs = listOf(
                SurahAyah(1, "وَالْعَصْرِ", "Wal 'asr", "By the passage of time!"),
                SurahAyah(2, "إِنَّ الْإِنْسَانَ لَفِي خُسْرٍ", "Innal insaana lafee khusr", "Surely humanity is in grave loss,"),
                SurahAyah(3, "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ", "Illal ladheena aamanoo wa 'amilus saalihaati wa tawaasaw bil haqqi wa tawaasaw bis sabr", "except those who have faith, do good, and urge each other to truth and perseverance.")
            )
        )
    )
}
