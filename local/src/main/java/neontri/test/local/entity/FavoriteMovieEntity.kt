package neontri.test.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long? = null,
  val movieId: Int,
  val posterPath: String?,
  val title: String,
  val releaseDate: String,
  val voteAverage: Double,
  val voteCount: Int
)