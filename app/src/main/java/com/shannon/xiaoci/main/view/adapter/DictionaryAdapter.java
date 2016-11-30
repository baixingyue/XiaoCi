package com.shannon.xiaoci.main.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Shannon on 2016/4/9.
 */
public class DictionaryAdapter extends CursorAdapter{



    public DictionaryAdapter(Context context, Cursor c) {
        super(context, c);

    }

    @Override
    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "":cursor.getString(cursor.getColumnIndex("_id"));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {


        TextView tv = (TextView) LayoutInflater.from(context).inflate(android.R.layout.simple_expandable_list_item_1,null);
        tv.setText(cursor.getString(cursor.getColumnIndex("_id")));
        return tv;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        TextView tv = (TextView) view;
        tv.setText(cursor.getString(cursor.getColumnIndex("_id")));


    }



}
