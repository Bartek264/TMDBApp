package neontri.test.tmdbapp.repository.di

import neontri.test.tmdbapp.repository.MovieRepository
import neontri.test.tmdbapp.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
  single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}