package com.fitquest.rpg.core.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.fitquest.rpg.core.data.local.entity.RewardCardEntity;
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
public final class RewardCardDao_Impl implements RewardCardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RewardCardEntity> __insertionAdapterOfRewardCardEntity;

  private final EntityDeletionOrUpdateAdapter<RewardCardEntity> __deletionAdapterOfRewardCardEntity;

  private final EntityDeletionOrUpdateAdapter<RewardCardEntity> __updateAdapterOfRewardCardEntity;

  public RewardCardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRewardCardEntity = new EntityInsertionAdapter<RewardCardEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `reward_cards` (`id`,`title`,`description`,`apCost`,`emoji`,`isPredefined`,`isRedeemed`,`redeemedAtMs`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RewardCardEntity entity) {
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
        statement.bindLong(4, entity.getApCost());
        if (entity.getEmoji() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEmoji());
        }
        final int _tmp = entity.isPredefined() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final int _tmp_1 = entity.isRedeemed() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getRedeemedAtMs() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getRedeemedAtMs());
        }
      }
    };
    this.__deletionAdapterOfRewardCardEntity = new EntityDeletionOrUpdateAdapter<RewardCardEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `reward_cards` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RewardCardEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRewardCardEntity = new EntityDeletionOrUpdateAdapter<RewardCardEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `reward_cards` SET `id` = ?,`title` = ?,`description` = ?,`apCost` = ?,`emoji` = ?,`isPredefined` = ?,`isRedeemed` = ?,`redeemedAtMs` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RewardCardEntity entity) {
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
        statement.bindLong(4, entity.getApCost());
        if (entity.getEmoji() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEmoji());
        }
        final int _tmp = entity.isPredefined() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final int _tmp_1 = entity.isRedeemed() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getRedeemedAtMs() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getRedeemedAtMs());
        }
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insertAll(final List<RewardCardEntity> cards,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRewardCardEntity.insert(cards);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final RewardCardEntity card, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRewardCardEntity.insertAndReturnId(card);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final RewardCardEntity card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRewardCardEntity.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final RewardCardEntity card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRewardCardEntity.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RewardCardEntity>> observeAvailableCards() {
    final String _sql = "SELECT * FROM reward_cards WHERE isRedeemed = 0 ORDER BY apCost ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reward_cards"}, new Callable<List<RewardCardEntity>>() {
      @Override
      @NonNull
      public List<RewardCardEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfApCost = CursorUtil.getColumnIndexOrThrow(_cursor, "apCost");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final int _cursorIndexOfIsPredefined = CursorUtil.getColumnIndexOrThrow(_cursor, "isPredefined");
          final int _cursorIndexOfIsRedeemed = CursorUtil.getColumnIndexOrThrow(_cursor, "isRedeemed");
          final int _cursorIndexOfRedeemedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAtMs");
          final List<RewardCardEntity> _result = new ArrayList<RewardCardEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RewardCardEntity _item;
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
            final int _tmpApCost;
            _tmpApCost = _cursor.getInt(_cursorIndexOfApCost);
            final String _tmpEmoji;
            if (_cursor.isNull(_cursorIndexOfEmoji)) {
              _tmpEmoji = null;
            } else {
              _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            }
            final boolean _tmpIsPredefined;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPredefined);
            _tmpIsPredefined = _tmp != 0;
            final boolean _tmpIsRedeemed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsRedeemed);
            _tmpIsRedeemed = _tmp_1 != 0;
            final Long _tmpRedeemedAtMs;
            if (_cursor.isNull(_cursorIndexOfRedeemedAtMs)) {
              _tmpRedeemedAtMs = null;
            } else {
              _tmpRedeemedAtMs = _cursor.getLong(_cursorIndexOfRedeemedAtMs);
            }
            _item = new RewardCardEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpApCost,_tmpEmoji,_tmpIsPredefined,_tmpIsRedeemed,_tmpRedeemedAtMs);
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
  public Flow<List<RewardCardEntity>> observeAllCards() {
    final String _sql = "SELECT * FROM reward_cards ORDER BY isRedeemed ASC, apCost ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reward_cards"}, new Callable<List<RewardCardEntity>>() {
      @Override
      @NonNull
      public List<RewardCardEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfApCost = CursorUtil.getColumnIndexOrThrow(_cursor, "apCost");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final int _cursorIndexOfIsPredefined = CursorUtil.getColumnIndexOrThrow(_cursor, "isPredefined");
          final int _cursorIndexOfIsRedeemed = CursorUtil.getColumnIndexOrThrow(_cursor, "isRedeemed");
          final int _cursorIndexOfRedeemedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAtMs");
          final List<RewardCardEntity> _result = new ArrayList<RewardCardEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RewardCardEntity _item;
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
            final int _tmpApCost;
            _tmpApCost = _cursor.getInt(_cursorIndexOfApCost);
            final String _tmpEmoji;
            if (_cursor.isNull(_cursorIndexOfEmoji)) {
              _tmpEmoji = null;
            } else {
              _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            }
            final boolean _tmpIsPredefined;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPredefined);
            _tmpIsPredefined = _tmp != 0;
            final boolean _tmpIsRedeemed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsRedeemed);
            _tmpIsRedeemed = _tmp_1 != 0;
            final Long _tmpRedeemedAtMs;
            if (_cursor.isNull(_cursorIndexOfRedeemedAtMs)) {
              _tmpRedeemedAtMs = null;
            } else {
              _tmpRedeemedAtMs = _cursor.getLong(_cursorIndexOfRedeemedAtMs);
            }
            _item = new RewardCardEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpApCost,_tmpEmoji,_tmpIsPredefined,_tmpIsRedeemed,_tmpRedeemedAtMs);
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
  public Object countPredefinedCards(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM reward_cards WHERE isPredefined = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
