package com.enger.estatusunicda
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.enger.estatusunicda.ui.theme.EstatusUnicdaTheme

class MainActivity : ComponentActivity() {
    private val viewModel: EstadoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstatusUnicdaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(viewModel, navController)
                        }
                        composable("votacion") {
                            PantallaVotacion(viewModel) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}