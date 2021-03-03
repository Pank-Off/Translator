package ru.punkoff.translator.main.model.datasource

import ru.punkoff.translator.main.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
