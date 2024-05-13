package com.example.shoppingapplication.di

import com.example.shoppingapplication.data.api.ApiDetail
import com.example.shoppingapplication.data.api.ApiEndpoints
import com.example.shoppingapplication.data.api.RetrofitInstance
import com.example.shoppingapplication.data.repository.Repository
import com.example.shoppingapplication.data.repository.RepositoryImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class MainModule {


    // the return type will always be used as reference for HILT to provide instance
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun showingADummyNameSample(
        gson: Gson
    ) = GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

//    @Provides
//    fun provideRetrofit(
//        okHttpClient: OkHttpClient,
//        converterFactory: GsonConverterFactory
//    ): ApiEndpoints = Retrofit.Builder()
//        .baseUrl(ApiDetail.BASE_URL)
//        .client(
//            OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                })
//                .build()
//        )
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(ApiEndpoints::class.java)

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ApiDetail.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    fun apiInstance(retrofit: Retrofit): ApiEndpoints = retrofit.create(ApiEndpoints::class.java)

    @Provides
    fun providesRepository(apiEndpoints: ApiEndpoints): Repository {
        return RepositoryImpl(apiEndpoints)
    }
}