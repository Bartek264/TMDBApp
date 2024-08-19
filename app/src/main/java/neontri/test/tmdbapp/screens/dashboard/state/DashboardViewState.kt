package neontri.test.tmdbapp.screens.dashboard.state

import neontri.test.tmdbapp.model.MoviePreviewModel
import neontri.test.tmdbapp.model.MovieSearchModel

data class DashboardViewState(
  val isLoading: Boolean = false,
  val isConnected: Boolean = false,
  val lastLoadedPage: Int = 0,
  val searchModeLastLoadedPage: Int = 0,
  val movies: List<MoviePreviewModel> = emptyList(),
  val searchedMovies: List<MovieSearchModel> = listOf(),
  val isSearchMode: Boolean = false,
  val searchQuery: String = ""
)
