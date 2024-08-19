package neontri.test.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import neontri.test.local.entity.FavoriteMovieEntity

@Dao
interface FavoriteMoviesDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertFavoriteMovie(movie: FavoriteMovieEntity)
  @Query("SELECT * FROM favorite_movies")
  fun getAll(): Flow<List<FavoriteMovieEntity>>
  @Query("DELETE FROM favorite_movies WHERE movieId = :movieId")
  fun deleteById(movieId: Int)
}