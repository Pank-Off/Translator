package ru.punkoff.translator.main.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.punkoff.translator.main.interactor.MainInteractorImpl
import ru.punkoff.translator.main.model.data.AppState

class MainViewModel(private val interactor: MainInteractorImpl) :
    BaseViewModel<AppState>() {
    override fun getData(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            stareInteractor(word, isOnline)
        }
    }

    private suspend fun stareInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(interactor.getData(word, isOnline))
        }

    fun subscribe() = liveDataForViewToObserve

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
    }
}