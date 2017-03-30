package com.example.mtalha.deneme3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by M.TaLha on 1.2.2017.
 */

public class MsgDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Dbmsg.db";
    public static final String CONTACTS_TABLE_NAME = "db20";
    public static final String CONTACTS_TABLE_NAME2 = "db30";
    public static final String CONTACTS_TABLE_NAME3 = "db35";
    public static final String CONTACTS_TABLE_NAME4 = "db40";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_Gunesli = "gunesli";
    public static final String CONTACTS_COLUMN_Karli = "karli";
    public static final String CONTACTS_COLUMN_Yahmurlu = "yagmurlu";
    public static final String CONTACTS_COLUMN_Sisli = "sisli";
    public MsgDb(Context context){
        super(context, DATABASE_NAME , null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE "+CONTACTS_TABLE_NAME +
                        "(id integer primary key, gunesli text,karli text,yagmurlu text, sisli text)"
        );
        /*db.execSQL(
                "CREATE TABLE "+CONTACTS_TABLE_NAME2 +
                        "(id integer primary key, gunesli text,karli text,yagmurlu text, sisli text)"
        );
        db.execSQL(
                "CREATE TABLE "+CONTACTS_TABLE_NAME3 +
                        "(id integer primary key, gunesli text,karli text,yagmurlu text, sisli text)"
        );
        db.execSQL(
                "CREATE TABLE "+CONTACTS_TABLE_NAME4 +
                        "(id integer primary key, gunesli text,karli text,yagmurlu text, sisli text)"
        );*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertContact (String gunesli, String karli, String yagmurlu, String sisli) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gunesli", gunesli);
        contentValues.put("karli", karli);
        contentValues.put("yagmurlu", yagmurlu);
        contentValues.put("sisli", sisli);
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;

    }
    /*public boolean insertContact2 (String gunesli, String karli, String yagmurlu, String sisli) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gunesli", gunesli);
        contentValues.put("karli", karli);
        contentValues.put("yagmurlu", yagmurlu);
        contentValues.put("sisli", sisli);
        db.insert(CONTACTS_TABLE_NAME2, null, contentValues);
        return true;

    }
    public boolean insertContact3 (String gunesli, String karli, String yagmurlu, String sisli) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gunesli", gunesli);
        contentValues.put("karli", karli);
        contentValues.put("yagmurlu", yagmurlu);
        contentValues.put("sisli", sisli);
        db.insert(CONTACTS_TABLE_NAME3, null, contentValues);
        return true;

    }
    public boolean insertContact4 (String gunesli, String karli, String yagmurlu, String sisli) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gunesli", gunesli);
        contentValues.put("karli", karli);
        contentValues.put("yagmurlu", yagmurlu);
        contentValues.put("sisli", sisli);
        db.insert(CONTACTS_TABLE_NAME4, null, contentValues);
        return true;

    }*/
    public Cursor getData(int id,String drm) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select "+ drm +" from "+CONTACTS_TABLE_NAME+" where id="+id, null );
        return res;
    }
    /*public Cursor getData2(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME2+" where id="+id+"", null );
        return res;
    }
    public Cursor getData3(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME3+" where id="+id+"", null );
        return res;
    }
    public Cursor getData4(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME4+" where id="+id+"", null );
        return res;
    }*/

}
