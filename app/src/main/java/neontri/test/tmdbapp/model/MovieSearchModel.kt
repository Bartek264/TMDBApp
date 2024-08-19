package neontri.test.tmdbapp.model

import neontri.test.api.model.search.MovieSearchResultResponse

data class MovieSearchModel(
  val movieSearchResultResponse: MovieSearchResultResponse,
  val isFavorite: Boolean
)
