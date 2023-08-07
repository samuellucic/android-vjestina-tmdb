package endava.codebase.android.project.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = DbDate::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("dateId"),
            onDelete = ForeignKey.CASCADE,
        ),
    ]
)
data class DbNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "checked")
    val checked: Boolean,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(
        name = "dateId",
        index = true,
    )
    val dateId: Int,
)
