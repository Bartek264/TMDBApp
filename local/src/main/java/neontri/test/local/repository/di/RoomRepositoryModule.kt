package neontri.test.local.repository.di

import neontri.test.local.repository.MovieLocalRepository
import neontri.test.local.repository.MovieLocalRepositoryImpl
import org.koin.dsl.module

val roomRepositoryModule = module {
  single<MovieLocalRepository> { MovieLocalRepositoryImpl(get()) }
}