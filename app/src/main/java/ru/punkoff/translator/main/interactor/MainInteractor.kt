package ru.punkoff.translator.main.interactor

interface MainInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}