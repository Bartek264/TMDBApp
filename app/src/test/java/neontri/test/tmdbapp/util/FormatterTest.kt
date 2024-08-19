package neontri.test.tmdbapp.util

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FormatterTest {

  companion object {
    private const val PI = 3.14159265
    private const val POSTER_PATH = "/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg"
  }

  private lateinit var correctFullPosterPath: String

  @Before
  fun setUp() {
    correctFullPosterPath = provideMovieDetailResponse().posterPath!!
  }

  @Test
  fun `formatTMDBImageUrl should return correct full poster path`() {
    val result = formatTMDBImageUrl(POSTER_PATH)
    assertThat(result).isEqualTo(correctFullPosterPath)
  }

  @Test
  fun `roundToTwoDecimalPlaces should round to two decimal places`() {
    val result = PI.roundToTwoDecimalPlaces()
    assertThat(result).isEqualTo(3.15)
  }
}