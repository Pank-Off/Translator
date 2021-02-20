package ru.punkoff.translator.main.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.punkoff.translator.main.view.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
