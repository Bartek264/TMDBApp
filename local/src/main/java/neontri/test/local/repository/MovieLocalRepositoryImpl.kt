package neontri.test.local.repository

import kotlinx.coroutines.flow.Flow
import neontri.test.local.dao.FavoriteMoviesDao
import neontri.test.local.entity.FavoriteMovieEntity

class MovieLocalRepositoryImpl(private val favoriteMoviesDao: FavoriteMoviesDao): MovieLocalRepository {
  override suspend fun insert(movie: FavoriteMovieEntity) {
    favoriteMoviesDao.insertFavoriteMovie(movie)
  }

  override suspend fun getAll(): Flow<List<FavoriteMovieEntity>> {
    return favoriteMoviesDao.getAll()
  }

  override suspend fun deleteById(movieId: Int) {
    favoriteMoviesDao.deleteById(movieId)
  }
}