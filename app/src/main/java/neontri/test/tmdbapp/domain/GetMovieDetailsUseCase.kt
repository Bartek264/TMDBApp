package neontri.test.tmdbapp.domain

import kotlinx.coroutines.flow.first
import neontri.test.api.model.detail.MovieDetailResponse
import neontri.test.local.entity.FavoriteMovieEntity
import neontri.test.tmdbapp.model.MovieDetailModel
import neontri.test.tmdbapp.repository.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
  suspend operator fun invoke(movieId: Int): Result<MovieDetailModel> {
    val favoriteMovies = movieRepository.getFavoriteMovies().first()
    val movieDetails = movieRepository.getMovieDetails(movieId)
    return movieDetails.map {
      MovieDetailModel(
        isFavorite = checkIfFavorite(favoriteMovies, it),
        movieDetailResponse = it
      )
    }
  }

  private fun checkIfFavorite(favoriteMovies: List<FavoriteMovieEntity>, movieDetailResponse: MovieDetailResponse): Boolean {
    return favoriteMovies.any { it.movieId == movieDetailResponse.id }
  }
}