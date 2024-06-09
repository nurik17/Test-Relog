package com.example.testrelog.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.testrelog.R

data class Typographies(
    val l24sfD700: TextStyle,
    val l16sfD400: TextStyle,
    val l16sfD600:TextStyle
)

val typography = Typographies(
    l24sfD700 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_bold)),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    ),
    l16sfD400 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Grey500
    ),
    l16sfD600 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_bold)),
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    ),
)
