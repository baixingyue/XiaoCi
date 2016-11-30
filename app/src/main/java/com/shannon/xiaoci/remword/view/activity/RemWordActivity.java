package com.shannon.xiaoci.remword.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.model.bean.Word;
import com.shannon.xiaoci.main.model.dao.DicCollectionDao;
import com.shannon.xiaoci.remword.view.fragment.WordFragment;

import java.util.ArrayList;

public class RemWordActivity extends AppCompatActivity implements View.OnClickListener{



    private ViewPager arw_vp_container;
    private ArrayList<Word> al_word = new ArrayList<>();
    private ArrayList<WordFragment> al_fragment = new ArrayList<>();
    private MyApater ma = new MyApater(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rem_word);

        ActionBar ab = getSupportActionBar();
        if(ab!=null){

            ab.setDisplayHomeAsUpEnabled(true);
        }




        al_word = DicCollectionDao.getAllColl(this);

        arw_vp_container = (ViewPager) findViewById(R.id.arw_vp_container);
        arw_vp_container.setPageMargin(30);
        arw_vp_container.setOffscreenPageLimit(3);

        System.out.println("记单词页面的个数为：" + al_word.size());

        if(al_word!=null){


            int length = al_word.size();
            Toast.makeText(this,"你还有" + length + "个单词没有掌握",Toast.LENGTH_SHORT).show();
            for(int i=0 ; i<length ; i++){

                al_fragment.add(new WordFragment(length,i+1,al_word.get(i)));


            }

            arw_vp_container.setAdapter(new MyApater(getSupportFragmentManager()));


        }






    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                finish();
                break;



        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){




        }


    }


    private class MyApater extends FragmentPagerAdapter{


        public MyApater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {


            return al_fragment.get(position);

        }

        @Override
        public int getCount() {

            return al_fragment == null?0:al_fragment.size();
        }
    }







}
