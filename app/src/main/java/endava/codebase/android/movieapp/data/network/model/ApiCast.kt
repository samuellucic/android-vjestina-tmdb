package endava.codebase.android.movieapp.data.network.model

import endava.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import endava.codebase.android.movieapp.model.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCast(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("character")
    val character: String,
    @SerialName("profile_path")
    val profilePath: String?,
) {
    fun toCast() = Actor(
        id = id,
        name = name,
        character = character,
        imageUrl = "$BASE_IMAGE_URL/$profilePath"
    )
}
