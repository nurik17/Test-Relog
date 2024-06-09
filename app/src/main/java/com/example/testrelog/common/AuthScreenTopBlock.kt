package com.example.testrelog.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testrelog.ui.theme.typography


@Composable
fun AuthScreenTopBlock(
    onBackIconClick: () -> Unit,
    mainText: String,
    subText: String
) {
    Icon(
        modifier = Modifier
            .size(24.dp)
            .clickable {
                onBackIconClick()
            },
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "",
        tint = Color.Black
    )
    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = mainText,
        style = typography.l24sfD700
    )
    Text(
        modifier = Modifier.padding(top = 5.dp),
        text = subText,
    )
}


