package com.fitquest.rpg.core.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.fitquest.rpg.core.data.local.dao.*;
import com.fitquest.rpg.core.data.local.entity.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\r"}, d2 = {"Lcom/fitquest/rpg/core/data/local/FitQuestDatabase;", "Landroidx/room/RoomDatabase;", "()V", "attributeDao", "Lcom/fitquest/rpg/core/data/local/dao/AttributeDao;", "dailyTaskDao", "Lcom/fitquest/rpg/core/data/local/dao/DailyTaskDao;", "economyDao", "Lcom/fitquest/rpg/core/data/local/dao/EconomyDao;", "rewardCardDao", "Lcom/fitquest/rpg/core/data/local/dao/RewardCardDao;", "userProfileDao", "Lcom/fitquest/rpg/core/data/local/dao/UserProfileDao;", "app_debug"})
@androidx.room.Database(entities = {com.fitquest.rpg.core.data.local.entity.UserProfileEntity.class, com.fitquest.rpg.core.data.local.entity.AttributeEntity.class, com.fitquest.rpg.core.data.local.entity.DailyTaskEntity.class, com.fitquest.rpg.core.data.local.entity.RewardCardEntity.class, com.fitquest.rpg.core.data.local.entity.EconomyEntity.class}, version = 2, exportSchema = false)
public abstract class FitQuestDatabase extends androidx.room.RoomDatabase {
    
    public FitQuestDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.fitquest.rpg.core.data.local.dao.UserProfileDao userProfileDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.fitquest.rpg.core.data.local.dao.AttributeDao attributeDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.fitquest.rpg.core.data.local.dao.DailyTaskDao dailyTaskDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.fitquest.rpg.core.data.local.dao.RewardCardDao rewardCardDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.fitquest.rpg.core.data.local.dao.EconomyDao economyDao();
}