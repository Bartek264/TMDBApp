package neontri.test.tmdbapp.ui.screens.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import neontri.test.tmdbapp.R
import neontri.test.tmdbapp.navigation.NavigationScreens
import neontri.test.tmdbapp.ui.screens.favorite.component.FavoriteMovieItem
import neontri.test.tmdbapp.ui.screens.favorite.component.NoItemsBox
import neontri.test.tmdbapp.ui.screens.favorite.state.FavoriteEvent
import neontri.test.tmdbapp.viewmodel.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navHostController: NavHostController) {

  val viewModel = koinViewModel<FavoriteViewModel>()
  val state by viewModel.viewState.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = stringResource(id = R.string.favorite_movies)) },
        navigationIcon = {
          IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(id = R.string.back))
          }
        }
      )
    }
  ) { pv ->
    if (state.favoriteMovieList.isEmpty()) {
       NoItemsBox(modifier = Modifier.fillMaxSize().padding(pv))
    }

    LazyColumn(modifier = Modifier.padding(pv), contentPadding = PaddingValues(horizontal = 16.dp)) {
      items(state.favoriteMovieList) { movie ->
        FavoriteMovieItem(
          favoriteMovieEntity = movie,
          onFavoriteClick = { viewModel.obtainEvent(FavoriteEvent.RemoveFavoriteMovie(movie.movieId)) },
          onItemClick = { navHostController.navigate(NavigationScreens.MovieDetail(movie.movieId)) }
        )
      }
    }
  }

}