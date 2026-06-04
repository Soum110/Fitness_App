package com.fitquest.rpg.features.onboarding;

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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<UserRepository> userRepoProvider;

  public OnboardingViewModel_Factory(Provider<UserRepository> userRepoProvider) {
    this.userRepoProvider = userRepoProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(userRepoProvider.get());
  }

  public static OnboardingViewModel_Factory create(Provider<UserRepository> userRepoProvider) {
    return new OnboardingViewModel_Factory(userRepoProvider);
  }

  public static OnboardingViewModel newInstance(UserRepository userRepo) {
    return new OnboardingViewModel(userRepo);
  }
}
