package com.example.testrelog.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testrelog.ui.theme.Grey400
import com.example.testrelog.ui.theme.PrimaryRed300
import com.example.testrelog.ui.theme.typography

@Composable
fun SpannableNavigateText(
    subText: String,
    navigation:()->Unit,
    annotatedString: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = subText,
            color = Grey400,
            fontSize = 14.sp,
            style = typography.l16sfD400,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.clickable {
                navigation()
            },
            text = buildAnnotatedString {
                pushStyle(SpanStyle(color = PrimaryRed300))
                append(" $annotatedString")
                pop()
            },
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            style = typography.l16sfD400
        )
    }
}