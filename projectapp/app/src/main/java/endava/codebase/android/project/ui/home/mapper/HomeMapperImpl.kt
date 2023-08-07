package endava.codebase.android.project.ui.home.mapper

import endava.codebase.android.project.model.DateModel
import endava.codebase.android.project.ui.component.DateItemViewState
import endava.codebase.android.project.ui.home.HomeViewState

class HomeMapperImpl : HomeMapper {

    override fun toHomeViewState(dates: List<DateModel>): HomeViewState {
        return HomeViewState(
            dates = dates.map { dateModel ->
                DateItemViewState(
                    id = dateModel.id,
                    date = dateModel.date,
                )
            }
        )
    }
}
