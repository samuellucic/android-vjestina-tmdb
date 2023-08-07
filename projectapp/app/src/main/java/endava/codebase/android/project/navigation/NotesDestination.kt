package endava.codebase.android.project.navigation

const val DATE_ID_KEY = "dateId"
const val NOTE_ID_KEY = "noteId"

const val NOTES_ROUTE = "Notes"
const val NOTE_INPUT_ROUTE = "NoteInput"
const val NOTE_EDIT_ROUTE = "NoteEdit"

const val NOTES_ROUTE_WITH_PARAMS = "$NOTES_ROUTE/{$DATE_ID_KEY}"
const val NOTE_INPUT_ROUTE_WITH_PARAMS = "$NOTE_INPUT_ROUTE/{$DATE_ID_KEY}"
const val NOTE_EDIT_ROUTE_WITH_PARAMS = "$NOTE_EDIT_ROUTE/{$NOTE_ID_KEY}"

object NoteEditDestination : ProjectAppDestination(NOTE_EDIT_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(noteId: Int): String = "$NOTE_EDIT_ROUTE/$noteId"
}

object NoteInputDestination : ProjectAppDestination(NOTE_INPUT_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(dateId: Int): String = "$NOTE_INPUT_ROUTE/$dateId"
}

object NotesDestination : ProjectAppDestination(NOTES_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(dateId: Int): String = "$NOTES_ROUTE/$dateId"
}
