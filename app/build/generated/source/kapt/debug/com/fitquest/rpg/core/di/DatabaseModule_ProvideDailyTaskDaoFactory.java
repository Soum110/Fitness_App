package com.fitquest.rpg.core.di;

import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.local.dao.DailyTaskDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class DatabaseModule_ProvideDailyTaskDaoFactory implements Factory<DailyTaskDao> {
  private final Provider<FitQuestDatabase> dbProvider;

  public DatabaseModule_ProvideDailyTaskDaoFactory(Provider<FitQuestDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DailyTaskDao get() {
    return provideDailyTaskDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideDailyTaskDaoFactory create(
      Provider<FitQuestDatabase> dbProvider) {
    return new DatabaseModule_ProvideDailyTaskDaoFactory(dbProvider);
  }

  public static DailyTaskDao provideDailyTaskDao(FitQuestDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDailyTaskDao(db));
  }
}
