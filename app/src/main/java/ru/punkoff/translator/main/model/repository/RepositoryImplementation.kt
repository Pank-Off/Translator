package ru.punkoff.translator.main.model.repository

import io.reactivex.Observable
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.datasource.DataSource


class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
