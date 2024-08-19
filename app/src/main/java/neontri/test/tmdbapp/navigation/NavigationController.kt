package neontri.test.tmdbapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import neontri.test.tmdbapp.screens.dashboard.DashboardScreen
import neontri.test.tmdbapp.screens.detail.DetailScreen
import neontri.test.tmdbapp.screens.favorite.FavoriteScreen

@Composable
fun NavigationController() {
  val navHostController = rememberNavController()

  NavHost(navController = navHostController, startDestination = NavigationScreens.Dashboard) {
    composable<NavigationScreens.Dashboard> {
      DashboardScreen(navController = navHostController)
    }
    composable<NavigationScreens.MovieDetail> {
      val movieId = it.toRoute<NavigationScreens.MovieDetail>().movieId
      DetailScreen(navHostController = navHostController, movieId =  movieId)
    }
    composable<NavigationScreens.Favorite> {
      FavoriteScreen(navHostController = navHostController)
    }
  }
}