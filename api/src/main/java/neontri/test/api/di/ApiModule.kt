package neontri.test.api.di

import neontri.test.api.client.KtorClient
import neontri.test.api.repository.MovieRemoteRepository
import neontri.test.api.repository.MovieRemoteRepositoryImpl
import org.koin.dsl.module

val apiModule = module {
  single<KtorClient> { KtorClient() }
  single<MovieRemoteRepository> { MovieRemoteRepositoryImpl(get()) }
}