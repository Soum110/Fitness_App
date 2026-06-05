package com.fitquest.rpg.core.data.repository;

import android.content.Context;
import com.fitquest.rpg.core.data.local.dao.*;
import com.fitquest.rpg.core.data.local.entity.*;
import com.fitquest.rpg.core.data.remote.FirestoreRepository;
import com.fitquest.rpg.core.data.remote.WgerApiService;
import com.fitquest.rpg.core.domain.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.*;
import kotlinx.coroutines.flow.*;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0002\u00a8\u0006\b"}, d2 = {"loadBundledExercises", "", "Lcom/fitquest/rpg/core/data/repository/ExerciseData;", "context", "Landroid/content/Context;", "toEntity", "Lcom/fitquest/rpg/core/data/local/entity/EconomyEntity;", "Lcom/fitquest/rpg/core/domain/model/Economy;", "app_debug"})
public final class RepositoriesKt {
    
    private static final com.fitquest.rpg.core.data.local.entity.EconomyEntity toEntity(com.fitquest.rpg.core.domain.model.Economy $this$toEntity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.fitquest.rpg.core.data.repository.ExerciseData> loadBundledExercises(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}