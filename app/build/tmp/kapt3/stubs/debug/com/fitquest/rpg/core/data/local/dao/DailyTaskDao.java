package com.fitquest.rpg.core.data.local.dao;

import androidx.room.*;
import com.fitquest.rpg.core.data.local.entity.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J&\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00150\u00182\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\'J,\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015H\u0097@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/fitquest/rpg/core/data/local/dao/DailyTaskDao;", "", "countCompletedTasksForDay", "", "startOfDay", "", "endOfDay", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "countTotalTasksForDay", "deleteTasksForDay", "", "deleteUncompletedTasksForDayByType", "type", "", "(JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTask", "Lcom/fitquest/rpg/core/data/local/entity/DailyTaskEntity;", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "tasks", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeTasksForDay", "Lkotlinx/coroutines/flow/Flow;", "replaceTasksForDay", "(JJLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "task", "(Lcom/fitquest/rpg/core/data/local/entity/DailyTaskEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface DailyTaskDao {
    
    @androidx.room.Query(value = "SELECT * FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay ORDER BY isCompleted ASC, taskType ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.fitquest.rpg.core.data.local.entity.DailyTaskEntity>> observeTasksForDay(long startOfDay, long endOfDay);
    
    @androidx.room.Query(value = "SELECT * FROM daily_tasks WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTask(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.data.local.entity.DailyTaskEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.data.local.entity.DailyTaskEntity> tasks, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.entity.DailyTaskEntity task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTasksForDay(long startOfDay, long endOfDay, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay AND taskType = :type AND isCompleted = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteUncompletedTasksForDayByType(long startOfDay, long endOfDay, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Transaction()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object replaceTasksForDay(long startOfDay, long endOfDay, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.data.local.entity.DailyTaskEntity> tasks, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay AND isCompleted = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countCompletedTasksForDay(long startOfDay, long endOfDay, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countTotalTasksForDay(long startOfDay, long endOfDay, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        @androidx.room.Transaction()
        @org.jetbrains.annotations.Nullable()
        public static java.lang.Object replaceTasksForDay(@org.jetbrains.annotations.NotNull()
        com.fitquest.rpg.core.data.local.dao.DailyTaskDao $this, long startOfDay, long endOfDay, @org.jetbrains.annotations.NotNull()
        java.util.List<com.fitquest.rpg.core.data.local.entity.DailyTaskEntity> tasks, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
    }
}