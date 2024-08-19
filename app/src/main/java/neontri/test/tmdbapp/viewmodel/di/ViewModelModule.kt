package neontri.test.tmdbapp.viewmodel.di

import neontri.test.tmdbapp.viewmodel.DashboardViewModel
import neontri.test.tmdbapp.viewmodel.FavoriteViewModel
import neontri.test.tmdbapp.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { DashboardViewModel(get(), get(), get(), get(), get()) }
  viewModel { MovieDetailsViewModel(get(), get(), get()) }
  viewModel { FavoriteViewModel(get()) }
}