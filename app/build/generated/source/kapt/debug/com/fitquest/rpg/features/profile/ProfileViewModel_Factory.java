package com.fitquest.rpg.features.profile;

import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.remote.FirestoreRepository;
import com.fitquest.rpg.core.data.repository.RewardCardRepository;
import com.fitquest.rpg.core.data.repository.TaskRepository;
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

  private final Provider<TaskRepository> taskRepoProvider;

  private final Provider<RewardCardRepository> rewardCardRepoProvider;

  private final Provider<FirestoreRepository> firestoreRepoProvider;

  private final Provider<FitQuestDatabase> dbProvider;

  private final Provider<FirebaseAuth> authProvider;

  public ProfileViewModel_Factory(Provider<UserRepository> userRepoProvider,
      Provider<TaskRepository> taskRepoProvider,
      Provider<RewardCardRepository> rewardCardRepoProvider,
      Provider<FirestoreRepository> firestoreRepoProvider, Provider<FitQuestDatabase> dbProvider,
      Provider<FirebaseAuth> authProvider) {
    this.userRepoProvider = userRepoProvider;
    this.taskRepoProvider = taskRepoProvider;
    this.rewardCardRepoProvider = rewardCardRepoProvider;
    this.firestoreRepoProvider = firestoreRepoProvider;
    this.dbProvider = dbProvider;
    this.authProvider = authProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(userRepoProvider.get(), taskRepoProvider.get(), rewardCardRepoProvider.get(), firestoreRepoProvider.get(), dbProvider.get(), authProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<UserRepository> userRepoProvider,
      Provider<TaskRepository> taskRepoProvider,
      Provider<RewardCardRepository> rewardCardRepoProvider,
      Provider<FirestoreRepository> firestoreRepoProvider, Provider<FitQuestDatabase> dbProvider,
      Provider<FirebaseAuth> authProvider) {
    return new ProfileViewModel_Factory(userRepoProvider, taskRepoProvider, rewardCardRepoProvider, firestoreRepoProvider, dbProvider, authProvider);
  }

  public static ProfileViewModel newInstance(UserRepository userRepo, TaskRepository taskRepo,
      RewardCardRepository rewardCardRepo, FirestoreRepository firestoreRepo, FitQuestDatabase db,
      FirebaseAuth auth) {
    return new ProfileViewModel(userRepo, taskRepo, rewardCardRepo, firestoreRepo, db, auth);
  }
}
