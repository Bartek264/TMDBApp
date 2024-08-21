package neontri.test.tmdbapp.domain.di

import neontri.test.tmdbapp.domain.usecase.detail.GetMovieDetailsUseCase
import neontri.test.tmdbapp.domain.usecase.dashboard.GetMoviePreviewUseCase
import neontri.test.tmdbapp.domain.usecase.dashboard.GetMovieSearchUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.DeleteFavoriteMovieUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.GetFavoriteMoviesUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.SetFavoriteMovieUseCase
import org.koin.dsl.module

val domainModule = module {
  factory { GetMovieSearchUseCase(get()) }
  factory { GetMovieDetailsUseCase(get()) }
  factory { GetMoviePreviewUseCase(get()) }
  factory { SetFavoriteMovieUseCase(get()) }
  factory { GetFavoriteMoviesUseCase(get()) }
  factory { DeleteFavoriteMovieUseCase(get()) }
}