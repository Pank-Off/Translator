package ru.punkoff.translator.main.view.history

import androidx.lifecycle.LiveData
import ru.punkoff.model.AppState
import ru.punkoff.translator.main.utils.parseLocalSearchResults
import geekbrains.ru.translator.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<ru.punkoff.model.AppState>() {

    private val liveDataForViewToObserve: LiveData<ru.punkoff.model.AppState> = _mutableLiveData

    fun subscribe(): LiveData<ru.punkoff.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = ru.punkoff.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(ru.punkoff.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = ru.punkoff.model.AppState.Success(null)//Set View to original state in onStop
        super.onCleared()
    }
}
