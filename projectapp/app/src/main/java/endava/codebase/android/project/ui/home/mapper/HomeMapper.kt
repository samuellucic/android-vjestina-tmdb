package endava.codebase.android.project.ui.home.mapper

import endava.codebase.android.project.model.DateModel
import endava.codebase.android.project.ui.home.HomeViewState

interface HomeMapper {
    fun toHomeViewState(
        dates: List<DateModel>
    ): HomeViewState
}
