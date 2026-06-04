package com.fitquest.rpg.core.data.local.dao;

import androidx.room.*;
import com.fitquest.rpg.core.data.local.entity.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0006H\'J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/fitquest/rpg/core/data/local/dao/EconomyDao;", "", "getEconomy", "Lcom/fitquest/rpg/core/data/local/entity/EconomyEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeEconomy", "Lkotlinx/coroutines/flow/Flow;", "upsert", "", "economy", "(Lcom/fitquest/rpg/core/data/local/entity/EconomyEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface EconomyDao {
    
    @androidx.room.Query(value = "SELECT * FROM economy WHERE id = 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.fitquest.rpg.core.data.local.entity.EconomyEntity> observeEconomy();
    
    @androidx.room.Query(value = "SELECT * FROM economy WHERE id = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEconomy(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.data.local.entity.EconomyEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.entity.EconomyEntity economy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}