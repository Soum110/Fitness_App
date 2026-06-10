package com.fitquest.rpg.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fitquest.rpg.core.data.local.dao.*
import com.fitquest.rpg.core.data.local.entity.*

@Database(
    entities = [
        UserProfileEntity::class,
        AttributeEntity::class,
        DailyTaskEntity::class,
        RewardCardEntity::class,
        EconomyEntity::class
    ],
    version = 6,
    exportSchema = false
)
abstract class FitQuestDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun attributeDao(): AttributeDao
    abstract fun dailyTaskDao(): DailyTaskDao
    abstract fun rewardCardDao(): RewardCardDao
    abstract fun economyDao(): EconomyDao
}
