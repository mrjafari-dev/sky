package dev.mrjafari.weatherapp.model.remote

import dev.mrjafari.weatherapp.model.remote.RequestModel.RequestModel
import dev.mrjafari.weatherapp.model.remote.ResponsModel.ResponseModel
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class PostServiceImpl(
    private val client: HttpClient
) : PostService {
    override suspend fun getPosts(requestModel: RequestModel): ResponseModel {
        return client.post(ApiRoutes.CityDetails) {

            FormDataContent(Parameters.build {
                parameter("key", "f24c70ecf7e740b0b51ebf6ff3097ec6")
                parameter("country", requestModel.country)
                parameter("city", requestModel.city)
            })
        }
    }

    override suspend fun createPost(requestModel: RequestModel): RequestModel? {
        return client.post<RequestModel> {
            url(ApiRoutes.CityDetails)
            contentType(ContentType.Application.Json)
            body = requestModel
        }
    }
}