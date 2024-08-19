package neontri.test.local

import androidx.room.Database
import androidx.room.RoomDatabase
import neontri.test.local.dao.FavoriteMoviesDao
import neontri.test.local.entity.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
  abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}