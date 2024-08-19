package neontri.test.api.client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object KtorClient {

  private const val BASE_URL = "https://api.themoviedb.org/3/"
  private const val TAG = "KtorClient"

  operator fun invoke() = HttpClient(Android) {
    setLogging()
    setSerialization()
    setDefaultParams()
  }

  private fun HttpClientConfig<*>.setLogging() {
    install(Logging) {
      level = LogLevel.BODY
      logger = object : Logger {
        override fun log(message: String) {
          Log.i(TAG, message)
        }
      }
    }
  }

  @OptIn(ExperimentalSerializationApi::class)
  private fun HttpClientConfig<*>.setSerialization() {
    install(ContentNegotiation) {
      json(
        Json {
          prettyPrint = true
          explicitNulls = false
          coerceInputValues = true
          ignoreUnknownKeys = true
        }
      )
    }
  }

  private fun HttpClientConfig<*>.setDefaultParams() {
    install(DefaultRequest) {
      url(BASE_URL)
      contentType(ContentType.Application.Json)
      header(HttpHeaders.Authorization, "Bearer $API_KEY")
    }
  }

  private const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MTMxNGY5NTMyOWYwZmEyZWQyNWY3ZmYxNTY4NGNhZiIsIm5iZiI6MTcyMzczMjM1OC4xOTc2MTMsInN1YiI6IjYzMDA4NWM1ODM5ZDkzMDA3ZTRiZjhlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.IYPRikPWWLDKUqQCgZnihOpsraXq7R0jk-meAGlKZvQ"
}