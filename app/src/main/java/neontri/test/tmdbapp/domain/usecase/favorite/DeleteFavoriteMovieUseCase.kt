package neontri.test.tmdbapp.domain.usecase.favorite

import neontri.test.tmdbapp.repository.MovieRepository

class DeleteFavoriteMovieUseCase(private val movieRepository: MovieRepository) {

  suspend operator fun invoke(movieId: Int) {
    movieRepository.deleteFavoriteMovie(movieId)
  }
}