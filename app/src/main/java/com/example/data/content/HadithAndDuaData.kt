package com.example.data.content

data class HadithItem(
    val id: String,
    val number: Int,
    val category: String,
    val titleArabic: String,
    val textArabic: String,
    val sourceArabic: String,
    val transliteration: String,
    val meaning: String
)

data class DuaAzkarItem(
    val id: String,
    val category: String,
    val titleArabic: String,
    val titleEnglish: String,
    val textArabic: String,
    val transliteration: String,
    val meaning: String,
    val targetCount: Int = 1
)

object HadithAndDuaData {

    val hadithList: List<HadithItem> = listOf(
        HadithItem(
            id = "h1",
            number = 1,
            category = "الْقُرْآنُ وَالْعِلْمُ",
            titleArabic = "خَيْرُكُمْ مَنْ تَعَلَّمَ الْقُرْآنَ وَعَلَّمَهُ",
            textArabic = "قَالَ رَسُولُ اللَّهِ ﷺ: «خَيْرُكُمْ مَنْ تَعَلَّمَ الْقُرْآنَ وَعَلَّمَهُ».",
            sourceArabic = "رَوَاهُ الْبُخَارِيُّ",
            transliteration = "Khayrukum man ta'allamal-Qur'aana wa 'allamahu",
            meaning = "The best among you are those who learn the Quran and teach it. (Sahih al-Bukhari)"
        ),
        HadithItem(
            id = "h2",
            number = 2,
            category = "النِّيَّةُ الصَّالِحَةُ",
            titleArabic = "إِنَّمَا الْأَعْمَالُ بِالنِّيَّاتِ",
            textArabic = "قَالَ رَسُولُ اللَّهِ ﷺ: «إِنَّمَا الْأَعْمَالُ بِالنِّيَّاتِ، وَإِنَّمَا لِكُلِّ امْرِئٍ مَا نَوَى».",
            sourceArabic = "مُتَّفَقٌ عَلَيْهِ (الْبُخَارِيُّ وَمُسْلِمٌ)",
            transliteration = "Innamal-a'maalu bin-niyyaati wa innamaa likullimri'in maa nawaa",
            meaning = "Actions are judged by intentions, and every person will have what they intended. (Bukhari & Muslim)"
        ),
        HadithItem(
            id = "h3",
            number = 3,
            category = "حُسْنُ الْخُلُقِ",
            titleArabic = "الْكَلِمَةُ الطَّيِّبَةُ صَدَقَةٌ",
            textArabic = "قَالَ رَسُولُ اللَّهِ ﷺ: «وَالْكَلِمَةُ الطَّيِّبَةُ صَدَقَةٌ».",
            sourceArabic = "مُتَّفَقٌ عَلَيْهِ",
            transliteration = "Wal-kalimatut-tayyibatu sadaqah",
            meaning = "A good, kind word is charity. (Bukhari & Muslim)"
        ),
        HadithItem(
            id = "h4",
            number = 4,
            category = "الْبَسْمَلَةُ وَالْأَخْلَاقُ",
            titleArabic = "سَمِّ اللَّهَ وَكُلْ بِيَمِينِكَ",
            textArabic = "قَالَ رَسُولُ اللَّهِ ﷺ: «يَا غُلَامُ، سَمِّ اللَّهَ، وَكُلْ بِيَمِينِكَ، وَكُلْ مِمَّا يَلِيكَ».",
            sourceArabic = "مُتَّفَقٌ عَلَيْهِ",
            transliteration = "Yaa ghulaamu, sammil-laaha wa kul bi-yameenika wa kul mimmaa yaleek",
            meaning = "O young boy, say Bismillah, eat with your right hand, and eat from what is nearest to you."
        ),
        HadithItem(
            id = "h5",
            number = 5,
            category = "الطَّهَارَةُ",
            titleArabic = "الطُّهُورُ شَطْرُ الْإِيمَانِ",
            textArabic = "قَالَ رَسُولُ اللَّهِ ﷺ: «الطُّهُورُ شَطْرُ الْإِيمَانِ، وَالْحَمْدُ لِلَّهِ تَمْلَأُ الْمِيزَانَ».",
            sourceArabic = "رَوَاهُ مُسْلِمٌ",
            transliteration = "At-tuhooru shatrul-eemaan, wal-hamdu lillaahi tamla'ul-meezaan",
            meaning = "Cleanliness is half of faith, and Alhamdulillah fills the scale of good deeds. (Sahih Muslim)"
        ),
        HadithItem(
            id = "h6",
            number = 6,
            category = "الرَّحْمَةُ",
            titleArabic = "مَنْ لَا يَرْحَمْ لَا يُرْحَمْ",
            textArabic = "قَالَ رَسُولُ اللَّهِ ﷺ: «مَنْ لَا يَرْحَمِ النَّاسَ لَا يَرْحَمْهُ اللَّهُ».",
            sourceArabic = "مُتَّفَقٌ عَلَيْهِ",
            transliteration = "Man laa yarhamin-naasa laa yarhamhullaah",
            meaning = "Whoever does not show mercy to people, Allah will not show mercy to them."
        ),
        HadithItem(
            id = "h7",
            number = 7,
            category = "طَلَبُ الْعِلْمِ",
            titleArabic = "مَنْ سَلَكَ طَرِيقًا يَلْتَمِسُ فِيهِ عِلْمًا",
            textArabic = "قَالَ رَسُولُ اللَّهِ ﷺ: «مَنْ سَلَكَ طَرِيقًا يَلْتَمِسُ فِيهِ عِلْمًا سَهَّلَ اللَّهُ لَهُ بِهِ طَرِيقًا إِلَى الْجَنَّةِ».",
            sourceArabic = "رَوَاهُ مُسْلِمٌ",
            transliteration = "Man salaka tareeqan yaltamisu feehi 'ilman sahhalallaahu lahu bihi tareeqan ilal-jannah",
            meaning = "Whoever takes a path seeking knowledge, Allah makes easy for them a path to Paradise. (Sahih Muslim)"
        )
    )

    val duaAzkarList: List<DuaAzkarItem> = listOf(
        DuaAzkarItem(
            id = "d1",
            category = "دُعَاءُ الْعِلْمِ",
            titleArabic = "دُعَاءُ طَلَبِ الْعِلْمِ",
            titleEnglish = "Dua for Increasing Knowledge",
            textArabic = "رَبِّ زِدْنِي عِلْمًا وَارْزُقْنِي فَهْمًا",
            transliteration = "Rabbi zidnee 'ilman warzuqnee fahmaa",
            meaning = "My Lord, increase me in knowledge and grant me understanding.",
            targetCount = 3
        ),
        DuaAzkarItem(
            id = "d2",
            category = "أَذْكَارُ الصَّبَاحِ",
            titleArabic = "دُعَاءُ الصَّبَاحِ",
            titleEnglish = "Morning Remembrance",
            textArabic = "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لَا إِلَٰهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ",
            transliteration = "Asbahnaa wa asbahal-mulku lillaahi wal-hamdu lillaah, laa ilaaha illallaahu wahdahu laa shareeka lah",
            meaning = "We have reached the morning and all sovereignty belongs to Allah, and all praise is for Allah.",
            targetCount = 1
        ),
        DuaAzkarItem(
            id = "d3",
            category = "دُعَاءُ الْوَالِدَيْنِ",
            titleArabic = "الدُّعَاءُ لِلْوَالِدَيْنِ",
            titleEnglish = "Dua for Parents",
            textArabic = "رَبِّ ارْحَمْهُمَا كَمَا رَبَّيَانِي صَغِيرًا",
            transliteration = "Rabbir-hamhumaa kamaa rabbayaanee sagheeraa",
            meaning = "My Lord, have mercy upon my parents as they raised me when I was small.",
            targetCount = 3
        ),
        DuaAzkarItem(
            id = "d4",
            category = "أَدَبُ الطَّعَامِ",
            titleArabic = "دُعَاءُ قَبْلَ الطَّعَامِ",
            titleEnglish = "Before Eating",
            textArabic = "بِسْمِ اللَّهِ وَعَلَىٰ بَرَكَةِ اللَّهِ",
            transliteration = "Bismillaahi wa 'alaa barakatillaah",
            meaning = "In the name of Allah and upon the blessing of Allah.",
            targetCount = 1
        ),
        DuaAzkarItem(
            id = "d5",
            category = "أَدَبُ الطَّعَامِ",
            titleArabic = "دُعَاءُ بَعْدَ الطَّعَامِ",
            titleEnglish = "After Eating",
            textArabic = "الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مُسْلِمِينَ",
            transliteration = "Al-hamdu lillaahil-ladhee at'amanaa wa saqaanaa wa ja'alanaa muslimeen",
            meaning = "All praise is for Allah who fed us, gave us drink, and made us Muslims.",
            targetCount = 1
        ),
        DuaAzkarItem(
            id = "d6",
            category = "أَذْكَارُ النَّوْمِ",
            titleArabic = "دُعَاءُ قَبْلَ النَّوْمِ",
            titleEnglish = "Before Sleeping",
            textArabic = "بِاسْمِكَ اللَّهُمَّ أَمُوتُ وَأَحْيَا",
            transliteration = "Bismikallaahumma amootu wa ahyaa",
            meaning = "In Your name, O Allah, I die and I live.",
            targetCount = 1
        ),
        DuaAzkarItem(
            id = "d7",
            category = "تَسْبِيحُ اللَّهِ",
            titleArabic = "التَّسْبِيحُ وَالتَّحْمِيدُ",
            titleEnglish = "Daily Tasbeeh & Praise",
            textArabic = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ، سُبْحَانَ اللَّهِ الْعَظِيمِ",
            transliteration = "Subhaanallaahi wa bihamdihi, Subhaanallaahil-'Adheem",
            meaning = "Glory be to Allah and praise Him; Glory be to Allah, the Most Great.",
            targetCount = 33
        ),
        DuaAzkarItem(
            id = "d8",
            category = "دُعَاءٌ جَامِعٌ",
            titleArabic = "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً",
            titleEnglish = "Comprehensive Quranic Dua",
            textArabic = "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّارِ",
            transliteration = "Rabbanaa aatinaa fid-dunyaa hasanatan wa fil-aakhirati hasanatan wa qinaa 'adhaaban-naar",
            meaning = "Our Lord, grant us good in this world and good in the Hereafter, and protect us from the Fire.",
            targetCount = 3
        )
    )
}
