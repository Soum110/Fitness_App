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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ \u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0082@\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\u00020 2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0014\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170$H\u0002J\u001a\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001a0&2\u0006\u0010\u0015\u001a\u00020\u000eJ\u0010\u0010\'\u001a\u00020 2\u0006\u0010\u0015\u001a\u00020\u000eH\u0002J\u0006\u0010(\u001a\u00020 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/fitquest/rpg/core/data/repository/TaskRepository;", "", "taskDao", "Lcom/fitquest/rpg/core/data/local/dao/DailyTaskDao;", "attributeDao", "Lcom/fitquest/rpg/core/data/local/dao/AttributeDao;", "firestoreRepo", "Lcom/fitquest/rpg/core/data/remote/FirestoreRepository;", "wgerApi", "Lcom/fitquest/rpg/core/data/remote/WgerApiService;", "context", "Landroid/content/Context;", "(Lcom/fitquest/rpg/core/data/local/dao/DailyTaskDao;Lcom/fitquest/rpg/core/data/local/dao/AttributeDao;Lcom/fitquest/rpg/core/data/remote/FirestoreRepository;Lcom/fitquest/rpg/core/data/remote/WgerApiService;Landroid/content/Context;)V", "lastSyncedUid", "", "syncScope", "Lkotlinx/coroutines/CoroutineScope;", "tasksSyncJob", "Lkotlinx/coroutines/Job;", "completeTask", "Lcom/fitquest/rpg/core/domain/model/DailyTask;", "uid", "taskId", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchExercisesOrFallback", "", "Lcom/fitquest/rpg/core/data/repository/ExerciseData;", "profile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "(Lcom/fitquest/rpg/core/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateDailyTasks", "", "(Ljava/lang/String;Lcom/fitquest/rpg/core/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDietDescription", "getTodayRange", "Lkotlin/Pair;", "observeTodaysTasks", "Lkotlinx/coroutines/flow/Flow;", "startTasksSync", "stopSync", "app_debug"})
public final class TaskRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.local.dao.DailyTaskDao taskDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.local.dao.AttributeDao attributeDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.FirestoreRepository firestoreRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.WgerApiService wgerApi = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope syncScope = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job tasksSyncJob;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastSyncedUid;
    
    @javax.inject.Inject()
    public TaskRepository(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.dao.DailyTaskDao taskDao, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.dao.AttributeDao attributeDao, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.FirestoreRepository firestoreRepo, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.WgerApiService wgerApi, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final kotlin.Pair<java.lang.Long, java.lang.Long> getTodayRange() {
        return null;
    }
    
    private final void startTasksSync(java.lang.String uid) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.domain.model.DailyTask>> observeTodaysTasks(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object completeTask(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, long taskId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.domain.model.DailyTask> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generateDailyTasks(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object fetchExercisesOrFallback(com.fitquest.rpg.core.domain.model.UserProfile profile, kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.data.repository.ExerciseData>> $completion) {
        return null;
    }
    
    private final java.lang.String getDietDescription(com.fitquest.rpg.core.domain.model.UserProfile profile) {
        return null;
    }
    
    public final void stopSync() {
    }
}