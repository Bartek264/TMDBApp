package neontri.test.tmdbapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import neontri.test.api.domain.model.detail.MovieDetailResponse
import neontri.test.tmdbapp.R
import neontri.test.tmdbapp.domain.model.MovieDetailModel
import neontri.test.tmdbapp.ui.screens.detail.component.ProductionCompanyItem
import neontri.test.tmdbapp.ui.screens.detail.state.MovieDetailViewState
import neontri.test.tmdbapp.ui.theme.FavoriteColor
import neontri.test.tmdbapp.ui.theme.TMDBAppTheme
import neontri.test.tmdbapp.util.provideMovieDetailResponse

@Composable
fun DetailContent(
  paddingValues: PaddingValues,
  state: MovieDetailViewState,
  onFavoriteClick: () -> Unit
) {

  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(paddingValues)
      .verticalScroll(scrollState)
  ) {
    MovieBackdrop(state, onFavoriteClick)
    state.movieDetailModel?.movieDetailResponse?.let { MovieDescription(movieDetailResponse = it) }
    LazyRow(Modifier.padding(top = 8.dp)) {
      items(state.movieDetailModel?.movieDetailResponse?.productionCompanies ?: listOf()) {
        ProductionCompanyItem(productionCompany = it)
      }
    }
  }
}

@Composable
private fun MovieBackdrop(
  state: MovieDetailViewState,
  onFavoriteClick: () -> Unit
) {
  val context = LocalContext.current
  val isFavorite = state.movieDetailModel?.isFavorite ?: false

  Box(contentAlignment = Alignment.BottomCenter) {
    SubcomposeAsyncImage(
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1.78f),
      model = ImageRequest.Builder(context)
        .data(state.movieDetailModel?.movieDetailResponse?.backdropPath ?: R.drawable.no_image)
        .placeholder(R.drawable.no_image)
        .build(),
      contentDescription = null,
      contentScale = ContentScale.Fit
    )

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          brush = Brush.verticalGradient(
            startY = 0f,
            endY = 100f,
            colors = listOf(
              Color.Transparent,
              MaterialTheme.colorScheme.background
            )
          )
        )
    ) {
      Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {

        state.movieDetailModel?.movieDetailResponse?.let {
          Text(
            text = "${it.voteAverage} (${it.voteCount})",
            fontWeight = FontWeight.SemiBold
          )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
          onClick = { onFavoriteClick() }
        ) {
          Icon(
            imageVector = if (isFavorite) {
              Icons.Default.Star
            } else {
              Icons.Outlined.StarOutline
            },
            contentDescription = stringResource(id = R.string.favorite),
            tint = if (isFavorite) {
              FavoriteColor
            } else {
              LocalContentColor.current
            }
          )
        }
      }
    }

  }
}

@Composable
fun MovieDescription(movieDetailResponse: MovieDetailResponse) {
  Column(
    Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
  ) {
    Text(
      modifier = Modifier.padding(top = 8.dp),
      text = movieDetailResponse.title,
      fontWeight = FontWeight.W800,
      fontSize = 18.sp
    )

    Column {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 8.dp)
      ) {
        Text(
          text = movieDetailResponse.releaseDate,
          fontSize = 12.sp,
          fontWeight = FontWeight.Light
        )
        Text(
          modifier = Modifier.padding(horizontal = 8.dp),
          text = "${movieDetailResponse.runtime} min",
          fontSize = 12.sp,
          fontWeight = FontWeight.Light
        )
      }

      LazyRow(modifier = Modifier.padding(bottom = 8.dp)) {
        items(movieDetailResponse.genres) { genre ->
          Card(
            modifier = Modifier.padding(horizontal = 4.dp),
            shape = RoundedCornerShape(20)
          ) {
            Text(
              modifier = Modifier.padding(4.dp),
              text = genre.name,
              fontSize = 12.sp,
              fontWeight = FontWeight.Light
            )
          }
        }
      }
    }

    Text(
      text = movieDetailResponse.overview,
      textAlign = TextAlign.Justify
    )

    if (movieDetailResponse.productionCompanies.isNotEmpty()) {
      Text(
        modifier = Modifier.padding(top = 8.dp),
        text = stringResource(id = R.string.production_companies),
        fontWeight = FontWeight.W800,
        fontSize = 18.sp
      )
    }
  }
}

@Preview(showSystemUi = true)
@Composable
private fun DetailContentPreview() {
  TMDBAppTheme {
    DetailContent(
      paddingValues = PaddingValues(),
      state = MovieDetailViewState(
        movieDetailModel = MovieDetailModel(provideMovieDetailResponse(), false)
      ),
      onFavoriteClick = {}
    )
  }
}
