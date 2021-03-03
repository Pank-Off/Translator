package ru.punkoff.translator.main.model.repository

import ru.punkoff.model.AppState
import ru.punkoff.model.DataModel
import ru.punkoff.translator.main.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<ru.punkoff.model.DataModel>>) :
    RepositoryLocal<List<ru.punkoff.model.DataModel>> {

    override suspend fun getData(word: String): List<ru.punkoff.model.DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: ru.punkoff.model.AppState) {
        dataSource.saveToDB(appState)
    }
}
