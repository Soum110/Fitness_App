package com.fitquest.rpg.core.di;

import android.content.Context;
import com.fitquest.rpg.core.data.remote.SupabaseApiService;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SupabaseModule_ProvideSupabaseAuthFactory implements Factory<SupabaseAuth> {
  private final Provider<Context> contextProvider;

  private final Provider<SupabaseApiService> apiServiceProvider;

  public SupabaseModule_ProvideSupabaseAuthFactory(Provider<Context> contextProvider,
      Provider<SupabaseApiService> apiServiceProvider) {
    this.contextProvider = contextProvider;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public SupabaseAuth get() {
    return provideSupabaseAuth(contextProvider.get(), apiServiceProvider.get());
  }

  public static SupabaseModule_ProvideSupabaseAuthFactory create(Provider<Context> contextProvider,
      Provider<SupabaseApiService> apiServiceProvider) {
    return new SupabaseModule_ProvideSupabaseAuthFactory(contextProvider, apiServiceProvider);
  }

  public static SupabaseAuth provideSupabaseAuth(Context context, SupabaseApiService apiService) {
    return Preconditions.checkNotNullFromProvides(SupabaseModule.INSTANCE.provideSupabaseAuth(context, apiService));
  }
}
