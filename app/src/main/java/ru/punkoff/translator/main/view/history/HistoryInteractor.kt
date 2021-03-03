package ru.punkoff.translator.main.view.history

import ru.punkoff.model.AppState
import ru.punkoff.model.DataModel
import ru.punkoff.translator.main.model.repository.Repository
import ru.punkoff.translator.main.model.repository.RepositoryLocal
import geekbrains.ru.translator.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<ru.punkoff.model.DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<ru.punkoff.model.DataModel>>
) : Interactor<ru.punkoff.model.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): ru.punkoff.model.AppState {
        return ru.punkoff.model.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
