package com.example.vinod.doggeneratorapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vinod.doggeneratorapp.R
import com.example.vinod.doggeneratorapp.adapter.ImageRecyclerViewAdapter
import com.example.vinod.doggeneratorapp.adapter.SpacesItemDecoration
import com.example.vinod.doggeneratorapp.base.AppController
import com.example.vinod.doggeneratorapp.base.BaseActivity
import com.example.vinod.doggeneratorapp.databinding.ActivityRecentlyGeneratedDogsBinding
import com.example.vinod.doggeneratorapp.view.MainActivity.Companion.IMAGE_URL_LIST_KEY

class RecentlyGeneratedDogsActivity : BaseActivity() {

  private var mBinder: ActivityRecentlyGeneratedDogsBinding? = null

  private var mImageUrlList = arrayListOf<String>()

  private var sharedPreferences: SharedPreferences? = null

  companion object {
    const val MY_SHAREDPREF_KEY = "mypref"
    const val IMAGE_LIST_IN_SP_KEY = "IMAGE_LIST_IN_SP"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recently_generated_dogs)
    sharedPreferences = getSharedPreferences(MY_SHAREDPREF_KEY,
      Context.MODE_PRIVATE)
    mImageUrlList = intent.getStringArrayListExtra(IMAGE_URL_LIST_KEY) as ArrayList<String>
    if (mImageUrlList.isNullOrEmpty()) {
      val set = LinkedHashSet<String>()
      sharedPreferences?.getStringSet(IMAGE_LIST_IN_SP_KEY, set)?.let {
        mImageUrlList = ArrayList(it)
        AppController.getAppController().getCache().putAllValues(mImageUrlList)
      }
    }
    mBinder = DataBindingUtil.setContentView(
      this, R.layout.activity_recently_generated_dogs
    )
    initViews()
  }

  private fun initViews() {
    mBinder?.run {
      initToolbar(tbToolbar, getString(R.string.text_recently_generated_dogs), true)
    }
    saveImageUrlsToSharedPreference(HashSet<String>(AppController.getAppController().getCache().values()))
    val adapter = ImageRecyclerViewAdapter(mImageUrlList)
    mBinder?.run {
      mBinder?.rvList?.layoutManager = LinearLayoutManager(
        this@RecentlyGeneratedDogsActivity, LinearLayoutManager.HORIZONTAL, false
      )
      mBinder?.rvList?.adapter = adapter
      mBinder?.rvList?.addItemDecoration(SpacesItemDecoration(8))
      btClearDogs?.setOnClickListener {
        adapter.clearImageUrlList()
        saveImageUrlsToSharedPreference(HashSet())
        val intent = Intent()
        setResult(RESULT_OK, intent)
      }
    }
  }

  private fun saveImageUrlsToSharedPreference(set: Set<String>) {
    val editor = sharedPreferences?.edit()
    editor?.putStringSet(IMAGE_LIST_IN_SP_KEY, set)
    editor?.apply()
  }

}
