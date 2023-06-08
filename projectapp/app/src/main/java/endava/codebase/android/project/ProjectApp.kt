package endava.codebase.android.project

import android.app.Application
import android.util.Log
import endava.codebase.android.project.data.di.dataModule
import endava.codebase.android.project.data.di.databaseModule
import endava.codebase.android.project.ui.calendar.di.calendarModule
import endava.codebase.android.project.ui.home.di.homeModule
import endava.codebase.android.project.ui.noteedit.di.noteEditModule
import endava.codebase.android.project.ui.noteinput.di.noteInputModule
import endava.codebase.android.project.ui.notes.di.notesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ProjectApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProjectApp)
            modules(
                homeModule,
                dataModule,
                notesModule,
                noteInputModule,
                noteEditModule,
                calendarModule,
                databaseModule,
            )
        }
        Log.d("ProjectApp", "App started")
    }
}
