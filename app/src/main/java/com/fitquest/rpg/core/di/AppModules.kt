package com.fitquest.rpg.core.di

import android.content.Context
import androidx.room.Room
import com.fitquest.rpg.core.data.local.FitQuestDatabase
import com.fitquest.rpg.core.data.local.dao.*
import com.fitquest.rpg.core.data.remote.WgerApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FitQuestDatabase =
        Room.databaseBuilder(context, FitQuestDatabase::class.java, "fitquest.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideUserProfileDao(db: FitQuestDatabase): UserProfileDao = db.userProfileDao()
    @Provides fun provideAttributeDao(db: FitQuestDatabase): AttributeDao = db.attributeDao()
    @Provides fun provideDailyTaskDao(db: FitQuestDatabase): DailyTaskDao = db.dailyTaskDao()
    @Provides fun provideRewardCardDao(db: FitQuestDatabase): RewardCardDao = db.rewardCardDao()
    @Provides fun provideEconomyDao(db: FitQuestDatabase): EconomyDao = db.economyDao()
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides @Singleton
    fun provideWgerApiService(client: OkHttpClient): WgerApiService =
        Retrofit.Builder()
            .baseUrl("https://wger.de/api/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WgerApiService::class.java)
}
