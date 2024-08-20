package neontri.test.tmdbapp.domain.model

import neontri.test.api.domain.model.detail.MovieDetailResponse

data class MovieDetailModel(
  val movieDetailResponse: MovieDetailResponse,
  val isFavorite: Boolean
)