package com.example.data.content

import com.example.data.model.ArabicLetter

object ArabicAlphabetData {
    val letters: List<ArabicLetter> = listOf(
        ArabicLetter(
            number = 1, letter = "ا", arabicName = "أَلِف", englishName = "Alif",
            pronunciationHint = "A-lif (clear open 'aa' sound)",
            makhraj = "Al-Jawf — Empty space of mouth & throat",
            initialForm = "ا", medialForm = "ـا", finalForm = "ـا",
            withFatha = "أَ", withKasra = "إِ", withDamma = "أُ",
            exampleArabic = "أَسَدٌ", exampleTransliteration = "Asadun", exampleMeaning = "Lion",
            speakText = "ألف. أَ، إِ، أُ. أَسَدٌ"
        ),
        ArabicLetter(
            number = 2, letter = "ب", arabicName = "بَاء", englishName = "Ba",
            pronunciationHint = "Baa (both lips gently pressed)",
            makhraj = "Ash-Shafatan — Wet inner part of both lips",
            initialForm = "بـ", medialForm = "ـبـ", finalForm = "ـب",
            withFatha = "بَ", withKasra = "بِ", withDamma = "بُ",
            exampleArabic = "بَيْتٌ", exampleTransliteration = "Baytun", exampleMeaning = "House",
            speakText = "باء. بَ، بِ، بُ. بَيْتٌ"
        ),
        ArabicLetter(
            number = 3, letter = "ت", arabicName = "تَاء", englishName = "Ta",
            pronunciationHint = "Taa (soft 't' from upper teeth base)",
            makhraj = "Tip of tongue touching roots of upper front teeth",
            initialForm = "تـ", medialForm = "ـتـ", finalForm = "ـت",
            withFatha = "تَ", withKasra = "تِ", withDamma = "تُ",
            exampleArabic = "تَمْرٌ", exampleTransliteration = "Tamrun", exampleMeaning = "Dates",
            speakText = "تاء. تَ، تِ، تُ. تَمْرٌ"
        ),
        ArabicLetter(
            number = 4, letter = "ث", arabicName = "ثَاء", englishName = "Tha",
            pronunciationHint = "Thaa (gentle 'th' as in 'think')",
            makhraj = "Tip of tongue touching edge of upper front teeth",
            initialForm = "ثـ", medialForm = "ـثـ", finalForm = "ـث",
            withFatha = "ثَ", withKasra = "ثِ", withDamma = "ثُ",
            exampleArabic = "ثَوْبٌ", exampleTransliteration = "Thawbun", exampleMeaning = "Garment",
            speakText = "ثاء. ثَ، ثِ، ثُ. ثَوْبٌ"
        ),
        ArabicLetter(
            number = 5, letter = "ج", arabicName = "جِيم", englishName = "Jeem",
            pronunciationHint = "Jeem (crisp 'j' as in 'jam')",
            makhraj = "Middle of tongue against upper palate",
            initialForm = "جـ", medialForm = "ـجـ", finalForm = "ـج",
            withFatha = "جَ", withKasra = "جِ", withDamma = "جُ",
            exampleArabic = "جَنَّةٌ", exampleTransliteration = "Jannatun", exampleMeaning = "Garden / Paradise",
            speakText = "جيم. جَ، جِ، جُ. جَنَّةٌ"
        ),
        ArabicLetter(
            number = 6, letter = "ح", arabicName = "حَاء", englishName = "Ha",
            pronunciationHint = "Haa (sharp breathy 'h' from middle throat)",
            makhraj = "Middle of the throat (Wasat al-Halq)",
            initialForm = "حـ", medialForm = "ـحـ", finalForm = "ـح",
            withFatha = "حَ", withKasra = "حِ", withDamma = "حُ",
            exampleArabic = "حَمْدٌ", exampleTransliteration = "Hamdun", exampleMeaning = "Praise",
            speakText = "حاء. حَ، حِ، حُ. حَمْدٌ"
        ),
        ArabicLetter(
            number = 7, letter = "خ", arabicName = "خَاء", englishName = "Kha",
            pronunciationHint = "Khaa (heavy 'kh' from upper throat)",
            makhraj = "Upper throat closest to mouth (Adna al-Halq)",
            initialForm = "خـ", medialForm = "ـخـ", finalForm = "ـخ",
            withFatha = "خَ", withKasra = "خِ", withDamma = "خُ",
            exampleArabic = "خَيْرٌ", exampleTransliteration = "Khayrun", exampleMeaning = "Goodness",
            speakText = "خاء. خَ، خِ، خُ. خَيْرٌ"
        ),
        ArabicLetter(
            number = 8, letter = "د", arabicName = "دَال", englishName = "Dal",
            pronunciationHint = "Daal (clear 'd' from upper teeth roots)",
            makhraj = "Tip of tongue touching roots of upper front teeth",
            initialForm = "د", medialForm = "ـد", finalForm = "ـد",
            withFatha = "دَ", withKasra = "دِ", withDamma = "دُ",
            exampleArabic = "دِينٌ", exampleTransliteration = "Deenun", exampleMeaning = "Religion / Way of Life",
            speakText = "دال. دَ، دِ، دُ. دِينٌ"
        ),
        ArabicLetter(
            number = 9, letter = "ذ", arabicName = "ذَال", englishName = "Dhal",
            pronunciationHint = "Dhaal (soft 'th' as in 'this')",
            makhraj = "Tip of tongue touching edges of upper front teeth",
            initialForm = "ذ", medialForm = "ـذ", finalForm = "ـذ",
            withFatha = "ذَ", withKasra = "ذِ", withDamma = "ذُ",
            exampleArabic = "ذِكْرٌ", exampleTransliteration = "Dhikrun", exampleMeaning = "Remembrance",
            speakText = "ذال. ذَ، ذِ، ذُ. ذِكْرٌ"
        ),
        ArabicLetter(
            number = 10, letter = "ر", arabicName = "رَاء", englishName = "Ra",
            pronunciationHint = "Raa (rolled 'r', full with Fatha/Damma)",
            makhraj = "Tip of tongue with upper gum ridge",
            initialForm = "ر", medialForm = "ـر", finalForm = "ـر",
            withFatha = "رَ", withKasra = "رِ", withDamma = "رُ",
            exampleArabic = "رَحْمَةٌ", exampleTransliteration = "Rahmatun", exampleMeaning = "Mercy",
            speakText = "راء. رَ، رِ، رُ. رَحْمَةٌ"
        ),
        ArabicLetter(
            number = 11, letter = "ز", arabicName = "زَاي", englishName = "Zay",
            pronunciationHint = "Zaay (buzzing 'z' sound)",
            makhraj = "Tip of tongue near lower front teeth",
            initialForm = "ز", medialForm = "ـز", finalForm = "ـز",
            withFatha = "زَ", withKasra = "زِ", withDamma = "زُ",
            exampleArabic = "زَكَاةٌ", exampleTransliteration = "Zakaatun", exampleMeaning = "Charity",
            speakText = "زاي. زَ، زِ، زُ. زَكَاةٌ"
        ),
        ArabicLetter(
            number = 12, letter = "س", arabicName = "سِين", englishName = "Seen",
            pronunciationHint = "Seen (clear whistling 's')",
            makhraj = "Tip of tongue near lower front teeth",
            initialForm = "سـ", medialForm = "ـسـ", finalForm = "ـس",
            withFatha = "سَ", withKasra = "سِ", withDamma = "سُ",
            exampleArabic = "سَلَامٌ", exampleTransliteration = "Salaamun", exampleMeaning = "Peace",
            speakText = "سين. سَ، سِ، سُ. سَلَامٌ"
        ),
        ArabicLetter(
            number = 13, letter = "ش", arabicName = "شِين", englishName = "Sheen",
            pronunciationHint = "Sheen (soft spreading 'sh')",
            makhraj = "Middle of tongue with upper palate",
            initialForm = "شـ", medialForm = "ـشـ", finalForm = "ـش",
            withFatha = "شَ", withKasra = "شِ", withDamma = "شُ",
            exampleArabic = "شَمْسٌ", exampleTransliteration = "Shamsun", exampleMeaning = "Sun",
            speakText = "شين. شَ، شِ، شُ. شَمْسٌ"
        ),
        ArabicLetter(
            number = 14, letter = "ص", arabicName = "صَاد", englishName = "Sad",
            pronunciationHint = "Saad (heavy, full-mouth 's')",
            makhraj = "Tip of tongue with lower front teeth, back raised",
            initialForm = "صـ", medialForm = "ـصـ", finalForm = "ـص",
            withFatha = "صَ", withKasra = "صِ", withDamma = "صُ",
            exampleArabic = "صَبْرٌ", exampleTransliteration = "Sabrun", exampleMeaning = "Patience",
            speakText = "صاد. صَ، صِ، صُ. صَبْرٌ"
        ),
        ArabicLetter(
            number = 15, letter = "ض", arabicName = "ضَاد", englishName = "Dad",
            pronunciationHint = "Daad (heavy 'd' from side of tongue)",
            makhraj = "Side edge of tongue against upper molars",
            initialForm = "ضـ", medialForm = "ـضـ", finalForm = "ـض",
            withFatha = "ضَ", withKasra = "ضِ", withDamma = "ضُ",
            exampleArabic = "ضُحًى", exampleTransliteration = "Duhan", exampleMeaning = "Morning Brightness",
            speakText = "ضاد. ضَ، ضِ، ضُ. ضُحًى"
        ),
        ArabicLetter(
            number = 16, letter = "ط", arabicName = "طَاء", englishName = "Taa",
            pronunciationHint = "Taa (heavy, full-mouth 't')",
            makhraj = "Tip of tongue touching roots of upper front teeth, back raised",
            initialForm = "طـ", medialForm = "ـطـ", finalForm = "ـط",
            withFatha = "طَ", withKasra = "طِ", withDamma = "طُ",
            exampleArabic = "طَيِّبٌ", exampleTransliteration = "Tayyibun", exampleMeaning = "Pure / Good",
            speakText = "طاء. طَ، طِ، طُ. طَيِّبٌ"
        ),
        ArabicLetter(
            number = 17, letter = "ظ", arabicName = "ظَاء", englishName = "Dhaa",
            pronunciationHint = "Dhaa (heavy 'th' as in 'though')",
            makhraj = "Tip of tongue touching upper teeth edge, back raised",
            initialForm = "ظـ", medialForm = "ـظـ", finalForm = "ـظ",
            withFatha = "ظَ", withKasra = "ظِ", withDamma = "ظُ",
            exampleArabic = "ظِلٌّ", exampleTransliteration = "Dhillun", exampleMeaning = "Shade",
            speakText = "ظاء. ظَ، ظِ، ظُ. ظِلٌّ"
        ),
        ArabicLetter(
            number = 18, letter = "ع", arabicName = "عَيْن", englishName = "Ayn",
            pronunciationHint = "Ayn (deep resonant sound from middle throat)",
            makhraj = "Middle of the throat (Wasat al-Halq)",
            initialForm = "عـ", medialForm = "ـعـ", finalForm = "ـع",
            withFatha = "عَ", withKasra = "عِ", withDamma = "عُ",
            exampleArabic = "عِلْمٌ", exampleTransliteration = "Ilmun", exampleMeaning = "Knowledge",
            speakText = "عين. عَ، عِ، عُ. عِلْمٌ"
        ),
        ArabicLetter(
            number = 19, letter = "غ", arabicName = "غَيْن", englishName = "Ghayn",
            pronunciationHint = "Ghayn (heavy gargled 'gh' from upper throat)",
            makhraj = "Upper throat (Adna al-Halq)",
            initialForm = "غـ", medialForm = "ـغـ", finalForm = "ـغ",
            withFatha = "غَ", withKasra = "غِ", withDamma = "غُ",
            exampleArabic = "غَفُورٌ", exampleTransliteration = "Ghafoorun", exampleMeaning = "Most Forgiving",
            speakText = "غين. غَ، غِ، غُ. غَفُورٌ"
        ),
        ArabicLetter(
            number = 20, letter = "ف", arabicName = "فَاء", englishName = "Fa",
            pronunciationHint = "Faa (soft 'f' with upper teeth on lower lip)",
            makhraj = "Edge of upper front teeth on inside of lower lip",
            initialForm = "فـ", medialForm = "ـفـ", finalForm = "ـف",
            withFatha = "فَ", withKasra = "فِ", withDamma = "فُ",
            exampleArabic = "فَجْرٌ", exampleTransliteration = "Fajrun", exampleMeaning = "Dawn",
            speakText = "فاء. فَ، فِ، فُ. فَجْرٌ"
        ),
        ArabicLetter(
            number = 21, letter = "ق", arabicName = "قَاف", englishName = "Qaf",
            pronunciationHint = "Qaaf (deep heavy 'q' from back of tongue)",
            makhraj = "Deepest part of tongue touching soft palate",
            initialForm = "قـ", medialForm = "ـقـ", finalForm = "ـق",
            withFatha = "قَ", withKasra = "قِ", withDamma = "قُ",
            exampleArabic = "قُرْآنٌ", exampleTransliteration = "Qur'aanun", exampleMeaning = "The Holy Quran",
            speakText = "قاف. قَ، قِ، قُ. قُرْآنٌ"
        ),
        ArabicLetter(
            number = 22, letter = "ك", arabicName = "كَاف", englishName = "Kaf",
            pronunciationHint = "Kaaf (light 'k' slightly forward of Qaf)",
            makhraj = "Back of tongue slightly forward touching hard/soft palate",
            initialForm = "كـ", medialForm = "ـكـ", finalForm = "ـك",
            withFatha = "كَ", withKasra = "كِ", withDamma = "كُ",
            exampleArabic = "كِتَابٌ", exampleTransliteration = "Kitaabun", exampleMeaning = "Book",
            speakText = "كاف. كَ، كِ، كُ. كِتَابٌ"
        ),
        ArabicLetter(
            number = 23, letter = "ل", arabicName = "لَام", englishName = "Lam",
            pronunciationHint = "Laam (clear light 'l')",
            makhraj = "Front edges of tongue touching upper front gums",
            initialForm = "لـ", medialForm = "ـلـ", finalForm = "ـل",
            withFatha = "لَ", withKasra = "لِ", withDamma = "لُ",
            exampleArabic = "لَيْلٌ", exampleTransliteration = "Laylun", exampleMeaning = "Night",
            speakText = "لام. لَ، لِ، لُ. لَيْلٌ"
        ),
        ArabicLetter(
            number = 24, letter = "م", arabicName = "مِيم", englishName = "Meem",
            pronunciationHint = "Meem (both lips lightly closed)",
            makhraj = "Dry outer part of both lips",
            initialForm = "مـ", medialForm = "ـمـ", finalForm = "ـم",
            withFatha = "مَ", withKasra = "مِ", withDamma = "مُ",
            exampleArabic = "مَسْجِدٌ", exampleTransliteration = "Masjidun", exampleMeaning = "Mosque",
            speakText = "ميم. مَ، مِ، مُ. مَسْجِدٌ"
        ),
        ArabicLetter(
            number = 25, letter = "ن", arabicName = "نُون", englishName = "Noon",
            pronunciationHint = "Noon (clear 'n' with gentle nasal resonance)",
            makhraj = "Tip of tongue touching upper gum ridge",
            initialForm = "نـ", medialForm = "ـنـ", finalForm = "ـن",
            withFatha = "نَ", withKasra = "نِ", withDamma = "نُ",
            exampleArabic = "نُورٌ", exampleTransliteration = "Noorun", exampleMeaning = "Light",
            speakText = "نون. نَ، نِ، نُ. نُورٌ"
        ),
        ArabicLetter(
            number = 26, letter = "ه", arabicName = "هَاء", englishName = "Ha (Soft)",
            pronunciationHint = "Haa (gentle soft 'h' from deepest throat)",
            makhraj = "Deepest part of throat (Aqsa al-Halq)",
            initialForm = "هـ", medialForm = "ـهـ", finalForm = "ـه",
            withFatha = "هَ", withKasra = "هِ", withDamma = "هُ",
            exampleArabic = "هُدًى", exampleTransliteration = "Hudan", exampleMeaning = "Guidance",
            speakText = "هاء. هَ، هِ، هُ. هُدًى"
        ),
        ArabicLetter(
            number = 27, letter = "و", arabicName = "وَاو", englishName = "Waw",
            pronunciationHint = "Waaw (rounded lips 'w')",
            makhraj = "Rounding both lips in a circle",
            initialForm = "و", medialForm = "ـو", finalForm = "ـو",
            withFatha = "وَ", withKasra = "وِ", withDamma = "وُ",
            exampleArabic = "وَقْتٌ", exampleTransliteration = "Waqtun", exampleMeaning = "Time",
            speakText = "واو. وَ، وِ، وُ. وَقْتٌ"
        ),
        ArabicLetter(
            number = 28, letter = "ء", arabicName = "هَمْزَة", englishName = "Hamzah",
            pronunciationHint = "Hamzah (crisp glottal catch from deep throat)",
            makhraj = "Deepest part of the throat (Aqsa al-Halq)",
            initialForm = "أ", medialForm = "ـئـ", finalForm = "ء",
            withFatha = "أَ", withKasra = "إِ", withDamma = "أُ",
            exampleArabic = "إِيمَانٌ", exampleTransliteration = "Eemaanun", exampleMeaning = "Faith",
            speakText = "همزة. أَ، إِ، أُ. إِيمَانٌ"
        ),
        ArabicLetter(
            number = 29, letter = "ي", arabicName = "يَاء", englishName = "Ya",
            pronunciationHint = "Yaa (clear 'y' as in 'yes')",
            makhraj = "Middle of tongue rising toward upper palate",
            initialForm = "يـ", medialForm = "ـيـ", finalForm = "ـي",
            withFatha = "يَ", withKasra = "يِ", withDamma = "يُ",
            exampleArabic = "يَوْمٌ", exampleTransliteration = "Yawmun", exampleMeaning = "Day",
            speakText = "ياء. يَ، يِ، يُ. يَوْمٌ"
        )
    )
}
