package com.example.vinod.doggeneratorapp.base.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinod.doggeneratorapp.base.ViewModelKey
import com.example.vinod.doggeneratorapp.base.ViewModelProviderFactory
import com.example.vinod.doggeneratorapp.base.schedulars.BaseSchedulerProvider
import com.example.vinod.doggeneratorapp.base.schedulars.SchedulerProvider
import com.example.vinod.doggeneratorapp.base.scope.ApplicationScope
import com.example.vinod.doggeneratorapp.viewmodel.DataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module abstract class BaseViewModelModule {

  @Binds @ApplicationScope
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

  @Binds @IntoMap @ViewModelKey(DataViewModel::class) abstract fun bindRegistrationViewModule(
    dataViewModel: DataViewModel
  ): ViewModel

  @Binds @ApplicationScope
  abstract fun provideSchedulerProvider(schedulerProvider: SchedulerProvider): BaseSchedulerProvider

}