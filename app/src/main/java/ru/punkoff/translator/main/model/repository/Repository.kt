package ru.punkoff.translator.main.model.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}
