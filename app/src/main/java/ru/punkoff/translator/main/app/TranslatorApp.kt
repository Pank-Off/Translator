package ru.punkoff.translator.main.app

import android.app.Application
import org.koin.core.context.startKoin
import ru.punkoff.translator.main.di.DependencyGraph

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(DependencyGraph.modules)
        }
    }
}