package com.fitquest.rpg;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.data.local.dao.AttributeDao;
import com.fitquest.rpg.core.data.local.dao.DailyTaskDao;
import com.fitquest.rpg.core.data.local.dao.EconomyDao;
import com.fitquest.rpg.core.data.local.dao.RewardCardDao;
import com.fitquest.rpg.core.data.local.dao.UserProfileDao;
import com.fitquest.rpg.core.data.remote.SupabaseApiService;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import com.fitquest.rpg.core.data.remote.SupabaseRepository;
import com.fitquest.rpg.core.data.remote.WgerApiService;
import com.fitquest.rpg.core.data.repository.RewardCardRepository;
import com.fitquest.rpg.core.data.repository.TaskRepository;
import com.fitquest.rpg.core.data.repository.UserRepository;
import com.fitquest.rpg.core.di.DatabaseModule_ProvideAttributeDaoFactory;
import com.fitquest.rpg.core.di.DatabaseModule_ProvideDailyTaskDaoFactory;
import com.fitquest.rpg.core.di.DatabaseModule_ProvideDatabaseFactory;
import com.fitquest.rpg.core.di.DatabaseModule_ProvideEconomyDaoFactory;
import com.fitquest.rpg.core.di.DatabaseModule_ProvideRewardCardDaoFactory;
import com.fitquest.rpg.core.di.DatabaseModule_ProvideUserProfileDaoFactory;
import com.fitquest.rpg.core.di.NetworkModule_ProvideOkHttpClientFactory;
import com.fitquest.rpg.core.di.NetworkModule_ProvideWgerApiServiceFactory;
import com.fitquest.rpg.core.di.SupabaseModule_ProvideSupabaseApiServiceFactory;
import com.fitquest.rpg.core.di.SupabaseModule_ProvideSupabaseAuthFactory;
import com.fitquest.rpg.features.attributes.AttributesViewModel;
import com.fitquest.rpg.features.attributes.AttributesViewModel_HiltModules;
import com.fitquest.rpg.features.auth.AuthViewModel;
import com.fitquest.rpg.features.auth.AuthViewModel_HiltModules;
import com.fitquest.rpg.features.dashboard.DashboardViewModel;
import com.fitquest.rpg.features.dashboard.DashboardViewModel_HiltModules;
import com.fitquest.rpg.features.diet.DietViewModel;
import com.fitquest.rpg.features.diet.DietViewModel_HiltModules;
import com.fitquest.rpg.features.onboarding.OnboardingViewModel;
import com.fitquest.rpg.features.onboarding.OnboardingViewModel_HiltModules;
import com.fitquest.rpg.features.profile.ProfileViewModel;
import com.fitquest.rpg.features.profile.ProfileViewModel_HiltModules;
import com.fitquest.rpg.features.roadmap.RoadmapViewModel;
import com.fitquest.rpg.features.roadmap.RoadmapViewModel_HiltModules;
import com.fitquest.rpg.features.store.StoreViewModel;
import com.fitquest.rpg.features.store.StoreViewModel_HiltModules;
import com.fitquest.rpg.features.workout.WorkoutViewModel;
import com.fitquest.rpg.features.workout.WorkoutViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class DaggerFitQuestApp_HiltComponents_SingletonC {
  private DaggerFitQuestApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public FitQuestApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements FitQuestApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public FitQuestApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements FitQuestApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public FitQuestApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements FitQuestApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public FitQuestApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements FitQuestApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public FitQuestApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements FitQuestApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public FitQuestApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements FitQuestApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public FitQuestApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements FitQuestApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public FitQuestApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends FitQuestApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends FitQuestApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends FitQuestApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends FitQuestApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(9).put(LazyClassKeyProvider.com_fitquest_rpg_features_attributes_AttributesViewModel, AttributesViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_fitquest_rpg_features_auth_AuthViewModel, AuthViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_fitquest_rpg_features_dashboard_DashboardViewModel, DashboardViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_fitquest_rpg_features_diet_DietViewModel, DietViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_fitquest_rpg_features_onboarding_OnboardingViewModel, OnboardingViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_fitquest_rpg_features_profile_ProfileViewModel, ProfileViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_fitquest_rpg_features_roadmap_RoadmapViewModel, RoadmapViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_fitquest_rpg_features_store_StoreViewModel, StoreViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_fitquest_rpg_features_workout_WorkoutViewModel, WorkoutViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectSupabaseAuth(instance, singletonCImpl.provideSupabaseAuthProvider.get());
      return instance;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_fitquest_rpg_features_auth_AuthViewModel = "com.fitquest.rpg.features.auth.AuthViewModel";

      static String com_fitquest_rpg_features_profile_ProfileViewModel = "com.fitquest.rpg.features.profile.ProfileViewModel";

      static String com_fitquest_rpg_features_store_StoreViewModel = "com.fitquest.rpg.features.store.StoreViewModel";

      static String com_fitquest_rpg_features_workout_WorkoutViewModel = "com.fitquest.rpg.features.workout.WorkoutViewModel";

      static String com_fitquest_rpg_features_onboarding_OnboardingViewModel = "com.fitquest.rpg.features.onboarding.OnboardingViewModel";

      static String com_fitquest_rpg_features_dashboard_DashboardViewModel = "com.fitquest.rpg.features.dashboard.DashboardViewModel";

      static String com_fitquest_rpg_features_attributes_AttributesViewModel = "com.fitquest.rpg.features.attributes.AttributesViewModel";

      static String com_fitquest_rpg_features_diet_DietViewModel = "com.fitquest.rpg.features.diet.DietViewModel";

      static String com_fitquest_rpg_features_roadmap_RoadmapViewModel = "com.fitquest.rpg.features.roadmap.RoadmapViewModel";

      @KeepFieldType
      AuthViewModel com_fitquest_rpg_features_auth_AuthViewModel2;

      @KeepFieldType
      ProfileViewModel com_fitquest_rpg_features_profile_ProfileViewModel2;

      @KeepFieldType
      StoreViewModel com_fitquest_rpg_features_store_StoreViewModel2;

      @KeepFieldType
      WorkoutViewModel com_fitquest_rpg_features_workout_WorkoutViewModel2;

      @KeepFieldType
      OnboardingViewModel com_fitquest_rpg_features_onboarding_OnboardingViewModel2;

      @KeepFieldType
      DashboardViewModel com_fitquest_rpg_features_dashboard_DashboardViewModel2;

      @KeepFieldType
      AttributesViewModel com_fitquest_rpg_features_attributes_AttributesViewModel2;

      @KeepFieldType
      DietViewModel com_fitquest_rpg_features_diet_DietViewModel2;

      @KeepFieldType
      RoadmapViewModel com_fitquest_rpg_features_roadmap_RoadmapViewModel2;
    }
  }

  private static final class ViewModelCImpl extends FitQuestApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AttributesViewModel> attributesViewModelProvider;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<DietViewModel> dietViewModelProvider;

    private Provider<OnboardingViewModel> onboardingViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<RoadmapViewModel> roadmapViewModelProvider;

    private Provider<StoreViewModel> storeViewModelProvider;

    private Provider<WorkoutViewModel> workoutViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.attributesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.dietViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.onboardingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.roadmapViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.storeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.workoutViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(9).put(LazyClassKeyProvider.com_fitquest_rpg_features_attributes_AttributesViewModel, ((Provider) attributesViewModelProvider)).put(LazyClassKeyProvider.com_fitquest_rpg_features_auth_AuthViewModel, ((Provider) authViewModelProvider)).put(LazyClassKeyProvider.com_fitquest_rpg_features_dashboard_DashboardViewModel, ((Provider) dashboardViewModelProvider)).put(LazyClassKeyProvider.com_fitquest_rpg_features_diet_DietViewModel, ((Provider) dietViewModelProvider)).put(LazyClassKeyProvider.com_fitquest_rpg_features_onboarding_OnboardingViewModel, ((Provider) onboardingViewModelProvider)).put(LazyClassKeyProvider.com_fitquest_rpg_features_profile_ProfileViewModel, ((Provider) profileViewModelProvider)).put(LazyClassKeyProvider.com_fitquest_rpg_features_roadmap_RoadmapViewModel, ((Provider) roadmapViewModelProvider)).put(LazyClassKeyProvider.com_fitquest_rpg_features_store_StoreViewModel, ((Provider) storeViewModelProvider)).put(LazyClassKeyProvider.com_fitquest_rpg_features_workout_WorkoutViewModel, ((Provider) workoutViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_fitquest_rpg_features_onboarding_OnboardingViewModel = "com.fitquest.rpg.features.onboarding.OnboardingViewModel";

      static String com_fitquest_rpg_features_workout_WorkoutViewModel = "com.fitquest.rpg.features.workout.WorkoutViewModel";

      static String com_fitquest_rpg_features_auth_AuthViewModel = "com.fitquest.rpg.features.auth.AuthViewModel";

      static String com_fitquest_rpg_features_profile_ProfileViewModel = "com.fitquest.rpg.features.profile.ProfileViewModel";

      static String com_fitquest_rpg_features_store_StoreViewModel = "com.fitquest.rpg.features.store.StoreViewModel";

      static String com_fitquest_rpg_features_diet_DietViewModel = "com.fitquest.rpg.features.diet.DietViewModel";

      static String com_fitquest_rpg_features_roadmap_RoadmapViewModel = "com.fitquest.rpg.features.roadmap.RoadmapViewModel";

      static String com_fitquest_rpg_features_dashboard_DashboardViewModel = "com.fitquest.rpg.features.dashboard.DashboardViewModel";

      static String com_fitquest_rpg_features_attributes_AttributesViewModel = "com.fitquest.rpg.features.attributes.AttributesViewModel";

      @KeepFieldType
      OnboardingViewModel com_fitquest_rpg_features_onboarding_OnboardingViewModel2;

      @KeepFieldType
      WorkoutViewModel com_fitquest_rpg_features_workout_WorkoutViewModel2;

      @KeepFieldType
      AuthViewModel com_fitquest_rpg_features_auth_AuthViewModel2;

      @KeepFieldType
      ProfileViewModel com_fitquest_rpg_features_profile_ProfileViewModel2;

      @KeepFieldType
      StoreViewModel com_fitquest_rpg_features_store_StoreViewModel2;

      @KeepFieldType
      DietViewModel com_fitquest_rpg_features_diet_DietViewModel2;

      @KeepFieldType
      RoadmapViewModel com_fitquest_rpg_features_roadmap_RoadmapViewModel2;

      @KeepFieldType
      DashboardViewModel com_fitquest_rpg_features_dashboard_DashboardViewModel2;

      @KeepFieldType
      AttributesViewModel com_fitquest_rpg_features_attributes_AttributesViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.fitquest.rpg.features.attributes.AttributesViewModel 
          return (T) new AttributesViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          case 1: // com.fitquest.rpg.features.auth.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.provideSupabaseAuthProvider.get(), singletonCImpl.userRepositoryProvider.get(), singletonCImpl.taskRepositoryProvider.get(), singletonCImpl.rewardCardRepositoryProvider.get(), singletonCImpl.provideDatabaseProvider.get());

          case 2: // com.fitquest.rpg.features.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.taskRepositoryProvider.get(), singletonCImpl.rewardCardRepositoryProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          case 3: // com.fitquest.rpg.features.diet.DietViewModel 
          return (T) new DietViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          case 4: // com.fitquest.rpg.features.onboarding.OnboardingViewModel 
          return (T) new OnboardingViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          case 5: // com.fitquest.rpg.features.profile.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.taskRepositoryProvider.get(), singletonCImpl.rewardCardRepositoryProvider.get(), singletonCImpl.supabaseRepositoryProvider.get(), singletonCImpl.provideDatabaseProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          case 6: // com.fitquest.rpg.features.roadmap.RoadmapViewModel 
          return (T) new RoadmapViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          case 7: // com.fitquest.rpg.features.store.StoreViewModel 
          return (T) new StoreViewModel(singletonCImpl.rewardCardRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          case 8: // com.fitquest.rpg.features.workout.WorkoutViewModel 
          return (T) new WorkoutViewModel(singletonCImpl.taskRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends FitQuestApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends FitQuestApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends FitQuestApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<SupabaseApiService> provideSupabaseApiServiceProvider;

    private Provider<SupabaseAuth> provideSupabaseAuthProvider;

    private Provider<FitQuestDatabase> provideDatabaseProvider;

    private Provider<SupabaseRepository> supabaseRepositoryProvider;

    private Provider<UserRepository> userRepositoryProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<WgerApiService> provideWgerApiServiceProvider;

    private Provider<TaskRepository> taskRepositoryProvider;

    private Provider<RewardCardRepository> rewardCardRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private UserProfileDao userProfileDao() {
      return DatabaseModule_ProvideUserProfileDaoFactory.provideUserProfileDao(provideDatabaseProvider.get());
    }

    private AttributeDao attributeDao() {
      return DatabaseModule_ProvideAttributeDaoFactory.provideAttributeDao(provideDatabaseProvider.get());
    }

    private EconomyDao economyDao() {
      return DatabaseModule_ProvideEconomyDaoFactory.provideEconomyDao(provideDatabaseProvider.get());
    }

    private DailyTaskDao dailyTaskDao() {
      return DatabaseModule_ProvideDailyTaskDaoFactory.provideDailyTaskDao(provideDatabaseProvider.get());
    }

    private RewardCardDao rewardCardDao() {
      return DatabaseModule_ProvideRewardCardDaoFactory.provideRewardCardDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideSupabaseApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<SupabaseApiService>(singletonCImpl, 1));
      this.provideSupabaseAuthProvider = DoubleCheck.provider(new SwitchingProvider<SupabaseAuth>(singletonCImpl, 0));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<FitQuestDatabase>(singletonCImpl, 3));
      this.supabaseRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<SupabaseRepository>(singletonCImpl, 4));
      this.userRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserRepository>(singletonCImpl, 2));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 7));
      this.provideWgerApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<WgerApiService>(singletonCImpl, 6));
      this.taskRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TaskRepository>(singletonCImpl, 5));
      this.rewardCardRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<RewardCardRepository>(singletonCImpl, 8));
    }

    @Override
    public void injectFitQuestApp(FitQuestApp fitQuestApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.fitquest.rpg.core.data.remote.SupabaseAuth 
          return (T) SupabaseModule_ProvideSupabaseAuthFactory.provideSupabaseAuth(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideSupabaseApiServiceProvider.get());

          case 1: // com.fitquest.rpg.core.data.remote.SupabaseApiService 
          return (T) SupabaseModule_ProvideSupabaseApiServiceFactory.provideSupabaseApiService();

          case 2: // com.fitquest.rpg.core.data.repository.UserRepository 
          return (T) new UserRepository(singletonCImpl.userProfileDao(), singletonCImpl.attributeDao(), singletonCImpl.economyDao(), singletonCImpl.supabaseRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.fitquest.rpg.core.data.local.FitQuestDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.fitquest.rpg.core.data.remote.SupabaseRepository 
          return (T) new SupabaseRepository(singletonCImpl.provideSupabaseApiServiceProvider.get(), singletonCImpl.provideSupabaseAuthProvider.get());

          case 5: // com.fitquest.rpg.core.data.repository.TaskRepository 
          return (T) new TaskRepository(singletonCImpl.dailyTaskDao(), singletonCImpl.attributeDao(), singletonCImpl.supabaseRepositoryProvider.get(), singletonCImpl.provideWgerApiServiceProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // com.fitquest.rpg.core.data.remote.WgerApiService 
          return (T) NetworkModule_ProvideWgerApiServiceFactory.provideWgerApiService(singletonCImpl.provideOkHttpClientProvider.get());

          case 7: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 8: // com.fitquest.rpg.core.data.repository.RewardCardRepository 
          return (T) new RewardCardRepository(singletonCImpl.rewardCardDao(), singletonCImpl.supabaseRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
