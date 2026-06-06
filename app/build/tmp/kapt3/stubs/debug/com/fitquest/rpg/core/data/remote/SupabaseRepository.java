package com.fitquest.rpg.core.data.remote;

import com.fitquest.rpg.core.domain.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\b\u0010\u0015\u001a\u00020\fH\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u001e2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001e2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00130\u001e2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00130\u001e2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010#\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010%J$\u0010&\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0086@\u00a2\u0006\u0002\u0010(J\u001e\u0010)\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010+J\u001e\u0010,\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010.J$\u0010/\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013H\u0086@\u00a2\u0006\u0002\u0010(J$\u00101\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0013H\u0086@\u00a2\u0006\u0002\u0010(J\u0010\u00103\u001a\n 4*\u0004\u0018\u00010\f0\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/fitquest/rpg/core/data/remote/SupabaseRepository;", "", "apiService", "Lcom/fitquest/rpg/core/data/remote/SupabaseApiService;", "auth", "Lcom/fitquest/rpg/core/data/remote/SupabaseAuth;", "(Lcom/fitquest/rpg/core/data/remote/SupabaseApiService;Lcom/fitquest/rpg/core/data/remote/SupabaseAuth;)V", "gson", "Lcom/google/gson/Gson;", "deleteCard", "", "uid", "", "card", "Lcom/fitquest/rpg/core/domain/model/RewardCard;", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/RewardCard;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteUserData", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAttributes", "", "Lcom/fitquest/rpg/core/domain/model/Attribute;", "getAuthHeader", "getEconomy", "Lcom/fitquest/rpg/core/domain/model/Economy;", "getProfile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "getRewardCards", "getTodaysTasks", "Lcom/fitquest/rpg/core/domain/model/DailyTask;", "observeAttributes", "Lkotlinx/coroutines/flow/Flow;", "observeEconomy", "observeProfile", "observeRewardCards", "observeTodaysTasks", "saveAttribute", "attribute", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/Attribute;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveAttributes", "attributes", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveEconomy", "economy", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/Economy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveProfile", "profile", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveRewardCards", "cards", "saveTasks", "tasks", "todayKey", "kotlin.jvm.PlatformType", "app_debug"})
public final class SupabaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.SupabaseApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.SupabaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    @javax.inject.Inject()
    public SupabaseRepository(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseApiService apiService, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseAuth auth) {
        super();
    }
    
    private final java.lang.String getAuthHeader() {
        return null;
    }
    
    private final java.lang.String todayKey() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.fitquest.rpg.core.domain.model.UserProfile> observeProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.domain.model.UserProfile> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveEconomy(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Economy economy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.fitquest.rpg.core.domain.model.Economy> observeEconomy(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getEconomy(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.domain.model.Economy> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveAttribute(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Attribute attribute, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveAttributes(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.domain.model.Attribute>> observeAttributes(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAttributes(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.domain.model.Attribute>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveTasks(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.DailyTask> tasks, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.domain.model.DailyTask>> observeTodaysTasks(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTodaysTasks(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.domain.model.DailyTask>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveRewardCards(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.RewardCard> cards, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.domain.model.RewardCard>> observeRewardCards(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRewardCards(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.domain.model.RewardCard>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteCard(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.RewardCard card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteUserData(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}