package com.fitquest.rpg.features.diet;

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
public final class DietViewModel_Factory implements Factory<DietViewModel> {
  private final Provider<UserRepository> userRepoProvider;

  private final Provider<FirebaseAuth> authProvider;

  public DietViewModel_Factory(Provider<UserRepository> userRepoProvider,
      Provider<FirebaseAuth> authProvider) {
    this.userRepoProvider = userRepoProvider;
    this.authProvider = authProvider;
  }

  @Override
  public DietViewModel get() {
    return newInstance(userRepoProvider.get(), authProvider.get());
  }

  public static DietViewModel_Factory create(Provider<UserRepository> userRepoProvider,
      Provider<FirebaseAuth> authProvider) {
    return new DietViewModel_Factory(userRepoProvider, authProvider);
  }

  public static DietViewModel newInstance(UserRepository userRepo, FirebaseAuth auth) {
    return new DietViewModel(userRepo, auth);
  }
}
