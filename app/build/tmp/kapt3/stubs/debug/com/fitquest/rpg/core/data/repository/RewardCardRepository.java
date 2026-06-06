package com.fitquest.rpg.core.data.repository;

import android.content.Context;
import com.fitquest.rpg.core.data.local.dao.*;
import com.fitquest.rpg.core.data.local.entity.*;
import com.fitquest.rpg.core.data.remote.SupabaseRepository;
import com.fitquest.rpg.core.data.remote.WgerApiService;
import com.fitquest.rpg.core.domain.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.*;
import kotlinx.coroutines.flow.*;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0013J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0013J&\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001f0\u001e2\u0006\u0010\u0011\u001a\u00020\fJ\u001e\u0010 \u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\fH\u0002J\u0006\u0010\"\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/fitquest/rpg/core/data/repository/RewardCardRepository;", "", "cardDao", "Lcom/fitquest/rpg/core/data/local/dao/RewardCardDao;", "supabaseRepo", "Lcom/fitquest/rpg/core/data/remote/SupabaseRepository;", "context", "Landroid/content/Context;", "(Lcom/fitquest/rpg/core/data/local/dao/RewardCardDao;Lcom/fitquest/rpg/core/data/remote/SupabaseRepository;Landroid/content/Context;)V", "cardsSyncJob", "Lkotlinx/coroutines/Job;", "lastSyncedUid", "", "syncScope", "Lkotlinx/coroutines/CoroutineScope;", "addCustomCard", "Lcom/fitquest/rpg/core/domain/model/RewardCard;", "uid", "card", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/RewardCard;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "claimCheatDayBonus", "", "userRepo", "Lcom/fitquest/rpg/core/data/repository/UserRepository;", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/RewardCard;Lcom/fitquest/rpg/core/data/repository/UserRepository;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCard", "incrementQuestProgress", "initializePredefinedCards", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAvailableCards", "Lkotlinx/coroutines/flow/Flow;", "", "redeemCard", "startCardsSync", "stopSync", "app_debug"})
public final class RewardCardRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.local.dao.RewardCardDao cardDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.SupabaseRepository supabaseRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope syncScope = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job cardsSyncJob;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastSyncedUid;
    
    @javax.inject.Inject()
    public RewardCardRepository(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.dao.RewardCardDao cardDao, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseRepository supabaseRepo, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final void startCardsSync(java.lang.String uid) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.domain.model.RewardCard>> observeAvailableCards(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initializePredefinedCards(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addCustomCard(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.RewardCard card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.domain.model.RewardCard> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object redeemCard(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.RewardCard card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
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
    public final java.lang.Object incrementQuestProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.RewardCard card, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.UserRepository userRepo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object claimCheatDayBonus(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.RewardCard card, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.UserRepository userRepo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void stopSync() {
    }
}