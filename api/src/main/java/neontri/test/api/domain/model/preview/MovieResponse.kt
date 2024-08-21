package neontri.test.api.domain.model.preview

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
  @SerialName("dates")
  val dates: Dates,
  @SerialName("page")
  val page: Int,
  @SerialName("results")
  val results: List<MoviePreviewResponse>,
  @SerialName("total_pages")
  val totalPages: Int,
  @SerialName("total_results")
  val totalResults: Int
)