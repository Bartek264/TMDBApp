package neontri.test.tmdbapp.screens.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import neontri.test.api.model.detail.ProductionCompany
import neontri.test.tmdbapp.R

@Composable
fun ProductionCompanyItem(productionCompany: ProductionCompany) {
  val context = LocalContext.current

  Card(
    modifier = Modifier
      .width(160.dp)
      .padding(horizontal = 4.dp)
  ) {
    Column {
      SubcomposeAsyncImage(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(1f),
        model = ImageRequest.Builder(context)
          .data(productionCompany.logoPath ?: R.drawable.no_image)
          .placeholder(R.drawable.no_image)
          .build(),
        contentDescription = stringResource(R.string.production_company_logo),
        contentScale = ContentScale.Fit
      )

      Text(
        modifier = Modifier
          .fillMaxWidth()
          .padding(all = 8.dp),
        text = productionCompany.name,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp
      )
    }
  }
}