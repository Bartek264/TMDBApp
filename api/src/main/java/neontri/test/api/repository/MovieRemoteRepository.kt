package neontri.test.api.repository

import neontri.test.api.model.detail.MovieDetailResponse
import neontri.test.api.model.preview.MovieResponse
import neontri.test.api.model.search.MovieSearchResponse

interface MovieRemoteRepository {
  suspend fun getPopularMovies(page: Int): Result<MovieResponse>
  suspend fun getMovieDetails(movieId: Int): Result<MovieDetailResponse>
  suspend fun getSearchedMovies(query: String, page: Int): Result<MovieSearchResponse>
}