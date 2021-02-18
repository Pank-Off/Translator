package ru.punkoff.translator.main.presenter

import ru.punkoff.translator.main.model.data.AppState
import ru.punkoff.translator.main.view.base.MainView

interface MainPresenter<T : AppState, V : MainView> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
