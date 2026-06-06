package com.fitquest.rpg.features.profile;

import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import com.fitquest.rpg.core.data.remote.SupabaseRepository;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<UserRepository> userRepoProvider;

  private final Provider<TaskRepository> taskRepoProvider;

  private final Provider<RewardCardRepository> rewardCardRepoProvider;

  private final Provider<SupabaseRepository> supabaseRepoProvider;

  private final Provider<FitQuestDatabase> dbProvider;

  private final Provider<SupabaseAuth> authProvider;

  public ProfileViewModel_Factory(Provider<UserRepository> userRepoProvider,
      Provider<TaskRepository> taskRepoProvider,
      Provider<RewardCardRepository> rewardCardRepoProvider,
      Provider<SupabaseRepository> supabaseRepoProvider, Provider<FitQuestDatabase> dbProvider,
      Provider<SupabaseAuth> authProvider) {
    this.userRepoProvider = userRepoProvider;
    this.taskRepoProvider = taskRepoProvider;
    this.rewardCardRepoProvider = rewardCardRepoProvider;
    this.supabaseRepoProvider = supabaseRepoProvider;
    this.dbProvider = dbProvider;
    this.authProvider = authProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(userRepoProvider.get(), taskRepoProvider.get(), rewardCardRepoProvider.get(), supabaseRepoProvider.get(), dbProvider.get(), authProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<UserRepository> userRepoProvider,
      Provider<TaskRepository> taskRepoProvider,
      Provider<RewardCardRepository> rewardCardRepoProvider,
      Provider<SupabaseRepository> supabaseRepoProvider, Provider<FitQuestDatabase> dbProvider,
      Provider<SupabaseAuth> authProvider) {
    return new ProfileViewModel_Factory(userRepoProvider, taskRepoProvider, rewardCardRepoProvider, supabaseRepoProvider, dbProvider, authProvider);
  }

  public static ProfileViewModel newInstance(UserRepository userRepo, TaskRepository taskRepo,
      RewardCardRepository rewardCardRepo, SupabaseRepository supabaseRepo, FitQuestDatabase db,
      SupabaseAuth auth) {
    return new ProfileViewModel(userRepo, taskRepo, rewardCardRepo, supabaseRepo, db, auth);
  }
}
