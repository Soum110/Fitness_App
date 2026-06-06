package com.fitquest.rpg.core.data.remote;

import com.fitquest.rpg.core.domain.model.*;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import kotlinx.coroutines.flow.Flow;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * All Firestore documents are scoped under users/{uid}/...
 * This ensures each user's data is completely isolated.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00180\u001b2\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001b2\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001b2\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00180\u001b2\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u001b2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001e\u0010\"\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010$J\u001e\u0010%\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\'J\u001e\u0010(\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010*J$\u0010+\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\r0\u0018H\u0086@\u00a2\u0006\u0002\u0010-J$\u0010.\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0086@\u00a2\u0006\u0002\u0010-J\u0010\u00100\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u00101\u001a\n 2*\u0004\u0018\u00010\b0\bH\u0002J\u0010\u00103\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00064"}, d2 = {"Lcom/fitquest/rpg/core/data/remote/FirestoreRepository;", "", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "attributesCol", "Lcom/google/firebase/firestore/CollectionReference;", "uid", "", "cardsCol", "deleteCard", "", "card", "Lcom/fitquest/rpg/core/domain/model/RewardCard;", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/RewardCard;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteUserData", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "economyDoc", "Lcom/google/firebase/firestore/DocumentReference;", "getEconomy", "Lcom/fitquest/rpg/core/domain/model/Economy;", "getProfile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "getTodaysTasks", "", "Lcom/fitquest/rpg/core/domain/model/DailyTask;", "observeAttributes", "Lkotlinx/coroutines/flow/Flow;", "Lcom/fitquest/rpg/core/domain/model/Attribute;", "observeEconomy", "observeProfile", "observeRewardCards", "observeTodaysTasks", "profileDoc", "saveAttribute", "attribute", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/Attribute;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveEconomy", "economy", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/Economy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveProfile", "profile", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveRewardCards", "cards", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveTasks", "tasks", "tasksCol", "todayKey", "kotlin.jvm.PlatformType", "userDoc", "app_debug"})
public final class FirestoreRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    
    @javax.inject.Inject()
    public FirestoreRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore db) {
        super();
    }
    
    private final com.google.firebase.firestore.DocumentReference userDoc(java.lang.String uid) {
        return null;
    }
    
    private final com.google.firebase.firestore.DocumentReference profileDoc(java.lang.String uid) {
        return null;
    }
    
    private final com.google.firebase.firestore.DocumentReference economyDoc(java.lang.String uid) {
        return null;
    }
    
    private final com.google.firebase.firestore.CollectionReference attributesCol(java.lang.String uid) {
        return null;
    }
    
    private final com.google.firebase.firestore.CollectionReference tasksCol(java.lang.String uid) {
        return null;
    }
    
    private final com.google.firebase.firestore.CollectionReference cardsCol(java.lang.String uid) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.domain.model.Attribute>> observeAttributes(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
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