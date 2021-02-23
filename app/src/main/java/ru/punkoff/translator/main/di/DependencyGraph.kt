package ru.punkoff.translator.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.punkoff.translator.main.interactor.MainInteractorImpl
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.datasource.RetrofitImplementation
import ru.punkoff.translator.main.model.datasource.RoomDataBaseImplementation
import ru.punkoff.translator.main.model.repository.Repository
import ru.punkoff.translator.main.model.repository.RepositoryImplementation
import ru.punkoff.translator.main.viewmodel.MainViewModel

object DependencyGraph {
    private val repositoryModule by lazy {
        module {
            single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
                RepositoryImplementation(
                   RetrofitImplementation()
                )
            }
            single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
                RepositoryImplementation(
                   RoomDataBaseImplementation()
                )
            }
        }
    }
    private val interactorModule by lazy {
        module {
            factory { MainInteractorImpl(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
        }
    }
    private val viewModelModule by lazy {
        module {
            viewModel { MainViewModel(get()) }
        }
    }
    val modules = listOf( repositoryModule, interactorModule, viewModelModule)
}