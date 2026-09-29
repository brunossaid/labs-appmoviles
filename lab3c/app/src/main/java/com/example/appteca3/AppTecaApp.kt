package com.example.appteca3

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AppTecaApp(
    viewModel: AppTecaViewModel
) {
    var appSeleccionadaId by rememberSaveable {
        mutableStateOf<Int?>(null)
    }

    val apps by viewModel.apps.collectAsStateWithLifecycle()

    val appSeleccionada = apps.find {
        it.id == appSeleccionadaId
    }

    if (appSeleccionada == null) {

        PantallaAppTeca(
            viewModel = viewModel,
            onAppClick = { app ->
                appSeleccionadaId = app.id
            }
        )

    } else {

        DetalleApp(
            app = appSeleccionada,
            onVolver = {
                appSeleccionadaId = null
            }
        )
    }
}