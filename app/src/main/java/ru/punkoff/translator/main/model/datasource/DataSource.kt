package ru.punkoff.translator.main.model.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}
