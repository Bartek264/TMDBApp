package neontri.test.tmdbapp.repository

import kotlinx.coroutines.flow.Flow
import neontri.test.api.domain.model.detail.MovieDetailResponse
import neontri.test.api.domain.model.preview.MovieResponse
import neontri.test.api.domain.model.search.MovieSearchResponse
import neontri.test.local.entity.FavoriteMovieEntity

interface MovieRepository {
  suspend fun getNowPlayedMovie(page: Int): Result<MovieResponse>
  suspend fun getMovieDetails(movieId: Int): Result<MovieDetailResponse>
  suspend fun getSearchedMovies(query: String, page: Int): Result<MovieSearchResponse>
  suspend fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
  suspend fun insertNewFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)
  suspend fun deleteFavoriteMovie(movieId: Int)
}