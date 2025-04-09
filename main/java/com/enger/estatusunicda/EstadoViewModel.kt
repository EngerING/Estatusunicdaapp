package com.enger.estatusunicda
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class VotacionItem(
    val nombre: String,
    val votosPositivos: MutableState<Int> = mutableStateOf(0),
    val votosNegativos: MutableState<Int> = mutableStateOf(0)
)

class EstadoViewModel : ViewModel() {
    val votaciones = mutableStateListOf(
        VotacionItem("Aulas"),
        VotacionItem("Baños"),
        VotacionItem("Cafetería"),
        VotacionItem("Biblioteca"),
        VotacionItem("Laboratorios")
    )

    val comentarios = mutableStateListOf<String>()
}
