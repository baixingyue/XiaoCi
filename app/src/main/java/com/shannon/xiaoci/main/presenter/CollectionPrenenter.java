package com.shannon.xiaoci.main.presenter;

import android.text.TextUtils;

import com.shannon.xiaoci.main.model.dao.DicCollectionDao;
import com.shannon.xiaoci.main.view.activity.MainActivity;
import com.shannon.xiaoci.main.view.inter.CollectionViewInter;

public class CollectionPrenenter {


    private MainActivity ma;

    public CollectionPrenenter(CollectionViewInter cvi) {
        this.ma = (MainActivity) cvi;
    }




    //搜藏单词
    public void collectWord(String word){


        String result = DicCollectionDao.selectWord(ma , word);
        if(TextUtils.isEmpty(result)){


            DicCollectionDao.addWord(ma,word);
            ma.setCollecIcon(true);


        }else{

            DicCollectionDao.delete(ma,word);
            ma.setCollecIcon(false);

        }


    }



}
