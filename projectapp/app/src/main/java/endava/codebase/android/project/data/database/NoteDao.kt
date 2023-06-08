package endava.codebase.android.project.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun notes(): Flow<List<DbNote>>

    @Query("SELECT * FROM notes WHERE checked = true")
    fun checkedNotes(): Flow<List<DbNote>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun findNote(id: Int): DbNote

    @Insert
    suspend fun insertNote(vararg notes: DbNote)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Int)

    @Query("UPDATE notes SET text = :text WHERE id = :id")
    suspend fun updateNoteText(id: Int, text: String)

    @Query("UPDATE notes SET checked = :checked WHERE id = :id")
    suspend fun updateNoteChecked(id: Int, checked: Boolean)
}
