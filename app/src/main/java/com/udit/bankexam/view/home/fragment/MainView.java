package com.udit.bankexam.view.home.fragment;

import android.content.Context;

import com.udit.bankexam.bean.AdvBean;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.ModuleBean;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.bean.ZhaoPinInfo;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

import java.util.List;

public interface MainView {
    public static abstract class Presenter extends BasePresenter<View> {
        public Presenter(MainView.View param1View) {
            super(param1View);
        }

        public abstract void checkHomeNode(String param1String1, String param1String2);

        public abstract void getAdv();

        public abstract void getExamNode(String param1String);

        public abstract void getHomeExam(Context param1Context, String param1String1, String param1String2);

        public abstract void getHomeSJ(String param1String);

        public abstract void getHomeSJDTK(String param1String1, String param1String2);

        public abstract void getModule();

        public abstract void getModule_net();

        public abstract void getTypeOne(String param1String);

        public abstract void getZpList();

        public abstract void saveShouYe(String param1String1, String param1String2, String param1String3);

        public abstract void shoucang(String param1String);
    }

    public static interface View extends IBaseView {
        void checkHomeNode(List<ExamNodeBean> param1List);

        void saveExamShouYe(String param1String);

        void saveShoucang(List<FavoriteRecord> param1List);

        void setAdv(List<AdvBean> param1List);

        void setExamNode(List<ExamNodeBean> param1List);

        void setHomeExam(List<ExamTitleBean> param1List);

        void setHomeExamDTK(List<ExamTitleBean> param1List);

        void setHomeExmaSJ(ExamInfoBean param1ExamInfoBean);

        void setModule(List<ModuleBean> param1List);

        void setTypeOne(List<VideoTypeOneBean> param1List);

        void setWork(List<ZhaoPinInfo.DataBean.ResponseBean.ZhaopinsBean> param1List, String url);
    }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\home\fragment\MainView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */