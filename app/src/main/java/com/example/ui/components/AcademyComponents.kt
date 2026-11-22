package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldMid
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldSoft
import com.example.ui.theme.PureWhite
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Custom decorative Islamic eight-pointed star (Rub el Hizb) geometric pattern overlay.
 */
@Composable
fun IslamicGeometricOverlay(
    modifier: Modifier = Modifier,
    lineColor: Color = GoldBright.copy(alpha = 0.14f)
) {
    Canvas(modifier = modifier) {
        val spacing = 56.dp.toPx()
        val radius = 18.dp.toPx()
        val cols = (size.width / spacing).toInt() + 2
        val rows = (size.height / spacing).toInt() + 2

        for (r in 0..rows) {
            for (c in 0..cols) {
                val cx = c * spacing + if (r % 2 == 1) spacing / 2f else 0f
                val cy = r * spacing
                val path = Path()
                val points = 8
                for (i in 0 until points * 2) {
                    val angle = (i * PI / points)
                    val rad = if (i % 2 == 0) radius else radius * 0.45f
                    val x = cx + (rad * cos(angle)).toFloat()
                    val y = cy + (rad * sin(angle)).toFloat()
                    if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }
                path.close()
                drawPath(path = path, color = lineColor, style = Stroke(width = 1.2f))
                drawCircle(color = lineColor, radius = 2.5f, center = Offset(cx, cy))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcademySubTopBar(
    title: String,
    subtitle: String? = null,
    onBack: () -> Unit,
    actions: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onBack,
                modifier = Modifier.testTag("back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        actions = { actions() },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun ArabicTextDisplay(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 32.sp,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        fontFamily = AmiriFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
        lineHeight = (fontSize.value * 1.65f).sp,
        color = color,
        textAlign = textAlign,
        style = MaterialTheme.typography.headlineMedium.copy(
            textDirection = androidx.compose.ui.text.style.TextDirection.Rtl
        ),
        modifier = modifier
    )
}

@Composable
fun AudioPronounceButton(
    isSpeaking: Boolean,
    label: String = "Listen",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSpeaking) GoldBright else MaterialTheme.colorScheme.primary,
            contentColor = if (isSpeaking) EmeraldDeep else PureWhite
        )
    ) {
        Icon(
            imageVector = if (isSpeaking) Icons.Filled.Stop else Icons.AutoMirrored.Filled.VolumeUp,
            contentDescription = label,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = if (isSpeaking) "Playing..." else label,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        if (actionLabel != null && onActionClick != null) {
            androidx.compose.material3.TextButton(onClick = onActionClick) {
                Text(
                    text = actionLabel,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun StarRatingRow(stars: Int, maxStars: Int = 3, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        repeat(maxStars) { index ->
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = if (index < stars) GoldBright else MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun DownloadApkPromoCard(
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.2.dp, GoldBorder),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(EmeraldDeep, EmeraldPrimary, EmeraldMid)
                    )
                )
        ) {
            IslamicGeometricOverlay(modifier = Modifier.matchParentSize())
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = GoldBright.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, GoldBright.copy(alpha = 0.6f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Verified,
                                contentDescription = null,
                                tint = GoldBright,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = "Official Android APK • Offline & Child-Safe",
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldSoft
                            )
                        }
                    }
                }
                Text(
                    text = "Install Quran Learning Academy on Any Android Device",
                    style = MaterialTheme.typography.titleLarge,
                    color = PureWhite
                )
                Text(
                    text = "Fast, secure, and mobile-friendly. Save the official APK package or share it with family and teachers.",
                    style = MaterialTheme.typography.bodySmall,
                    color = PureWhite.copy(alpha = 0.88f)
                )
                Button(
                    onClick = onDownloadClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("download_apk_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldBright,
                        contentColor = EmeraldDeep
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Download,
                        contentDescription = "Download APK",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.download_apk_cta),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun AcademyOfficialFooter(
    brandTitle: String? = null,
    founderName: String? = null,
    footerText: String? = null,
    onAboutFounderClick: (() -> Unit)? = null,
    onPrivacyClick: (() -> Unit)? = null,
    onContactClick: (() -> Unit)? = null
) {
    val resolvedBrand = brandTitle?.takeIf { it.isNotBlank() } ?: stringResource(R.string.brand_title)
    val resolvedProjectBy = if (!founderName.isNullOrBlank()) {
        "A Project by $founderName"
    } else {
        stringResource(R.string.project_by)
    }
    val resolvedFooter = footerText?.takeIf { it.isNotBlank() } ?: stringResource(R.string.official_footer)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = EmeraldDeep,
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IslamicGeometricOverlay(
                modifier = Modifier.matchParentSize(),
                lineColor = GoldBright.copy(alpha = 0.08f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.img_academy_logo),
                    contentDescription = "Quran Learning Academy Logo",
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = resolvedBrand,
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldBright,
                    letterSpacing = 1.2.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = resolvedProjectBy,
                    style = MaterialTheme.typography.labelLarge,
                    color = PureWhite,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "\"${stringResource(R.string.vision_quote)}\"",
                    style = MaterialTheme.typography.bodySmall,
                    color = GoldSoft.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )
                if (onAboutFounderClick != null || onPrivacyClick != null || onContactClick != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (onAboutFounderClick != null) {
                            androidx.compose.material3.TextButton(onClick = onAboutFounderClick) {
                                Text("Founder", color = GoldBright, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                        if (onContactClick != null) {
                            androidx.compose.material3.TextButton(onClick = onContactClick) {
                                Text("Contact", color = GoldBright, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                        if (onPrivacyClick != null) {
                            androidx.compose.material3.TextButton(onClick = onPrivacyClick) {
                                Text("Privacy Policy", color = GoldBright, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                    }
                }
                HorizontalDivider(color = PureWhite.copy(alpha = 0.18f))
                Text(
                    text = resolvedFooter,
                    style = MaterialTheme.typography.labelSmall,
                    color = PureWhite.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
