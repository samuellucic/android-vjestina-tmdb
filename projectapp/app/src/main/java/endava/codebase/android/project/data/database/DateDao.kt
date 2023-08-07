package endava.codebase.android.project.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DateDao {

    @Query("SELECT * FROM dates ORDER BY CAST(date AS DATE)")
    fun dates(): Flow<List<DbDate>>

    @Query("SELECT * FROM dates where date = :date")
    fun findByDate(date: String): DbDate

    @Insert
    suspend fun insertDate(vararg dates: DbDate)

    @Query("DELETE FROM dates WHERE id = :id")
    suspend fun deleteDate(id: Int)
}
