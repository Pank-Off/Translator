package ru.punkoff.translator.main.di

import dagger.Module
import dagger.Provides
import ru.punkoff.translator.main.di.NAME_LOCAL
import ru.punkoff.translator.main.di.NAME_REMOTE
import ru.punkoff.translator.main.interactor.MainInteractor
import ru.punkoff.translator.main.interactor.MainInteractorImpl
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.repository.Repository
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractorImpl(repositoryRemote, repositoryLocal)
}
