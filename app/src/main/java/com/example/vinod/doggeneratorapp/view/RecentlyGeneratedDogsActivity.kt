package com.example.vinod.doggeneratorapp.view

import android.content.Intent
import androidx.databinding.DataBindingUtil

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vinod.doggeneratorapp.R
import com.example.vinod.doggeneratorapp.adapter.ImageRecyclerViewAdapter
import com.example.vinod.doggeneratorapp.base.BaseActivity

import com.example.vinod.doggeneratorapp.databinding.ActivityRecentlyGeneratedDogsBinding
import com.example.vinod.doggeneratorapp.view.MainActivity.Companion.IMAGE_URL_LIST_KEY

class RecentlyGeneratedDogsActivity : BaseActivity() {

  private var mBinder: ActivityRecentlyGeneratedDogsBinding? = null

  private var mImageUrlList = arrayListOf<String>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recently_generated_dogs)
    mImageUrlList = intent.getStringArrayListExtra(IMAGE_URL_LIST_KEY) as ArrayList<String>
    mBinder = DataBindingUtil.setContentView(
      this, R.layout.activity_recently_generated_dogs
    )
    initViews()
  }

  private fun initViews() {
    mBinder?.run {
      initToolbar(tbToolbar, getString(R.string.text_recently_generated_dogs), true)
    }
    val adapter = ImageRecyclerViewAdapter(mImageUrlList)
    mBinder?.rvList?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    mBinder?.rvList?.adapter = adapter
    mBinder?.btClearDogs?.setOnClickListener {
      adapter.clearImageUrlList()
    }
  }

  override fun onBackPressed() {
    val intent = Intent()
    setResult(RESULT_OK, intent)
    super.onBackPressed()
  }
}