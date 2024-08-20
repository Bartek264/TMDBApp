package neontri.test.tmdbapp.ui.screens.dashboard.state

import neontri.test.api.domain.model.preview.MoviePreviewResponse
import neontri.test.tmdbapp.util.mvi.Event

sealed class DashboardEvents : Event {
  data object LoadNextPage : DashboardEvents()
  data object DisableSearchMode : DashboardEvents()
  data object LoadNextSearchPage : DashboardEvents()
  data class ChangeSearchMode(val state: Boolean) : DashboardEvents()
  data class OnSearchQueryChange(val query: String) : DashboardEvents()
  data class OnFavoriteClick(val moviePreviewResponse: MoviePreviewResponse) : DashboardEvents()
}