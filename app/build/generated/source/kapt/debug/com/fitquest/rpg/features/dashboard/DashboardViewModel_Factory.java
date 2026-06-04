package com.fitquest.rpg.features.dashboard;

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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<UserRepository> userRepoProvider;

  private final Provider<TaskRepository> taskRepoProvider;

  private final Provider<RewardCardRepository> cardRepoProvider;

  public DashboardViewModel_Factory(Provider<UserRepository> userRepoProvider,
      Provider<TaskRepository> taskRepoProvider, Provider<RewardCardRepository> cardRepoProvider) {
    this.userRepoProvider = userRepoProvider;
    this.taskRepoProvider = taskRepoProvider;
    this.cardRepoProvider = cardRepoProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(userRepoProvider.get(), taskRepoProvider.get(), cardRepoProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<UserRepository> userRepoProvider,
      Provider<TaskRepository> taskRepoProvider, Provider<RewardCardRepository> cardRepoProvider) {
    return new DashboardViewModel_Factory(userRepoProvider, taskRepoProvider, cardRepoProvider);
  }

  public static DashboardViewModel newInstance(UserRepository userRepo, TaskRepository taskRepo,
      RewardCardRepository cardRepo) {
    return new DashboardViewModel(userRepo, taskRepo, cardRepo);
  }
}
