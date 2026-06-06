package com.fitquest.rpg.core.data.repository;

import android.content.Context;
import com.fitquest.rpg.core.data.local.dao.RewardCardDao;
import com.fitquest.rpg.core.data.remote.SupabaseRepository;
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

  private final Provider<SupabaseRepository> supabaseRepoProvider;

  private final Provider<Context> contextProvider;

  public RewardCardRepository_Factory(Provider<RewardCardDao> cardDaoProvider,
      Provider<SupabaseRepository> supabaseRepoProvider, Provider<Context> contextProvider) {
    this.cardDaoProvider = cardDaoProvider;
    this.supabaseRepoProvider = supabaseRepoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public RewardCardRepository get() {
    return newInstance(cardDaoProvider.get(), supabaseRepoProvider.get(), contextProvider.get());
  }

  public static RewardCardRepository_Factory create(Provider<RewardCardDao> cardDaoProvider,
      Provider<SupabaseRepository> supabaseRepoProvider, Provider<Context> contextProvider) {
    return new RewardCardRepository_Factory(cardDaoProvider, supabaseRepoProvider, contextProvider);
  }

  public static RewardCardRepository newInstance(RewardCardDao cardDao,
      SupabaseRepository supabaseRepo, Context context) {
    return new RewardCardRepository(cardDao, supabaseRepo, context);
  }
}
