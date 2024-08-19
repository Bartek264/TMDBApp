package neontri.test.tmdbapp.screens.detail.state

import neontri.test.tmdbapp.util.mvi.Event

sealed class MovieDetailEvent: Event {
  data object ToggleFavorite : MovieDetailEvent()
  data class LoadMovieDetails(val movieId: Int) : MovieDetailEvent()
}