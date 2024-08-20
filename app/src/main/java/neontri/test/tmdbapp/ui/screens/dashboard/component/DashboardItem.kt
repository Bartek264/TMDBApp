package neontri.test.tmdbapp.ui.screens.dashboard.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import neontri.test.tmdbapp.R
import neontri.test.tmdbapp.domain.model.MoviePreviewModel
import neontri.test.tmdbapp.ui.theme.FavoriteColor
import neontri.test.tmdbapp.ui.theme.TMDBAppTheme
import neontri.test.tmdbapp.util.provideMoviePreview

@Composable
fun DashboardItem(
  moviePreviewModel: MoviePreviewModel,
  onFavoriteClick: () -> Unit,
  onItemClick: () -> Unit
) {
  val context = LocalContext.current

  Card(
    modifier = Modifier
      .height(160.dp)
      .fillMaxWidth()
      .padding(vertical = 8.dp)
      .clickable { onItemClick() }
  ) {
    Row {
      SubcomposeAsyncImage(
        modifier = Modifier
          .fillMaxHeight()
          .aspectRatio(2f / 3f),
        model = ImageRequest.Builder(context)
          .data(moviePreviewModel.moviePreviewResponse.poster_path)
          .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit
      )

      Column(
        modifier = Modifier
          .weight(2f)
          .fillMaxHeight()
          .padding(start = 16.dp)
          .padding(vertical = 16.dp)
      ) {
        Text(
          modifier = Modifier.weight(2f),
          text = moviePreviewModel.moviePreviewResponse.title,
          fontWeight = FontWeight.W800,
          fontSize = 18.sp,
          maxLines = 2
        )
        Text(
          modifier = Modifier.weight(1f),
          text = moviePreviewModel.moviePreviewResponse.release_date,
          fontWeight = FontWeight.W400,
          fontSize = 14.sp
        )
        Text(
          modifier = Modifier.weight(1f),
          text = "${moviePreviewModel.moviePreviewResponse.vote_average} (${moviePreviewModel.moviePreviewResponse.vote_count})",
          fontWeight = FontWeight.W500,
          fontSize = 16.sp
        )
      }

      IconButton(onClick = { onFavoriteClick() }) {
        Icon(
          modifier = Modifier.size(24.dp),
          imageVector = if (moviePreviewModel.isFavorite) {
            Icons.Default.Star
          } else {
            Icons.Outlined.StarOutline
          },
          contentDescription = stringResource(id = R.string.favorite),
          tint = if (moviePreviewModel.isFavorite) {
            FavoriteColor
          } else {
            LocalContentColor.current
          }
        )
      }
    }
  }
}

@Preview
@Composable
private fun PreviewDashboardItem2() {
  TMDBAppTheme {
    DashboardItem(
      moviePreviewModel = MoviePreviewModel(provideMoviePreview(), false),
      onFavoriteClick = {},
      onItemClick = {}
    )
  }
}