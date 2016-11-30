package com.shannon.xiaoci.main.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.model.NewsModel;
import com.shannon.xiaoci.main.model.bean.News;

import java.util.ArrayList;


public class NewsFragment extends Fragment{

    private RecyclerView fm_rlv_news;
    private ArrayList<News> al_news = new ArrayList<>();
    private NFAdapter nfa = new NFAdapter();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,null);
        fm_rlv_news = (RecyclerView) view.findViewById(R.id.fm_rlv_news);
        fm_rlv_news.setLayoutManager(new LinearLayoutManager(getContext()));
        fm_rlv_news.setAdapter(nfa);


        new AsyncTask<Integer,Integer,ArrayList<News>>(){


            @Override
            protected ArrayList<News> doInBackground(Integer... params) {

                return NewsModel.getNews(getContext(),params[0]);
            }

            @Override
            protected void onPostExecute(ArrayList<News> newses) {
                super.onPostExecute(newses);
                if(newses!=null){


                    al_news.clear();
                    al_news.addAll(newses);

                    nfa.notifyDataSetChanged();


                }


            }
        }.execute(1);




        return view;
    }


    private class NFAdapter extends RecyclerView.Adapter<NFAdapter.ViewHolder>{


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.view_item_news,null));

        }


        //刷新viewholder中的数据
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            News news = al_news.get(position);
            ImageLoader.getInstance().displayImage(news.getCover_url(),holder.vin_iv_cover);
            holder.vin_tv_title.setText(news.getTitle());
            holder.vin_tv_describle.setText(news.getDescrible());

        }

        @Override
        public int getItemCount() {
            return al_news.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView vin_iv_cover;
            TextView vin_tv_title,vin_tv_describle;

            public ViewHolder(View itemView) {
                super(itemView);
                vin_iv_cover = (ImageView) itemView.findViewById(R.id.vin_iv_cover);
                vin_tv_title = (TextView) itemView.findViewById(R.id.vin_tv_title);
                vin_tv_describle = (TextView) itemView.findViewById(R.id.vin_tv_describle);

            }
        }

    }






}
