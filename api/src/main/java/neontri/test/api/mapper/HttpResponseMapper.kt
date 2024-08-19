package neontri.test.api.mapper

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

suspend inline fun <reified T> HttpResponse.mapToResult(): Result<T> {
  return try {
    when (status.value) {
      in 200..299 -> {
        Result.success(body<T>())
      }
      else -> Result.failure(HttpResponseException(status, bodyAsText()))
    }
  } catch (e: Exception) {
    Result.failure(e)
  }
}

class HttpResponseException(val status: HttpStatusCode, message: String) : Exception(message)
