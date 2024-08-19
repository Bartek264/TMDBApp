package neontri.test.tmdbapp.screens.favorite.state

import neontri.test.tmdbapp.util.mvi.Event

sealed class FavoriteEvent: Event {
  data class RemoveFavoriteMovie(val movieId: Int): FavoriteEvent()
}