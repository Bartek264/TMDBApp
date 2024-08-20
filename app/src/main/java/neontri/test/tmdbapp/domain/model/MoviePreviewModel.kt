package neontri.test.tmdbapp.domain.model

import neontri.test.api.domain.model.preview.MoviePreviewResponse

data class MoviePreviewModel(
  val moviePreviewResponse: MoviePreviewResponse,
  val isFavorite: Boolean,
)
