package com.shannon.xiaoci.main.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shannon on 2016/4/12.
 */
public class DicCollectionDb extends SQLiteOpenHelper{

    public static final String DIC_COLLECTION_DB_NAME = "dic_collection.db";
    public static final String DIC_COLLECTION_TABLE_NAME = "dic_collection";
    public static final int DIC_COLLECTION_VERSION = 1;



    public DicCollectionDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建数据库
        String createSql = "create table " + DIC_COLLECTION_TABLE_NAME +"(" +
                "_id integer primary key autoincrement , word varchar unique , isRemember integer default 0)";
        db.execSQL(createSql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
