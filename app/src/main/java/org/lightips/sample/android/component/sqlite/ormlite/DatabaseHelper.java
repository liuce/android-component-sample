package org.lightips.sample.android.component.sqlite.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuce on 11/5/14.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    protected static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DB_NAME = "ORM.db";
    private static final int DB_VERSION = 1;

    private Dao<Person, Integer> personDao = null;

    private static final AtomicInteger usageCounter = new AtomicInteger(0);

    private static DatabaseHelper helper = null;

    public static synchronized DatabaseHelper getHelper(Context context){
        if(helper==null) {
            helper = new DatabaseHelper(context);
        }
        usageCounter.incrementAndGet();
        return helper;
    }

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            Log.i(TAG, "onCreate");
            TableUtils.createTable(connectionSource, Person.class);
        } catch (SQLException e) {
            Log.e(TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(TAG, "onUpgrade");
            TableUtils.dropTable(connectionSource, Person.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Person, Integer> getPersonDao() throws SQLException{
        if(personDao ==null){
            personDao = getDao(Person.class);
        }
        return personDao;
    }

    @Override
    public void close() {
        if (usageCounter.decrementAndGet() == 0) {
            super.close();
            personDao = null;
            helper = null;
        }
    }
}
