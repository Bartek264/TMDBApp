package neontri.test.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationController() {
  val navHostController = rememberNavController()

  NavHost(navController = navHostController, startDestination = NavigationScreens.Dashboard) {
    composable<NavigationScreens.Dashboard> {

    }
    composable<NavigationScreens.MovieDetail> {

    }
  }
}