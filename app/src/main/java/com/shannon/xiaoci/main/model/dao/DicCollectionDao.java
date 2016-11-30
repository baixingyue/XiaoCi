package com.shannon.xiaoci.main.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.shannon.xiaoci.main.model.WordModel;
import com.shannon.xiaoci.main.model.bean.Word;

import java.util.ArrayList;

/**
 * Created by Shannon on 2016/4/12.
 */
public class DicCollectionDao {


    //添加单词到单词本

    public static void addWord(Context context , String word){


        if(!TextUtils.isEmpty(word)){


            DicCollectionDb dcd = new DicCollectionDb(context, DicCollectionDb.DIC_COLLECTION_DB_NAME,null, DicCollectionDb.DIC_COLLECTION_VERSION);
            SQLiteDatabase sd = dcd.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("word",word);
            sd.insert(DicCollectionDb.DIC_COLLECTION_TABLE_NAME,"other",cv);
            System.out.println("收藏单词成功");



        }


    }

    //返回搜藏的cursor
    public static ArrayList<Word> getAllColl(Context context){

        ArrayList<Word> al_word = new ArrayList<>();
        DicCollectionDb dcd = new DicCollectionDb(context, DicCollectionDb.DIC_COLLECTION_DB_NAME,null, DicCollectionDb.DIC_COLLECTION_VERSION);
        SQLiteDatabase sd = dcd.getReadableDatabase();
        String selectSql = "select * from "+ DicCollectionDb.DIC_COLLECTION_TABLE_NAME + " where isRemember = 0";
        Cursor cursor = sd.rawQuery(selectSql,null);
        if(cursor!=null){

            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("word"));

                al_word.add(WordModel.searchFromDatabase(context,name));

            }


        }


        return al_word;



    }




    //查询
    public static String selectWord(Context context,String word){


        if(!TextUtils.isEmpty(word)){

            System.out.println("开始查询收藏本:" + word);
            DicCollectionDb dcd = new DicCollectionDb(context, DicCollectionDb.DIC_COLLECTION_DB_NAME,null, DicCollectionDb.DIC_COLLECTION_VERSION);
            SQLiteDatabase sd = dcd.getReadableDatabase();
            String selectSql = "select * from "+ DicCollectionDb.DIC_COLLECTION_TABLE_NAME + " where word = "+"'"+word+"'";
            System.out.println("查询收藏的sql:" + selectSql);
            Cursor cursor = sd.rawQuery(selectSql , new String[]{});
            if(cursor!=null){

                if(cursor.moveToFirst()){
                    System.out.println("查询单词成功");
                    return cursor.getString(cursor.getColumnIndex("word"));

                }

            }


        }
        System.out.println("查询单词失败");
        return null;



    }


    //删除
    public static void delete(Context context , String word){


        if(!TextUtils.isEmpty(word)){


            DicCollectionDb dcd = new DicCollectionDb(context, DicCollectionDb.DIC_COLLECTION_DB_NAME,null, DicCollectionDb.DIC_COLLECTION_VERSION);
            SQLiteDatabase sd = dcd.getWritableDatabase();
            String deleteSql = "delete from "+ DicCollectionDb.DIC_COLLECTION_TABLE_NAME + " where word = ?";
            sd.execSQL(deleteSql , new String[]{word});


            System.out.println("删除收藏成功");


        }


    }



    public static void rememberedWord(Context context , Word word){

        DicCollectionDb db = new DicCollectionDb(context,DicCollectionDb.DIC_COLLECTION_DB_NAME,null,DicCollectionDb.DIC_COLLECTION_VERSION);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        String setSQL = "update " + DicCollectionDb.DIC_COLLECTION_TABLE_NAME + " set isRemember = 1 where word = " + "'" + word.getName() + "'";
        System.out.println(setSQL);
        sqLiteDatabase.execSQL(setSQL);

    }


}
