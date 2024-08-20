package neontri.test.tmdbapp.util

import neontri.test.api.domain.model.detail.BelongsToCollection
import neontri.test.api.domain.model.detail.Genre
import neontri.test.api.domain.model.detail.MovieDetailResponse
import neontri.test.api.domain.model.detail.ProductionCompany
import neontri.test.api.domain.model.detail.ProductionCountry
import neontri.test.api.domain.model.detail.SpokenLanguage
import neontri.test.api.domain.model.preview.MoviePreviewResponse

fun provideMoviePreview(): MoviePreviewResponse {
  return MoviePreviewResponse(
    adult = false,
    backdrop_path = "https://image.tmdb.org/t/p/w185/yDHYTfA3R0jFYba16jBB1ef8oIt.jpg",
    genre_ids = listOf(28, 35, 878),
    id = 533535,
    original_language = "en",
    original_title = "Deadpool & Wolverine",
    overview = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine.",
    popularity = 9734.605,
    poster_path = "https://image.tmdb.org/t/p/w185/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
    release_date = "2024-07-24",
    title = "Deadpool & Wolverine",
    video = false,
    vote_average = 7.833,
    vote_count = 1847
  )
}

fun provideMovieDetailResponse(): MovieDetailResponse {
  return MovieDetailResponse(
    adult = false,
    backdropPath = "https://image.tmdb.org/t/p/w185/yDHYTfA3R0jFYba16jBB1ef8oIt.jpg",
    belongsToCollection = BelongsToCollection(
      id = 448150,
      name = "Deadpool Collection",
      posterPath = "https://image.tmdb.org/t/p/w185/30c5jO7YEXuF8KiWXLg9m28GWDA.jpg",
      backdropPath = "https://image.tmdb.org/t/p/w185/hBQOWY8qWXJVFAc8yLTh1teIu43.jpg"
    ),
    budget = 200000000,
    genres = listOf(
      Genre(28, "Action"),
      Genre(35, "Comedy"),
      Genre(878, "Science Fiction"),
    ),
    homepage = "https://www.marvel.com/movies/deadpool-and-wolverine",
    id = 533535,
    imdbId = "tt6263850",
    originCountry = listOf("US"),
    originalLanguage = "en",
    originalTitle = "Deadpool & Wolverine",
    overview = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces " +
      "an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine.",
    popularity = 9734.605,
    posterPath = "https://image.tmdb.org/t/p/w185/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
    productionCompanies = listOf(
      ProductionCompany(
        id = 420,
        logoPath = "https://image.tmdb.org/t/p/w185/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
        name = "Marvel Studios",
        originCountry = "US"
      ),
      ProductionCompany(
        id = 104228,
        logoPath = "https://image.tmdb.org/t/p/w185/hx0C1XcSxGgat8N62GpxoJGTkCk.png",
        name = "Maximum Effort",
        originCountry = "US"
      ),
      ProductionCompany(
        id = 2575,
        logoPath = "https://image.tmdb.org/t/p/w185/9YJrHYlcfHtwtulkFMAies3aFEl.png",
        name = "21 Laps Entertainment",
        originCountry = "US"
      ),
      ProductionCompany(
        id = 127928,
        logoPath = "https://image.tmdb.org/t/p/w185/h0rjX5vjW5r8yEnUBStFarjcLT4.png",
        name = "20th Century Studios",
        originCountry = "US"
      ),
      ProductionCompany(
        id = 176762,
        logoPath = null,
        name = "Kevin Feige Productions",
        originCountry = "US"
      ),

      ),
    productionCountries = listOf(
      ProductionCountry(
        iso31661 = "US",
        name = "United States of America"
      )
    ),
    releaseDate = "2024-07-24",
    revenue = 1043180185,
    runtime = 128,
    spokenLanguages = listOf(
      SpokenLanguage(
        englishName = "English",
        iso6391 = "en",
        name = "English"
      )
    ),
    status = "Released",
    tagline = "Come together.",
    title = "Deadpool & Wolverine",
    video = false,
    voteAverage = 7.83,
    voteCount = 1881
  )
}