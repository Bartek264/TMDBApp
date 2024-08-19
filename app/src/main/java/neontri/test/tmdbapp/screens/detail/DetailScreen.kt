package neontri.test.tmdbapp.screens.detail

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import neontri.test.tmdbapp.R
import neontri.test.tmdbapp.screens.detail.state.MovieDetailEvent
import neontri.test.tmdbapp.viewmodel.MovieDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
  navHostController: NavHostController,
  movieId: Int
) {

  val viewModel = koinViewModel<MovieDetailsViewModel>()

  val state by viewModel.movieDetailViewState.collectAsState()
  viewModel.obtainEvent(MovieDetailEvent.LoadMovieDetails(movieId))

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = stringResource(id = R.string.movie_details)) },
        navigationIcon = {
          IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(id = R.string.back))
          }
        }
      )
    }
  ) { pv ->
    DetailContent(
      paddingValues = pv,
      state = state,
      onFavoriteClick = { viewModel.obtainEvent(MovieDetailEvent.ToggleFavorite) }
    )
  }
}