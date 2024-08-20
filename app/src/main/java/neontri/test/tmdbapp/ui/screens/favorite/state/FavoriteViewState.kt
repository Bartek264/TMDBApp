package neontri.test.tmdbapp.ui.screens.favorite.state

import neontri.test.local.entity.FavoriteMovieEntity
import neontri.test.tmdbapp.util.mvi.State

data class FavoriteViewState(
  val isLoading: Boolean = false,
  val favoriteMovieList: List<FavoriteMovieEntity> = listOf()
): State