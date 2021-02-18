package ru.punkoff.translator.main.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.punkoff.translator.main.model.data.AppState
import ru.punkoff.translator.main.presenter.MainPresenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), MainView {
    protected lateinit var presenter: MainPresenter<T, MainView>

    protected abstract fun createPresenter(): MainPresenter<T, MainView>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}