package data.network

import data.PostDtoItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.logging.Logger

class PostRepository() {

    suspend fun getPosts() : List<PostDtoItem>{
        val client = PostAppClient.host("https://jsonplaceholder.typicode.com/").build()
        val res = client.get("posts").body<List<PostDtoItem>>()
        return res
    }


}