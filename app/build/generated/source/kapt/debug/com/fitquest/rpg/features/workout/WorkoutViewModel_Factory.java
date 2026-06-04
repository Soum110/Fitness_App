package com.fitquest.rpg.features.workout;

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
public final class WorkoutViewModel_Factory implements Factory<WorkoutViewModel> {
  private final Provider<TaskRepository> taskRepoProvider;

  private final Provider<UserRepository> userRepoProvider;

  public WorkoutViewModel_Factory(Provider<TaskRepository> taskRepoProvider,
      Provider<UserRepository> userRepoProvider) {
    this.taskRepoProvider = taskRepoProvider;
    this.userRepoProvider = userRepoProvider;
  }

  @Override
  public WorkoutViewModel get() {
    return newInstance(taskRepoProvider.get(), userRepoProvider.get());
  }

  public static WorkoutViewModel_Factory create(Provider<TaskRepository> taskRepoProvider,
      Provider<UserRepository> userRepoProvider) {
    return new WorkoutViewModel_Factory(taskRepoProvider, userRepoProvider);
  }

  public static WorkoutViewModel newInstance(TaskRepository taskRepo, UserRepository userRepo) {
    return new WorkoutViewModel(taskRepo, userRepo);
  }
}
