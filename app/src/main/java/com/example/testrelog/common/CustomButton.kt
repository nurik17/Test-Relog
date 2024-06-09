package com.example.testrelog.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testrelog.ui.theme.PrimaryRed500
import com.example.testrelog.ui.theme.typography


@Composable
fun CustomButton(
    text: String,
    enabled: Boolean = true,
    textColor: Color = Color.White,
    backgroundColor: Color = PrimaryRed500,
    borderColor: Color = PrimaryRed500,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(12.dp)

    Button(
        modifier = modifier,
        onClick = { onButtonClick() },
        colors = ButtonColors(
            containerColor = backgroundColor,
            contentColor = Color.White,
            disabledContainerColor = backgroundColor,
            disabledContentColor = Color.White
        ),
        shape = shape,
        enabled = enabled,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Text(
            text = text,
            style = typography.l16sfD600,
            color = textColor
        )
    }
}
