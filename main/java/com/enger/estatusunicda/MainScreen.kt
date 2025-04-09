package com.enger.estatusunicda
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(viewModel: EstadoViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.portada),
            contentDescription = "Logo UNICDA",
            modifier = Modifier.height(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Bienvenido a UNICDA Status", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(24.dp))

        EstadoCampus(viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("votacion") }) {
            Text("Ir a Votar y Comentar")
        }
    }
}

@Composable
fun EstadoCampus(viewModel: EstadoViewModel) {
    val secciones = viewModel.votaciones

    LazyColumn {
        items(secciones.size) { index ->
            val item = secciones[index]
            val emoji = when {
                item.votosPositivos.value > item.votosNegativos.value -> "🟢" // verde
                item.votosPositivos.value < item.votosNegativos.value -> "🔴" // rojo
                else -> "🟡" // amarillo
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item.nombre, fontSize = 18.sp, modifier = Modifier.weight(1f))
                    Text(text = emoji, fontSize = 18.sp)
                }
            }
        }
    }
}