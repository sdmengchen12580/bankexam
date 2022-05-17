package com.udit.bankexam.utils;

import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/6/12.
 */

public class ExamShouCang {

    private static final String TAG = ExamShouCang.class.getSimpleName();

    public static void deleteAllExamShouCang()
    {
        DBUtils.getInstance().getNewSession().getFavoriteRecordDao().deleteAll();
    }

    public static void insertAllExamShouCang(final String uid,final List<FavoriteRecord> list)
    {
        deleteAllExamShouCang();
        DBUtils.getInstance().getNewSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;list!=null && i<list.size();i++)
                {
                    FavoriteRecord record = list.get(i);
                    record.setUID(uid);
                    String key =record.getFType()+":"+record.getLinkID()+":"+uid;
                    DBUtils.getInstance().getNewSession().getFavoriteRecordDao().insertOrReplace(record);

                }
            }
        });

       /* HashMap<String,FavoriteRecord> map_save = new HashMap<>();
        for(int i = 0;list!=null && i<list.size();i++)
        {
            FavoriteRecord record = list.get(i);
            record.setUID(uid);
            String key =record.getFType()+":"+record.getLinkID()+":"+uid;
            if(!map_save.containsKey(key))
            {
                map_save.put(key,record);
                DBUtils.getInstance().getNewSession().getFavoriteRecordDao().insert(record);
            }

        }*/
    }

    public static boolean getLocalExamShouCang(String uid,String linkid,String type)
    {
        FavoriteRecord record = new FavoriteRecord();
        record.setUID(uid);
        record.setFType(type);
        String id = linkid;
        record.setLinkID(id);
        List<FavoriteRecord> list= DBUtils.getInstance().getNewSession().getFavoriteRecordDao().loadAll();
        if(list.contains(record))
        {
            MyLogUtils.e(TAG,"   数据库存在收藏"+linkid);
            return true;
        }
        else
        {
            MyLogUtils.e(TAG,"   数据库不存在收藏"+linkid);
            return false;
        }


    }

}
