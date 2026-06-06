package com.fitquest.rpg.core.di;

import android.content.Context;
import com.fitquest.rpg.core.data.remote.SupabaseApiService;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\u001a\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/fitquest/rpg/core/di/SupabaseModule;", "", "()V", "SUPABASE_ANON_KEY", "", "SUPABASE_URL", "provideSupabaseApiService", "Lcom/fitquest/rpg/core/data/remote/SupabaseApiService;", "provideSupabaseAuth", "Lcom/fitquest/rpg/core/data/remote/SupabaseAuth;", "context", "Landroid/content/Context;", "apiService", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class SupabaseModule {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SUPABASE_URL = "https://fgxbcowjetgtxqfmpaco.supabase.co";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZneGJjb3dqZXRndHhxZm1wYWNvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODA3NTIzNzMsImV4cCI6MjA5NjMyODM3M30.2f2vNeqApQrCI6y1SvkXEG6DMqWGmf5SnfmeL9PAU1k";
    @org.jetbrains.annotations.NotNull()
    public static final com.fitquest.rpg.core.di.SupabaseModule INSTANCE = null;
    
    private SupabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.remote.SupabaseApiService provideSupabaseApiService() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.remote.SupabaseAuth provideSupabaseAuth(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseApiService apiService) {
        return null;
    }
}