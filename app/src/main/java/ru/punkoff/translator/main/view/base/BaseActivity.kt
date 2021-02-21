package ru.punkoff.translator.main.view.base

import androidx.appcompat.app.AppCompatActivity
import ru.punkoff.translator.main.model.data.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity() {
    abstract fun renderData(appState: AppState)
}