package com.fitquest.rpg.features.dashboard;

import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.repository.*;
import com.fitquest.rpg.core.domain.model.*;
import com.google.firebase.auth.FirebaseAuth;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u0012J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\nH\u00c6\u0003J\t\u0010$\u001a\u00020\fH\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003J\t\u0010&\u001a\u00020\u0010H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003Jk\u0010(\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u00c6\u0001J\u0013\u0010)\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020,H\u00d6\u0001J\t\u0010-\u001a\u00020\u0010H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0019R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018\u00a8\u0006."}, d2 = {"Lcom/fitquest/rpg/features/dashboard/DashboardUiState;", "", "profile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "attributes", "", "Lcom/fitquest/rpg/core/domain/model/Attribute;", "todaysTasks", "Lcom/fitquest/rpg/core/domain/model/DailyTask;", "economy", "Lcom/fitquest/rpg/core/domain/model/Economy;", "isLoading", "", "rankUpEvent", "Lcom/fitquest/rpg/core/domain/model/Rank;", "weekPhaseDescription", "", "errorMessage", "(Lcom/fitquest/rpg/core/domain/model/UserProfile;Ljava/util/List;Ljava/util/List;Lcom/fitquest/rpg/core/domain/model/Economy;ZLcom/fitquest/rpg/core/domain/model/Rank;Ljava/lang/String;Ljava/lang/String;)V", "getAttributes", "()Ljava/util/List;", "getEconomy", "()Lcom/fitquest/rpg/core/domain/model/Economy;", "getErrorMessage", "()Ljava/lang/String;", "()Z", "getProfile", "()Lcom/fitquest/rpg/core/domain/model/UserProfile;", "getRankUpEvent", "()Lcom/fitquest/rpg/core/domain/model/Rank;", "getTodaysTasks", "getWeekPhaseDescription", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class DashboardUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.fitquest.rpg.core.domain.model.UserProfile profile = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.fitquest.rpg.core.domain.model.DailyTask> todaysTasks = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.Economy economy = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final com.fitquest.rpg.core.domain.model.Rank rankUpEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String weekPhaseDescription = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String errorMessage = null;
    
    public DashboardUiState(@org.jetbrains.annotations.Nullable()
    com.fitquest.rpg.core.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.DailyTask> todaysTasks, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Economy economy, boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.fitquest.rpg.core.domain.model.Rank rankUpEvent, @org.jetbrains.annotations.NotNull()
    java.lang.String weekPhaseDescription, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fitquest.rpg.core.domain.model.UserProfile getProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.fitquest.rpg.core.domain.model.Attribute> getAttributes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.fitquest.rpg.core.domain.model.DailyTask> getTodaysTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.Economy getEconomy() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fitquest.rpg.core.domain.model.Rank getRankUpEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWeekPhaseDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public DashboardUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fitquest.rpg.core.domain.model.UserProfile component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.fitquest.rpg.core.domain.model.Attribute> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.fitquest.rpg.core.domain.model.DailyTask> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.Economy component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fitquest.rpg.core.domain.model.Rank component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.features.dashboard.DashboardUiState copy(@org.jetbrains.annotations.Nullable()
    com.fitquest.rpg.core.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.DailyTask> todaysTasks, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Economy economy, boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.fitquest.rpg.core.domain.model.Rank rankUpEvent, @org.jetbrains.annotations.NotNull()
    java.lang.String weekPhaseDescription, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}