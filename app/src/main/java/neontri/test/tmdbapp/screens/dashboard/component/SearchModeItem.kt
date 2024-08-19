package neontri.test.tmdbapp.screens.dashboard.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import neontri.test.api.model.search.MovieSearchResultResponse

@Composable
fun SearchModeItem(
  searchResult: MovieSearchResultResponse,
  onItemClick: () -> Unit = {}
) {
  Column(modifier = Modifier.fillMaxWidth().clickable { onItemClick() }) {
    Text(modifier = Modifier.padding(8.dp), text = searchResult.title ?: "")
    Divider(modifier = Modifier.fillMaxWidth())
  }
}