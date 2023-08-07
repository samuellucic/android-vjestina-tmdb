package endava.codebase.android.project.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import endava.codebase.android.project.data.converters.Converters

@TypeConverters(Converters::class)
@Database(
    entities = [
        DbNote::class,
        DbDate::class
    ],
    version = 1
)
abstract class ProjectAppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun dateDao(): DateDao
}
