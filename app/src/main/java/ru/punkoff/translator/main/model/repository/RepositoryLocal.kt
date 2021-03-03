package ru.punkoff.translator.main.model.repository

import ru.punkoff.translator.main.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
