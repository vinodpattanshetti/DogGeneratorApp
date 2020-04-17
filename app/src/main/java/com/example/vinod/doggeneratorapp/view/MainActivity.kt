package com.example.vinod.doggeneratorapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.vinod.doggeneratorapp.R
import com.example.vinod.doggeneratorapp.base.BaseActivity
import com.example.vinod.doggeneratorapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

  private var mBinder: ActivityMainBinding? = null
  private var mImageUrlList = arrayListOf<String>()

  companion object {
    const val IMAGE_URL_LIST_KEY = "IMAGE_URL_LIST"
    const val VALUE_HUNDRED = 100
    const val VALUE_TWO_HUNDRED = 200
    const val VALUE_TWENTY = 20
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mBinder = DataBindingUtil.setContentView(
      this, R.layout.activity_main
    )
    initViews()
  }

  private fun initViews() {
    initToolbar(mBinder?.tbToolbar, getString(R.string.text_dog_generator_app), true)
    mBinder?.run {
      btGenerateDogs.setOnClickListener {
        val intent = Intent(this@MainActivity, GenerateDogsActivity::class.java)
        startActivityForResult(intent, VALUE_HUNDRED)
      }
      btRecentlyGeneratedDogs.setOnClickListener {
        val intent = Intent(this@MainActivity, RecentlyGeneratedDogsActivity::class.java)
        intent.putExtra(IMAGE_URL_LIST_KEY, mImageUrlList)
        startActivityForResult(intent, VALUE_TWO_HUNDRED)
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK && requestCode == VALUE_HUNDRED) {
      mImageUrlList = data?.getStringArrayListExtra(IMAGE_URL_LIST_KEY) as ArrayList<String>
    } else if(resultCode == Activity.RESULT_OK && requestCode == VALUE_TWO_HUNDRED) {
      mImageUrlList.clear()
    }
  }

}
