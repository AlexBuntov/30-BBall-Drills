package com.example.a30bballdrills.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.a30bballdrills.R

// Set of Material typography styles to start with
val Dmsans = FontFamily(
    Font(R.font.dmsans_regular),
    Font(R.font.dmsans_semibold, FontWeight.SemiBold)
)
val Typography = Typography(
    labelLarge = TextStyle(
        fontFamily = Dmsans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Dmsans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Dmsans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,

    )
    /* Other default text styles to override

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)