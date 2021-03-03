package ru.punkoff.translator.main.model.repository

import ru.punkoff.translator.main.model.data.AppState
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
