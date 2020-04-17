package com.example.vinod.doggeneratorapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinod.doggeneratorapp.base.scope.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

@ApplicationScope @Suppress("UNCHECKED_CAST") class ViewModelProviderFactory @Inject constructor(
  private val mapOfViewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    val creator = mapOfViewModels[modelClass]
        ?: mapOfViewModels.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
        ?: throw IllegalArgumentException("unknown model class $modelClass") as Throwable
    return try {
      creator.get() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

}