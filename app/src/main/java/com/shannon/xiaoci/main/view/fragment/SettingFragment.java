package com.shannon.xiaoci.main.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.view.activity.AboutActivity;
import com.shannon.xiaoci.remword.view.activity.RemWordActivity;


public class SettingFragment extends Fragment implements View.OnClickListener{


    private TextView fs_tv_remember_word,fs_backup_collection,fs_tv_clear_cache,fs_tv_about;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting,null);
        fs_tv_remember_word = (TextView) view.findViewById(R.id.fs_tv_remember_word);
        fs_backup_collection = (TextView) view.findViewById(R.id.fs_backup_collection);
        fs_tv_clear_cache = (TextView) view.findViewById(R.id.fs_tv_clear_cache);
        fs_tv_about = (TextView) view.findViewById(R.id.fs_tv_about);

        fs_tv_remember_word.setOnClickListener(this);
        fs_backup_collection.setOnClickListener(this);
        fs_tv_clear_cache.setOnClickListener(this);
        fs_tv_about.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.fs_tv_remember_word:

                startActivity(new Intent(getActivity() , RemWordActivity.class));
                break;

            case R.id.fs_backup_collection:

                break;

            case R.id.fs_tv_clear_cache:

                break;

            case R.id.fs_tv_about:
                startActivity(new Intent(getActivity() , AboutActivity.class));
                break;

        }





    }
}
