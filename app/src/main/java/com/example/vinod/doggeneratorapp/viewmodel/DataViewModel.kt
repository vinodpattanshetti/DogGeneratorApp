package com.example.vinod.doggeneratorapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinod.doggeneratorapp.base.schedulars.BaseSchedulerProvider
import com.example.vinod.doggeneratorapp.model.DataResponse
import com.example.vinod.doggeneratorapp.repository.DataRepository
import javax.inject.Inject

class DataViewModel @Inject constructor(
  private val dataRepository: DataRepository,
  private val scheduler: BaseSchedulerProvider
) : ViewModel() {

  var mDataResponse: MutableLiveData<DataResponse> = MutableLiveData()

  @SuppressLint("CheckResult") fun initDataApiCall() {
    dataRepository.callDataResponseApi().subscribeOn(scheduler.io()).observeOn(scheduler.ui())
      .doOnError { _ ->
        getNewsErrorResponse()
      }.subscribe({ getNewsResponse(it) }, {})
  }

  private fun getNewsErrorResponse() {
    val mNews = DataResponse()
    mNews.isError = true
    mDataResponse.value = mNews
  }

  private fun getNewsResponse(dataResponse: DataResponse) {
    dataResponse.isApiSuccessful = true
    mDataResponse.value = dataResponse
  }
}
