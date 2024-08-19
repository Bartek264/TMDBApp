package neontri.test.local.repository

import kotlinx.coroutines.flow.Flow
import neontri.test.local.entity.FavoriteMovieEntity

interface MovieLocalRepository {
  suspend fun insert(movie: FavoriteMovieEntity)
  suspend fun getAll(): Flow<List<FavoriteMovieEntity>>
  suspend fun deleteById(id: Int)
}