package com.shannon.xiaoci.main.model.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shannon.xiaoci.main.model.bean.News;

/**
 * Created by Shannon on 2016/4/10.
 */
public class NewsDao {




    public void insert(Context context , News news){

        if(news!=null){

            NewsDatabase nd = new NewsDatabase(context, NewsDatabase.DB_NAME,null, NewsDatabase.NEWS_DB_V);
            SQLiteDatabase sd = nd.getWritableDatabase();
            String insert_sql = "insert into " + NewsDatabase.NEWS_DB_TABLE + "(content_url,title,describle,cover_url,content)values (?,?,?,?,?)";
            sd.execSQL(insert_sql,new String[]{news.getContent_url()
                    ,news.getTitle()
                    ,news.getDescrible()
                    ,news.getCover_url()
                    ,news.getContent()});

            sd.close();

        }


    }


    public News select(Context context,String url){

        //System.out.println("要查找新闻的网址：" + url);
        NewsDatabase nd = new NewsDatabase(context, NewsDatabase.DB_NAME,null, NewsDatabase.NEWS_DB_V);
        SQLiteDatabase sd = nd.getReadableDatabase();
        String select_sql = "select * from " + NewsDatabase.NEWS_DB_TABLE + " where content_url = ?";
        Cursor cursor = sd.rawQuery(select_sql , new String[]{url});
        if(cursor!=null){

            if(cursor.moveToFirst()){

                String content_url = cursor.getString(cursor.getColumnIndex("content_url"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String describle = cursor.getString(cursor.getColumnIndex("describle"));
                String cover_url = cursor.getString(cursor.getColumnIndex("cover_url"));
                String content = cursor.getString(cursor.getColumnIndex("content"));

                return new News(cover_url,content_url,content,describle,title);



            }


        }


        return null;
    }


}
