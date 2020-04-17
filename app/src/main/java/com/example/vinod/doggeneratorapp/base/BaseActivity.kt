package com.example.vinod.doggeneratorapp.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.vinod.doggeneratorapp.R

@SuppressLint("Registered") open class BaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  protected fun initToolbar(toolbar: Toolbar?, title: String, setBackPressClick: Boolean) {
    toolbar?.title = title
    toolbar?.setNavigationIcon(R.drawable.arrow_left)
    if (setBackPressClick) {
      toolbar?.setNavigationOnClickListener {
        finish()
      }
    }
  }

}
