package com.fitquest.rpg.core.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.fitquest.rpg.core.data.local.entity.DailyTaskEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DailyTaskDao_Impl implements DailyTaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyTaskEntity> __insertionAdapterOfDailyTaskEntity;

  private final EntityDeletionOrUpdateAdapter<DailyTaskEntity> __updateAdapterOfDailyTaskEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTasksForDay;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUncompletedTasksForDayByType;

  public DailyTaskDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyTaskEntity = new EntityInsertionAdapter<DailyTaskEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_tasks` (`id`,`title`,`description`,`taskType`,`targetAttribute`,`xpReward`,`apReward`,`sets`,`reps`,`durationMinutes`,`isCompleted`,`completedAtMs`,`dateMs`,`difficultyMultiplier`,`weight`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyTaskEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getTaskType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTaskType());
        }
        if (entity.getTargetAttribute() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTargetAttribute());
        }
        statement.bindLong(6, entity.getXpReward());
        statement.bindLong(7, entity.getApReward());
        if (entity.getSets() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getSets());
        }
        if (entity.getReps() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getReps());
        }
        if (entity.getDurationMinutes() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getDurationMinutes());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(11, _tmp);
        if (entity.getCompletedAtMs() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getCompletedAtMs());
        }
        statement.bindLong(13, entity.getDateMs());
        statement.bindDouble(14, entity.getDifficultyMultiplier());
        if (entity.getWeight() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getWeight());
        }
      }
    };
    this.__updateAdapterOfDailyTaskEntity = new EntityDeletionOrUpdateAdapter<DailyTaskEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `daily_tasks` SET `id` = ?,`title` = ?,`description` = ?,`taskType` = ?,`targetAttribute` = ?,`xpReward` = ?,`apReward` = ?,`sets` = ?,`reps` = ?,`durationMinutes` = ?,`isCompleted` = ?,`completedAtMs` = ?,`dateMs` = ?,`difficultyMultiplier` = ?,`weight` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyTaskEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getTaskType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTaskType());
        }
        if (entity.getTargetAttribute() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTargetAttribute());
        }
        statement.bindLong(6, entity.getXpReward());
        statement.bindLong(7, entity.getApReward());
        if (entity.getSets() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getSets());
        }
        if (entity.getReps() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getReps());
        }
        if (entity.getDurationMinutes() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getDurationMinutes());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(11, _tmp);
        if (entity.getCompletedAtMs() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getCompletedAtMs());
        }
        statement.bindLong(13, entity.getDateMs());
        statement.bindDouble(14, entity.getDifficultyMultiplier());
        if (entity.getWeight() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getWeight());
        }
        statement.bindLong(16, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteTasksForDay = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM daily_tasks WHERE dateMs >= ? AND dateMs < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteUncompletedTasksForDayByType = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM daily_tasks WHERE dateMs >= ? AND dateMs < ? AND taskType = ? AND isCompleted = 0";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<DailyTaskEntity> tasks,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDailyTaskEntity.insert(tasks);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final DailyTaskEntity task, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDailyTaskEntity.handle(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object replaceTasksForDay(final long startOfDay, final long endOfDay,
      final List<DailyTaskEntity> tasks, final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> DailyTaskDao.DefaultImpls.replaceTasksForDay(DailyTaskDao_Impl.this, startOfDay, endOfDay, tasks, __cont), $completion);
  }

  @Override
  public Object deleteTasksForDay(final long startOfDay, final long endOfDay,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTasksForDay.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, startOfDay);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, endOfDay);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteTasksForDay.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUncompletedTasksForDayByType(final long startOfDay, final long endOfDay,
      final String type, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUncompletedTasksForDayByType.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, startOfDay);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, endOfDay);
        _argIndex = 3;
        if (type == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, type);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteUncompletedTasksForDayByType.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DailyTaskEntity>> observeTasksForDay(final long startOfDay,
      final long endOfDay) {
    final String _sql = "SELECT * FROM daily_tasks WHERE dateMs >= ? AND dateMs < ? ORDER BY isCompleted ASC, taskType ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_tasks"}, new Callable<List<DailyTaskEntity>>() {
      @Override
      @NonNull
      public List<DailyTaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfTargetAttribute = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAttribute");
          final int _cursorIndexOfXpReward = CursorUtil.getColumnIndexOrThrow(_cursor, "xpReward");
          final int _cursorIndexOfApReward = CursorUtil.getColumnIndexOrThrow(_cursor, "apReward");
          final int _cursorIndexOfSets = CursorUtil.getColumnIndexOrThrow(_cursor, "sets");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final int _cursorIndexOfDurationMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMinutes");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtMs");
          final int _cursorIndexOfDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "dateMs");
          final int _cursorIndexOfDifficultyMultiplier = CursorUtil.getColumnIndexOrThrow(_cursor, "difficultyMultiplier");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final List<DailyTaskEntity> _result = new ArrayList<DailyTaskEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyTaskEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final String _tmpTargetAttribute;
            if (_cursor.isNull(_cursorIndexOfTargetAttribute)) {
              _tmpTargetAttribute = null;
            } else {
              _tmpTargetAttribute = _cursor.getString(_cursorIndexOfTargetAttribute);
            }
            final long _tmpXpReward;
            _tmpXpReward = _cursor.getLong(_cursorIndexOfXpReward);
            final int _tmpApReward;
            _tmpApReward = _cursor.getInt(_cursorIndexOfApReward);
            final Integer _tmpSets;
            if (_cursor.isNull(_cursorIndexOfSets)) {
              _tmpSets = null;
            } else {
              _tmpSets = _cursor.getInt(_cursorIndexOfSets);
            }
            final Integer _tmpReps;
            if (_cursor.isNull(_cursorIndexOfReps)) {
              _tmpReps = null;
            } else {
              _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            }
            final Integer _tmpDurationMinutes;
            if (_cursor.isNull(_cursorIndexOfDurationMinutes)) {
              _tmpDurationMinutes = null;
            } else {
              _tmpDurationMinutes = _cursor.getInt(_cursorIndexOfDurationMinutes);
            }
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final Long _tmpCompletedAtMs;
            if (_cursor.isNull(_cursorIndexOfCompletedAtMs)) {
              _tmpCompletedAtMs = null;
            } else {
              _tmpCompletedAtMs = _cursor.getLong(_cursorIndexOfCompletedAtMs);
            }
            final long _tmpDateMs;
            _tmpDateMs = _cursor.getLong(_cursorIndexOfDateMs);
            final float _tmpDifficultyMultiplier;
            _tmpDifficultyMultiplier = _cursor.getFloat(_cursorIndexOfDifficultyMultiplier);
            final String _tmpWeight;
            if (_cursor.isNull(_cursorIndexOfWeight)) {
              _tmpWeight = null;
            } else {
              _tmpWeight = _cursor.getString(_cursorIndexOfWeight);
            }
            _item = new DailyTaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpTaskType,_tmpTargetAttribute,_tmpXpReward,_tmpApReward,_tmpSets,_tmpReps,_tmpDurationMinutes,_tmpIsCompleted,_tmpCompletedAtMs,_tmpDateMs,_tmpDifficultyMultiplier,_tmpWeight);
            _result.add(_item);
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
  public Object getTask(final long id, final Continuation<? super DailyTaskEntity> $completion) {
    final String _sql = "SELECT * FROM daily_tasks WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyTaskEntity>() {
      @Override
      @Nullable
      public DailyTaskEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfTargetAttribute = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAttribute");
          final int _cursorIndexOfXpReward = CursorUtil.getColumnIndexOrThrow(_cursor, "xpReward");
          final int _cursorIndexOfApReward = CursorUtil.getColumnIndexOrThrow(_cursor, "apReward");
          final int _cursorIndexOfSets = CursorUtil.getColumnIndexOrThrow(_cursor, "sets");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final int _cursorIndexOfDurationMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMinutes");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtMs");
          final int _cursorIndexOfDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "dateMs");
          final int _cursorIndexOfDifficultyMultiplier = CursorUtil.getColumnIndexOrThrow(_cursor, "difficultyMultiplier");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final DailyTaskEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final String _tmpTargetAttribute;
            if (_cursor.isNull(_cursorIndexOfTargetAttribute)) {
              _tmpTargetAttribute = null;
            } else {
              _tmpTargetAttribute = _cursor.getString(_cursorIndexOfTargetAttribute);
            }
            final long _tmpXpReward;
            _tmpXpReward = _cursor.getLong(_cursorIndexOfXpReward);
            final int _tmpApReward;
            _tmpApReward = _cursor.getInt(_cursorIndexOfApReward);
            final Integer _tmpSets;
            if (_cursor.isNull(_cursorIndexOfSets)) {
              _tmpSets = null;
            } else {
              _tmpSets = _cursor.getInt(_cursorIndexOfSets);
            }
            final Integer _tmpReps;
            if (_cursor.isNull(_cursorIndexOfReps)) {
              _tmpReps = null;
            } else {
              _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            }
            final Integer _tmpDurationMinutes;
            if (_cursor.isNull(_cursorIndexOfDurationMinutes)) {
              _tmpDurationMinutes = null;
            } else {
              _tmpDurationMinutes = _cursor.getInt(_cursorIndexOfDurationMinutes);
            }
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final Long _tmpCompletedAtMs;
            if (_cursor.isNull(_cursorIndexOfCompletedAtMs)) {
              _tmpCompletedAtMs = null;
            } else {
              _tmpCompletedAtMs = _cursor.getLong(_cursorIndexOfCompletedAtMs);
            }
            final long _tmpDateMs;
            _tmpDateMs = _cursor.getLong(_cursorIndexOfDateMs);
            final float _tmpDifficultyMultiplier;
            _tmpDifficultyMultiplier = _cursor.getFloat(_cursorIndexOfDifficultyMultiplier);
            final String _tmpWeight;
            if (_cursor.isNull(_cursorIndexOfWeight)) {
              _tmpWeight = null;
            } else {
              _tmpWeight = _cursor.getString(_cursorIndexOfWeight);
            }
            _result = new DailyTaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpTaskType,_tmpTargetAttribute,_tmpXpReward,_tmpApReward,_tmpSets,_tmpReps,_tmpDurationMinutes,_tmpIsCompleted,_tmpCompletedAtMs,_tmpDateMs,_tmpDifficultyMultiplier,_tmpWeight);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object countCompletedTasksForDay(final long startOfDay, final long endOfDay,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM daily_tasks WHERE dateMs >= ? AND dateMs < ? AND isCompleted = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object countTotalTasksForDay(final long startOfDay, final long endOfDay,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM daily_tasks WHERE dateMs >= ? AND dateMs < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
