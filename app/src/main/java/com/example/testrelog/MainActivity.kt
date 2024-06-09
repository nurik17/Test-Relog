package com.example.testrelog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testrelog.navigation.NavGraph
import com.example.testrelog.ui.theme.TestRelogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestRelogTheme {
                NavGraph()
            }
        }
    }
}
