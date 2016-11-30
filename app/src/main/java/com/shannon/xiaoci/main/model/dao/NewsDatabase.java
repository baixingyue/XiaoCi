package com.shannon.xiaoci.main.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shannon on 2016/4/10.
 */
public class NewsDatabase extends SQLiteOpenHelper{


    //数据库的名称，该数据为网络缓存用的数据库
    public static final String DB_NAME = "dic_cache_db.db";
    //新闻缓存的表名
    public static final String NEWS_DB_TABLE = "news";


    public static final Integer NEWS_DB_V = 1;


    //指定数据库的名称
    public NewsDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }


    //打开数据库是会进行判断，如果不存在则会执行创建操作，并创建表
    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_sql = "create table " + NEWS_DB_TABLE + "(" +
                "content_url varchar primary key" +
                ", title varchar " +
                ", describle varchar " +
                ", cover_url varchar " +
                ", content varchar)";


        db.execSQL(create_sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
