package com.example.vinod.doggeneratorapp.base.schedulars

import com.example.vinod.doggeneratorapp.base.scope.ApplicationScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScope
class SchedulerProvider @Inject constructor() : BaseSchedulerProvider {
  override fun computation() = Schedulers.computation()
  override fun ui() = AndroidSchedulers.mainThread()
  override fun io() = Schedulers.io()
}