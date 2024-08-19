package neontri.test.tmdbapp.screens.dashboard.state

import neontri.test.tmdbapp.util.mvi.Effect

sealed class DashboardEffect: Effect {
  data class SnackbarNotification(val message: String): DashboardEffect()
}