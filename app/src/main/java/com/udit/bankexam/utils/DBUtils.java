package com.udit.bankexam.utils;

import com.udit.bankexam.MyApplication;
import com.udit.bankexam.bean.NoteBean;
import com.udit.bankexam.db.DaoMaster;
import com.udit.bankexam.db.DaoSession;
import com.udit.frame.utils.MyLogUtils;

public class DBUtils
{
    private final String TAG = this.getClass().getSimpleName();

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static DBUtils mInstance; //单例
    private DBUtils(){
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(MyApplication.getContext(), "Exam.db");//此处为自己需要处理的表
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static DBUtils getInstance() {
        if (mInstance == null) {
            synchronized (DBUtils.class) {//保证异步处理安全操作

                if (mInstance == null) {
                    mInstance = new DBUtils();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }
    public DaoSession getSession() {
        return mDaoSession;
    }
    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }





}