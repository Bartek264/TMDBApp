package neontri.test.tmdbapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import neontri.test.api.domain.model.preview.MoviePreviewResponse
import neontri.test.tmdbapp.domain.model.MoviePreviewModel
import neontri.test.tmdbapp.domain.usecase.dashboard.GetMoviePreviewUseCase
import neontri.test.tmdbapp.domain.usecase.dashboard.GetMovieSearchUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.DeleteFavoriteMovieUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.GetFavoriteMoviesUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.SetFavoriteMovieUseCase
import neontri.test.tmdbapp.ui.screens.dashboard.state.DashboardEffect
import neontri.test.tmdbapp.ui.screens.dashboard.state.DashboardEvents
import neontri.test.tmdbapp.ui.screens.dashboard.state.DashboardViewState
import neontri.test.tmdbapp.services.ConnectivityObserver
import neontri.test.tmdbapp.util.formatTMDBImageUrl
import neontri.test.tmdbapp.util.mvi.EventHandler
import neontri.test.tmdbapp.util.roundToTwoDecimalPlaces

class DashboardViewModel(
  private val connectivityObserver: ConnectivityObserver,
  private val getMovieSearchUseCase: GetMovieSearchUseCase,
  private val getMoviePreviewUseCase: GetMoviePreviewUseCase,
  private val setFavoriteMovieUseCase: SetFavoriteMovieUseCase,
  private val getFavoriteMovieUseCase: GetFavoriteMoviesUseCase,
  private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
  private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), EventHandler<DashboardEvents> {

  private val _viewState = MutableStateFlow(DashboardViewState())
  val viewState = _viewState.asStateFlow()

  private val _viewEffect = Channel<DashboardEffect>()
  val viewEffect = _viewEffect.receiveAsFlow()

  init {
    initData()
  }

  private fun initData() = viewModelScope.launch(backgroundDispatcher) {
    _viewState.update { it.copy(isLoading = true) }

    connectivityObserver.status.map { isConnected ->
      when (isConnected) {
        true -> {
          loadNextPage()
          if (_viewState.value.isConnected.not()) {
            _viewEffect.send(DashboardEffect.SnackbarNotification("Internet connection restored"))
          }
        }

        false -> {
          obtainEvent(DashboardEvents.DisableSearchMode)
          _viewEffect.send(DashboardEffect.SnackbarNotification("No internet connection"))
        }
      }
      _viewState.update { it.copy(isConnected = isConnected) }
    }.shareIn(viewModelScope, SharingStarted.Eagerly)

    reloadFavoriteMovies()
  }

  private fun loadNextPage() = viewModelScope.launch(backgroundDispatcher) {
    val nextPage = _viewState.value.lastLoadedPage + 1
    getMoviePreviewUseCase(nextPage).let { result ->
      if (result.isSuccess) {
        updateMovieList(result.getOrDefault(listOf()))
      } else {
        Log.e(TAG, "loadNextPage: Can't upload data for page $nextPage", result.exceptionOrNull())
        _viewEffect.send(DashboardEffect.SnackbarNotification("Can't upload data from TMDB"))
      }
    }
    _viewState.update { it.copy(lastLoadedPage = nextPage) }
  }

  private fun updateMovieList(movieList: List<MoviePreviewModel>) {
    val updatedList = movieList.map { movie ->
      movie.copy(
        moviePreviewResponse = movie.moviePreviewResponse.copy(
          posterPath = movie.moviePreviewResponse.posterPath?.let { formatTMDBImageUrl(it) },
          voteAverage = movie.moviePreviewResponse.voteAverage.roundToTwoDecimalPlaces()
        )
      )
    }
    _viewState.update { state ->
      state.copy(
        isLoading = false,
        movies = (state.movies + updatedList).distinctBy { it.moviePreviewResponse.id }.toMutableList()
      )
    }
  }

  override fun obtainEvent(event: DashboardEvents) {
    viewModelScope.launch(backgroundDispatcher) {
      when (event) {
        is DashboardEvents.OnFavoriteClick -> {
          changeFavoriteStatus(event.moviePreviewResponse)
        }

        is DashboardEvents.LoadNextPage -> {
          loadNextPage()
        }

        is DashboardEvents.OnSearchQueryChange -> {
          _viewState.update { it.copy(searchQuery = event.query, searchModeLastLoadedPage = 0, searchedMovies = listOf()) }
          if (event.query.isNotBlank()) {
            updateSearchList()
          }
        }

        is DashboardEvents.ChangeSearchMode -> {
          _viewState.update { it.copy(isSearchMode = event.state) }
        }

        is DashboardEvents.DisableSearchMode -> {
          _viewState.update { it.copy(searchQuery = "", isSearchMode = false, searchModeLastLoadedPage = 0) }
        }

        is DashboardEvents.LoadNextSearchPage -> {
          if (_viewState.value.searchQuery.isNotBlank()) {
            updateSearchList()
          }
        }
      }
    }
  }

  private fun changeFavoriteStatus(movie: MoviePreviewResponse) = viewModelScope.launch(backgroundDispatcher) {
    _viewState.update { state ->
      state.copy(movies = state.movies.map { movieItem ->
        when (movieItem.moviePreviewResponse.id) {
          movie.id -> {
            val newFavoriteStatus = !movieItem.isFavorite
            if (newFavoriteStatus) {
              setFavoriteMovieUseCase(movieItem.moviePreviewResponse)
            } else {
              deleteFavoriteMovieUseCase(movieId = movie.id)
            }
            movieItem.copy(isFavorite = newFavoriteStatus)
          }

          else -> {
            movieItem
          }
        }
      })
    }
  }

  private fun updateSearchList() = viewModelScope.launch(backgroundDispatcher) {
    delay(SEARCH_MODE_DELAY)
    val nextPage = _viewState.value.searchModeLastLoadedPage + 1
    _viewState.update {
      it.copy(
        searchModeLastLoadedPage = nextPage,
        searchedMovies = it.searchedMovies + getMovieSearchUseCase(_viewState.value.searchQuery, nextPage).getOrDefault(listOf())
      )
    }
  }

  private fun reloadFavoriteMovies() = viewModelScope.launch(backgroundDispatcher) {
    getFavoriteMovieUseCase().map { favoriteMovies ->
      var movies = _viewState.value.movies
      movies = movies.map { movie -> movie.copy(isFavorite = favoriteMovies.find { it.movieId == movie.moviePreviewResponse.id } != null) }
      _viewState.update { it.copy(movies = movies) }
    }.shareIn(viewModelScope, SharingStarted.Eagerly)
  }

  companion object {
    private const val SEARCH_MODE_DELAY = 1000L
    private const val TAG = "DashboardViewModel"
  }
}