package ru.punkoff.translator.main.interactor

import io.reactivex.Observable
import ru.punkoff.translator.main.di.NAME_LOCAL
import ru.punkoff.translator.main.di.NAME_REMOTE
import ru.punkoff.translator.main.model.data.AppState
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.repository.Repository
import javax.inject.Inject
import javax.inject.Named

class MainInteractorImpl @Inject constructor(
    @Named(
        NAME_REMOTE
    ) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
) : MainInteractor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository
        } else {
            localRepository
        }.getData(word).map { AppState.Success(it) }
    }
}
