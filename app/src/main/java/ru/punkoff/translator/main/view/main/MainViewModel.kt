package ru.punkoff.translator.main.view.main

import androidx.lifecycle.LiveData
import ru.punkoff.model.AppState
import ru.punkoff.translator.main.utils.parseOnlineSearchResults
import geekbrains.ru.translator.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<ru.punkoff.model.AppState>() {

    private val liveDataForViewToObserve: LiveData<ru.punkoff.model.AppState> = _mutableLiveData

    fun subscribe(): LiveData<ru.punkoff.model.AppState> {
        return _mutableLiveData
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = ru.punkoff.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    //Doesn't have to use withContext for Retrofit call if you use .addCallAdapterFactory(CoroutineCallAdapterFactory()). The same goes for Room
    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(ru.punkoff.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = ru.punkoff.model.AppState.Success(null)//TODO Workaround. Set View to original state
        super.onCleared()
    }
}
