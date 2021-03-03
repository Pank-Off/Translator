package ru.punkoff.translator.main.model.datasource

import ru.punkoff.model.AppState
import ru.punkoff.model.DataModel
import ru.punkoff.translator.main.room.HistoryDao
import ru.punkoff.translator.main.utils.convertDataModelSuccessToEntity
import ru.punkoff.translator.main.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<ru.punkoff.model.DataModel>> {

    override suspend fun getData(word: String): List<ru.punkoff.model.DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: ru.punkoff.model.AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
