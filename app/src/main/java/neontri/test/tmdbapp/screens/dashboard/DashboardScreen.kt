package neontri.test.tmdbapp.screens.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import neontri.test.tmdbapp.R
import neontri.test.tmdbapp.navigation.NavigationScreens
import neontri.test.tmdbapp.screens.dashboard.component.DashboardItem
import neontri.test.tmdbapp.screens.dashboard.component.NoConnectionBox
import neontri.test.tmdbapp.screens.dashboard.component.SearchModeItem
import neontri.test.tmdbapp.screens.dashboard.state.DashboardEffect
import neontri.test.tmdbapp.screens.dashboard.state.DashboardEvents
import neontri.test.tmdbapp.viewmodel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {

  val viewModel = koinViewModel<DashboardViewModel>()

  val snackBarHostState = SnackbarHostState()

  val state by viewModel.viewState.collectAsState()
  val effect = viewModel.viewEffect

  val searchLazyListState = rememberLazyListState()
  val dashboardLazyListState = rememberLazyListState()

  Scaffold(
    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    topBar = {
      Row(verticalAlignment = Alignment.CenterVertically) {
        SearchBar(
          modifier = Modifier
            .weight(1f)
            .padding(all = 16.dp),
          query = state.searchQuery,
          onQueryChange = { viewModel.obtainEvent(DashboardEvents.OnSearchQueryChange(it)) },
          onSearch = {},
          enabled = state.isConnected,
          active = state.isSearchMode,
          onActiveChange = { viewModel.obtainEvent(DashboardEvents.ChangeSearchMode(it)) },
          placeholder = { Text(text = stringResource(id = R.string.search)) },
          leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(id = R.string.search)) },
          trailingIcon = {
            AnimatedVisibility(visible = state.isSearchMode) {
              IconButton(onClick = { viewModel.obtainEvent(DashboardEvents.DisableSearchMode) }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = stringResource(id = R.string.clear))
              }
            }
          }
        ) {
          if (state.isSearchMode) {
            state.searchedMovies.let { item ->
              LazyColumn(state = searchLazyListState) {
                items(item) {
                  SearchModeItem(
                    searchResult = it.movieSearchResultResponse,
                    onItemClick = { navController.navigate(NavigationScreens.MovieDetail(it.movieSearchResultResponse.id)) }
                  )
                }
              }
            }
          }
        }

        AnimatedVisibility(state.isSearchMode.not()) {
          IconButton(modifier = Modifier
            .padding(end = 16.dp)
            .size(36.dp), onClick = { navController.navigate(NavigationScreens.Favorite) }) {
            Icon(imageVector = Icons.Default.Star, contentDescription = stringResource(id = R.string.favorite))
          }
        }
      }
    }
  ) {
    if (state.isConnected) {
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
          .padding(it),
        state = dashboardLazyListState,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
      ) {
        if (!state.isSearchMode) {
          item {
            Text(
              modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 16.dp),
              text = stringResource(id = R.string.now_played_movies),
              fontWeight = FontWeight.W800,
              fontSize = 24.sp
            )
          }

          items(state.movies) { moviePreview ->
            DashboardItem(
              moviePreviewModel = moviePreview,
              onFavoriteClick = { viewModel.obtainEvent(DashboardEvents.OnFavoriteClick(moviePreview.moviePreviewResponse)) },
              onItemClick = { navController.navigate(NavigationScreens.MovieDetail(moviePreview.moviePreviewResponse.id)) }
            )
          }
        }
      }
    } else {
      NoConnectionBox(Modifier.fillMaxSize())
    }
  }

  LaunchedEffect(key1 = state.lastLoadedPage) {
    snapshotFlow { dashboardLazyListState.layoutInfo }.first { layoutInfo ->
      layoutInfo.totalItemsCount - 1 == layoutInfo.visibleItemsInfo.lastOrNull()?.index
    }.let { viewModel.obtainEvent(DashboardEvents.LoadNextPage) }
  }

  LaunchedEffect(key1 = state.searchModeLastLoadedPage) {
    snapshotFlow { searchLazyListState.layoutInfo }.first { layoutInfo ->
      layoutInfo.totalItemsCount - 1 == layoutInfo.visibleItemsInfo.lastOrNull()?.index
    }.let { viewModel.obtainEvent(DashboardEvents.LoadNextSearchPage) }
  }

  LaunchedEffect(Unit) {
    effect.collectLatest {
      when (it) {
        is DashboardEffect.SnackbarNotification -> {
          snackBarHostState.showSnackbar(message = it.message)
        }
      }
    }
  }
}