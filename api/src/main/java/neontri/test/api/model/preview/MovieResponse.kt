package neontri.test.api.model.preview

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MoviePreviewResponse>,
    val total_pages: Int,
    val total_results: Int
)