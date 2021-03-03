package ru.punkoff.translator.main.view.main

import ru.punkoff.model.AppState
import ru.punkoff.model.DataModel
import ru.punkoff.translator.main.model.repository.Repository
import ru.punkoff.translator.main.model.repository.RepositoryLocal
import geekbrains.ru.translator.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<ru.punkoff.model.DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<ru.punkoff.model.DataModel>>
) : Interactor<ru.punkoff.model.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): ru.punkoff.model.AppState {
        val appState: ru.punkoff.model.AppState
        if (fromRemoteSource) {
            appState = ru.punkoff.model.AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = ru.punkoff.model.AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
