package com.example.vinod.doggeneratorapp.base.module

import android.app.Application
import android.content.Context
import com.example.vinod.doggeneratorapp.BuildConfig
import com.example.vinod.doggeneratorapp.base.scope.ApplicationScope
import com.example.vinod.doggeneratorapp.base.utils.NETWORK_CACHE_SIZE
import com.example.vinod.doggeneratorapp.base.utils.NETWORK_TIMEOUT_IN_SECONDS
import com.example.vinod.doggeneratorapp.network.NewsApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

  @Provides @ApplicationScope fun provideRetrofit(
    retrofitBuilder: Retrofit.Builder, okHttpClientBuilder: OkHttpClient.Builder
  ): Retrofit {
    retrofitBuilder.apply {
      baseUrl("https://hn.algolia.com/v1/")
      client(okHttpClientBuilder.build())
    }
    return retrofitBuilder.build()
  }

  @Provides @ApplicationScope fun provideCache(context: Context): Cache {
    return Cache(context.cacheDir, NETWORK_CACHE_SIZE)
  }

  @Provides @ApplicationScope fun provideOkHttpClientBuilder(cache: Cache): OkHttpClient.Builder {
    return OkHttpClient.Builder().apply {
      readTimeout(NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
      connectTimeout(NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
      addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
      cache(cache)
    }
  }

  @Provides @ApplicationScope
  fun provideRetrofitBuilder(converterFactory: Converter.Factory): Retrofit.Builder {
    return Retrofit.Builder().addConverterFactory(converterFactory)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
  }

  @Provides @ApplicationScope fun provideGson(): Gson = GsonBuilder().create()

  @Provides @ApplicationScope fun provideConverter(gson: Gson): Converter.Factory =
    GsonConverterFactory.create(gson)

  @Provides @ApplicationScope fun provideContext(application: Application): Context =
    application.applicationContext

  @Provides @ApplicationScope fun provideService(retrofit: Retrofit): NewsApiService {
    return retrofit.create(NewsApiService::class.java)
  }

}