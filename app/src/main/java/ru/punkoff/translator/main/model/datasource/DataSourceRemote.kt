package ru.punkoff.translator.main.model.datasource

import io.reactivex.Observable
import ru.punkoff.translator.main.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
