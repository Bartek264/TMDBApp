package neontri.test.tmdbapp.screens.favorite.component

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import neontri.test.local.entity.FavoriteMovieEntity
import neontri.test.tmdbapp.R
import neontri.test.tmdbapp.ui.theme.FavoriteColor
import neontri.test.tmdbapp.ui.theme.TMDBAppTheme

@Composable
fun FavoriteMovieItem(
  favoriteMovieEntity: FavoriteMovieEntity,
  onFavoriteClick: () -> Unit,
  onItemClick:() -> Unit
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
          .data(favoriteMovieEntity.posterPath)
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
          text = favoriteMovieEntity.title,
          fontWeight = FontWeight.W800,
          fontSize = 18.sp,
          maxLines = 2
        )
        Text(
          modifier = Modifier.weight(1f),
          text = favoriteMovieEntity.releaseDate,
          fontWeight = FontWeight.W400,
          fontSize = 14.sp
        )
        Text(
          modifier = Modifier.weight(1f),
          text = "${favoriteMovieEntity.voteAverage} (${favoriteMovieEntity.voteCount})",
          fontWeight = FontWeight.W500,
          fontSize = 16.sp
        )
      }

      IconButton(onClick = { onFavoriteClick() }) {
        Icon(
          modifier = Modifier.size(24.dp),
          imageVector = Icons.Default.Star,
          contentDescription = stringResource(id = R.string.favorite),
          tint = FavoriteColor
        )
      }
    }
  }
}
