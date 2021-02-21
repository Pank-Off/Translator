package ru.punkoff.translator.main.interactor

import io.reactivex.Observable

interface MainInteractor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}