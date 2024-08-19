package neontri.test.navigation

import kotlinx.serialization.Serializable

sealed class NavigationScreens {
  @Serializable
  data object Dashboard: NavigationScreens()
  @Serializable
  data object MovieDetail: NavigationScreens()
}