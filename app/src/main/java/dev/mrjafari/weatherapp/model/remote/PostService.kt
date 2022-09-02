package dev.mrjafari.weatherapp.model.remote

import dev.mrjafari.weatherapp.model.remote.RequestModel.RequestModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.serializerOrNull

interface PostService {
    suspend fun getPosts(requestModel: RequestModel) :ResponseModel
    suspend fun createPost(requestModel: RequestModel) :RequestModel?

    companion object{
        fun create() : PostService{
            return PostServiceImpl(client = HttpClient(Android){
                install(Logging){
                    level = LogLevel.ALL
                }
                install(JsonFeature){
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                    })

                }
                install(HttpTimeout) {
                    requestTimeoutMillis = 15000L
                    connectTimeoutMillis = 15000L
                    socketTimeoutMillis = 15000L
                }
                // Apply to all requests
                defaultRequest {
                    // Parameter("api_key", "some_api_key")
                    // Content Type
                    if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            })
        }
    }
}