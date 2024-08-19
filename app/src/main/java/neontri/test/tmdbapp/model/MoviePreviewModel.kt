package neontri.test.tmdbapp.model

import neontri.test.api.model.preview.MoviePreviewResponse

data class MoviePreviewModel(
  val moviePreviewResponse: MoviePreviewResponse,
  val isFavorite: Boolean,
)
