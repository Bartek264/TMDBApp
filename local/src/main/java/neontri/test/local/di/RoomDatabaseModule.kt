package neontri.test.local.di

import androidx.room.Room
import neontri.test.local.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDatabaseModule = module {

  single {
    Room.databaseBuilder(androidContext(), MovieDatabase::class.java, DATABASE_NAME)
      .fallbackToDestructiveMigration()
      .build()
  }

  single { get<MovieDatabase>().favoriteMoviesDao() }

}

private const val DATABASE_NAME = "movie_database"