package com.fitquest.rpg.core.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.fitquest.rpg.core.data.local.dao.AttributeDao;
import com.fitquest.rpg.core.data.local.dao.AttributeDao_Impl;
import com.fitquest.rpg.core.data.local.dao.DailyTaskDao;
import com.fitquest.rpg.core.data.local.dao.DailyTaskDao_Impl;
import com.fitquest.rpg.core.data.local.dao.EconomyDao;
import com.fitquest.rpg.core.data.local.dao.EconomyDao_Impl;
import com.fitquest.rpg.core.data.local.dao.RewardCardDao;
import com.fitquest.rpg.core.data.local.dao.RewardCardDao_Impl;
import com.fitquest.rpg.core.data.local.dao.UserProfileDao;
import com.fitquest.rpg.core.data.local.dao.UserProfileDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FitQuestDatabase_Impl extends FitQuestDatabase {
  private volatile UserProfileDao _userProfileDao;

  private volatile AttributeDao _attributeDao;

  private volatile DailyTaskDao _dailyTaskDao;

  private volatile RewardCardDao _rewardCardDao;

  private volatile EconomyDao _economyDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_profile` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `age` INTEGER NOT NULL, `gender` TEXT NOT NULL, `heightCm` REAL NOT NULL, `weightKg` REAL NOT NULL, `fitnessLevel` TEXT NOT NULL, `primaryGoal` TEXT NOT NULL, `dietaryStyle` TEXT NOT NULL, `workoutDaysPerWeek` INTEGER NOT NULL, `wakeTimeHour` INTEGER NOT NULL, `sleepTimeHour` INTEGER NOT NULL, `onboardingComplete` INTEGER NOT NULL, `joinDateMs` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `attributes` (`type` TEXT NOT NULL, `level` INTEGER NOT NULL, `currentXp` INTEGER NOT NULL, `totalXpEarned` INTEGER NOT NULL, PRIMARY KEY(`type`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `daily_tasks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `taskType` TEXT NOT NULL, `targetAttribute` TEXT NOT NULL, `xpReward` INTEGER NOT NULL, `apReward` INTEGER NOT NULL, `sets` INTEGER, `reps` INTEGER, `durationMinutes` INTEGER, `isCompleted` INTEGER NOT NULL, `completedAtMs` INTEGER, `dateMs` INTEGER NOT NULL, `difficultyMultiplier` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reward_cards` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `apCost` INTEGER NOT NULL, `emoji` TEXT NOT NULL, `isPredefined` INTEGER NOT NULL, `isRedeemed` INTEGER NOT NULL, `redeemedAtMs` INTEGER, `lastRedeemedAtMs` INTEGER, `timesRedeemed` INTEGER NOT NULL, `hasTask` INTEGER NOT NULL, `taskType` TEXT NOT NULL, `taskProgress` INTEGER NOT NULL, `taskTarget` INTEGER NOT NULL, `taskCompleted` INTEGER NOT NULL, `targetAttribute` TEXT NOT NULL, `bonusXp` INTEGER NOT NULL, `bonusAp` INTEGER NOT NULL, `overachieveXpPerCount` INTEGER NOT NULL, `overachieveApPerCount` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `economy` (`id` INTEGER NOT NULL, `totalActionPoints` INTEGER NOT NULL, `availableActionPoints` INTEGER NOT NULL, `totalSpent` INTEGER NOT NULL, `currentStreak` INTEGER NOT NULL, `longestStreak` INTEGER NOT NULL, `lastCompletionDateMs` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'dac24c74e71afae38df2ae2d0f8bacef')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `user_profile`");
        db.execSQL("DROP TABLE IF EXISTS `attributes`");
        db.execSQL("DROP TABLE IF EXISTS `daily_tasks`");
        db.execSQL("DROP TABLE IF EXISTS `reward_cards`");
        db.execSQL("DROP TABLE IF EXISTS `economy`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUserProfile = new HashMap<String, TableInfo.Column>(14);
        _columnsUserProfile.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("age", new TableInfo.Column("age", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("gender", new TableInfo.Column("gender", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("heightCm", new TableInfo.Column("heightCm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("weightKg", new TableInfo.Column("weightKg", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("fitnessLevel", new TableInfo.Column("fitnessLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("primaryGoal", new TableInfo.Column("primaryGoal", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("dietaryStyle", new TableInfo.Column("dietaryStyle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("workoutDaysPerWeek", new TableInfo.Column("workoutDaysPerWeek", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("wakeTimeHour", new TableInfo.Column("wakeTimeHour", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("sleepTimeHour", new TableInfo.Column("sleepTimeHour", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("onboardingComplete", new TableInfo.Column("onboardingComplete", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("joinDateMs", new TableInfo.Column("joinDateMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProfile = new TableInfo("user_profile", _columnsUserProfile, _foreignKeysUserProfile, _indicesUserProfile);
        final TableInfo _existingUserProfile = TableInfo.read(db, "user_profile");
        if (!_infoUserProfile.equals(_existingUserProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "user_profile(com.fitquest.rpg.core.data.local.entity.UserProfileEntity).\n"
                  + " Expected:\n" + _infoUserProfile + "\n"
                  + " Found:\n" + _existingUserProfile);
        }
        final HashMap<String, TableInfo.Column> _columnsAttributes = new HashMap<String, TableInfo.Column>(4);
        _columnsAttributes.put("type", new TableInfo.Column("type", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttributes.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttributes.put("currentXp", new TableInfo.Column("currentXp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttributes.put("totalXpEarned", new TableInfo.Column("totalXpEarned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttributes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAttributes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAttributes = new TableInfo("attributes", _columnsAttributes, _foreignKeysAttributes, _indicesAttributes);
        final TableInfo _existingAttributes = TableInfo.read(db, "attributes");
        if (!_infoAttributes.equals(_existingAttributes)) {
          return new RoomOpenHelper.ValidationResult(false, "attributes(com.fitquest.rpg.core.data.local.entity.AttributeEntity).\n"
                  + " Expected:\n" + _infoAttributes + "\n"
                  + " Found:\n" + _existingAttributes);
        }
        final HashMap<String, TableInfo.Column> _columnsDailyTasks = new HashMap<String, TableInfo.Column>(14);
        _columnsDailyTasks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("taskType", new TableInfo.Column("taskType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("targetAttribute", new TableInfo.Column("targetAttribute", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("xpReward", new TableInfo.Column("xpReward", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("apReward", new TableInfo.Column("apReward", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("sets", new TableInfo.Column("sets", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("reps", new TableInfo.Column("reps", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("durationMinutes", new TableInfo.Column("durationMinutes", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("completedAtMs", new TableInfo.Column("completedAtMs", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("dateMs", new TableInfo.Column("dateMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyTasks.put("difficultyMultiplier", new TableInfo.Column("difficultyMultiplier", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDailyTasks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDailyTasks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDailyTasks = new TableInfo("daily_tasks", _columnsDailyTasks, _foreignKeysDailyTasks, _indicesDailyTasks);
        final TableInfo _existingDailyTasks = TableInfo.read(db, "daily_tasks");
        if (!_infoDailyTasks.equals(_existingDailyTasks)) {
          return new RoomOpenHelper.ValidationResult(false, "daily_tasks(com.fitquest.rpg.core.data.local.entity.DailyTaskEntity).\n"
                  + " Expected:\n" + _infoDailyTasks + "\n"
                  + " Found:\n" + _existingDailyTasks);
        }
        final HashMap<String, TableInfo.Column> _columnsRewardCards = new HashMap<String, TableInfo.Column>(20);
        _columnsRewardCards.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("apCost", new TableInfo.Column("apCost", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("emoji", new TableInfo.Column("emoji", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("isPredefined", new TableInfo.Column("isPredefined", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("isRedeemed", new TableInfo.Column("isRedeemed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("redeemedAtMs", new TableInfo.Column("redeemedAtMs", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("lastRedeemedAtMs", new TableInfo.Column("lastRedeemedAtMs", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("timesRedeemed", new TableInfo.Column("timesRedeemed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("hasTask", new TableInfo.Column("hasTask", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("taskType", new TableInfo.Column("taskType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("taskProgress", new TableInfo.Column("taskProgress", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("taskTarget", new TableInfo.Column("taskTarget", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("taskCompleted", new TableInfo.Column("taskCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("targetAttribute", new TableInfo.Column("targetAttribute", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("bonusXp", new TableInfo.Column("bonusXp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("bonusAp", new TableInfo.Column("bonusAp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("overachieveXpPerCount", new TableInfo.Column("overachieveXpPerCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewardCards.put("overachieveApPerCount", new TableInfo.Column("overachieveApPerCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRewardCards = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRewardCards = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRewardCards = new TableInfo("reward_cards", _columnsRewardCards, _foreignKeysRewardCards, _indicesRewardCards);
        final TableInfo _existingRewardCards = TableInfo.read(db, "reward_cards");
        if (!_infoRewardCards.equals(_existingRewardCards)) {
          return new RoomOpenHelper.ValidationResult(false, "reward_cards(com.fitquest.rpg.core.data.local.entity.RewardCardEntity).\n"
                  + " Expected:\n" + _infoRewardCards + "\n"
                  + " Found:\n" + _existingRewardCards);
        }
        final HashMap<String, TableInfo.Column> _columnsEconomy = new HashMap<String, TableInfo.Column>(7);
        _columnsEconomy.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEconomy.put("totalActionPoints", new TableInfo.Column("totalActionPoints", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEconomy.put("availableActionPoints", new TableInfo.Column("availableActionPoints", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEconomy.put("totalSpent", new TableInfo.Column("totalSpent", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEconomy.put("currentStreak", new TableInfo.Column("currentStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEconomy.put("longestStreak", new TableInfo.Column("longestStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEconomy.put("lastCompletionDateMs", new TableInfo.Column("lastCompletionDateMs", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEconomy = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEconomy = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEconomy = new TableInfo("economy", _columnsEconomy, _foreignKeysEconomy, _indicesEconomy);
        final TableInfo _existingEconomy = TableInfo.read(db, "economy");
        if (!_infoEconomy.equals(_existingEconomy)) {
          return new RoomOpenHelper.ValidationResult(false, "economy(com.fitquest.rpg.core.data.local.entity.EconomyEntity).\n"
                  + " Expected:\n" + _infoEconomy + "\n"
                  + " Found:\n" + _existingEconomy);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "dac24c74e71afae38df2ae2d0f8bacef", "24b2bb1aaed1df23fe05367c0fde7ebf");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_profile","attributes","daily_tasks","reward_cards","economy");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `user_profile`");
      _db.execSQL("DELETE FROM `attributes`");
      _db.execSQL("DELETE FROM `daily_tasks`");
      _db.execSQL("DELETE FROM `reward_cards`");
      _db.execSQL("DELETE FROM `economy`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserProfileDao.class, UserProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AttributeDao.class, AttributeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DailyTaskDao.class, DailyTaskDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RewardCardDao.class, RewardCardDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EconomyDao.class, EconomyDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserProfileDao userProfileDao() {
    if (_userProfileDao != null) {
      return _userProfileDao;
    } else {
      synchronized(this) {
        if(_userProfileDao == null) {
          _userProfileDao = new UserProfileDao_Impl(this);
        }
        return _userProfileDao;
      }
    }
  }

  @Override
  public AttributeDao attributeDao() {
    if (_attributeDao != null) {
      return _attributeDao;
    } else {
      synchronized(this) {
        if(_attributeDao == null) {
          _attributeDao = new AttributeDao_Impl(this);
        }
        return _attributeDao;
      }
    }
  }

  @Override
  public DailyTaskDao dailyTaskDao() {
    if (_dailyTaskDao != null) {
      return _dailyTaskDao;
    } else {
      synchronized(this) {
        if(_dailyTaskDao == null) {
          _dailyTaskDao = new DailyTaskDao_Impl(this);
        }
        return _dailyTaskDao;
      }
    }
  }

  @Override
  public RewardCardDao rewardCardDao() {
    if (_rewardCardDao != null) {
      return _rewardCardDao;
    } else {
      synchronized(this) {
        if(_rewardCardDao == null) {
          _rewardCardDao = new RewardCardDao_Impl(this);
        }
        return _rewardCardDao;
      }
    }
  }

  @Override
  public EconomyDao economyDao() {
    if (_economyDao != null) {
      return _economyDao;
    } else {
      synchronized(this) {
        if(_economyDao == null) {
          _economyDao = new EconomyDao_Impl(this);
        }
        return _economyDao;
      }
    }
  }
}
