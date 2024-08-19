package neontri.test.tmdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import neontri.test.api.di.apiModule
import neontri.test.local.di.roomDatabaseModule
import neontri.test.local.repository.di.roomRepositoryModule
import neontri.test.tmdbapp.domain.di.domainModule
import neontri.test.tmdbapp.navigation.NavigationController
import neontri.test.tmdbapp.repository.di.repositoryModule
import neontri.test.tmdbapp.services.di.serviceModule
import neontri.test.tmdbapp.ui.theme.TMDBAppTheme
import neontri.test.tmdbapp.viewmodel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    startKoin {
      androidContext(this@MainActivity)
      modules(apiModule, repositoryModule, viewModelModule, domainModule, roomRepositoryModule, roomDatabaseModule, serviceModule)
    }

    setContent {
      TMDBAppTheme {
        NavigationController()
      }
    }
  }
}