package neontri.test.tmdbapp.domain

import kotlinx.coroutines.flow.first
import neontri.test.api.model.search.MovieSearchResultResponse
import neontri.test.local.entity.FavoriteMovieEntity
import neontri.test.tmdbapp.model.MovieSearchModel
import neontri.test.tmdbapp.repository.MovieRepository

class GetMovieSearchUseCase(
  private val movieRepository: MovieRepository
) {

  suspend operator fun invoke(query: String, page: Int): Result<List<MovieSearchModel>> {
    val favoriteMovies = movieRepository.getFavoriteMovies().first()

    return movieRepository.getSearchedMovies(query, page).map { movieResponse ->
      movieResponse.results.map { movieSearchResultResponse ->
        MovieSearchModel(
          movieSearchResultResponse = movieSearchResultResponse,
          isFavorite = isMovieFavorite(favoriteMovies, movieSearchResultResponse)
        )
      }
    }
  }

  private fun isMovieFavorite(favoriteMovies: List<FavoriteMovieEntity>, movieSearchResultResponse: MovieSearchResultResponse): Boolean {
    return favoriteMovies.any { it.id?.toInt() == movieSearchResultResponse.id }
  }
}