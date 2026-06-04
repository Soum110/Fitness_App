package com.fitquest.rpg.core.di;

import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.local.dao.RewardCardDao;
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
public final class DatabaseModule_ProvideRewardCardDaoFactory implements Factory<RewardCardDao> {
  private final Provider<FitQuestDatabase> dbProvider;

  public DatabaseModule_ProvideRewardCardDaoFactory(Provider<FitQuestDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public RewardCardDao get() {
    return provideRewardCardDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideRewardCardDaoFactory create(
      Provider<FitQuestDatabase> dbProvider) {
    return new DatabaseModule_ProvideRewardCardDaoFactory(dbProvider);
  }

  public static RewardCardDao provideRewardCardDao(FitQuestDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRewardCardDao(db));
  }
}
