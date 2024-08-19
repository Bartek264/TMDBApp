package neontri.test.tmdbapp.model

import neontri.test.api.model.detail.MovieDetailResponse

data class MovieDetailModel(
  val movieDetailResponse: MovieDetailResponse,
  val isFavorite: Boolean
)