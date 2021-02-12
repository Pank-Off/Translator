package ru.punkoff.translator.main.presenter

import io.reactivex.Observable

interface MainInteractor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}