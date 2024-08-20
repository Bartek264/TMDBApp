package neontri.test.tmdbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import neontri.test.tmdbapp.domain.usecase.favorite.DeleteFavoriteMovieUseCase
import neontri.test.tmdbapp.domain.usecase.favorite.GetFavoriteMoviesUseCase
import neontri.test.tmdbapp.ui.screens.favorite.state.FavoriteEvent
import neontri.test.tmdbapp.ui.screens.favorite.state.FavoriteViewState
import neontri.test.tmdbapp.util.mvi.EventHandler

class FavoriteViewModel(
  private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
  private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
  private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel(), EventHandler<FavoriteEvent> {

  private val _viewState = MutableStateFlow(FavoriteViewState())
  val viewState get() = _viewState.asStateFlow()

  init {
    initData()
  }

  private fun initData() = viewModelScope.launch(backgroundDispatcher) {
    getFavoriteMoviesUseCase().map { favorite ->
      _viewState.update { it.copy(favoriteMovieList = favorite) }
    }.shareIn(viewModelScope, SharingStarted.Eagerly)
  }

  override fun obtainEvent(event: FavoriteEvent) {
    when(event) {
      is FavoriteEvent.RemoveFavoriteMovie -> {
        deleteFavoriteMovie(event.movieId)
      }
    }
  }

  private fun deleteFavoriteMovie(movieId: Int) = viewModelScope.launch(backgroundDispatcher) {
    deleteFavoriteMovieUseCase(movieId)
  }
}