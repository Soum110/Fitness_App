package com.fitquest.rpg.core.data.repository;

import android.content.Context;
import com.fitquest.rpg.core.data.local.dao.AttributeDao;
import com.fitquest.rpg.core.data.local.dao.EconomyDao;
import com.fitquest.rpg.core.data.local.dao.UserProfileDao;
import com.fitquest.rpg.core.data.remote.FirestoreRepository;
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
public final class UserRepository_Factory implements Factory<UserRepository> {
  private final Provider<UserProfileDao> profileDaoProvider;

  private final Provider<AttributeDao> attributeDaoProvider;

  private final Provider<EconomyDao> economyDaoProvider;

  private final Provider<FirestoreRepository> firestoreRepoProvider;

  private final Provider<Context> contextProvider;

  public UserRepository_Factory(Provider<UserProfileDao> profileDaoProvider,
      Provider<AttributeDao> attributeDaoProvider, Provider<EconomyDao> economyDaoProvider,
      Provider<FirestoreRepository> firestoreRepoProvider, Provider<Context> contextProvider) {
    this.profileDaoProvider = profileDaoProvider;
    this.attributeDaoProvider = attributeDaoProvider;
    this.economyDaoProvider = economyDaoProvider;
    this.firestoreRepoProvider = firestoreRepoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public UserRepository get() {
    return newInstance(profileDaoProvider.get(), attributeDaoProvider.get(), economyDaoProvider.get(), firestoreRepoProvider.get(), contextProvider.get());
  }

  public static UserRepository_Factory create(Provider<UserProfileDao> profileDaoProvider,
      Provider<AttributeDao> attributeDaoProvider, Provider<EconomyDao> economyDaoProvider,
      Provider<FirestoreRepository> firestoreRepoProvider, Provider<Context> contextProvider) {
    return new UserRepository_Factory(profileDaoProvider, attributeDaoProvider, economyDaoProvider, firestoreRepoProvider, contextProvider);
  }

  public static UserRepository newInstance(UserProfileDao profileDao, AttributeDao attributeDao,
      EconomyDao economyDao, FirestoreRepository firestoreRepo, Context context) {
    return new UserRepository(profileDao, attributeDao, economyDao, firestoreRepo, context);
  }
}
