package com.example.appteca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appteca3.ui.theme.AppTeca3Theme
import androidx.activity.viewModels

class MainActivity : ComponentActivity() {

    private val viewModel: AppTecaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTeca3Theme {
                AppTecaApp(
                    viewModel = viewModel
                )
            }
        }
    }
}
