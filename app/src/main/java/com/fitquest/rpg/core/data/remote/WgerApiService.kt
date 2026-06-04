package com.fitquest.rpg.core.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Wger Workout Manager API — completely free, no API key required.
 * Base URL: https://wger.de/api/v2/
 */
interface WgerApiService {

    /**
     * Fetch exercises by muscle category and language (2 = English).
     * Category IDs:
     *  8 = Arms, 9 = Legs, 10 = Abs, 11 = Chest, 12 = Back, 13 = Shoulders, 14 = Calves
     */
    @GET("exercise/")
    suspend fun getExercisesByCategory(
        @Query("format") format: String = "json",
        @Query("language") language: Int = 2,
        @Query("category") categoryId: Int,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): WgerExerciseResponse

    @GET("exercise/")
    suspend fun getAllExercises(
        @Query("format") format: String = "json",
        @Query("language") language: Int = 2,
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int = 0
    ): WgerExerciseResponse

    @GET("exercisecategory/")
    suspend fun getCategories(
        @Query("format") format: String = "json"
    ): WgerCategoryResponse
}

data class WgerExerciseResponse(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<WgerExercise> = emptyList()
)

data class WgerExercise(
    val id: Int = 0,
    val uuid: String = "",
    val name: String = "",
    val description: String = "",
    val category: WgerCategory? = null,
    val muscles: List<WgerMuscle> = emptyList(),
    val equipment: List<WgerEquipment> = emptyList()
)

data class WgerCategory(val id: Int = 0, val name: String = "")
data class WgerMuscle(val id: Int = 0, val name: String = "", val name_en: String = "")
data class WgerEquipment(val id: Int = 0, val name: String = "")
data class WgerCategoryResponse(val count: Int = 0, val results: List<WgerCategory> = emptyList())
