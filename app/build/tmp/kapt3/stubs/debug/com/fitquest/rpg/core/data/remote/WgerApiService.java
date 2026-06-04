package com.fitquest.rpg.core.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Wger Workout Manager API — completely free, no API key required.
 * Base URL: https://wger.de/api/v2/
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\t\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\b\b\u0003\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\rJ@\u0010\u000e\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u000f\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\t\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/fitquest/rpg/core/data/remote/WgerApiService;", "", "getAllExercises", "Lcom/fitquest/rpg/core/data/remote/WgerExerciseResponse;", "format", "", "language", "", "limit", "offset", "(Ljava/lang/String;IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategories", "Lcom/fitquest/rpg/core/data/remote/WgerCategoryResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExercisesByCategory", "categoryId", "(Ljava/lang/String;IIIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface WgerApiService {
    
    /**
     * Fetch exercises by muscle category and language (2 = English).
     * Category IDs:
     * 8 = Arms, 9 = Legs, 10 = Abs, 11 = Chest, 12 = Back, 13 = Shoulders, 14 = Calves
     */
    @retrofit2.http.GET(value = "exercise/")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExercisesByCategory(@retrofit2.http.Query(value = "format")
    @org.jetbrains.annotations.NotNull()
    java.lang.String format, @retrofit2.http.Query(value = "language")
    int language, @retrofit2.http.Query(value = "category")
    int categoryId, @retrofit2.http.Query(value = "limit")
    int limit, @retrofit2.http.Query(value = "offset")
    int offset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.data.remote.WgerExerciseResponse> $completion);
    
    @retrofit2.http.GET(value = "exercise/")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllExercises(@retrofit2.http.Query(value = "format")
    @org.jetbrains.annotations.NotNull()
    java.lang.String format, @retrofit2.http.Query(value = "language")
    int language, @retrofit2.http.Query(value = "limit")
    int limit, @retrofit2.http.Query(value = "offset")
    int offset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.data.remote.WgerExerciseResponse> $completion);
    
    @retrofit2.http.GET(value = "exercisecategory/")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategories(@retrofit2.http.Query(value = "format")
    @org.jetbrains.annotations.NotNull()
    java.lang.String format, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.data.remote.WgerCategoryResponse> $completion);
    
    /**
     * Wger Workout Manager API — completely free, no API key required.
     * Base URL: https://wger.de/api/v2/
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}