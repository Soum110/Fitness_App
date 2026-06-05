package com.fitquest.rpg;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.navigation.compose.*;
import com.fitquest.rpg.features.auth.AuthViewModel;
import com.fitquest.rpg.features.onboarding.OnboardingViewModel;
import com.fitquest.rpg.ui.theme.*;
import com.google.firebase.auth.FirebaseAuth;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\b\u0007\b\t\n\u000b\f\r\u000eB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\b\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/fitquest/rpg/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "Attributes", "Auth", "Dashboard", "Diet", "Onboarding", "Profile", "Roadmap", "Store", "Lcom/fitquest/rpg/Screen$Attributes;", "Lcom/fitquest/rpg/Screen$Auth;", "Lcom/fitquest/rpg/Screen$Dashboard;", "Lcom/fitquest/rpg/Screen$Diet;", "Lcom/fitquest/rpg/Screen$Onboarding;", "Lcom/fitquest/rpg/Screen$Profile;", "Lcom/fitquest/rpg/Screen$Roadmap;", "Lcom/fitquest/rpg/Screen$Store;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/fitquest/rpg/Screen$Attributes;", "Lcom/fitquest/rpg/Screen;", "()V", "app_debug"})
    public static final class Attributes extends com.fitquest.rpg.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.fitquest.rpg.Screen.Attributes INSTANCE = null;
        
        private Attributes() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/fitquest/rpg/Screen$Auth;", "Lcom/fitquest/rpg/Screen;", "()V", "app_debug"})
    public static final class Auth extends com.fitquest.rpg.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.fitquest.rpg.Screen.Auth INSTANCE = null;
        
        private Auth() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/fitquest/rpg/Screen$Dashboard;", "Lcom/fitquest/rpg/Screen;", "()V", "app_debug"})
    public static final class Dashboard extends com.fitquest.rpg.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.fitquest.rpg.Screen.Dashboard INSTANCE = null;
        
        private Dashboard() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/fitquest/rpg/Screen$Diet;", "Lcom/fitquest/rpg/Screen;", "()V", "app_debug"})
    public static final class Diet extends com.fitquest.rpg.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.fitquest.rpg.Screen.Diet INSTANCE = null;
        
        private Diet() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/fitquest/rpg/Screen$Onboarding;", "Lcom/fitquest/rpg/Screen;", "()V", "app_debug"})
    public static final class Onboarding extends com.fitquest.rpg.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.fitquest.rpg.Screen.Onboarding INSTANCE = null;
        
        private Onboarding() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/fitquest/rpg/Screen$Profile;", "Lcom/fitquest/rpg/Screen;", "()V", "app_debug"})
    public static final class Profile extends com.fitquest.rpg.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.fitquest.rpg.Screen.Profile INSTANCE = null;
        
        private Profile() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/fitquest/rpg/Screen$Roadmap;", "Lcom/fitquest/rpg/Screen;", "()V", "app_debug"})
    public static final class Roadmap extends com.fitquest.rpg.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.fitquest.rpg.Screen.Roadmap INSTANCE = null;
        
        private Roadmap() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/fitquest/rpg/Screen$Store;", "Lcom/fitquest/rpg/Screen;", "()V", "app_debug"})
    public static final class Store extends com.fitquest.rpg.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.fitquest.rpg.Screen.Store INSTANCE = null;
        
        private Store() {
        }
    }
}