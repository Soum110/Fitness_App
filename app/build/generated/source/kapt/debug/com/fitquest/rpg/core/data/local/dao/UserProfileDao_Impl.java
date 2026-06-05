package com.fitquest.rpg.core.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.fitquest.rpg.core.data.local.entity.UserProfileEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProfileDao_Impl implements UserProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProfileEntity> __insertionAdapterOfUserProfileEntity;

  public UserProfileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProfileEntity = new EntityInsertionAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_profile` (`id`,`name`,`age`,`gender`,`heightCm`,`weightKg`,`fitnessLevel`,`primaryGoal`,`dietaryStyle`,`workoutDaysPerWeek`,`wakeTimeHour`,`sleepTimeHour`,`onboardingComplete`,`joinDateMs`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindLong(3, entity.getAge());
        if (entity.getGender() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getGender());
        }
        statement.bindDouble(5, entity.getHeightCm());
        statement.bindDouble(6, entity.getWeightKg());
        if (entity.getFitnessLevel() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getFitnessLevel());
        }
        if (entity.getPrimaryGoal() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPrimaryGoal());
        }
        if (entity.getDietaryStyle() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getDietaryStyle());
        }
        statement.bindLong(10, entity.getWorkoutDaysPerWeek());
        statement.bindLong(11, entity.getWakeTimeHour());
        statement.bindLong(12, entity.getSleepTimeHour());
        final int _tmp = entity.getOnboardingComplete() ? 1 : 0;
        statement.bindLong(13, _tmp);
        statement.bindLong(14, entity.getJoinDateMs());
      }
    };
  }

  @Override
  public Object upsertProfile(final UserProfileEntity profile,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserProfileEntity.insert(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<UserProfileEntity> observeProfile() {
    final String _sql = "SELECT * FROM user_profile WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_profile"}, new Callable<UserProfileEntity>() {
      @Override
      @Nullable
      public UserProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfFitnessLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "fitnessLevel");
          final int _cursorIndexOfPrimaryGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryGoal");
          final int _cursorIndexOfDietaryStyle = CursorUtil.getColumnIndexOrThrow(_cursor, "dietaryStyle");
          final int _cursorIndexOfWorkoutDaysPerWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutDaysPerWeek");
          final int _cursorIndexOfWakeTimeHour = CursorUtil.getColumnIndexOrThrow(_cursor, "wakeTimeHour");
          final int _cursorIndexOfSleepTimeHour = CursorUtil.getColumnIndexOrThrow(_cursor, "sleepTimeHour");
          final int _cursorIndexOfOnboardingComplete = CursorUtil.getColumnIndexOrThrow(_cursor, "onboardingComplete");
          final int _cursorIndexOfJoinDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "joinDateMs");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final String _tmpFitnessLevel;
            if (_cursor.isNull(_cursorIndexOfFitnessLevel)) {
              _tmpFitnessLevel = null;
            } else {
              _tmpFitnessLevel = _cursor.getString(_cursorIndexOfFitnessLevel);
            }
            final String _tmpPrimaryGoal;
            if (_cursor.isNull(_cursorIndexOfPrimaryGoal)) {
              _tmpPrimaryGoal = null;
            } else {
              _tmpPrimaryGoal = _cursor.getString(_cursorIndexOfPrimaryGoal);
            }
            final String _tmpDietaryStyle;
            if (_cursor.isNull(_cursorIndexOfDietaryStyle)) {
              _tmpDietaryStyle = null;
            } else {
              _tmpDietaryStyle = _cursor.getString(_cursorIndexOfDietaryStyle);
            }
            final int _tmpWorkoutDaysPerWeek;
            _tmpWorkoutDaysPerWeek = _cursor.getInt(_cursorIndexOfWorkoutDaysPerWeek);
            final int _tmpWakeTimeHour;
            _tmpWakeTimeHour = _cursor.getInt(_cursorIndexOfWakeTimeHour);
            final int _tmpSleepTimeHour;
            _tmpSleepTimeHour = _cursor.getInt(_cursorIndexOfSleepTimeHour);
            final boolean _tmpOnboardingComplete;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfOnboardingComplete);
            _tmpOnboardingComplete = _tmp != 0;
            final long _tmpJoinDateMs;
            _tmpJoinDateMs = _cursor.getLong(_cursorIndexOfJoinDateMs);
            _result = new UserProfileEntity(_tmpId,_tmpName,_tmpAge,_tmpGender,_tmpHeightCm,_tmpWeightKg,_tmpFitnessLevel,_tmpPrimaryGoal,_tmpDietaryStyle,_tmpWorkoutDaysPerWeek,_tmpWakeTimeHour,_tmpSleepTimeHour,_tmpOnboardingComplete,_tmpJoinDateMs);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getProfile(final Continuation<? super UserProfileEntity> arg0) {
    final String _sql = "SELECT * FROM user_profile WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserProfileEntity>() {
      @Override
      @Nullable
      public UserProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfFitnessLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "fitnessLevel");
          final int _cursorIndexOfPrimaryGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryGoal");
          final int _cursorIndexOfDietaryStyle = CursorUtil.getColumnIndexOrThrow(_cursor, "dietaryStyle");
          final int _cursorIndexOfWorkoutDaysPerWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutDaysPerWeek");
          final int _cursorIndexOfWakeTimeHour = CursorUtil.getColumnIndexOrThrow(_cursor, "wakeTimeHour");
          final int _cursorIndexOfSleepTimeHour = CursorUtil.getColumnIndexOrThrow(_cursor, "sleepTimeHour");
          final int _cursorIndexOfOnboardingComplete = CursorUtil.getColumnIndexOrThrow(_cursor, "onboardingComplete");
          final int _cursorIndexOfJoinDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "joinDateMs");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final String _tmpFitnessLevel;
            if (_cursor.isNull(_cursorIndexOfFitnessLevel)) {
              _tmpFitnessLevel = null;
            } else {
              _tmpFitnessLevel = _cursor.getString(_cursorIndexOfFitnessLevel);
            }
            final String _tmpPrimaryGoal;
            if (_cursor.isNull(_cursorIndexOfPrimaryGoal)) {
              _tmpPrimaryGoal = null;
            } else {
              _tmpPrimaryGoal = _cursor.getString(_cursorIndexOfPrimaryGoal);
            }
            final String _tmpDietaryStyle;
            if (_cursor.isNull(_cursorIndexOfDietaryStyle)) {
              _tmpDietaryStyle = null;
            } else {
              _tmpDietaryStyle = _cursor.getString(_cursorIndexOfDietaryStyle);
            }
            final int _tmpWorkoutDaysPerWeek;
            _tmpWorkoutDaysPerWeek = _cursor.getInt(_cursorIndexOfWorkoutDaysPerWeek);
            final int _tmpWakeTimeHour;
            _tmpWakeTimeHour = _cursor.getInt(_cursorIndexOfWakeTimeHour);
            final int _tmpSleepTimeHour;
            _tmpSleepTimeHour = _cursor.getInt(_cursorIndexOfSleepTimeHour);
            final boolean _tmpOnboardingComplete;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfOnboardingComplete);
            _tmpOnboardingComplete = _tmp != 0;
            final long _tmpJoinDateMs;
            _tmpJoinDateMs = _cursor.getLong(_cursorIndexOfJoinDateMs);
            _result = new UserProfileEntity(_tmpId,_tmpName,_tmpAge,_tmpGender,_tmpHeightCm,_tmpWeightKg,_tmpFitnessLevel,_tmpPrimaryGoal,_tmpDietaryStyle,_tmpWorkoutDaysPerWeek,_tmpWakeTimeHour,_tmpSleepTimeHour,_tmpOnboardingComplete,_tmpJoinDateMs);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
