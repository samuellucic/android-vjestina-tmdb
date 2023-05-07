package endava.codebase.android.movieapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun favorites(): Flow<List<DbFavoriteMovie>>

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    fun findFavorite(id: Int): Flow<List<DbFavoriteMovie>>

    @Insert
    fun insertFavorites(vararg favoriteMovies: DbFavoriteMovie)

    @Delete
    fun deleteFavorites(vararg favoriteMovies: DbFavoriteMovie)
}
