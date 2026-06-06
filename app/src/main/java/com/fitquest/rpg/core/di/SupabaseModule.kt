package com.fitquest.rpg.core.di

import android.content.Context
import com.fitquest.rpg.core.data.remote.SupabaseApiService
import com.fitquest.rpg.core.data.remote.SupabaseAuth
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
object SupabaseModule {

    private const val SUPABASE_URL = "https://fgxbcowjetgtxqfmpaco.supabase.co"
    private const val SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZneGJjb3dqZXRndHhxZm1wYWNvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODA3NTIzNzMsImV4cCI6MjA5NjMyODM3M30.2f2vNeqApQrCI6y1SvkXEG6DMqWGmf5SnfmeL9PAU1k"

    @Provides
    @Singleton
    fun provideSupabaseApiService(): SupabaseApiService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("apikey", SUPABASE_ANON_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()

        val url = if (SUPABASE_URL.endsWith("/")) SUPABASE_URL else "$SUPABASE_URL/"
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupabaseApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSupabaseAuth(
        @ApplicationContext context: Context,
        apiService: SupabaseApiService
    ): SupabaseAuth {
        return SupabaseAuth(context, apiService, SUPABASE_ANON_KEY)
    }
}
