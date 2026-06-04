package com.fitquest.rpg.core.di;

import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.local.dao.AttributeDao;
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
public final class DatabaseModule_ProvideAttributeDaoFactory implements Factory<AttributeDao> {
  private final Provider<FitQuestDatabase> dbProvider;

  public DatabaseModule_ProvideAttributeDaoFactory(Provider<FitQuestDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public AttributeDao get() {
    return provideAttributeDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideAttributeDaoFactory create(
      Provider<FitQuestDatabase> dbProvider) {
    return new DatabaseModule_ProvideAttributeDaoFactory(dbProvider);
  }

  public static AttributeDao provideAttributeDao(FitQuestDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAttributeDao(db));
  }
}
