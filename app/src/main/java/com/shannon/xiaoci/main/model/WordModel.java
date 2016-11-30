package com.shannon.xiaoci.main.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.model.bean.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 *
 * 1.本地数据库查询
 * 2.网络查询
 *
 */
public class WordModel {




    // 定义数据库的存放路径
    public static final String DATABASE_PATH = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/dictionary";

    // 定义数据库的名字
    private static final String DATABASE_FILENAME = "dictionary.db";

    //数据库文件路径
    private static final String DATABASE_FILEPATH = DATABASE_PATH + "/" + DATABASE_FILENAME;

    //定义插叙单词的SQL语句
    private static final String SQL_SEARCH_ChinToEng = "select chinese from t_words where english = ?";


    //模糊查询单词
    private static final String SQL_SEARCH_WORD_INPUT = "select english as _id from t_words where english like ?";


    //从本地数据库查询
    public static Word searchFromDatabase(Context context , String input){


        copyDatabase(context);


        SQLiteDatabase sdb = SQLiteDatabase.openOrCreateDatabase(DATABASE_FILEPATH,null);
        Cursor cursor = sdb.rawQuery(SQL_SEARCH_ChinToEng, new String[]{input});
        String result = "";
        if(cursor.getCount()>0){

            cursor.moveToFirst();
            result = cursor.getString(cursor.getColumnIndex("chinese"));

        }

        String[] sr = result.split("\\.");
        if(sr.length >1){

            return new Word(input,sr[0],sr[1]);

        }else if(sr.length == 1){

            return new Word(input,sr[0],"未查询到中文意思");

        }else{

            return null;

        }






    }



    //拷贝数据库
    public static void copyDatabase(Context context){



        //sd上数据库的路径
        File dir = new File(DATABASE_PATH);
        if(!dir.exists()){

            dir.mkdirs();


            if(!new File(DATABASE_FILEPATH).exists()){


                //将安装包的数据库复制到SD卡上
                InputStream is = context.getResources().openRawResource(R.raw.dictionary);

                try {

                    FileOutputStream fos = new FileOutputStream(DATABASE_FILEPATH);
                    byte[] buffers = new byte[1024];
                    int length;
                    while((length = is.read(buffers))!=-1){

                        fos.write(buffers,0,length);


                    }

                    is.close();
                    fos.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                }

            }


        }




    }


    //从网络查询
    public Word searchFromNet(String input){


        return null;

    }


    //输入的情况下返回查询的指针
    public static Cursor getWordCursor(Context context,String input){

        copyDatabase(context);


        SQLiteDatabase sdb = SQLiteDatabase.openOrCreateDatabase(DATABASE_FILEPATH,null);
        Cursor cursor = sdb.rawQuery(SQL_SEARCH_WORD_INPUT, new String[]{input});

        return cursor;



    }




}
