package neontri.test.tmdbapp.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow
import neontri.test.local.entity.FavoriteMovieEntity
import neontri.test.tmdbapp.repository.MovieRepository

class GetFavoriteMoviesUseCase(private val movieRepository: MovieRepository) {

  suspend operator fun invoke(): Flow<List<FavoriteMovieEntity>> {
    return movieRepository.getFavoriteMovies()
  }

}