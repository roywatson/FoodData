package com.delasystems.fooddata.data.di

import android.content.Context
import androidx.room.Room
import com.delasystems.fooddata.data.local.database.FoodDataDatabase
import com.delasystems.fooddata.data.local.preferences.FoodDataPreferencesSource
import com.delasystems.fooddata.data.remote.FoodDataApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodDataModule {

    @Singleton
    @Provides
    fun providesFoodDataPreferencesSource(@ApplicationContext appContext: Context): FoodDataPreferencesSource {
        return FoodDataPreferencesSource(appContext)
    }

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context): FoodDataDatabase {
        return Room.databaseBuilder(appContext, FoodDataDatabase::class.java, "history.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nal.usda.gov/")
            .client(client.build())
            .build()
    }

    @Provides
    fun provideRetrofitApi(retrofit: Retrofit): FoodDataApiService {
        return retrofit.create(FoodDataApiService::class.java)
    }

}