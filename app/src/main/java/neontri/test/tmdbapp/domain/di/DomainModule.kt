package neontri.test.tmdbapp.domain.di

import neontri.test.tmdbapp.domain.GetMovieDetailsUseCase
import neontri.test.tmdbapp.domain.GetMoviePreviewUseCase
import neontri.test.tmdbapp.domain.GetMovieSearchUseCase
import neontri.test.tmdbapp.domain.SetFavoriteMovieUseCase
import org.koin.dsl.module

val domainModule = module {
  single { GetMovieSearchUseCase(get()) }
  single { GetMovieDetailsUseCase(get()) }
  single { GetMoviePreviewUseCase(get()) }
  single { SetFavoriteMovieUseCase(get()) }
}