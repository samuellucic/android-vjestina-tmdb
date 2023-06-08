package endava.codebase.android.project.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.project.data.repository.date.DateRepository
import endava.codebase.android.project.ui.home.mapper.HomeMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    val dateRepository: DateRepository,
    val homeMapper: HomeMapper,
) : ViewModel() {

    val homeViewState: StateFlow<HomeViewState> = dateRepository.dates().map {
        homeMapper.toHomeViewState(it)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = HomeViewState(listOf()),
        )

    fun removeDate(dateId: Int) {
        viewModelScope.launch {
            dateRepository.removeDate(dateId)
        }
    }
}
