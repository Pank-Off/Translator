package ru.punkoff.translator.main.model.repository

import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.datasource.DataSource
import ru.punkoff.translator.main.model.repository.Repository

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
