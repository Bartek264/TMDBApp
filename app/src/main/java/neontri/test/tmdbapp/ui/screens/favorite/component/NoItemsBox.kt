package neontri.test.tmdbapp.ui.screens.favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import neontri.test.tmdbapp.R

@Composable
fun NoItemsBox(modifier: Modifier) {
  Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
    Icon(
      modifier = Modifier.size(96.dp),
      imageVector = Icons.Outlined.Star,
      contentDescription = stringResource(id = R.string.no_favorite_movies)
    )
    Spacer(modifier = Modifier.height(32.dp))
    Text(
      text = stringResource(id = R.string.no_favorite_movies),
      fontWeight = FontWeight.W800,
      fontSize = 24.sp
    )
  }
}