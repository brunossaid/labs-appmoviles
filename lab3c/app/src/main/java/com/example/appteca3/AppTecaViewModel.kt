package com.example.appteca3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppTecaViewModel : ViewModel() {

    private val _apps = MutableStateFlow(Catalogo.apps)
    val apps: StateFlow<List<App>> = _apps.asStateFlow()

    fun toggleFavorita(app: App) {
        _apps.value = _apps.value.map {
            if (it.id == app.id) {
                it.copy(esFavorita = !it.esFavorita)
            } else {
                it
            }
        }
    }
}