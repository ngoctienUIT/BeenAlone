package com.tnt.beenalone.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tnt.beenalone.R

val Inter = FontFamily(
    Font(R.font.inter_thin, weight = FontWeight(100)),
    Font(R.font.inter_extra_light, weight = FontWeight(200)),
    Font(R.font.inter_light, weight = FontWeight(300)),
    Font(R.font.inter_regular, weight = FontWeight(400)),
    Font(R.font.inter_medium, weight = FontWeight(500)),
    Font(R.font.inter_semi_bold, weight = FontWeight(600)),
    Font(R.font.inter_bold, weight = FontWeight(700)),
    Font(R.font.inter_extra_bold, weight = FontWeight(800)),
    Font(R.font.inter_black, weight = FontWeight(900)),
)

val THJalliya = FontFamily(
    Font(R.font.th_jalliya_one, weight = FontWeight(100)),
    Font(R.font.th_jalliya_two, weight = FontWeight(200)),
    Font(R.font.th_jalliya_three, weight = FontWeight(300)),
    Font(R.font.th_jalliya_four, weight = FontWeight(400)),
    Font(R.font.th_jalliya_five, weight = FontWeight(500)),
    Font(R.font.th_jalliya_six, weight = FontWeight(600)),
    Font(R.font.th_jalliya_seven, weight = FontWeight(700)),
    Font(R.font.th_jalliya_pro, weight = FontWeight(800)),
    Font(R.font.th_jalliya_pro, weight = FontWeight(900)),
)

val Roboto = FontFamily(
    Font(R.font.roboto_thin, weight = FontWeight(100)),
    Font(R.font.roboto_thin_italic, weight = FontWeight(100), style = FontStyle.Italic),
    Font(R.font.roboto_light, weight = FontWeight(300)),
    Font(R.font.roboto_light_italic, weight = FontWeight(300), style = FontStyle.Italic),
    Font(R.font.roboto_regular, weight = FontWeight(400)),
    Font(R.font.roboto_italic, weight = FontWeight(400), style = FontStyle.Italic),
    Font(R.font.roboto_medium, weight = FontWeight(500)),
    Font(R.font.roboto_medium_italic, weight = FontWeight(500), style = FontStyle.Italic),
    Font(R.font.roboto_bold, weight = FontWeight(700)),
    Font(R.font.roboto_bold_italic, weight = FontWeight(700), style = FontStyle.Italic),
    Font(R.font.roboto_black, weight = FontWeight(900)),
    Font(R.font.roboto_black_italic, weight = FontWeight(900), style = FontStyle.Italic),
)

val DancingScript = FontFamily(
    Font(R.font.dancing_regular, weight = FontWeight.Normal),
    Font(R.font.dancing_medium, weight = FontWeight.Medium),
    Font(R.font.dancing_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.dancing_bold, weight = FontWeight.Bold),
)

val Janelotus = FontFamily(Font(R.font.svn_janelotus))

val Pangolin = FontFamily(Font(R.font.pangolin_regular))

private val defaultTypography = Typography()

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Inter, //FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    displayLarge = defaultTypography.displayLarge.copy(fontFamily = Inter),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = Inter),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = Inter),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = Inter),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = Inter),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = Inter),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = Inter),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = Inter),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = Inter),

    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = Inter),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = Inter),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = Inter),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = Inter),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = Inter)
)