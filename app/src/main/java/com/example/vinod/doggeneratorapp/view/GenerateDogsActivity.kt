package com.example.vinod.doggeneratorapp.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.vinod.doggeneratorapp.R
import com.example.vinod.doggeneratorapp.base.AppController
import com.example.vinod.doggeneratorapp.base.BaseActivity
import com.example.vinod.doggeneratorapp.base.utils.setImageLoader
import com.example.vinod.doggeneratorapp.databinding.ActivityGenerateDogsBinding
import com.example.vinod.doggeneratorapp.model.DataResponse
import com.example.vinod.doggeneratorapp.viewmodel.DataViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class GenerateDogsActivity : BaseActivity() {

  private var mBinder: ActivityGenerateDogsBinding? = null

  @Inject lateinit var mViewModel: ViewModelProvider.Factory
  lateinit var mDataViewModel: DataViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)
    mBinder = DataBindingUtil.setContentView(this,
      R.layout.activity_generate_dogs
    )
    initialiseToolbar()
    mDataViewModel = ViewModelProviders.of(this, mViewModel).get(DataViewModel::class.java)
    mDataViewModel.mDataResponse.observe(this, Observer {
      initViews(it)
    })
    mBinder?.btGenerateDogs?.setOnClickListener {
      mDataViewModel.initDataApiCall()
    }
  }

  private fun initialiseToolbar() {
    mBinder?.run {
      initToolbar(tbToolbar, getString(R.string.text_generate_dogs_title), false)
      tbToolbar.setNavigationOnClickListener {
        onBackPressed()
      }
    }
  }

  private fun initViews(dataResponse: DataResponse) {
    AppController.getAppController().getCache().put(dataResponse.message, dataResponse.message)
    mBinder?.ivImageView?.let { setImageLoader(it, dataResponse.message.orEmpty()) }
  }
}
