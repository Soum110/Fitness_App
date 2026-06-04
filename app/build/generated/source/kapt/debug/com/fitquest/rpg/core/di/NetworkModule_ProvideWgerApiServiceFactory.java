package com.fitquest.rpg.core.di;

import com.fitquest.rpg.core.data.remote.WgerApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

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
public final class NetworkModule_ProvideWgerApiServiceFactory implements Factory<WgerApiService> {
  private final Provider<OkHttpClient> clientProvider;

  public NetworkModule_ProvideWgerApiServiceFactory(Provider<OkHttpClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public WgerApiService get() {
    return provideWgerApiService(clientProvider.get());
  }

  public static NetworkModule_ProvideWgerApiServiceFactory create(
      Provider<OkHttpClient> clientProvider) {
    return new NetworkModule_ProvideWgerApiServiceFactory(clientProvider);
  }

  public static WgerApiService provideWgerApiService(OkHttpClient client) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideWgerApiService(client));
  }
}
