package ru.punkoff.translator.main.model.data.api

import ru.punkoff.model.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<ru.punkoff.model.DataModel>>
}
