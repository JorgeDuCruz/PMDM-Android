package com.dam.pruebasroomsqlite

import android.os.Bundle
import android.util.Log
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
import com.dam.pruebasroomsqlite.Model.Controller
import com.dam.pruebasroomsqlite.Model.User
import com.dam.pruebasroomsqlite.ui.theme.PruebasRoomSQLiteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebasRoomSQLiteTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }


        val controller = Controller(application)
        var users = controller.getAll()
        Log.d("mani","Hay $users")
        val user = User(2,"pepe","Pepez")
        controller.insertUser(user)
        users = controller.getAll()
        Log.d("mani","Hay $users")

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PruebasRoomSQLiteTheme {
        Greeting("Android")
    }
}