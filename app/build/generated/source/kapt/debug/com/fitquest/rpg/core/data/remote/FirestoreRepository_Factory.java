package com.fitquest.rpg.core.data.remote;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class FirestoreRepository_Factory implements Factory<FirestoreRepository> {
  private final Provider<FirebaseFirestore> dbProvider;

  public FirestoreRepository_Factory(Provider<FirebaseFirestore> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public FirestoreRepository get() {
    return newInstance(dbProvider.get());
  }

  public static FirestoreRepository_Factory create(Provider<FirebaseFirestore> dbProvider) {
    return new FirestoreRepository_Factory(dbProvider);
  }

  public static FirestoreRepository newInstance(FirebaseFirestore db) {
    return new FirestoreRepository(db);
  }
}
