package com.fitquest.rpg;

import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<SupabaseAuth> supabaseAuthProvider;

  public MainActivity_MembersInjector(Provider<SupabaseAuth> supabaseAuthProvider) {
    this.supabaseAuthProvider = supabaseAuthProvider;
  }

  public static MembersInjector<MainActivity> create(Provider<SupabaseAuth> supabaseAuthProvider) {
    return new MainActivity_MembersInjector(supabaseAuthProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectSupabaseAuth(instance, supabaseAuthProvider.get());
  }

  @InjectedFieldSignature("com.fitquest.rpg.MainActivity.supabaseAuth")
  public static void injectSupabaseAuth(MainActivity instance, SupabaseAuth supabaseAuth) {
    instance.supabaseAuth = supabaseAuth;
  }
}
