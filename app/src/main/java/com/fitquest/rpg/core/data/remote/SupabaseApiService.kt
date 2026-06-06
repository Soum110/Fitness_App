package com.fitquest.rpg.core.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.*

interface SupabaseApiService {

    // ── Auth API ────────────────────────────────────────────────────────────
    @POST("auth/v1/signup")
    suspend fun signUp(
        @Header("apikey") apiKey: String,
        @Body body: AuthRequest
    ): AuthResponse

    @POST("auth/v1/token?grant_type=password")
    suspend fun signIn(
        @Header("apikey") apiKey: String,
        @Body body: AuthRequest
    ): AuthResponse

    // ── Profiles ────────────────────────────────────────────────────────────
    @GET("rest/v1/profiles")
    suspend fun getProfile(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    ): List<SupabaseProfile>

    @POST("rest/v1/profiles")
    suspend fun upsertProfile(
        @Header("Authorization") token: String,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body profile: SupabaseProfile
    )

    // ── Economy ─────────────────────────────────────────────────────────────
    @GET("rest/v1/economies")
    suspend fun getEconomy(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    ): List<SupabaseEconomy>

    @POST("rest/v1/economies")
    suspend fun upsertEconomy(
        @Header("Authorization") token: String,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body economy: SupabaseEconomy
    )

    // ── Attributes ──────────────────────────────────────────────────────────
    @GET("rest/v1/attributes")
    suspend fun getAttributes(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    ): List<SupabaseAttribute>

    @POST("rest/v1/attributes")
    suspend fun upsertAttribute(
        @Header("Authorization") token: String,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body attribute: SupabaseAttribute
    )

    @POST("rest/v1/attributes")
    suspend fun upsertAttributes(
        @Header("Authorization") token: String,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body attributes: List<SupabaseAttribute>
    )

    // ── Tasks ───────────────────────────────────────────────────────────────
    @GET("rest/v1/tasks")
    suspend fun getTasks(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String,
        @Query("date_key") dateFilter: String
    ): List<SupabaseTaskRow>

    @POST("rest/v1/tasks")
    suspend fun upsertTasks(
        @Header("Authorization") token: String,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body taskRow: SupabaseTaskRow
    )

    // ── Reward Cards ────────────────────────────────────────────────────────
    @GET("rest/v1/reward_cards")
    suspend fun getRewardCards(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    ): List<SupabaseRewardCardRow>

    @POST("rest/v1/reward_cards")
    suspend fun upsertRewardCard(
        @Header("Authorization") token: String,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body card: SupabaseRewardCardRow
    )

    @POST("rest/v1/reward_cards")
    suspend fun upsertRewardCards(
        @Header("Authorization") token: String,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body cards: List<SupabaseRewardCardRow>
    )

    @DELETE("rest/v1/reward_cards")
    suspend fun deleteRewardCard(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String,
        @Query("card_id") cardIdFilter: String
    )

    // ── Account Deletion ────────────────────────────────────────────────────
    @DELETE("rest/v1/profiles")
    suspend fun deleteProfile(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    )

    @DELETE("rest/v1/economies")
    suspend fun deleteEconomy(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    )

    @DELETE("rest/v1/attributes")
    suspend fun deleteAttributes(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    )

    @DELETE("rest/v1/tasks")
    suspend fun deleteTasks(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    )

    @DELETE("rest/v1/reward_cards")
    suspend fun deleteRewardCards(
        @Header("Authorization") token: String,
        @Query("uid") uidFilter: String
    )

    @POST("rest/v1/rpc/delete_user_account")
    suspend fun deleteUserAccount(
        @Header("Authorization") token: String
    )
}

// ── Auth DTOs ───────────────────────────────────────────────────────────────
data class AuthRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Long,
    val user: AuthUser
)

data class AuthUser(
    val id: String,
    val email: String
)

// ── Database DTOs ───────────────────────────────────────────────────────────
data class SupabaseProfile(
    val uid: String,
    val name: String,
    val age: Int,
    @SerializedName("gender") val gender: String,
    @SerializedName("height_cm") val heightCm: Float,
    @SerializedName("weight_kg") val weightKg: Float,
    @SerializedName("fitness_level") val fitnessLevel: String,
    @SerializedName("primary_goal") val primaryGoal: String,
    @SerializedName("dietary_style") val dietaryStyle: String,
    @SerializedName("workout_days_per_week") val workoutDaysPerWeek: Int,
    @SerializedName("wake_time_hour") val wakeTimeHour: Int,
    @SerializedName("sleep_time_hour") val sleepTimeHour: Int,
    @SerializedName("onboarding_complete") val onboardingComplete: Boolean,
    @SerializedName("join_date_ms") val joinDateMs: Long
)

data class SupabaseEconomy(
    val uid: String,
    @SerializedName("total_action_points") val totalActionPoints: Int,
    @SerializedName("available_action_points") val availableActionPoints: Int,
    @SerializedName("total_spent") val totalSpent: Int,
    @SerializedName("current_streak") val currentStreak: Int,
    @SerializedName("longest_streak") val longestStreak: Int,
    @SerializedName("last_completion_date_ms") val lastCompletionDateMs: Long?
)

data class SupabaseAttribute(
    val uid: String,
    val type: String,
    val level: Int,
    @SerializedName("current_xp") val currentXp: Long,
    @SerializedName("total_xp_earned") val totalXpEarned: Long
)

data class SupabaseTaskRow(
    val uid: String,
    @SerializedName("date_key") val dateKey: String,
    @SerializedName("tasks_json") val tasksJson: String
)

data class SupabaseRewardCardRow(
    val uid: String,
    @SerializedName("card_id") val cardId: String,
    @SerializedName("card_json") val cardJson: String
)
