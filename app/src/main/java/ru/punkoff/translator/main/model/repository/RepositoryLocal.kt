package ru.punkoff.translator.main.model.repository

import ru.punkoff.model.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: ru.punkoff.model.AppState)
}
