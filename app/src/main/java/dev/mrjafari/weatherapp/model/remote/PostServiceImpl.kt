package dev.mrjafari.weatherapp.model.remote

import dev.mrjafari.weatherapp.model.remote.RequestModel.RequestModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(
    private val client : HttpClient
) : PostService {
    override suspend fun getPosts(): ResponseModel {
            return client.post {
                url(ApiRoutes.CityDetails)
            }
    }

    override suspend fun createPost(requestModel: RequestModel): RequestModel? {
        return client.post<RequestModel> {
            url(ApiRoutes.CityDetails)
            contentType(ContentType.Application.Json)
            body  = requestModel
        }
    }
}