package com.fitquest.rpg.core.di;

import com.fitquest.rpg.core.data.remote.SupabaseApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SupabaseModule_ProvideSupabaseApiServiceFactory implements Factory<SupabaseApiService> {
  @Override
  public SupabaseApiService get() {
    return provideSupabaseApiService();
  }

  public static SupabaseModule_ProvideSupabaseApiServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SupabaseApiService provideSupabaseApiService() {
    return Preconditions.checkNotNullFromProvides(SupabaseModule.INSTANCE.provideSupabaseApiService());
  }

  private static final class InstanceHolder {
    private static final SupabaseModule_ProvideSupabaseApiServiceFactory INSTANCE = new SupabaseModule_ProvideSupabaseApiServiceFactory();
  }
}
