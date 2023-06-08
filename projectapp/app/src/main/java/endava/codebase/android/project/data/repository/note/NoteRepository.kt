package endava.codebase.android.project.data.repository.note

import endava.codebase.android.project.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun notes(noteId: Int): Flow<List<Note>>

    suspend fun note(noteId: Int): Note

    suspend fun addNote(text: String, dateId: Int)

    suspend fun removeNote(noteId: Int)

    suspend fun changeCheck(noteId: Int)

    suspend fun changeText(noteId: Int, text: String)
}
