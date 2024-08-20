package neontri.test.tmdbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import neontri.test.api.domain.model.detail.MovieDetailResponse
import neontri.test.tmdbapp.domain.usecase.detail.GetMovieDetailsUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.SetFavoriteMovieUseCase
import neontri.test.tmdbapp.domain.model.MovieDetailModel
import neontri.test.tmdbapp.repository.MovieRepository
import neontri.test.tmdbapp.ui.screens.detail.state.MovieDetailEvent
import neontri.test.tmdbapp.ui.screens.detail.state.MovieDetailViewState
import neontri.test.tmdbapp.util.formatTMDBImageUrl
import neontri.test.tmdbapp.util.mvi.EventHandler

class MovieDetailsViewModel(
  private val movieRepository: MovieRepository,
  private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
  private val setFavoriteMovieUseCase: SetFavoriteMovieUseCase,
  private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel(), EventHandler<MovieDetailEvent> {

  private val _movieDetailViewState = MutableStateFlow(MovieDetailViewState())
  val movieDetailViewState = _movieDetailViewState.asStateFlow()

  override fun obtainEvent(event: MovieDetailEvent) {
    when(event) {
      is MovieDetailEvent.LoadMovieDetails -> {
        uploadMovieDetails(event.movieId)
      }
      is MovieDetailEvent.ToggleFavorite -> {
        _movieDetailViewState.value.movieDetailModel?.let { updateFavoriteMovie(it)}
      }
    }
  }

  private fun uploadMovieDetails(movieId: Int) = viewModelScope.launch(backgroundDispatcher) {
    _movieDetailViewState.update { it.copy(isLoading = true) }
    var data = getMovieDetailsUseCase(movieId).getOrNull()
    data = data?.copy(movieDetailResponse = mapMovieUrl(data.movieDetailResponse))
    _movieDetailViewState.update { it.copy(movieDetailModel = data, isLoading = false) }
  }

  private fun updateFavoriteMovie(movie: MovieDetailModel) = viewModelScope.launch(backgroundDispatcher) {
    if (movie.isFavorite) {
      movieRepository.deleteFavoriteMovie(movie.movieDetailResponse.id)
    } else {
      setFavoriteMovieUseCase(movie)
    }

    _movieDetailViewState.update { it.copy(movieDetailModel = movie.copy(isFavorite = !movie.isFavorite)) }
  }

  private fun mapMovieUrl(movieDetailResponse: MovieDetailResponse): MovieDetailResponse {
    return movieDetailResponse.copy(
      backdropPath = formatTMDBImageUrl(movieDetailResponse.backdropPath ?: ""),
      productionCompanies = movieDetailResponse.productionCompanies.map { it.copy(logoPath = formatTMDBImageUrl(it.logoPath ?: "")) }
    )
  }
}