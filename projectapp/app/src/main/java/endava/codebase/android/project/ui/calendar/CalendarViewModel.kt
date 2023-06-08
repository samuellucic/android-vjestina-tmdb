package endava.codebase.android.project.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.project.data.repository.date.DateRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class CalendarViewModel(
    val dateRepository: DateRepository,
) : ViewModel() {

    private val formatter = SimpleDateFormat("dd.MM.yyyy")

    fun addDate(date: Long) {
        viewModelScope.launch {
            dateRepository.addDate(formatter.format(Date(date)))
        }
    }
}
