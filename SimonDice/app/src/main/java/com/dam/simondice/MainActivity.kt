package com.dam.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dam.simondice.ui.theme.SimonDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val miViewModel: MyViewModel = MyViewModel()
        enableEdgeToEdge()
        setContent {
            IU(miViewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IU(MyViewModel())
}