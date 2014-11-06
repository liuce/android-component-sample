package org.lightips.sample.android.component.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by liuce on 11/5/14.
 */
public class SqlNativeHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "native_db";
    private static int DB_VERSION = 1;
    public static final String TABLE_NAME_PERSION = "persons";


    public SqlNativeHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.createTables(db);
    }

    private void createTables(SQLiteDatabase db){
        db.execSQL("create table if not exists "+TABLE_NAME_PERSION+"("
                + "id integer primary key,"
                + "name varchar,"
                + "age integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertPersonSQL(String username, int age){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("insert into "+TABLE_NAME_PERSION+"(name,age) values('+"+username+"+',"+age+")");
        database.close();
    }

    public void insertPersonContent(String username, int age){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", username);
        values.put("age", age);
        database.insert("hero_info", "id", values);
        database.close();
    }

    public String queryPersonAll(){
        SQLiteDatabase db=this.getReadableDatabase();
        String result = "";
        Cursor cursor = db.query(TABLE_NAME_PERSION, null, null, null, null, null, "id asc");
        int nameIndex = cursor.getColumnIndex("name");
        int ageIndex = cursor.getColumnIndex("age");
        for (cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()) {
            result = result + cursor.getString(nameIndex)+ "\t\t";
            result = result + cursor.getInt(ageIndex)+"       \n";
        }
        cursor.close();//关闭结果集
        db.close();//关闭数据库对象
        return result;
    }
}
