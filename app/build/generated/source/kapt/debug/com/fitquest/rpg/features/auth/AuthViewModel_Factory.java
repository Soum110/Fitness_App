package com.fitquest.rpg.features.auth;

import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import com.fitquest.rpg.core.data.repository.RewardCardRepository;
import com.fitquest.rpg.core.data.repository.TaskRepository;
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<SupabaseAuth> authProvider;

  private final Provider<UserRepository> userRepoProvider;

  private final Provider<TaskRepository> taskRepoProvider;

  private final Provider<RewardCardRepository> rewardCardRepoProvider;

  private final Provider<FitQuestDatabase> dbProvider;

  public AuthViewModel_Factory(Provider<SupabaseAuth> authProvider,
      Provider<UserRepository> userRepoProvider, Provider<TaskRepository> taskRepoProvider,
      Provider<RewardCardRepository> rewardCardRepoProvider,
      Provider<FitQuestDatabase> dbProvider) {
    this.authProvider = authProvider;
    this.userRepoProvider = userRepoProvider;
    this.taskRepoProvider = taskRepoProvider;
    this.rewardCardRepoProvider = rewardCardRepoProvider;
    this.dbProvider = dbProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authProvider.get(), userRepoProvider.get(), taskRepoProvider.get(), rewardCardRepoProvider.get(), dbProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<SupabaseAuth> authProvider,
      Provider<UserRepository> userRepoProvider, Provider<TaskRepository> taskRepoProvider,
      Provider<RewardCardRepository> rewardCardRepoProvider,
      Provider<FitQuestDatabase> dbProvider) {
    return new AuthViewModel_Factory(authProvider, userRepoProvider, taskRepoProvider, rewardCardRepoProvider, dbProvider);
  }

  public static AuthViewModel newInstance(SupabaseAuth auth, UserRepository userRepo,
      TaskRepository taskRepo, RewardCardRepository rewardCardRepo, FitQuestDatabase db) {
    return new AuthViewModel(auth, userRepo, taskRepo, rewardCardRepo, db);
  }
}
