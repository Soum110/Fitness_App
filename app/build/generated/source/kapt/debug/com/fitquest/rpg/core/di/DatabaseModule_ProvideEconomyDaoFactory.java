package com.fitquest.rpg.core.di;

import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.local.dao.EconomyDao;
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
public final class DatabaseModule_ProvideEconomyDaoFactory implements Factory<EconomyDao> {
  private final Provider<FitQuestDatabase> dbProvider;

  public DatabaseModule_ProvideEconomyDaoFactory(Provider<FitQuestDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public EconomyDao get() {
    return provideEconomyDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideEconomyDaoFactory create(
      Provider<FitQuestDatabase> dbProvider) {
    return new DatabaseModule_ProvideEconomyDaoFactory(dbProvider);
  }

  public static EconomyDao provideEconomyDao(FitQuestDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideEconomyDao(db));
  }
}
