package ru.punkoff.translator.main.model.repository

import ru.punkoff.model.DataModel
import ru.punkoff.translator.main.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<ru.punkoff.model.DataModel>>) :
    Repository<List<ru.punkoff.model.DataModel>> {

    override suspend fun getData(word: String): List<ru.punkoff.model.DataModel> {
        return dataSource.getData(word)
    }
}
