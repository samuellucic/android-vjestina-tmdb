package endava.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class MovieCategoryLabelTextViewState {
    @Composable
    abstract fun getCategoryText(): String
}

class MovieCategoryLabelTextStringViewState(val text: String) : MovieCategoryLabelTextViewState() {
    @Composable
    override fun getCategoryText(): String {
        return text
    }
}

class MovieCategoryLabelTextReferenceViewState(@StringRes val textRes: Int) :
    MovieCategoryLabelTextViewState() {
    @Composable
    override fun getCategoryText(): String {
        return stringResource(id = textRes)
    }
}