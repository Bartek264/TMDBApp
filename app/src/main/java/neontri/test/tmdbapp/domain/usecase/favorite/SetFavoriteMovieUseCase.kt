package neontri.test.tmdbapp.domain.usecase.favorite

import neontri.test.api.domain.model.preview.MoviePreviewResponse
import neontri.test.local.entity.FavoriteMovieEntity
import neontri.test.tmdbapp.domain.model.MovieDetailModel
import neontri.test.tmdbapp.repository.MovieRepository

class SetFavoriteMovieUseCase(private val repository: MovieRepository) {

  suspend operator fun invoke(movieDetailResponse: MovieDetailModel) {
    val entity = FavoriteMovieEntity(
      movieId = movieDetailResponse.movieDetailResponse.id,
      posterPath = movieDetailResponse.movieDetailResponse.posterPath ?: "",
      title = movieDetailResponse.movieDetailResponse.title,
      releaseDate = movieDetailResponse.movieDetailResponse.releaseDate,
      voteAverage = movieDetailResponse.movieDetailResponse.voteAverage,
      voteCount = movieDetailResponse.movieDetailResponse.voteCount
    )

    repository.insertNewFavoriteMovie(entity)
  }

  suspend operator fun invoke(moviePreviewResponse: MoviePreviewResponse) {
    val entity = FavoriteMovieEntity(
      movieId = moviePreviewResponse.id,
      posterPath = moviePreviewResponse.posterPath,
      title = moviePreviewResponse.title,
      releaseDate = moviePreviewResponse.releaseDate,
      voteAverage = moviePreviewResponse.voteAverage,
      voteCount = moviePreviewResponse.voteCount
    )

    repository.insertNewFavoriteMovie(entity)
  }

}