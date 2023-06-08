package endava.codebase.android.project.data.repository.note

import endava.codebase.android.project.data.database.DbNote
import endava.codebase.android.project.data.database.NoteDao
import endava.codebase.android.project.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
    private val bgDispatcher: CoroutineDispatcher,
) : NoteRepository {

    private val noteFlow = noteDao.notes().map {
        it.map { dbNote ->
            mapToNote(dbNote)
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1,
    )

    override fun notes(dateId: Int) = noteDao.notes().map {
        it.filter { dbNote ->
            dbNote.dateId == dateId
        }
            .map { dbNote ->
                mapToNote(dbNote)
            }
    }

    override suspend fun note(noteId: Int): Note = withContext(bgDispatcher) {
        return@withContext mapToNote(noteDao.findNote(noteId))
    }

    override suspend fun addNote(text: String, dateId: Int) = withContext(bgDispatcher) {
        noteDao.insertNote(
            DbNote(
                checked = false,
                text = text,
                dateId = dateId,
            )
        )
    }

    override suspend fun removeNote(noteId: Int) = withContext(bgDispatcher) {
        noteDao.deleteNote(noteId)
    }

    override suspend fun changeCheck(noteId: Int) = withContext(bgDispatcher) {
        noteDao.updateNoteChecked(
            id = noteId,
            checked = !noteFlow.first().first { it.id == noteId }.checked
        )
    }

    override suspend fun changeText(noteId: Int, text: String) = withContext(bgDispatcher) {
        noteDao.updateNoteText(
            id = noteId,
            text = text,
        )
    }

    private fun mapToNote(dbNote: DbNote) = Note(
        id = dbNote.id,
        checked = dbNote.checked,
        text = dbNote.text,
        dateId = dbNote.dateId,
    )
}
