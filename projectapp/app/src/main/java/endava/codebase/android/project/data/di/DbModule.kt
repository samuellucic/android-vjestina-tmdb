package endava.codebase.android.project.data.di

import androidx.room.Room
import endava.codebase.android.project.data.database.ProjectAppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val APP_DATABASE_NAME = "app_database.db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = ProjectAppDatabase::class.java,
            name = APP_DATABASE_NAME,
        ).build()
    }
}
