package neontri.test.api.repository

import android.util.Log
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import neontri.test.api.client.KtorClient
import neontri.test.api.mapper.mapToResult
import neontri.test.api.model.detail.MovieDetailResponse
import neontri.test.api.model.preview.MovieResponse
import neontri.test.api.model.search.MovieSearchResponse

class MovieRemoteRepositoryImpl: MovieRemoteRepository {

  override suspend fun getPopularMovies(page: Int): Result<MovieResponse> = runCatching {
    return KtorClient().get("movie/now_playing"){
      parameter("page", page)
    }.mapToResult()
  }.onFailure { Log.e(TAG, "getPopularMovies: Can't get popular movies, with error: ${it.message}", it) }

  override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailResponse> = runCatching {
    return KtorClient().get("movie/$movieId").mapToResult()
  }.onFailure { Log.e(TAG, "getMovieDetails: Can't get movie details, with error: ${it.message}", it) }

  override suspend fun getSearchedMovies(query: String, page: Int): Result<MovieSearchResponse> = runCatching {
    return KtorClient().get("search/movie") {
      parameter("query", query)
      parameter("page", page)
    }.mapToResult()
  }.onFailure { Log.e(TAG, "getSearchedMovies: Can't get searched movies, with error: ${it.message}", it) }

  companion object {
    private const val TAG = "MovieRemoteRepositoryImpl"
  }
}