package neontri.test.api.domain.model.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponse(
  @SerialName("page")
    val page: Int?,
  @SerialName("results")
    val results: List<MovieSearchResultResponse> = listOf(),
  @SerialName("total_pages")
    val totalPages: Int?,
  @SerialName("total_results")
    val totalResults: Int?
)