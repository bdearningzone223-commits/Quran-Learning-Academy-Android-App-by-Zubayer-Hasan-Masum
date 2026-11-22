package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.AcademyOfficialFooter
import com.example.ui.components.AcademySubTopBar
import com.example.ui.components.IslamicGeometricOverlay
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoft
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft
import com.example.ui.theme.PureWhite

@Composable
fun AboutUsScreen(
    onBack: () -> Unit,
    onOpenFounder: () -> Unit,
    onOpenPrivacy: () -> Unit,
    onOpenContact: () -> Unit
) {
    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "About Us",
                subtitle = "QURAN LEARNING ACADEMY",
                onBack = onBack
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(1.2.dp, GoldBorder)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.verticalGradient(listOf(EmeraldDeep, EmeraldPrimary)))
                    ) {
                        IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(22.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.img_academy_logo),
                                contentDescription = "Academy Emblem",
                                modifier = Modifier.size(72.dp).clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = stringResource(R.string.brand_title),
                                style = MaterialTheme.typography.headlineMedium,
                                color = GoldSoft,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = stringResource(R.string.founder_attribution),
                                style = MaterialTheme.typography.labelLarge,
                                color = PureWhite,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "\"${stringResource(R.string.official_tagline)}\"",
                                style = MaterialTheme.typography.bodySmall,
                                color = PureWhite.copy(alpha = 0.9f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("Our Educational Mission", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                        Text(
                            text = "Quran Learning Academy is a dedicated Islamic educational platform crafted specifically for children around the world. Founded and created by Zubayer Hasan Masum, our structured 13-level curriculum guides every child joyfully from their very first Arabic letter through complete Noorani Qaida, authentic Tajweed, Makharijul Huruf, and confident recitation of the Holy Quran.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        val pillars = listOf(
                            "Step-by-step 13-level structured progression with exams, stars & stage certificates.",
                            "Interactive 29-letter Arabic Alphabet studio with clear vowel marks and audio.",
                            "Qualified Quran teacher review commitment for authentic Quranic script and Tajweed rules.",
                            "Dedicated Student, Teacher, and Parent dashboards for safe family supervision."
                        )
                        pillars.forEach { point ->
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                                Text(point, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            item {
                Button(
                    onClick = onOpenFounder,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GoldBright, contentColor = EmeraldDeep)
                ) {
                    Text("Meet the Founder: Zubayer Hasan Masum", fontWeight = FontWeight.Bold)
                }
            }

            item {
                AcademyOfficialFooter(
                    onAboutFounderClick = onOpenFounder,
                    onPrivacyClick = onOpenPrivacy,
                    onContactClick = onOpenContact
                )
            }
        }
    }
}

@Composable
fun AboutFounderScreen(
    onBack: () -> Unit,
    onOpenPrivacy: () -> Unit,
    onOpenContact: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AcademySubTopBar(
                title = "About the Founder",
                subtitle = "ZUBAYER HASAN MASUM",
                onBack = onBack
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(1.5.dp, GoldBright)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.verticalGradient(listOf(EmeraldDeep, EmeraldPrimary)))
                    ) {
                        IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.img_founder_portrait),
                                contentDescription = "Zubayer Hasan Masum — Founder & Creator",
                                modifier = Modifier
                                    .size(132.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Surface(
                                color = GoldBright.copy(alpha = 0.22f),
                                shape = RoundedCornerShape(50),
                                border = BorderStroke(1.dp, GoldBright)
                            ) {
                                Text(
                                    text = "FOUNDER & CREATOR",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldSoft,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                                )
                            }
                            Text(
                                text = "ZUBAYER HASAN MASUM",
                                style = MaterialTheme.typography.headlineLarge,
                                color = PureWhite,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "\"Making Quran Learning Easier for Every Child.\"",
                                style = MaterialTheme.typography.titleMedium,
                                color = GoldSoft,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("Message from the Founder", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                        Text(
                            text = "Assalamu Alaikum wa Rahmatullahi wa Barakatuh. I founded Quran Learning Academy with one heartfelt dream: to give every child—anywhere in the world—a beautiful, gentle, and authentic pathway to read the Book of Allah with love and Tajweed.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "By combining traditional Noorani Qaida pedagogy with interactive Arabic audio, guided video lessons, joyful rewards, and parent-supervised dashboards, families can nurture lifelong Quran literacy right from home.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Surface(
                            color = EmeraldSoft,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text("QURAN LEARNING ACADEMY", style = MaterialTheme.typography.labelLarge, color = EmeraldDeep, fontWeight = FontWeight.Bold)
                                Text("A Project by ZUBAYER HASAN MASUM", style = MaterialTheme.typography.bodySmall, color = EmeraldDeep)
                            }
                        }
                    }
                }
            }

            // Share & Connect
            item {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Discover QURAN LEARNING ACADEMY — Founded & Created by ZUBAYER HASAN MASUM.\n\"Making Quran Learning Easier for Every Child.\""
                            )
                        }
                        context.startActivity(Intent.createChooser(intent, "Share Quran Learning Academy"))
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Filled.Share, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Share Founder's Mission", fontWeight = FontWeight.Bold)
                }
            }

            item {
                AcademyOfficialFooter(
                    onPrivacyClick = onOpenPrivacy,
                    onContactClick = onOpenContact
                )
            }
        }
    }
}
