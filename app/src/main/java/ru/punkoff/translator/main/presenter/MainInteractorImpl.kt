package ru.punkoff.translator.main.presenter

import io.reactivex.Observable
import ru.punkoff.translator.main.model.data.AppState
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.repository.Repository

class MainInteractorImpl(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : MainInteractor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
