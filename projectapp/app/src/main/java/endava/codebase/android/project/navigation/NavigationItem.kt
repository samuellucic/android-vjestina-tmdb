package endava.codebase.android.project.navigation

const val HOME_ROUTE = "Home"
const val CALENDAR_ROUTE = "Calendar"

sealed class NavigationItem(
    override val route: String,
) : ProjectAppDestination(route) {
    object HomeDestination : NavigationItem(
        route = HOME_ROUTE
    )

    object CalendarDestination : NavigationItem(
        route = CALENDAR_ROUTE
    )
}
