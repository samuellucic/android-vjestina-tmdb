package endava.codebase.android.project.data.repository.date

import endava.codebase.android.project.data.database.DateDao
import endava.codebase.android.project.data.database.DbDate
import endava.codebase.android.project.model.DateModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class DateRepositoryImpl(
    private val dateDao: DateDao,
    private val bgDispatcher: CoroutineDispatcher,
) : DateRepository {

    private val dateFlow = dateDao.dates().map {
        it.map { dbDate ->
            DateModel(
                id = dbDate.id,
                date = dbDate.date,
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1,
    )

    override fun dates(): Flow<List<DateModel>> = dateFlow

    override suspend fun addDate(date: String) = withContext(bgDispatcher) {
        if (dateDao.findByDate(date) == null) {
            dateDao.insertDate(DbDate(date = date))
        }
    }

    override suspend fun removeDate(dateId: Int) = withContext(bgDispatcher) {
        dateDao.deleteDate(dateId)
    }
}
