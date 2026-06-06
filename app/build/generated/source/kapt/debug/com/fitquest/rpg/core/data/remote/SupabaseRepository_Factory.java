package com.fitquest.rpg.core.data.remote;

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
public final class SupabaseRepository_Factory implements Factory<SupabaseRepository> {
  private final Provider<SupabaseApiService> apiServiceProvider;

  private final Provider<SupabaseAuth> authProvider;

  public SupabaseRepository_Factory(Provider<SupabaseApiService> apiServiceProvider,
      Provider<SupabaseAuth> authProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.authProvider = authProvider;
  }

  @Override
  public SupabaseRepository get() {
    return newInstance(apiServiceProvider.get(), authProvider.get());
  }

  public static SupabaseRepository_Factory create(Provider<SupabaseApiService> apiServiceProvider,
      Provider<SupabaseAuth> authProvider) {
    return new SupabaseRepository_Factory(apiServiceProvider, authProvider);
  }

  public static SupabaseRepository newInstance(SupabaseApiService apiService, SupabaseAuth auth) {
    return new SupabaseRepository(apiService, auth);
  }
}
