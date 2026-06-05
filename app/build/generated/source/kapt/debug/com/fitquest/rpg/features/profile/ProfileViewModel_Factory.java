package com.fitquest.rpg.features.profile;

import com.fitquest.rpg.core.data.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
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

  private final Provider<FirebaseAuth> authProvider;

  public ProfileViewModel_Factory(Provider<UserRepository> userRepoProvider,
      Provider<FirebaseAuth> authProvider) {
    this.userRepoProvider = userRepoProvider;
    this.authProvider = authProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(userRepoProvider.get(), authProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<UserRepository> userRepoProvider,
      Provider<FirebaseAuth> authProvider) {
    return new ProfileViewModel_Factory(userRepoProvider, authProvider);
  }

  public static ProfileViewModel newInstance(UserRepository userRepo, FirebaseAuth auth) {
    return new ProfileViewModel(userRepo, auth);
  }
}
