package neontri.test.api.repository

import android.util.Log
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import neontri.test.api.client.KtorClient
import neontri.test.api.domain.mapper.mapToResult
import neontri.test.api.domain.model.detail.MovieDetailResponse
import neontri.test.api.domain.model.preview.MovieResponse
import neontri.test.api.domain.model.search.MovieSearchResponse

class MovieRemoteRepositoryImpl(private val ktorClient: KtorClient): MovieRemoteRepository {

  override suspend fun getPopularMovies(page: Int): Result<MovieResponse> = runCatching {
    return ktorClient().get("movie/now_playing"){
      parameter("page", page)
    }.mapToResult()
  }.onFailure { Log.e(TAG, "getPopularMovies: Can't get popular movies, with error: ${it.message}", it) }

  override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailResponse> = runCatching {
    return ktorClient().get("movie/$movieId").mapToResult()
  }.onFailure { Log.e(TAG, "getMovieDetails: Can't get movie details, with error: ${it.message}", it) }

  override suspend fun getSearchedMovies(query: String, page: Int): Result<MovieSearchResponse> = runCatching {
    return ktorClient().get("search/movie") {
      parameter("query", query)
      parameter("page", page)
    }.mapToResult()
  }.onFailure { Log.e(TAG, "getSearchedMovies: Can't get searched movies, with error: ${it.message}", it) }

  companion object {
    private const val TAG = "MovieRemoteRepositoryImpl"
  }
}