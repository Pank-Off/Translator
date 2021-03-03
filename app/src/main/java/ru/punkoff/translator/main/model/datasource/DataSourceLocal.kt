package ru.punkoff.translator.main.model.datasource

import ru.punkoff.model.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: ru.punkoff.model.AppState)
}
