package com.example.vinod.doggeneratorapp.network

import com.example.vinod.doggeneratorapp.model.DataResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

  @GET("https://dog.ceo/api/breeds/image/random") fun getApiResponse(): Single<DataResponse>

}