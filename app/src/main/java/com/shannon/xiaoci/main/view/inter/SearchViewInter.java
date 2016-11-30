package com.shannon.xiaoci.main.view.inter;

import android.database.Cursor;

import com.shannon.xiaoci.main.model.bean.Word;


public interface SearchViewInter {

    //显示查找的结果
    public void showResult(Word word);


    //输入的过程中显示结果
    public void showResultInput(Cursor cursor);



}
