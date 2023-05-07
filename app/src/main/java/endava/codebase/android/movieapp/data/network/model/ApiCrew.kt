package endava.codebase.android.movieapp.data.network.model

import endava.codebase.android.movieapp.model.Crewman
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCrew(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("job")
    val job: String
) {
    fun toCrew() = Crewman(
        id = id,
        name = name,
        job = job
    )
}
