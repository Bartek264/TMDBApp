package neontri.test.tmdbapp.screens.detail.state

import neontri.test.tmdbapp.model.MovieDetailModel
import neontri.test.tmdbapp.util.mvi.State

data class MovieDetailViewState(
  val isLoading: Boolean = false,
  val movieDetailModel: MovieDetailModel? = null
): State