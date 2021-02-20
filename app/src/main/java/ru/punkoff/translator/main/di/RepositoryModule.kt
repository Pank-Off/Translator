package ru.punkoff.translator.main.di

import dagger.Module
import dagger.Provides
import ru.punkoff.translator.main.di.NAME_LOCAL
import ru.punkoff.translator.main.di.NAME_REMOTE
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.datasource.DataSource
import ru.punkoff.translator.main.model.datasource.RetrofitImplementation
import ru.punkoff.translator.main.model.datasource.RoomDataBaseImplementation
import ru.punkoff.translator.main.model.repository.Repository
import ru.punkoff.translator.main.model.repository.RepositoryImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}
