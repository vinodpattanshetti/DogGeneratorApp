package com.example.vinod.doggeneratorapp.base

import android.app.Activity
import android.app.Application
import com.example.vinod.doggeneratorapp.base.component.ApplicationComponent
import com.example.vinod.doggeneratorapp.base.component.DaggerApplicationComponent
import com.example.vinod.doggeneratorapp.base.utils.CacheLRU
import com.example.vinod.doggeneratorapp.view.MainActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AppController: Application(), HasActivityInjector {

  @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  private lateinit var mApplicationComponent: ApplicationComponent
  private var cache = CacheLRU<String, String>(MainActivity.VALUE_TWENTY)

  init {
    instance = this
  }

  companion object {
    private var instance: AppController? = null

    fun getAppController() : AppController {
      return instance as AppController
    }
  }

  override fun onCreate() {
    super.onCreate()
    mApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
    mApplicationComponent.inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return activityDispatchingAndroidInjector
  }

  fun getCache() : CacheLRU<String, String> {
    return cache
  }

}