package com.example.vinod.doggeneratorapp.repository

import com.example.vinod.doggeneratorapp.network.NewsApiService
import javax.inject.Inject

class DataRepository @Inject constructor(private val newsApiService: NewsApiService) {
  fun callDataResponseApi() = newsApiService.getApiResponse()
}