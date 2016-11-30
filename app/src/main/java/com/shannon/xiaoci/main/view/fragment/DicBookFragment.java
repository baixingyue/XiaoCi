package com.shannon.xiaoci.main.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.model.bean.Word;
import com.shannon.xiaoci.main.model.dao.DicCollectionDao;

import java.util.ArrayList;


public class DicBookFragment extends Fragment{

    private RecyclerView fm_rlv_news;
    private ArrayList<Word> al_word = new ArrayList<>();
    private NFAdapter nfa = new NFAdapter();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,null);
        fm_rlv_news = (RecyclerView) view.findViewById(R.id.fm_rlv_news);
        fm_rlv_news.setLayoutManager(new LinearLayoutManager(getContext()));
        fm_rlv_news.setAdapter(nfa);

        refreshData();
        return view;
    }


    public void refreshData(){


        al_word.clear();
        al_word.addAll(DicCollectionDao.getAllColl(getContext()));
        nfa.notifyDataSetChanged();

    }









    private class NFAdapter extends RecyclerView.Adapter<NFAdapter.ViewHolder>{


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.view_item_collection,null));

        }


        //刷新viewholder中的数据
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

          holder.tv.setText(al_word.get(position).getName());

        }

        @Override
        public int getItemCount() {
            return al_word.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);

                tv = (TextView) itemView.findViewById(R.id.vic_tv_item);

            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("onStop");
    }




}
