package neontri.test.tmdbapp.ui.screens.dashboard.state

import neontri.test.tmdbapp.util.mvi.Effect

sealed class DashboardEffect: Effect {
  data class SnackbarNotification(val message: String): DashboardEffect()
}