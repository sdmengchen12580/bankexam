package com.udit.bankexam.utils;

import com.google.gson.JsonNull;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamHistoryBean;
import com.udit.bankexam.bean.ExamNoteBean;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.bean.ExamTitle;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.SolutionBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.db.ExamBeanDao;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by zb on 2017/5/9.
 */

public class ExamUtils {

    public static void insertExam(ExamBean bean_current) {

        DBUtils.getInstance().getNewSession().getExamBeanDao().insertOrReplace(bean_current);

    }

    public static void saveHisory(final ExamHistoryBean bean_history, final List<ExamBean> list
            , final boolean flag_zhineng, final boolean flag_oid, final String examIds, final String userid) {
        DBUtils.getInstance().getNewSession().getExamHistoryBeanDao().insertOrReplace(bean_history);

        if(!bean_history.isFlag_cl())
        {//不需要保存 记录
            DBUtils.getInstance().getNewSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0;list!=null && i<list.size();i++)
                    {
                        ExamBean bean = list.get(i);
                        bean.setAnswer("");
                        bean.setIsOK("");
                        bean.setUserTime("");
                        bean.setATime("");
                        bean.setGetScore("");
                        bean.setFlag_biaoji(false);
                        bean.setUser_id(userid);
                        bean.set_id(bean.getID()+"_"+flag_zhineng+"_"+flag_oid+"_"+examIds);
                        DBUtils.getInstance().getNewSession().getExamBeanDao().insertOrReplace(bean);
                    }
                }
            });
        }

    }

    public static List<ExamBean> getLocalExamList(String examId,String userid) {

       Query<ExamBean> query =   DBUtils.getInstance().getNewSession().getExamBeanDao()
               .queryRawCreate("where examIds = ? and user_id = ?   order by cast(ordID as integer), cast(ord as integer) ASC",examId,userid);
       if(query!=null && query.list()!=null && query.list().size()>0)
       {
           List<ExamBean> list = query.list();
           for(int i = 0;list!=null && i<list.size();i++)
           {
               ExamBean bean_exam = list.get(i);
              Query<ExamOptionBean> query_option =
                      DBUtils.getInstance().getNewSession().getExamOptionBeanDao()
                              .queryRawCreate("where examid=?  and user_id = ?  order by single ASC",bean_exam.getID(),userid);
               if(query_option!=null && query_option.list()!=null && query_option.list().size()>0)
               {
                   List<ExamOptionBean> list_option = query_option.list();
                   //从本地拉出来的答题卡 要做一次检查，防止有重复的出现
                   removeDuplicate(list_option);
                   bean_exam.setList_TitleList(list_option);

               }
               else
               {//如果 本地答题卡出现不全的情况下，请求服务器，重新刷新本地库
                   return null;
               }
           }
           if(list!=null && list.size()>0)
                 return list;
            else
                return null;
       }
       else
       {//如果，本地题目 出现不全的情况下，请求服务器，重新刷新本地库

           return null;
       }


    }

    public static void removeDuplicate(List<ExamOptionBean> list)
    {
        Set<ExamOptionBean> set = new HashSet<ExamOptionBean>();
        List<ExamOptionBean> lists = new ArrayList<ExamOptionBean>();
        List<String> list_orderID = new ArrayList<>();
        for(Iterator iter = list.iterator();iter.hasNext();)
        {
            ExamOptionBean value = (ExamOptionBean) iter.next();

            if(set.add(value) && !list_orderID.contains(value.getOrderID()))
            {
                list_orderID.add(value.getOrderID());
                lists.add(value);
            }

        }
        list_orderID = null;
        list.clear();
        list.addAll(lists);
    }


    public static ExamHistoryBean insertExamHistory(ExamHistoryBean bean_history,
                                           String name,
                                           String exam_oid_id, boolean flag_zhineng, boolean flag_oid,
                                           boolean flag_main,String user_id) {

     /*   if(bean_history!=null)
        {
            return null;
        }*/
        ExamHistoryBean bean= new ExamHistoryBean();
        String local_id = "";
        if(exam_oid_id==null || MyCheckUtils.isEmpty(exam_oid_id))
        {
            local_id = "智能";
        }
        else
        {
            local_id=exam_oid_id;
        }

        bean.set_id(local_id+"_"+flag_oid+"_"+flag_zhineng);
        bean.setFlag_oid(flag_oid);
        bean.setFlag_zhineng(flag_zhineng);
        bean.setFlag_main(flag_main);
        bean.setEid_oid(exam_oid_id);
        bean.setName(name);
        bean.setUser_id(user_id);
        DBUtils.getInstance().getNewSession().getExamHistoryBeanDao().insertOrReplace(bean);
        return bean;


    }


    public static ExamHistoryBean getLocalExamHistory(String examId,String user_id) {

        Query<ExamHistoryBean> query = DBUtils.getInstance().getNewSession().getExamHistoryBeanDao()
                .queryRawCreate("where _ID=? and user_id = ? and flag_cl = 1", examId,user_id);
        if (query != null && query.list() != null && query.list().size() > 0) {
            ExamHistoryBean bean = query.list().get(0);
            return bean;

        } else
        {
            return null;
        }
    }

    public static void insertExamBiaoji(ExamBean bean,boolean flag_zhineng,boolean flag_oid,boolean flag_biaoji,
                                        ExamHistoryBean bean_history,String user_id)
    {
        bean.set_id(bean.getID()+"_"+flag_oid+"_"+flag_zhineng+"_"+bean_history.get_id());
        bean.setFlag_oid(flag_oid);
        bean.setFlag_zhineng(flag_zhineng);
        bean.setFlag_biaoji(flag_biaoji);
        bean.setUser_id(user_id);
        DBUtils.getInstance().getNewSession().getExamBeanDao().insertOrReplace(bean);

    }

    public static boolean getExamBiaoji(String id,boolean flag_zhineng,boolean flag_oid,ExamHistoryBean bean_history,String user_id)
    {
       Query<ExamBean> query =  DBUtils.getInstance().getNewSession().getExamBeanDao()
               .queryRawCreate("where _ID=? and user_id=? order by cast(ordID as integer), cast(ord as integer) ASC",id+"_"+flag_oid+"_"+flag_zhineng+"_"+bean_history.get_id(),user_id);
        if(query!=null && query.list()!=null && query.list().size()>0)
        {
            ExamBean bean = query.list().get(0);
            return bean.getFlag_biaoji();
        }
        else
            return false;

    }

    public static void insertExamList(final List<ExamBean> list, final boolean flag_zhineng, final boolean flag_oid,
                                      final String examIds, final String user_id)
    {

           DBUtils.getInstance().getNewSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;list!=null && i<list.size();i++)
                {
                    ExamBean bean = list.get(i);
                    bean.set_id(bean.getID()+"_"+flag_oid+"_"+flag_zhineng+"_"+examIds);
                    bean.setFlag_oid(flag_oid);
                    bean.setFlag_zhineng(flag_zhineng);
                    bean.setExamIds(examIds);
                    bean.setATime("");
                    bean.setGetScore("");
                    bean.setUserTime("");
                    bean.setIsOK("");
                    bean.setUser_id(user_id);
                    bean.setAnswer("");
                    for(int j = 0;bean.getList_TitleList()!=null && j<bean.getList_TitleList().size();j++)
                    {
                        ExamOptionBean bean_option = bean.getList_TitleList().get(j);
                        bean_option.setExamid(bean.getID());
                        bean_option.set_id(bean_option.getTitleListID()+"_"+flag_oid+"_"+flag_zhineng);
                        bean_option.setUser_id(user_id);
                        DBUtils.getInstance().getNewSession().getExamOptionBeanDao().insertOrReplace(bean_option);
                    }
                    DBUtils.getInstance().getNewSession().getExamBeanDao().insertOrReplace(bean);
                }

            }
        });

/*
        }*/


    }

    public static String getExamTitle(String id)
    {
        ArrayList<ExamTitle> list_title = new ArrayList<>();

        ExamTitle title = new ExamTitle();
        title.setID(id);
        list_title.add(title);
        if(list_title!=null && list_title.size()>0)
        {
            String json = JsonUtil.GSON.toJson(list_title);
            return json;
        }
        else
            return null;

    }

    public static String  getExamTtile(List<ExamTitleBean> list)
    {
        ArrayList<ExamTitle> list_title = new ArrayList<>();
        for(int i =0;list!=null && i<list.size();i++)
        {
            ExamTitle title = new ExamTitle();
            title.setID(list.get(i).getID());
            list_title.add(title);
        }
        if(list_title!=null && list_title.size()>0)
        {
            String json = JsonUtil.GSON.toJson(list_title);
            return json;
        }
        else
            return null;


    }
    /**
     * 首页 获取题目筛选
     *
     * @param list
     * @return
     */
    public static List<ExamTitleBean> getHomeExamTitleList(List<ExamTitleBean> list,int num) {
        List<ExamTitleBean> mlist = new ArrayList<>();


        //先去除  已经答题正确的
        for (int i = 0; list != null && i < list.size(); i++) {
            ExamTitleBean bean = list.get(i);
            if (bean.getIsOK().equals(Constant.ExamData.EXAM_OK)) {
                continue;
            } else {
                mlist.add(bean);
            }
        }
        //如果剩余的题目数小于 固定取数
        if (mlist.size() < num) {
            if(mlist.size()==0)
            {//如果 剩余题数为0
                if(list.size()>num)
                {//检查题库数量，如果大于固定取数
                    List<ExamTitleBean> list_return = list.subList(0, num);
                    return list_return;
                }
                else
                {
                    return list;
                }

            }
            else
            {//直接给出练习的题目数
                return mlist;
            }

        } else {
            List<ExamTitleBean> list_return = mlist.subList(0, num);
            return list_return;
        }


    }

    public static String getExamIds(List<FavoriteRecord> list) {
        try {
            ArrayList<ExamTitle> list_title = new ArrayList<>();
            for(int i =0;list!=null && i<list.size();i++)
            {
                ExamTitle title = new ExamTitle();
                title.setID(list.get(i).getLinkID());
                list_title.add(title);
            }
            if(list_title!=null && list_title.size()>0)
            {
                String json = JsonUtil.objectToJson(list_title);
                if(!MyCheckUtils.isEmpty(json))
                {
                    return json;
                }
                else
                    return null;
            }
            else
                return null;
        } catch (Exception e) {
            return null;
        }


    }
    public static String getExamBeanListTitle(ArrayList<ExamBean> list) {


        try {
            ArrayList<ExamTitle> list_title = new ArrayList<>();
            for(int i = 0;list!=null && i<list.size();i++)
            {
                ExamBean bean = list.get(i);
                ExamTitle title = new ExamTitle();
                title.setID(bean.getTitleID());
                list_title.add(title);
            }
            if(list_title!=null && list_title.size()>0)
            {
                String json = JsonUtil.objectToJson(list_title);
                if(!MyCheckUtils.isEmpty(json))
                {
                    return json;
                }
                else
                    return null;
            }
            else
                return null;
        } catch (Exception e) {
            return null;
        }



    }
    public static String  getExamBeanList(List<ExamBean> mlist_exam) {

        try {
            ArrayList<ExamTitle> list_title = new ArrayList<>();
            for(int i = 0;mlist_exam!=null && i<mlist_exam.size();i++)
            {
                ExamBean bean = mlist_exam.get(i);
                ExamTitle title = new ExamTitle();
                title.setID(bean.getID());
                list_title.add(title);
            }
            if(list_title!=null && list_title.size()>0)
            {
                String json = JsonUtil.objectToJson(list_title);
                if(!MyCheckUtils.isEmpty(json))
                {
                    return json;
                }
                else
                    return null;
            }
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }


    public static String getExamAnswer(List<ExamBean> list) {
        List<SolutionBean> list_title = new ArrayList<>();
        for(int i = 0;list!=null && i<list.size();i++)
        {
            ExamBean bean = list.get(i);
            SolutionBean bean_solution = new SolutionBean();
            bean_solution.setAnswer(bean.getAnswer());
            bean_solution.setATime(bean.getATime());
            bean_solution.setIsOK(bean.getIsOK());
            //2017-09-17 修改bug  之前提交 会把打错时，分数也计数提交
            if(bean.getIsOK().equals(Constant.ExamData.EXAM_OK))
            {
                bean_solution.setGetScore(bean.getScore());
            }
            else
                bean_solution.setGetScore("0");


            bean_solution.setID(bean.getID());
            bean_solution.setUserTime(bean.getUserTime());

            list_title.add(bean_solution);
        }
        if(list_title!=null && list_title.size()>0)
        {
            String return_solution = JsonUtil.objectToJson(list_title);
            if(!MyCheckUtils.isEmpty(return_solution))
            {
                return return_solution;
            }
            else
                return null;
        }
        else
            return null;

    }


    public static String getExamNote(String id) {

        try {
            ExamNoteBean note = DBUtils.getInstance().getNewSession().getExamNoteBeanDao().load(id);
            if(note==null || MyCheckUtils.isEmpty(note.getNote()))
            {
                return null;
            }
            else
                return note.getNote();
        } catch (Exception e) {
            return null;
        }

    }

    public static void saveNote(ExamNoteBean bean1) {
        DBUtils.getInstance().getNewSession().getExamNoteBeanDao().insertOrReplace(bean1);


    }

    private static final String TAG = ExamUtils.class.getSimpleName();

    public static List<String> getExamBeanLists(List<ExamBean> list, int num) {
        List<String> ids = new ArrayList<>();
        int m = 0;
        int n = num;
        boolean flag = true;
        while(flag)
        {
            if(n>list.size())
                n = list.size();
            System.out.println(m+"  "+n);
            List<ExamBean> ss =  list.subList(m, n);
            String idss =  getExamAnswer(ss);
            if(!MyCheckUtils.isEmpty(idss))
            {
                ids.add(idss);
            }
            m+=num;
            n+=num;
            if(m>list.size())
                flag = false;

        }
        if(ids!=null && ids.size()>0)
            return ids;
        else
            return null;

    }
}
