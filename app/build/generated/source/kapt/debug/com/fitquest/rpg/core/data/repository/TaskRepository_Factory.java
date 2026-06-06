package com.fitquest.rpg.core.data.repository;

import android.content.Context;
import com.fitquest.rpg.core.data.local.dao.AttributeDao;
import com.fitquest.rpg.core.data.local.dao.DailyTaskDao;
import com.fitquest.rpg.core.data.remote.SupabaseRepository;
import com.fitquest.rpg.core.data.remote.WgerApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class TaskRepository_Factory implements Factory<TaskRepository> {
  private final Provider<DailyTaskDao> taskDaoProvider;

  private final Provider<AttributeDao> attributeDaoProvider;

  private final Provider<SupabaseRepository> supabaseRepoProvider;

  private final Provider<WgerApiService> wgerApiProvider;

  private final Provider<Context> contextProvider;

  public TaskRepository_Factory(Provider<DailyTaskDao> taskDaoProvider,
      Provider<AttributeDao> attributeDaoProvider,
      Provider<SupabaseRepository> supabaseRepoProvider, Provider<WgerApiService> wgerApiProvider,
      Provider<Context> contextProvider) {
    this.taskDaoProvider = taskDaoProvider;
    this.attributeDaoProvider = attributeDaoProvider;
    this.supabaseRepoProvider = supabaseRepoProvider;
    this.wgerApiProvider = wgerApiProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public TaskRepository get() {
    return newInstance(taskDaoProvider.get(), attributeDaoProvider.get(), supabaseRepoProvider.get(), wgerApiProvider.get(), contextProvider.get());
  }

  public static TaskRepository_Factory create(Provider<DailyTaskDao> taskDaoProvider,
      Provider<AttributeDao> attributeDaoProvider,
      Provider<SupabaseRepository> supabaseRepoProvider, Provider<WgerApiService> wgerApiProvider,
      Provider<Context> contextProvider) {
    return new TaskRepository_Factory(taskDaoProvider, attributeDaoProvider, supabaseRepoProvider, wgerApiProvider, contextProvider);
  }

  public static TaskRepository newInstance(DailyTaskDao taskDao, AttributeDao attributeDao,
      SupabaseRepository supabaseRepo, WgerApiService wgerApi, Context context) {
    return new TaskRepository(taskDao, attributeDao, supabaseRepo, wgerApi, context);
  }
}
