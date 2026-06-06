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
import com.fitquest.rpg.core.data.local.entity.EconomyEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
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
public final class EconomyDao_Impl implements EconomyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EconomyEntity> __insertionAdapterOfEconomyEntity;

  public EconomyDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEconomyEntity = new EntityInsertionAdapter<EconomyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `economy` (`id`,`totalActionPoints`,`availableActionPoints`,`totalSpent`,`currentStreak`,`longestStreak`,`lastCompletionDateMs`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EconomyEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTotalActionPoints());
        statement.bindLong(3, entity.getAvailableActionPoints());
        statement.bindLong(4, entity.getTotalSpent());
        statement.bindLong(5, entity.getCurrentStreak());
        statement.bindLong(6, entity.getLongestStreak());
        if (entity.getLastCompletionDateMs() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getLastCompletionDateMs());
        }
      }
    };
  }

  @Override
  public Object upsert(final EconomyEntity economy, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEconomyEntity.insert(economy);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<EconomyEntity> observeEconomy() {
    final String _sql = "SELECT * FROM economy WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"economy"}, new Callable<EconomyEntity>() {
      @Override
      @Nullable
      public EconomyEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTotalActionPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "totalActionPoints");
          final int _cursorIndexOfAvailableActionPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "availableActionPoints");
          final int _cursorIndexOfTotalSpent = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSpent");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfLongestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "longestStreak");
          final int _cursorIndexOfLastCompletionDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCompletionDateMs");
          final EconomyEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpTotalActionPoints;
            _tmpTotalActionPoints = _cursor.getInt(_cursorIndexOfTotalActionPoints);
            final int _tmpAvailableActionPoints;
            _tmpAvailableActionPoints = _cursor.getInt(_cursorIndexOfAvailableActionPoints);
            final int _tmpTotalSpent;
            _tmpTotalSpent = _cursor.getInt(_cursorIndexOfTotalSpent);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final int _tmpLongestStreak;
            _tmpLongestStreak = _cursor.getInt(_cursorIndexOfLongestStreak);
            final Long _tmpLastCompletionDateMs;
            if (_cursor.isNull(_cursorIndexOfLastCompletionDateMs)) {
              _tmpLastCompletionDateMs = null;
            } else {
              _tmpLastCompletionDateMs = _cursor.getLong(_cursorIndexOfLastCompletionDateMs);
            }
            _result = new EconomyEntity(_tmpId,_tmpTotalActionPoints,_tmpAvailableActionPoints,_tmpTotalSpent,_tmpCurrentStreak,_tmpLongestStreak,_tmpLastCompletionDateMs);
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
  public Object getEconomy(final Continuation<? super EconomyEntity> $completion) {
    final String _sql = "SELECT * FROM economy WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EconomyEntity>() {
      @Override
      @Nullable
      public EconomyEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTotalActionPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "totalActionPoints");
          final int _cursorIndexOfAvailableActionPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "availableActionPoints");
          final int _cursorIndexOfTotalSpent = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSpent");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfLongestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "longestStreak");
          final int _cursorIndexOfLastCompletionDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCompletionDateMs");
          final EconomyEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpTotalActionPoints;
            _tmpTotalActionPoints = _cursor.getInt(_cursorIndexOfTotalActionPoints);
            final int _tmpAvailableActionPoints;
            _tmpAvailableActionPoints = _cursor.getInt(_cursorIndexOfAvailableActionPoints);
            final int _tmpTotalSpent;
            _tmpTotalSpent = _cursor.getInt(_cursorIndexOfTotalSpent);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final int _tmpLongestStreak;
            _tmpLongestStreak = _cursor.getInt(_cursorIndexOfLongestStreak);
            final Long _tmpLastCompletionDateMs;
            if (_cursor.isNull(_cursorIndexOfLastCompletionDateMs)) {
              _tmpLastCompletionDateMs = null;
            } else {
              _tmpLastCompletionDateMs = _cursor.getLong(_cursorIndexOfLastCompletionDateMs);
            }
            _result = new EconomyEntity(_tmpId,_tmpTotalActionPoints,_tmpAvailableActionPoints,_tmpTotalSpent,_tmpCurrentStreak,_tmpLongestStreak,_tmpLastCompletionDateMs);
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
