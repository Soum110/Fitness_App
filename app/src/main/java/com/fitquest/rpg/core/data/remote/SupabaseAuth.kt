package com.fitquest.rpg.core.data.remote

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class SupabaseUser(
    val uid: String,
    val email: String
)

class SupabaseAuth(
    private val context: Context,
    private val apiService: SupabaseApiService,
    private val apiKey: String
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("supabase_auth_prefs", Context.MODE_PRIVATE)

    var currentUser: SupabaseUser? = null
        private set

    var token: String? = null
        private set

    init {
        val uid = prefs.getString("uid", null)
        val email = prefs.getString("email", null)
        val savedToken = prefs.getString("token", null)
        if (uid != null && email != null && savedToken != null) {
            currentUser = SupabaseUser(uid, email)
            token = savedToken
        }
    }

    fun getAuthHeader(): String {
        return token?.let { "Bearer $it" } ?: "Bearer $apiKey"
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String): SupabaseUser {
        return withContext(Dispatchers.IO) {
            val response = apiService.signIn(apiKey, AuthRequest(email.trim(), password))
            val user = SupabaseUser(response.user.id, response.user.email)
            currentUser = user
            token = response.accessToken
            prefs.edit().apply {
                putString("uid", user.uid)
                putString("email", user.email)
                putString("token", response.accessToken)
                apply()
            }
            user
        }
    }

    suspend fun createUserWithEmailAndPassword(email: String, password: String): SupabaseUser {
        return withContext(Dispatchers.IO) {
            val response = apiService.signUp(apiKey, AuthRequest(email.trim(), password))
            val user = SupabaseUser(response.user.id, response.user.email)
            currentUser = user
            token = response.accessToken
            prefs.edit().apply {
                putString("uid", user.uid)
                putString("email", user.email)
                putString("token", response.accessToken)
                apply()
            }
            user
        }
    }

    suspend fun reauthenticate(password: String) {
        // Re-verify credentials
        val email = currentUser?.email ?: throw Exception("No user logged in")
        signInWithEmailAndPassword(email, password)
    }

    fun signOut() {
        currentUser = null
        token = null
        prefs.edit().clear().apply()
    }
}
