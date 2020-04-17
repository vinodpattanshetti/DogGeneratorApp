package com.example.vinod.doggeneratorapp.base.module

import com.example.vinod.doggeneratorapp.view.GenerateDogsActivity
import com.example.vinod.doggeneratorapp.base.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
  @ActivityScope @ContributesAndroidInjector abstract fun bindGenerateDogActivity(): GenerateDogsActivity
}