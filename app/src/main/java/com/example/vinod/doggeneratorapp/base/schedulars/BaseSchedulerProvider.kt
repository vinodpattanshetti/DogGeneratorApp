package com.example.vinod.doggeneratorapp.base.schedulars

import io.reactivex.Scheduler

interface BaseSchedulerProvider {
  fun computation(): Scheduler
  fun io(): Scheduler
  fun ui(): Scheduler
}