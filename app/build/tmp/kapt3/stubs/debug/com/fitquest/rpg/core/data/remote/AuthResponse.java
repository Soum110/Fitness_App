package com.fitquest.rpg.core.data.remote;

import com.google.gson.annotations.SerializedName;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lcom/fitquest/rpg/core/data/remote/AuthResponse;", "", "accessToken", "", "tokenType", "expiresIn", "", "user", "Lcom/fitquest/rpg/core/data/remote/AuthUser;", "(Ljava/lang/String;Ljava/lang/String;JLcom/fitquest/rpg/core/data/remote/AuthUser;)V", "getAccessToken", "()Ljava/lang/String;", "getExpiresIn", "()J", "getTokenType", "getUser", "()Lcom/fitquest/rpg/core/data/remote/AuthUser;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class AuthResponse {
    @com.google.gson.annotations.SerializedName(value = "access_token")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String accessToken = null;
    @com.google.gson.annotations.SerializedName(value = "token_type")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tokenType = null;
    @com.google.gson.annotations.SerializedName(value = "expires_in")
    private final long expiresIn = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.AuthUser user = null;
    
    public AuthResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull()
    java.lang.String tokenType, long expiresIn, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.AuthUser user) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccessToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTokenType() {
        return null;
    }
    
    public final long getExpiresIn() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.remote.AuthUser getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.remote.AuthUser component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.remote.AuthResponse copy(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull()
    java.lang.String tokenType, long expiresIn, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.AuthUser user) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}