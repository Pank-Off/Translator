package ru.punkoff.translator.main.application

import android.app.Application
import ru.punkoff.translator.main.di.application
import ru.punkoff.translator.main.di.historyScreen
import ru.punkoff.translator.main.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}
