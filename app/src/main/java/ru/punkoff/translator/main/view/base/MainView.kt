package ru.punkoff.translator.main.view.base

import ru.punkoff.translator.main.model.data.AppState

interface MainView {
    fun renderData(appState: AppState)
}
