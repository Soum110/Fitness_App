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
import com.fitquest.rpg.core.data.local.entity.AttributeEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class AttributeDao_Impl implements AttributeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AttributeEntity> __insertionAdapterOfAttributeEntity;

  public AttributeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAttributeEntity = new EntityInsertionAdapter<AttributeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `attributes` (`type`,`level`,`currentXp`,`totalXpEarned`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AttributeEntity entity) {
        if (entity.getType() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getType());
        }
        statement.bindLong(2, entity.getLevel());
        statement.bindLong(3, entity.getCurrentXp());
        statement.bindLong(4, entity.getTotalXpEarned());
      }
    };
  }

  @Override
  public Object upsert(final AttributeEntity attribute,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAttributeEntity.insert(attribute);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertAll(final List<AttributeEntity> attributes,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAttributeEntity.insert(attributes);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AttributeEntity>> observeAll() {
    final String _sql = "SELECT * FROM attributes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"attributes"}, new Callable<List<AttributeEntity>>() {
      @Override
      @NonNull
      public List<AttributeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfCurrentXp = CursorUtil.getColumnIndexOrThrow(_cursor, "currentXp");
          final int _cursorIndexOfTotalXpEarned = CursorUtil.getColumnIndexOrThrow(_cursor, "totalXpEarned");
          final List<AttributeEntity> _result = new ArrayList<AttributeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AttributeEntity _item;
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final long _tmpCurrentXp;
            _tmpCurrentXp = _cursor.getLong(_cursorIndexOfCurrentXp);
            final long _tmpTotalXpEarned;
            _tmpTotalXpEarned = _cursor.getLong(_cursorIndexOfTotalXpEarned);
            _item = new AttributeEntity(_tmpType,_tmpLevel,_tmpCurrentXp,_tmpTotalXpEarned);
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
  public Object getAttribute(final String type,
      final Continuation<? super AttributeEntity> $completion) {
    final String _sql = "SELECT * FROM attributes WHERE type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AttributeEntity>() {
      @Override
      @Nullable
      public AttributeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfCurrentXp = CursorUtil.getColumnIndexOrThrow(_cursor, "currentXp");
          final int _cursorIndexOfTotalXpEarned = CursorUtil.getColumnIndexOrThrow(_cursor, "totalXpEarned");
          final AttributeEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final long _tmpCurrentXp;
            _tmpCurrentXp = _cursor.getLong(_cursorIndexOfCurrentXp);
            final long _tmpTotalXpEarned;
            _tmpTotalXpEarned = _cursor.getLong(_cursorIndexOfTotalXpEarned);
            _result = new AttributeEntity(_tmpType,_tmpLevel,_tmpCurrentXp,_tmpTotalXpEarned);
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
