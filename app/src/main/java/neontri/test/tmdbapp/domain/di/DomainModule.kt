package neontri.test.tmdbapp.domain.di

import neontri.test.tmdbapp.domain.usecase.detail.GetMovieDetailsUseCase
import neontri.test.tmdbapp.domain.usecase.dashboard.GetMoviePreviewUseCase
import neontri.test.tmdbapp.domain.usecase.dashboard.GetMovieSearchUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.DeleteFavoriteMovieUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.GetFavoriteMoviesUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.SetFavoriteMovieUseCase
import org.koin.dsl.module

val domainModule = module {
  single { GetMovieSearchUseCase(get()) }
  single { GetMovieDetailsUseCase(get()) }
  single { GetMoviePreviewUseCase(get()) }
  single { SetFavoriteMovieUseCase(get()) }
  single { GetFavoriteMoviesUseCase(get()) }
  single { DeleteFavoriteMovieUseCase(get()) }

}