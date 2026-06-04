package com.fitquest.rpg.core.di;

import android.content.Context;
import androidx.room.Room;
import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.local.dao.*;
import com.fitquest.rpg.core.data.remote.WgerApiService;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/fitquest/rpg/core/di/DatabaseModule;", "", "()V", "provideAttributeDao", "Lcom/fitquest/rpg/core/data/local/dao/AttributeDao;", "db", "Lcom/fitquest/rpg/core/data/local/FitQuestDatabase;", "provideDailyTaskDao", "Lcom/fitquest/rpg/core/data/local/dao/DailyTaskDao;", "provideDatabase", "context", "Landroid/content/Context;", "provideEconomyDao", "Lcom/fitquest/rpg/core/data/local/dao/EconomyDao;", "provideRewardCardDao", "Lcom/fitquest/rpg/core/data/local/dao/RewardCardDao;", "provideUserProfileDao", "Lcom/fitquest/rpg/core/data/local/dao/UserProfileDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.fitquest.rpg.core.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.local.FitQuestDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.local.dao.UserProfileDao provideUserProfileDao(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.FitQuestDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.local.dao.AttributeDao provideAttributeDao(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.FitQuestDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.local.dao.DailyTaskDao provideDailyTaskDao(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.FitQuestDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.local.dao.RewardCardDao provideRewardCardDao(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.FitQuestDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.local.dao.EconomyDao provideEconomyDao(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.FitQuestDatabase db) {
        return null;
    }
}