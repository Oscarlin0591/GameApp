package com.example.gameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gameapp.navagation.GameNavigation
import com.example.gameapp.ui.theme.GamesAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                GameNavigation()
            }
        }
    }
}
@Composable
fun MyApp(content: @Composable () -> Unit) {
    GamesAppTheme {
        content()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   MyApp {
      GameNavigation()
   }
}