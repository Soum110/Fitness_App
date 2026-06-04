package com.fitquest.rpg.core.data.local.dao

import androidx.room.*
import com.fitquest.rpg.core.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun observeProfile(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getProfile(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProfile(profile: UserProfileEntity)
}

@Dao
interface AttributeDao {
    @Query("SELECT * FROM attributes")
    fun observeAll(): Flow<List<AttributeEntity>>

    @Query("SELECT * FROM attributes WHERE type = :type")
    suspend fun getAttribute(type: String): AttributeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(attribute: AttributeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(attributes: List<AttributeEntity>)
}

@Dao
interface DailyTaskDao {
    @Query("SELECT * FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay ORDER BY isCompleted ASC, taskType ASC")
    fun observeTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<DailyTaskEntity>>

    @Query("SELECT * FROM daily_tasks WHERE id = :id")
    suspend fun getTask(id: Long): DailyTaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<DailyTaskEntity>)

    @Update
    suspend fun update(task: DailyTaskEntity)

    @Query("DELETE FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay")
    suspend fun deleteTasksForDay(startOfDay: Long, endOfDay: Long)

    @Query("SELECT COUNT(*) FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay AND isCompleted = 1")
    suspend fun countCompletedTasksForDay(startOfDay: Long, endOfDay: Long): Int

    @Query("SELECT COUNT(*) FROM daily_tasks WHERE dateMs >= :startOfDay AND dateMs < :endOfDay")
    suspend fun countTotalTasksForDay(startOfDay: Long, endOfDay: Long): Int
}

@Dao
interface RewardCardDao {
    @Query("SELECT * FROM reward_cards WHERE isRedeemed = 0 ORDER BY apCost ASC")
    fun observeAvailableCards(): Flow<List<RewardCardEntity>>

    @Query("SELECT * FROM reward_cards ORDER BY isRedeemed ASC, apCost ASC")
    fun observeAllCards(): Flow<List<RewardCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cards: List<RewardCardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: RewardCardEntity): Long

    @Update
    suspend fun update(card: RewardCardEntity)

    @Delete
    suspend fun delete(card: RewardCardEntity)

    @Query("SELECT COUNT(*) FROM reward_cards WHERE isPredefined = 1")
    suspend fun countPredefinedCards(): Int
}

@Dao
interface EconomyDao {
    @Query("SELECT * FROM economy WHERE id = 1")
    fun observeEconomy(): Flow<EconomyEntity?>

    @Query("SELECT * FROM economy WHERE id = 1")
    suspend fun getEconomy(): EconomyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(economy: EconomyEntity)
}
