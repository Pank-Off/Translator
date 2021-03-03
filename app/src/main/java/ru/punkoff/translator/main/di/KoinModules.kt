package ru.punkoff.translator.main.di

import androidx.room.Room
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.model.datasource.RetrofitImplementation
import ru.punkoff.translator.main.model.datasource.RoomDataBaseImplementation
import ru.punkoff.translator.main.model.repository.Repository
import ru.punkoff.translator.main.model.repository.RepositoryImplementation
import ru.punkoff.translator.main.model.repository.RepositoryImplementationLocal
import ru.punkoff.translator.main.model.repository.RepositoryLocal
import ru.punkoff.translator.main.room.HistoryDataBase
import ru.punkoff.translator.main.view.history.HistoryInteractor
import ru.punkoff.translator.main.view.history.HistoryViewModel
import ru.punkoff.translator.main.view.main.MainInteractor
import ru.punkoff.translator.main.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(
        RoomDataBaseImplementation(get())
    )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
