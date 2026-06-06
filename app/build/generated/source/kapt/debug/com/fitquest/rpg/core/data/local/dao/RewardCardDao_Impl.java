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
        return "INSERT OR REPLACE INTO `reward_cards` (`id`,`title`,`description`,`apCost`,`emoji`,`isPredefined`,`isRedeemed`,`redeemedAtMs`,`lastRedeemedAtMs`,`timesRedeemed`,`hasTask`,`taskType`,`taskProgress`,`taskTarget`,`taskCompleted`,`targetAttribute`,`bonusXp`,`bonusAp`,`overachieveXpPerCount`,`overachieveApPerCount`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        if (entity.getLastRedeemedAtMs() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getLastRedeemedAtMs());
        }
        statement.bindLong(10, entity.getTimesRedeemed());
        final int _tmp_2 = entity.getHasTask() ? 1 : 0;
        statement.bindLong(11, _tmp_2);
        if (entity.getTaskType() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getTaskType());
        }
        statement.bindLong(13, entity.getTaskProgress());
        statement.bindLong(14, entity.getTaskTarget());
        final int _tmp_3 = entity.getTaskCompleted() ? 1 : 0;
        statement.bindLong(15, _tmp_3);
        if (entity.getTargetAttribute() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getTargetAttribute());
        }
        statement.bindLong(17, entity.getBonusXp());
        statement.bindLong(18, entity.getBonusAp());
        statement.bindLong(19, entity.getOverachieveXpPerCount());
        statement.bindLong(20, entity.getOverachieveApPerCount());
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
        return "UPDATE OR ABORT `reward_cards` SET `id` = ?,`title` = ?,`description` = ?,`apCost` = ?,`emoji` = ?,`isPredefined` = ?,`isRedeemed` = ?,`redeemedAtMs` = ?,`lastRedeemedAtMs` = ?,`timesRedeemed` = ?,`hasTask` = ?,`taskType` = ?,`taskProgress` = ?,`taskTarget` = ?,`taskCompleted` = ?,`targetAttribute` = ?,`bonusXp` = ?,`bonusAp` = ?,`overachieveXpPerCount` = ?,`overachieveApPerCount` = ? WHERE `id` = ?";
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
        if (entity.getLastRedeemedAtMs() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getLastRedeemedAtMs());
        }
        statement.bindLong(10, entity.getTimesRedeemed());
        final int _tmp_2 = entity.getHasTask() ? 1 : 0;
        statement.bindLong(11, _tmp_2);
        if (entity.getTaskType() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getTaskType());
        }
        statement.bindLong(13, entity.getTaskProgress());
        statement.bindLong(14, entity.getTaskTarget());
        final int _tmp_3 = entity.getTaskCompleted() ? 1 : 0;
        statement.bindLong(15, _tmp_3);
        if (entity.getTargetAttribute() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getTargetAttribute());
        }
        statement.bindLong(17, entity.getBonusXp());
        statement.bindLong(18, entity.getBonusAp());
        statement.bindLong(19, entity.getOverachieveXpPerCount());
        statement.bindLong(20, entity.getOverachieveApPerCount());
        statement.bindLong(21, entity.getId());
      }
    };
  }

  @Override
  public Object insertAll(final List<RewardCardEntity> cards,
      final Continuation<? super Unit> arg1) {
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
    }, arg1);
  }

  @Override
  public Object insert(final RewardCardEntity card, final Continuation<? super Long> arg1) {
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
    }, arg1);
  }

  @Override
  public Object delete(final RewardCardEntity card, final Continuation<? super Unit> arg1) {
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
    }, arg1);
  }

  @Override
  public Object update(final RewardCardEntity card, final Continuation<? super Unit> arg1) {
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
    }, arg1);
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
          final int _cursorIndexOfLastRedeemedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastRedeemedAtMs");
          final int _cursorIndexOfTimesRedeemed = CursorUtil.getColumnIndexOrThrow(_cursor, "timesRedeemed");
          final int _cursorIndexOfHasTask = CursorUtil.getColumnIndexOrThrow(_cursor, "hasTask");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfTaskProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "taskProgress");
          final int _cursorIndexOfTaskTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "taskTarget");
          final int _cursorIndexOfTaskCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "taskCompleted");
          final int _cursorIndexOfTargetAttribute = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAttribute");
          final int _cursorIndexOfBonusXp = CursorUtil.getColumnIndexOrThrow(_cursor, "bonusXp");
          final int _cursorIndexOfBonusAp = CursorUtil.getColumnIndexOrThrow(_cursor, "bonusAp");
          final int _cursorIndexOfOverachieveXpPerCount = CursorUtil.getColumnIndexOrThrow(_cursor, "overachieveXpPerCount");
          final int _cursorIndexOfOverachieveApPerCount = CursorUtil.getColumnIndexOrThrow(_cursor, "overachieveApPerCount");
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
            final Long _tmpLastRedeemedAtMs;
            if (_cursor.isNull(_cursorIndexOfLastRedeemedAtMs)) {
              _tmpLastRedeemedAtMs = null;
            } else {
              _tmpLastRedeemedAtMs = _cursor.getLong(_cursorIndexOfLastRedeemedAtMs);
            }
            final int _tmpTimesRedeemed;
            _tmpTimesRedeemed = _cursor.getInt(_cursorIndexOfTimesRedeemed);
            final boolean _tmpHasTask;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasTask);
            _tmpHasTask = _tmp_2 != 0;
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final int _tmpTaskProgress;
            _tmpTaskProgress = _cursor.getInt(_cursorIndexOfTaskProgress);
            final int _tmpTaskTarget;
            _tmpTaskTarget = _cursor.getInt(_cursorIndexOfTaskTarget);
            final boolean _tmpTaskCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfTaskCompleted);
            _tmpTaskCompleted = _tmp_3 != 0;
            final String _tmpTargetAttribute;
            if (_cursor.isNull(_cursorIndexOfTargetAttribute)) {
              _tmpTargetAttribute = null;
            } else {
              _tmpTargetAttribute = _cursor.getString(_cursorIndexOfTargetAttribute);
            }
            final long _tmpBonusXp;
            _tmpBonusXp = _cursor.getLong(_cursorIndexOfBonusXp);
            final int _tmpBonusAp;
            _tmpBonusAp = _cursor.getInt(_cursorIndexOfBonusAp);
            final long _tmpOverachieveXpPerCount;
            _tmpOverachieveXpPerCount = _cursor.getLong(_cursorIndexOfOverachieveXpPerCount);
            final int _tmpOverachieveApPerCount;
            _tmpOverachieveApPerCount = _cursor.getInt(_cursorIndexOfOverachieveApPerCount);
            _item = new RewardCardEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpApCost,_tmpEmoji,_tmpIsPredefined,_tmpIsRedeemed,_tmpRedeemedAtMs,_tmpLastRedeemedAtMs,_tmpTimesRedeemed,_tmpHasTask,_tmpTaskType,_tmpTaskProgress,_tmpTaskTarget,_tmpTaskCompleted,_tmpTargetAttribute,_tmpBonusXp,_tmpBonusAp,_tmpOverachieveXpPerCount,_tmpOverachieveApPerCount);
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
          final int _cursorIndexOfLastRedeemedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastRedeemedAtMs");
          final int _cursorIndexOfTimesRedeemed = CursorUtil.getColumnIndexOrThrow(_cursor, "timesRedeemed");
          final int _cursorIndexOfHasTask = CursorUtil.getColumnIndexOrThrow(_cursor, "hasTask");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfTaskProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "taskProgress");
          final int _cursorIndexOfTaskTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "taskTarget");
          final int _cursorIndexOfTaskCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "taskCompleted");
          final int _cursorIndexOfTargetAttribute = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAttribute");
          final int _cursorIndexOfBonusXp = CursorUtil.getColumnIndexOrThrow(_cursor, "bonusXp");
          final int _cursorIndexOfBonusAp = CursorUtil.getColumnIndexOrThrow(_cursor, "bonusAp");
          final int _cursorIndexOfOverachieveXpPerCount = CursorUtil.getColumnIndexOrThrow(_cursor, "overachieveXpPerCount");
          final int _cursorIndexOfOverachieveApPerCount = CursorUtil.getColumnIndexOrThrow(_cursor, "overachieveApPerCount");
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
            final Long _tmpLastRedeemedAtMs;
            if (_cursor.isNull(_cursorIndexOfLastRedeemedAtMs)) {
              _tmpLastRedeemedAtMs = null;
            } else {
              _tmpLastRedeemedAtMs = _cursor.getLong(_cursorIndexOfLastRedeemedAtMs);
            }
            final int _tmpTimesRedeemed;
            _tmpTimesRedeemed = _cursor.getInt(_cursorIndexOfTimesRedeemed);
            final boolean _tmpHasTask;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasTask);
            _tmpHasTask = _tmp_2 != 0;
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final int _tmpTaskProgress;
            _tmpTaskProgress = _cursor.getInt(_cursorIndexOfTaskProgress);
            final int _tmpTaskTarget;
            _tmpTaskTarget = _cursor.getInt(_cursorIndexOfTaskTarget);
            final boolean _tmpTaskCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfTaskCompleted);
            _tmpTaskCompleted = _tmp_3 != 0;
            final String _tmpTargetAttribute;
            if (_cursor.isNull(_cursorIndexOfTargetAttribute)) {
              _tmpTargetAttribute = null;
            } else {
              _tmpTargetAttribute = _cursor.getString(_cursorIndexOfTargetAttribute);
            }
            final long _tmpBonusXp;
            _tmpBonusXp = _cursor.getLong(_cursorIndexOfBonusXp);
            final int _tmpBonusAp;
            _tmpBonusAp = _cursor.getInt(_cursorIndexOfBonusAp);
            final long _tmpOverachieveXpPerCount;
            _tmpOverachieveXpPerCount = _cursor.getLong(_cursorIndexOfOverachieveXpPerCount);
            final int _tmpOverachieveApPerCount;
            _tmpOverachieveApPerCount = _cursor.getInt(_cursorIndexOfOverachieveApPerCount);
            _item = new RewardCardEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpApCost,_tmpEmoji,_tmpIsPredefined,_tmpIsRedeemed,_tmpRedeemedAtMs,_tmpLastRedeemedAtMs,_tmpTimesRedeemed,_tmpHasTask,_tmpTaskType,_tmpTaskProgress,_tmpTaskTarget,_tmpTaskCompleted,_tmpTargetAttribute,_tmpBonusXp,_tmpBonusAp,_tmpOverachieveXpPerCount,_tmpOverachieveApPerCount);
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
  public Object countPredefinedCards(final Continuation<? super Integer> arg0) {
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
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
