package neontri.test.tmdbapp.services.di

import neontri.test.tmdbapp.services.ConnectivityObserver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val serviceModule = module {
  single { ConnectivityObserver(androidContext()) }
}