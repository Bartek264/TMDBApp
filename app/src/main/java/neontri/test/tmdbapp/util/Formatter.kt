package neontri.test.tmdbapp.util

import neontri.test.tmdbapp.util.Const.IMAGE_BASE_URL
import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundToTwoDecimalPlaces(): Double {
  val df = DecimalFormat("#.##")
  df.roundingMode = RoundingMode.CEILING
  return df.format(this).replace(",",".").toDouble()
}

fun formatTMDBImageUrl(croppedUrl: String): String {
  return IMAGE_BASE_URL + croppedUrl
}