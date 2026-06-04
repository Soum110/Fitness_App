package com.fitquest.rpg.core.data.repository;

import android.content.Context;
import com.fitquest.rpg.core.data.local.dao.*;
import com.fitquest.rpg.core.data.local.entity.*;
import com.fitquest.rpg.core.data.remote.WgerApiService;
import com.fitquest.rpg.core.domain.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.Flow;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f0\u0019J\u0014\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u001bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/fitquest/rpg/core/data/repository/TaskRepository;", "", "taskDao", "Lcom/fitquest/rpg/core/data/local/dao/DailyTaskDao;", "wgerApi", "Lcom/fitquest/rpg/core/data/remote/WgerApiService;", "context", "Landroid/content/Context;", "(Lcom/fitquest/rpg/core/data/local/dao/DailyTaskDao;Lcom/fitquest/rpg/core/data/remote/WgerApiService;Landroid/content/Context;)V", "completeTask", "Lcom/fitquest/rpg/core/domain/model/DailyTask;", "taskId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchExercisesOrFallback", "", "Lcom/fitquest/rpg/core/data/repository/ExerciseData;", "profile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "(Lcom/fitquest/rpg/core/domain/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateDailyTasks", "", "getDietDescription", "", "observeTodaysTasks", "Lkotlinx/coroutines/flow/Flow;", "todayRange", "Lkotlin/Pair;", "app_debug"})
public final class TaskRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.local.dao.DailyTaskDao taskDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.WgerApiService wgerApi = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public TaskRepository(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.dao.DailyTaskDao taskDao, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.WgerApiService wgerApi, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.domain.model.DailyTask>> observeTodaysTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object completeTask(long taskId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.domain.model.DailyTask> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generateDailyTasks(@org.jetbrains.annotations.NotNull()
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
    
    private final kotlin.Pair<java.lang.Long, java.lang.Long> todayRange() {
        return null;
    }
}