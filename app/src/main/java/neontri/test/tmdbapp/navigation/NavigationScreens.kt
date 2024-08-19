package neontri.test.tmdbapp.navigation

import kotlinx.serialization.Serializable

sealed class NavigationScreens {
  @Serializable
  data object Dashboard: NavigationScreens()
  @Serializable
  data class MovieDetail(val movieId: Int): NavigationScreens()
  @Serializable
  data object Favorite : NavigationScreens()
}