package com.example.vinod.doggeneratorapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.LruCache
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.vinod.doggeneratorapp.R
import com.example.vinod.doggeneratorapp.base.BaseActivity
import com.example.vinod.doggeneratorapp.base.utils.CacheLRU
import com.example.vinod.doggeneratorapp.base.utils.setImageLoader
import com.example.vinod.doggeneratorapp.databinding.ActivityGenerateDogsBinding
import com.example.vinod.doggeneratorapp.model.DataResponse
import com.example.vinod.doggeneratorapp.view.MainActivity.Companion.IMAGE_URL_LIST_KEY
import com.example.vinod.doggeneratorapp.view.MainActivity.Companion.VALUE_TWENTY
import com.example.vinod.doggeneratorapp.viewmodel.DataViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class GenerateDogsActivity : BaseActivity() {

  private var mBinder: ActivityGenerateDogsBinding? = null

  @Inject lateinit var mViewModel: ViewModelProvider.Factory
  lateinit var mDataViewModel: DataViewModel
  private var cache = CacheLRU<String, String>(VALUE_TWENTY)

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
    cache.put(dataResponse.message, dataResponse.message)
    mBinder?.ivImageView?.let { setImageLoader(it, dataResponse.message.orEmpty()) }
  }

  override fun onBackPressed() {
    val intent = Intent()
    intent.putExtra(IMAGE_URL_LIST_KEY, cache.imageUrlList)
    setResult(RESULT_OK, intent)
    super.onBackPressed()
  }

}
