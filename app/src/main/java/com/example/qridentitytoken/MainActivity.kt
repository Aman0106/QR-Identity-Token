package com.example.qridentitytoken

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.navgraphs.rootNavGraph
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRIdentityTokenTheme {
                val navController = rememberNavController()
                rootNavGraph(
                    navHostController = navController,
                    context = applicationContext,
                    appLifeCycleScope = lifecycleScope
                )
            }
        }
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
    QRIdentityTokenTheme {
        Greeting("Android")
    }
}