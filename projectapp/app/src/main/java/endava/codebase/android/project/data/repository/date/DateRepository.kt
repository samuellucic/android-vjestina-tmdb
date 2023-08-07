package endava.codebase.android.project.data.repository.date

import endava.codebase.android.project.model.DateModel
import kotlinx.coroutines.flow.Flow

interface DateRepository {

    fun dates(): Flow<List<DateModel>>

    suspend fun addDate(date: String)

    suspend fun removeDate(dateId: Int)
}
