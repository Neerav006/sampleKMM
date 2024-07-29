package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDtoItem(
    @SerialName("body")
    val body: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("userId")
    val userId: Int?
)