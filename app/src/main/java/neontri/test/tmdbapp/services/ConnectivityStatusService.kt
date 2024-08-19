package neontri.test.tmdbapp.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConnectivityObserver(private val context: Context) {

  private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

  private val _status = MutableStateFlow(connectivityManager.isCurrentlyConnected())
  val status: StateFlow<Boolean> get() = _status.asStateFlow()

  init {
    connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
      override fun onAvailable(network: Network) {
        _status.value = true
      }

      override fun onLost(network: Network) {
        _status.value = false
      }
    })
  }

  private fun ConnectivityManager.isCurrentlyConnected() =
    activeNetwork?.let { connectivityManager.getNetworkCapabilities(it)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } ?: false
}