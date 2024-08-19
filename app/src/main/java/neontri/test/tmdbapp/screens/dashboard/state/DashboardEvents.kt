package neontri.test.tmdbapp.screens.dashboard.state

import neontri.test.api.model.preview.MoviePreviewResponse
import neontri.test.tmdbapp.util.mvi.Event

sealed class DashboardEvents : Event {
  data object LoadNextPage : DashboardEvents()
  data object DisableSearchMode : DashboardEvents()
  data object LoadNextSearchPage : DashboardEvents()
  data class ChangeSearchMode(val state: Boolean) : DashboardEvents()
  data class OnSearchQueryChange(val query: String) : DashboardEvents()
  data class OnFavoriteClick(val moviePreviewResponse: MoviePreviewResponse) : DashboardEvents()
}