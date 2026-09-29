package com.example.appteca3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PantallaAppTeca(
    viewModel: AppTecaViewModel,
    onAppClick: (App) -> Unit
) {
    val apps by viewModel.apps.collectAsStateWithLifecycle()

    var textoBusqueda by rememberSaveable {
        mutableStateOf("")
    }

    var soloFavoritas by rememberSaveable {
        mutableStateOf(false)
    }

    val appsFiltradas = apps.filter { app ->
        val coincideBusqueda =
            app.nombre.contains(textoBusqueda, ignoreCase = true) ||
                    app.categoria.contains(textoBusqueda, ignoreCase = true)

        val coincideFavorita =
            !soloFavoritas || app.esFavorita

        coincideBusqueda && coincideFavorita
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            label = { Text("Buscar por nombre o categoría") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = { soloFavoritas = !soloFavoritas },
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                if (soloFavoritas) "⭐ Solo favoritas"
                else "☆ Todas"
            )
        }

        ListaApps(
            apps = appsFiltradas,
            onAppClick = onAppClick,
            onFavoritoClick = { app ->
                viewModel.toggleFavorita(app)
            }
        )
    }
}