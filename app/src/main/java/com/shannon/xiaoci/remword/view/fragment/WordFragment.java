package com.shannon.xiaoci.remword.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.model.bean.Word;
import com.shannon.xiaoci.main.model.dao.DicCollectionDao;

/**
 * Created by shann on 2016/4/16.
 */
public class WordFragment extends Fragment{





    private TextView fw_tv_page,fw_tv_word,fw_tv_mean;
    private Button fw_btn_show_detail,fw_btn_remember;
    private Word word;
    private int page,all;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_word,null);
        fw_tv_page = (TextView) view.findViewById(R.id.fw_tv_page);
        fw_tv_word = (TextView) view.findViewById(R.id.fw_tv_word);
        fw_tv_mean = (TextView) view.findViewById(R.id.fw_tv_mean);
        fw_btn_show_detail = (Button) view.findViewById(R.id.fw_btn_show_detail);
        fw_btn_remember = (Button) view.findViewById(R.id.fw_btn_remember);

        fw_tv_page.setText(page+"/" + all);
        fw_tv_word.setText(word.getName());
        fw_tv_mean.setText(word.getType()+"."+word.getMean());


        fw_btn_show_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fw_tv_mean.setVisibility(View.VISIBLE);
            }
        });

        fw_btn_remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DicCollectionDao.rememberedWord(getActivity(),word);


            }
        });

        return view;
    }



    public WordFragment(int all, int page, Word word) {
        this.all = all;
        this.page = page;
        this.word = word;
    }
}
