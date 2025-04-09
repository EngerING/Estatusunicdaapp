package com.enger.estatusunicda
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaVotacion(viewModel: EstadoViewModel, onBack: () -> Unit) {
    val secciones = viewModel.votaciones
    val context = LocalContext.current
    val seccionesVotadas = remember { mutableStateListOf<String>() }
    var comentario by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Votación y Comentarios", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(secciones.size) { index ->
                val item = secciones[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth().padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(item.nombre, fontSize = 18.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = {
                                    if (!seccionesVotadas.contains(item.nombre)) {
                                        item.votosPositivos.value++
                                        seccionesVotadas.add(item.nombre)
                                    }
                                },
                                enabled = !seccionesVotadas.contains(item.nombre)
                            ) { Text("👍") }
                            Text(text = item.votosPositivos.value.toString())
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(
                                onClick = {
                                    if (!seccionesVotadas.contains(item.nombre)) {
                                        item.votosNegativos.value++
                                        seccionesVotadas.add(item.nombre)
                                    }
                                },
                                enabled = !seccionesVotadas.contains(item.nombre)
                            ) { Text("👎") }
                            Text(text = item.votosNegativos.value.toString())
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = comentario,
            onValueChange = { comentario = it },
            label = { Text("Comentario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (comentario.isNotBlank()) {
                viewModel.comentarios.add(comentario)
                Toast.makeText(context, "¡Gracias por tu comentario!", Toast.LENGTH_SHORT).show()
                comentario = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Enviar Comentario")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Comentarios recientes:", fontSize = 16.sp)
        LazyColumn {
            items(viewModel.comentarios.size) { index ->
                Text("- ${viewModel.comentarios[index]}", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }
}
