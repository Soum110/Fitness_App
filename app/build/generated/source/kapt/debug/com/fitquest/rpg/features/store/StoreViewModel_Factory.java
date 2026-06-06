package com.fitquest.rpg.features.store;

import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import com.fitquest.rpg.core.data.repository.RewardCardRepository;
import com.fitquest.rpg.core.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class StoreViewModel_Factory implements Factory<StoreViewModel> {
  private final Provider<RewardCardRepository> cardRepoProvider;

  private final Provider<UserRepository> userRepoProvider;

  private final Provider<SupabaseAuth> authProvider;

  public StoreViewModel_Factory(Provider<RewardCardRepository> cardRepoProvider,
      Provider<UserRepository> userRepoProvider, Provider<SupabaseAuth> authProvider) {
    this.cardRepoProvider = cardRepoProvider;
    this.userRepoProvider = userRepoProvider;
    this.authProvider = authProvider;
  }

  @Override
  public StoreViewModel get() {
    return newInstance(cardRepoProvider.get(), userRepoProvider.get(), authProvider.get());
  }

  public static StoreViewModel_Factory create(Provider<RewardCardRepository> cardRepoProvider,
      Provider<UserRepository> userRepoProvider, Provider<SupabaseAuth> authProvider) {
    return new StoreViewModel_Factory(cardRepoProvider, userRepoProvider, authProvider);
  }

  public static StoreViewModel newInstance(RewardCardRepository cardRepo, UserRepository userRepo,
      SupabaseAuth auth) {
    return new StoreViewModel(cardRepo, userRepo, auth);
  }
}
