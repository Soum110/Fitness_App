package com.fitquest.rpg.core.data.repository;

import android.content.Context;
import com.fitquest.rpg.core.data.local.dao.*;
import com.fitquest.rpg.core.data.local.entity.*;
import com.fitquest.rpg.core.data.remote.FirestoreRepository;
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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J&\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0018\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0015\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010!J\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0#2\u0006\u0010\u0015\u001a\u00020\u000eJ\u0016\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\'0#2\u0006\u0010\u0015\u001a\u00020\u000eJ\u0016\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0#2\u0006\u0010\u0015\u001a\u00020\u000eJ\u001e\u0010)\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010+J\u001e\u0010,\u001a\u00020-2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0010\u0010.\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0002J\u0016\u0010/\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/fitquest/rpg/core/data/repository/UserRepository;", "", "profileDao", "Lcom/fitquest/rpg/core/data/local/dao/UserProfileDao;", "attributeDao", "Lcom/fitquest/rpg/core/data/local/dao/AttributeDao;", "economyDao", "Lcom/fitquest/rpg/core/data/local/dao/EconomyDao;", "firestoreRepo", "Lcom/fitquest/rpg/core/data/remote/FirestoreRepository;", "context", "Landroid/content/Context;", "(Lcom/fitquest/rpg/core/data/local/dao/UserProfileDao;Lcom/fitquest/rpg/core/data/local/dao/AttributeDao;Lcom/fitquest/rpg/core/data/local/dao/EconomyDao;Lcom/fitquest/rpg/core/data/remote/FirestoreRepository;Landroid/content/Context;)V", "lastSyncedUid", "", "syncJob", "Lkotlinx/coroutines/Job;", "syncScope", "Lkotlinx/coroutines/CoroutineScope;", "addActionPoints", "", "uid", "amount", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addXpToAttribute", "type", "Lcom/fitquest/rpg/core/domain/model/AttributeType;", "xpAmount", "", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/AttributeType;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProfile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAttributes", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/fitquest/rpg/core/domain/model/Attribute;", "observeEconomy", "Lcom/fitquest/rpg/core/domain/model/Economy;", "observeProfile", "saveProfile", "profile", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "spendActionPoints", "", "startSync", "updateStreak", "app_debug"})
public final class UserRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.local.dao.UserProfileDao profileDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.local.dao.AttributeDao attributeDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.local.dao.EconomyDao economyDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.FirestoreRepository firestoreRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope syncScope = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job syncJob;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastSyncedUid;
    
    @javax.inject.Inject()
    public UserRepository(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.dao.UserProfileDao profileDao, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.dao.AttributeDao attributeDao, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.dao.EconomyDao economyDao, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.FirestoreRepository firestoreRepo, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final void startSync(java.lang.String uid) {
    }
    
    /**
     * Real-time stream from local Room cache — syncs Firestore in background.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.fitquest.rpg.core.domain.model.UserProfile> observeProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    /**
     * One-shot read — check Firestore first, then Room cache.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.domain.model.UserProfile> $completion) {
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
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.domain.model.Attribute>> observeAttributes(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addXpToAttribute(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.AttributeType type, long xpAmount, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.fitquest.rpg.core.domain.model.Economy> observeEconomy(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addActionPoints(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, int amount, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object spendActionPoints(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, int amount, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateStreak(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}