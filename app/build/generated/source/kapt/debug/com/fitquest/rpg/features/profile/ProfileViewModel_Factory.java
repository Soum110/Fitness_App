package com.fitquest.rpg.features.profile;

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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<UserRepository> userRepoProvider;

  public ProfileViewModel_Factory(Provider<UserRepository> userRepoProvider) {
    this.userRepoProvider = userRepoProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(userRepoProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<UserRepository> userRepoProvider) {
    return new ProfileViewModel_Factory(userRepoProvider);
  }

  public static ProfileViewModel newInstance(UserRepository userRepo) {
    return new ProfileViewModel(userRepo);
  }
}
