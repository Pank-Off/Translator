package ru.punkoff.translator.main.model.datasource

import ru.punkoff.translator.main.model.data.AppState
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.room.HistoryDao
import ru.punkoff.translator.main.utils.convertDataModelSuccessToEntity
import ru.punkoff.translator.main.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
