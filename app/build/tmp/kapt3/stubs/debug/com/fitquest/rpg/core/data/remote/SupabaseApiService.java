package com.fitquest.rpg.core.data.remote;

import com.google.gson.annotations.SerializedName;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\"\u0010\b\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\"\u0010\t\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J,\u0010\n\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u000b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\fJ\"\u0010\r\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\"\u0010\u000e\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u000f\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0010J(\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J(\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00122\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J(\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00122\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J(\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00122\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J2\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00122\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u001c\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\fJ\"\u0010\u001d\u001a\u00020\u001e2\b\b\u0001\u0010\u001f\u001a\u00020\u00052\b\b\u0001\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J\"\u0010#\u001a\u00020\u001e2\b\b\u0001\u0010\u001f\u001a\u00020\u00052\b\b\u0001\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J,\u0010$\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010%\u001a\u00020\u00052\b\b\u0001\u0010&\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\'J2\u0010(\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010%\u001a\u00020\u00052\u000e\b\u0001\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u00a7@\u00a2\u0006\u0002\u0010*J,\u0010+\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010%\u001a\u00020\u00052\b\b\u0001\u0010,\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010-J,\u0010.\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010%\u001a\u00020\u00052\b\b\u0001\u0010/\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u00100J,\u00101\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010%\u001a\u00020\u00052\b\b\u0001\u00102\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u00103J2\u00104\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010%\u001a\u00020\u00052\u000e\b\u0001\u00105\u001a\b\u0012\u0004\u0012\u00020\u00190\u0012H\u00a7@\u00a2\u0006\u0002\u0010*J,\u00106\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010%\u001a\u00020\u00052\b\b\u0001\u00107\u001a\u00020\u001bH\u00a7@\u00a2\u0006\u0002\u00108\u00a8\u00069"}, d2 = {"Lcom/fitquest/rpg/core/data/remote/SupabaseApiService;", "", "deleteAttributes", "", "token", "", "uidFilter", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteEconomy", "deleteProfile", "deleteRewardCard", "cardIdFilter", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRewardCards", "deleteTasks", "deleteUserAccount", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAttributes", "", "Lcom/fitquest/rpg/core/data/remote/SupabaseAttribute;", "getEconomy", "Lcom/fitquest/rpg/core/data/remote/SupabaseEconomy;", "getProfile", "Lcom/fitquest/rpg/core/data/remote/SupabaseProfile;", "getRewardCards", "Lcom/fitquest/rpg/core/data/remote/SupabaseRewardCardRow;", "getTasks", "Lcom/fitquest/rpg/core/data/remote/SupabaseTaskRow;", "dateFilter", "signIn", "Lcom/fitquest/rpg/core/data/remote/AuthResponse;", "apiKey", "body", "Lcom/fitquest/rpg/core/data/remote/AuthRequest;", "(Ljava/lang/String;Lcom/fitquest/rpg/core/data/remote/AuthRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signUp", "upsertAttribute", "prefer", "attribute", "(Ljava/lang/String;Ljava/lang/String;Lcom/fitquest/rpg/core/data/remote/SupabaseAttribute;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertAttributes", "attributes", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertEconomy", "economy", "(Ljava/lang/String;Ljava/lang/String;Lcom/fitquest/rpg/core/data/remote/SupabaseEconomy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertProfile", "profile", "(Ljava/lang/String;Ljava/lang/String;Lcom/fitquest/rpg/core/data/remote/SupabaseProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertRewardCard", "card", "(Ljava/lang/String;Ljava/lang/String;Lcom/fitquest/rpg/core/data/remote/SupabaseRewardCardRow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertRewardCards", "cards", "upsertTasks", "taskRow", "(Ljava/lang/String;Ljava/lang/String;Lcom/fitquest/rpg/core/data/remote/SupabaseTaskRow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface SupabaseApiService {
    
    @retrofit2.http.POST(value = "auth/v1/signup")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signUp(@retrofit2.http.Header(value = "apikey")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.AuthRequest body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.data.remote.AuthResponse> $completion);
    
    @retrofit2.http.POST(value = "auth/v1/token?grant_type=password")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signIn(@retrofit2.http.Header(value = "apikey")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.AuthRequest body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitquest.rpg.core.data.remote.AuthResponse> $completion);
    
    @retrofit2.http.GET(value = "rest/v1/profiles")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProfile(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.data.remote.SupabaseProfile>> $completion);
    
    @retrofit2.http.POST(value = "rest/v1/profiles")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertProfile(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Header(value = "Prefer")
    @org.jetbrains.annotations.NotNull()
    java.lang.String prefer, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "rest/v1/economies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEconomy(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.data.remote.SupabaseEconomy>> $completion);
    
    @retrofit2.http.POST(value = "rest/v1/economies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertEconomy(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Header(value = "Prefer")
    @org.jetbrains.annotations.NotNull()
    java.lang.String prefer, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseEconomy economy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "rest/v1/attributes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAttributes(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.data.remote.SupabaseAttribute>> $completion);
    
    @retrofit2.http.POST(value = "rest/v1/attributes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertAttribute(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Header(value = "Prefer")
    @org.jetbrains.annotations.NotNull()
    java.lang.String prefer, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseAttribute attribute, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.POST(value = "rest/v1/attributes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertAttributes(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Header(value = "Prefer")
    @org.jetbrains.annotations.NotNull()
    java.lang.String prefer, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.data.remote.SupabaseAttribute> attributes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "rest/v1/tasks")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTasks(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @retrofit2.http.Query(value = "date_key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String dateFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.data.remote.SupabaseTaskRow>> $completion);
    
    @retrofit2.http.POST(value = "rest/v1/tasks")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertTasks(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Header(value = "Prefer")
    @org.jetbrains.annotations.NotNull()
    java.lang.String prefer, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseTaskRow taskRow, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "rest/v1/reward_cards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRewardCards(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fitquest.rpg.core.data.remote.SupabaseRewardCardRow>> $completion);
    
    @retrofit2.http.POST(value = "rest/v1/reward_cards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertRewardCard(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Header(value = "Prefer")
    @org.jetbrains.annotations.NotNull()
    java.lang.String prefer, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseRewardCardRow card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.POST(value = "rest/v1/reward_cards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertRewardCards(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Header(value = "Prefer")
    @org.jetbrains.annotations.NotNull()
    java.lang.String prefer, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.data.remote.SupabaseRewardCardRow> cards, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.DELETE(value = "rest/v1/reward_cards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRewardCard(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @retrofit2.http.Query(value = "card_id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String cardIdFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.DELETE(value = "rest/v1/profiles")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteProfile(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.DELETE(value = "rest/v1/economies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteEconomy(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.DELETE(value = "rest/v1/attributes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAttributes(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.DELETE(value = "rest/v1/tasks")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTasks(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.DELETE(value = "rest/v1/reward_cards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRewardCards(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "uid")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uidFilter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.POST(value = "rest/v1/rpc/delete_user_account")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteUserAccount(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}