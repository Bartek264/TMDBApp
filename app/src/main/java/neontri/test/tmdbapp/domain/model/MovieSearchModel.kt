package neontri.test.tmdbapp.domain.model

import neontri.test.api.domain.model.search.MovieSearchResultResponse

data class MovieSearchModel(
  val movieSearchResultResponse: MovieSearchResultResponse,
  val isFavorite: Boolean
)
