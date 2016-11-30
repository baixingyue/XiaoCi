package com.shannon.xiaoci.main.view.activity;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shannon.xiaoci.R;
import com.shannon.xiaoci.main.model.NewsModel;
import com.shannon.xiaoci.main.model.WordModel;
import com.shannon.xiaoci.main.model.bean.Word;
import com.shannon.xiaoci.main.model.dao.DicCollectionDao;
import com.shannon.xiaoci.main.presenter.CollectionPrenenter;
import com.shannon.xiaoci.main.presenter.SearchPresenter;
import com.shannon.xiaoci.main.view.adapter.DictionaryAdapter;
import com.shannon.xiaoci.main.view.fragment.DicBookFragment;
import com.shannon.xiaoci.main.view.fragment.NewsFragment;
import com.shannon.xiaoci.main.view.fragment.SettingFragment;
import com.shannon.xiaoci.main.view.inter.CollectionViewInter;
import com.shannon.xiaoci.main.view.inter.SearchViewInter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchViewInter,TextWatcher,AdapterView.OnItemClickListener,CollectionViewInter{



    private TabLayout tabs;
    private ViewPager container;
    private String[] tabs_title={"主页","单词本","设置"};
    private ArrayList<Fragment> al_fragments = new ArrayList<>();
    private AutoCompleteTextView am_acet_input;
    private SearchPresenter sp = new SearchPresenter(this);
    private ImageView am_iv_collection;
    private TextView am_tv_word,am_tv_mt;
    private CollectionPrenenter cp = new CollectionPrenenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        //System.out.println("屏幕密度：" + displayMetrics.densityDpi);
        //System.out.println("屏幕宽：" + displayMetrics.widthPixels);
        //System.out.println("屏幕高：" + displayMetrics.heightPixels);
    }

    private void initData() {

        al_fragments.add(new NewsFragment());
        al_fragments.add(new DicBookFragment());
        al_fragments.add(new SettingFragment());


        new AsyncTask<Integer , Integer , String>(){


            @Override
            protected String doInBackground(Integer... params) {

                NewsModel.getNews(MainActivity.this , params[0]);
                return null;
            }
        }.execute(2);




    }

    private void initView() {

        tabs = (TabLayout) findViewById(R.id.tabs);
        container = (ViewPager) findViewById(R.id.container);
        container.setOffscreenPageLimit(2);
        am_acet_input = (AutoCompleteTextView) findViewById(R.id.am_acet_input);
        am_iv_collection = (ImageView) findViewById(R.id.am_iv_collection);
        am_tv_word = (TextView) findViewById(R.id.am_tv_word);
        am_tv_mt = (TextView) findViewById(R.id.am_tv_mt);

        am_acet_input.addTextChangedListener(this);
        am_acet_input.setOnItemClickListener(this);

        //搜藏按钮的点击事件
        am_iv_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCollectIcon();
                DicBookFragment dbf = (DicBookFragment) al_fragments.get(1);
                dbf.refreshData();
            }
        });

        container.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return al_fragments.get(position);
            }

            @Override
            public int getCount() {
                return al_fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs_title[position];
            }
        });

        tabs.setupWithViewPager(container);








    }

    public void setCollectIcon(){

        String word = am_tv_word.getText().toString();
        cp.collectWord(word);



    }

    @Override
    protected void onResume() {
        super.onResume();


        if(!TextUtils.isEmpty(DicCollectionDao.selectWord(this,am_tv_word.getText().toString()))){

            am_iv_collection.setSelected(true);


        }


    }

    @Override
    public void showResult(Word word) {

    }

    @Override
    public void showResultInput(Cursor cursor) {

        if(cursor!=null){

            DictionaryAdapter da = new DictionaryAdapter(this,cursor);
            am_acet_input.setAdapter(da);


        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {




    }

    @Override
    public void afterTextChanged(Editable s) {


        sp.searchInput(s.toString());




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView tv = (TextView) view;
        String input = tv.getText().toString().trim();
        Word wd = WordModel.searchFromDatabase(this,input);
        if(wd!=null){

            Toast.makeText(this,wd.toString(),Toast.LENGTH_SHORT).show();
            am_tv_word.setText(wd.getName());
            am_tv_mt.setText(wd.getType() + "."+wd.getMean());

            if(!TextUtils.isEmpty(DicCollectionDao.selectWord(this,wd.getName()))){

                am_iv_collection.setSelected(true);


            }else{

                am_iv_collection.setSelected(false);

            }

        }else{

            Toast.makeText(this,"未查询到",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setCollecIcon(boolean isCollected) {


        if(isCollected == true){


            am_iv_collection.setSelected(true);

        }else{


            am_iv_collection.setSelected(false);

        }


    }






}
