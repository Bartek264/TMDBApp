package neontri.test.tmdbapp.domain

import kotlinx.coroutines.flow.first
import neontri.test.api.model.preview.MoviePreviewResponse
import neontri.test.local.entity.FavoriteMovieEntity
import neontri.test.tmdbapp.model.MoviePreviewModel
import neontri.test.tmdbapp.repository.MovieRepository

class GetMoviePreviewUseCase(private val movieRepository: MovieRepository) {

  suspend operator fun invoke(page: Int): Result<List<MoviePreviewModel>> {
    val favoriteMovies = movieRepository.getFavoriteMovies().first()
    return movieRepository.getNowPlayedMovie(page).map { movieList ->
      movieList.results.map { movie ->
        MoviePreviewModel(
          isFavorite = isMovieFavorite(favoriteMovies, movie),
          moviePreviewResponse = movie
        )
      }
    }
  }

  private fun isMovieFavorite(favoriteMovies: List<FavoriteMovieEntity>, moviePreviewResponse: MoviePreviewResponse): Boolean {
    return favoriteMovies.any { it.movieId == moviePreviewResponse.id }
  }
}