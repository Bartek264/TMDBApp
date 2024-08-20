package neontri.test.tmdbapp.ui.screens.detail.state

import neontri.test.tmdbapp.domain.model.MovieDetailModel
import neontri.test.tmdbapp.util.mvi.State

data class MovieDetailViewState(
  val isLoading: Boolean = false,
  val movieDetailModel: MovieDetailModel? = null
): State