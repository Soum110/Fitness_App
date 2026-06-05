package com.fitquest.rpg.core.data.repository;

import android.content.Context;
import com.fitquest.rpg.core.data.local.dao.RewardCardDao;
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
public final class RewardCardRepository_Factory implements Factory<RewardCardRepository> {
  private final Provider<RewardCardDao> cardDaoProvider;

  private final Provider<FirestoreRepository> firestoreRepoProvider;

  private final Provider<Context> contextProvider;

  public RewardCardRepository_Factory(Provider<RewardCardDao> cardDaoProvider,
      Provider<FirestoreRepository> firestoreRepoProvider, Provider<Context> contextProvider) {
    this.cardDaoProvider = cardDaoProvider;
    this.firestoreRepoProvider = firestoreRepoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public RewardCardRepository get() {
    return newInstance(cardDaoProvider.get(), firestoreRepoProvider.get(), contextProvider.get());
  }

  public static RewardCardRepository_Factory create(Provider<RewardCardDao> cardDaoProvider,
      Provider<FirestoreRepository> firestoreRepoProvider, Provider<Context> contextProvider) {
    return new RewardCardRepository_Factory(cardDaoProvider, firestoreRepoProvider, contextProvider);
  }

  public static RewardCardRepository newInstance(RewardCardDao cardDao,
      FirestoreRepository firestoreRepo, Context context) {
    return new RewardCardRepository(cardDao, firestoreRepo, context);
  }
}
