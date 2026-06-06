package com.fitquest.rpg.features.roadmap;

import com.fitquest.rpg.core.data.remote.SupabaseAuth;
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
public final class RoadmapViewModel_Factory implements Factory<RoadmapViewModel> {
  private final Provider<UserRepository> userRepoProvider;

  private final Provider<SupabaseAuth> authProvider;

  public RoadmapViewModel_Factory(Provider<UserRepository> userRepoProvider,
      Provider<SupabaseAuth> authProvider) {
    this.userRepoProvider = userRepoProvider;
    this.authProvider = authProvider;
  }

  @Override
  public RoadmapViewModel get() {
    return newInstance(userRepoProvider.get(), authProvider.get());
  }

  public static RoadmapViewModel_Factory create(Provider<UserRepository> userRepoProvider,
      Provider<SupabaseAuth> authProvider) {
    return new RoadmapViewModel_Factory(userRepoProvider, authProvider);
  }

  public static RoadmapViewModel newInstance(UserRepository userRepo, SupabaseAuth auth) {
    return new RoadmapViewModel(userRepo, auth);
  }
}
