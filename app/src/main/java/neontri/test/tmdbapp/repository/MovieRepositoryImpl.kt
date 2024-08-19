package neontri.test.tmdbapp.repository

import kotlinx.coroutines.flow.Flow
import neontri.test.api.model.detail.MovieDetailResponse
import neontri.test.api.model.preview.MovieResponse
import neontri.test.api.model.search.MovieSearchResponse
import neontri.test.api.repository.MovieRemoteRepository
import neontri.test.local.entity.FavoriteMovieEntity
import neontri.test.local.repository.MovieLocalRepository

class MovieRepositoryImpl(
  private val movieLocalRepository: MovieLocalRepository,
  private val movieRemoteRepository: MovieRemoteRepository
): MovieRepository {
  override suspend fun getNowPlayedMovie(page: Int): Result<MovieResponse> {
    return movieRemoteRepository.getPopularMovies(page)
  }

  override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailResponse> {
    return movieRemoteRepository.getMovieDetails(movieId)
  }

  override suspend fun getSearchedMovies(query: String, page: Int): Result<MovieSearchResponse> {
    return movieRemoteRepository.getSearchedMovies(query, page)
  }

  override suspend fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
    return movieLocalRepository.getAll()
  }

  override suspend fun insertNewFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) {
    movieLocalRepository.insert(favoriteMovieEntity)
  }

  override suspend fun deleteFavoriteMovie(movieId: Int) {
    movieLocalRepository.deleteById(movieId)
  }

}