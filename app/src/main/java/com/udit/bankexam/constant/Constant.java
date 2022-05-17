package com.udit.bankexam.constant;

import com.udit.bankexam.R;

import android.graphics.Color;

public interface Constant
{
    /**
     * 广告跳转时间
     */
    public final int SKIP_TIME = 5 * 1000;
    
    public final int DIALOG_SHOW = 1;
    
    public final int DIALOG_CANCAL = 0;
    //2885867063
    public final String QQ_ZIXUN="3004628600";
    String URL_APP = "1";

    String URL_WEB ="2";


    public interface Shared
    {
        String URL = "http://yk.yinhangzhaopin.com/dw.yikao/download/index.jsp";

        String URL_TIMU = "http://yk.yinhangzhaopin.com/bshWeb/exam/ViewTitle.jsp?ID=";

        String URL_EXAM_FLAG = "FLAG=";

        String URL_EXAM = "0";

        String URL_ZN = "1";

        String title = "银行易考";
        String content = "考银行就用银行易考APP";

        String content_st = "考银行就用银行易考";
    }

    public interface  Broard
    {
        String WELCOME_LOAD="welcome_load";

        String WELCOME_NEXT ="welcome_next" ;
        String WELCOME_GO = "welcome_go";
    }

    public interface ZHIBO
    {
        String PID = "20170419161441324358";
        String APPKEY = "4f9739ab849c46cda986f665a903ff40";
    }

    public interface  IMAGE
    {
        public static final String  IMG_NEW_BEGIN = "<img  style='max-width:100%;height:auto;' ";

        public static final String IMG_OLD_BEGIN="<img";

    }

    public interface  PAY
    {
        String URL_WEIXIN = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";

        public static final String PAY_ZHIFUBAO="支付宝";

        public static final String PAY_WEIXIN="微信";

        public static final String PAY_MIANFEI = "免费";

        public static final String PAY_STATUS_OK = "已支付";
        public static final String PAY_STATUS_ERR = "未支付";

        public static final String PAY_WEIXIN_APPID="wxf41ad321ed049ffa";


        public static final String PAY_WEIXIN_APPSECRET="503e667c6581dd7008846d56ce97d12a";

    }

    public interface DataType
    {
        public static final String TYPE_ZHIBO="直播课";

        public static final String TYPE_SHIJUAN="试卷";

        public static final String TYPE_SHIJUAN_MK="模考试卷";

        public static final String TYPE_SHOUYE="首页";
        public static final String TYPE_SHITI="试题";

        public static final String TYPE_ZHINENG = "专项智能";

        public static final String TYPE_SHIPIN="视频";

        public static final int  WelcomeTime = 1500;
    }

    public interface ExamData
    {
        public static final int EXAM_HOME_NUM = 10;

        public static final int EXAM_NUM = 10;


        public static final String EXAM_OK="是";

        public static final String EXAM_ERR="否";

        String GOUMAI_OK = "是";

        String GOUMAI_ERR="否";
        int COUNT_PAGE =10 ;
        int INIT_PAGE =1 ;
    }

    public interface Params
    {
        public static final String ACTION="action";
        
        public static final String MOBILE = "mobile";

        public static final String PASS ="pass";

        public static final String UID = "uid";

        public static final String VTYPE = "VType";

        public static final String CID = "cID";

        public static final String FTYPE = "FType";

        public static final String LINKID = "LinkID";

        public static final String ATIME="ATime";

        public static final String LID = "LID";

        public static final String ID = "id";

        public static final String IDS = "ID";
        public static final String TYPE = "type";

        public static final String TYPE_PAY = "type_pay";

        public static final String IDLIST = "idlist";

        public static final String OID = "OID";
        public static final String SLIST ="slist" ;
        public static final String TYPEINFO ="TypeInfo" ;
        public static final String EID ="EID" ;
        public static final String PTYPE ="PType" ;
        public static final String FEEDATE ="FeeDate" ;
        public static final String ABSTRACT = "Abstract";
        String INTRO ="Intro" ;
        String PSTATE ="PState" ;
        String FEE = "Fee";
        String KEYWORD ="KeyWord" ;
        String NPAGE = "NPage";
        String TCOUNT = "tCount";
        String ITYPE = "IType";
        String CONTENT ="Content" ;
        String QPOINT="QPoint";
        String  TITLEID ="TitleID" ;
        String NDATE = "nDate";
        String NOTE = "Note";
        String TOKEN="token";
        String MOCKID="MockID";

        String PET ="Pet" ;
        String PATH="path";
        String TITLE ="title" ;
        String PID = "PID";
        String BEGIN ="begin" ;

        String END = "end";
        String INFO = "info";
        String NAME = "Name";
        String TEL ="Tel" ;
        String PROVINCE ="Province" ;
        String CITY ="City" ;
        String DISTRICT ="District" ;
        String ADDR ="Addr" ;


        String ID_ = "ID";
        String INTRFO ="intro" ;
    }

    public interface MainModule
    {
        public static final String MODULE_1="做题";
        public static final String MODULE_2="每日一练";
        public static final String MODULE_3="智能组卷";
        public static final String MODULE_4="专项练习";//智能练习
        public static final String MODULE_5="历年真题";
        public static final String MODULE_6="独家密卷";
        public static final String MODULE_7="模考大赛";
        public static final String MODULE_8="数据报告";
        public static final String MODULE_9="错题本";
        public static final String MODULE_10="收藏题目";
        public static final String MODULE_11="笔记题目";
        public static final String MODULE_12="我的试卷";//练习历史
        public static final String MODULE_13="练习报告";
        public static final String MODULE_14="练习周报";
        public static final String MODULE_15="最新招聘";
        public static final String MODULE_16="网申模拟";
    }

    public interface IntentParams
    {
        public static final String PHONE="PHONE";
        public static final String UID = "uid";

        String TOKEN ="TOKEN" ;
    }
    
    public interface ExamType
    {
        public static final String TYPE_DXT = "单选题";
        
        public static final String TYPE_DUOXT = "多选题";
        
        public static final String TYPE_ZLDXT = "资料单选";
        
        public static final String TYPE_ZLDUOXT = "资料多选";
        
        public static final String TYPE_XLDXT = "心理单选";
        
        public static final String TYPE_XLDUOXT = "心理多选";
        
        public static final String TYPE_XLPXT = "心理排序";
        
    }
    
    public interface ExamSelected
    {
        public static final int SELECTED = Color.parseColor("#F9491E");
        
        public static final int SELECTED_NOT = Color.parseColor("#FFFFFF");
        
        public static final int BACKGROUND_SELETED = R.drawable.drawable_background_transparent_in_exam_seleted_radius_5;
        
        public static final int BACKGROUND_SELETED_NOT = R.drawable.drawable_background_transparent_in_white_radius_5;
    }
    
    public interface ExamNode
    {
        public static final int[] node_one = {R.mipmap.content_icon_minus,R.mipmap.content_icon_add};
        public static final int[] node_two = {R.mipmap.content_icon_minus,R.mipmap.content_icon_add};
//        public static final int[] node_two = {R.mipmap.content_smallicon_minus,R.mipmap.content_smallicon_add};
        public static final int node_no = R.mipmap.content_circle_blue;
        
    }

    public interface RESULT
    {
        public static final int RESULT_VIDEO_TYPE_ONE = 0x000001;

        int RESULT_NOTE_START =0x000002;
        //首页提纲返回
        int RESULT_MAIN_OUTLINE = 0X000003;

        int RESULT_VIDEO_PAY = 0x000004;

        int RESULT_ZHIBO_DETAIL = 0x000005;

        int RESULT_ZHIBO_PAY = 0x000006;
        int RESULT_EXAM_YEAR_DETAIL = 0x000007;
        int RESULT_EXAM_PAY = 0x000008;
        int RESULT_EXAM_DAY_DETAIL = 0x000009;
        int RESULT_EXAM_SOLE_DETAIL = 0x000010;
        int RESULT_MK_EXAM_DETAIL= 0x000011;
        int RESULT_MK_VIDEO_DETAIL = 0x000012;
        int RESULT_MK_ZB_DETAIL = 0x000013;

        int RESULT_MSG_OK = 0x000014;

        int RESULT_MSG_ERR = 0x000015;
        int MSG_NUM = 60;

        int MSG_NUM_STEP = 1000;
        int RESULT_EXAM_HOME =0X000016;
        String OID = "oid";
        String TRUE_FLAG_OID = "true_flag_oid";
    }

    public interface VIDEO
    {
        /** 加密秘钥 */
        public static String AESKEY = "VXtlHmwfS2oYm0CZ";
        /** 加密向量 */
        public static String IV = "2u9gDPKdX6GyQJKU";

        public static String SDK_PASS="7u309Csi6l3ZsYAqSx3qjgIlxCT6/S+mWzRtZcAZAqzFs8iD1KkDuf19C+oZD1W+mMFFep0dET/+MExhlxgRJKhIiQKY1EYupuxfdvtHonUfJoJlNaZaUcn+PMFCqN5jJS9sUb8SaU7cba4Jszghlw==";
        //使用 vid 播放
        public static int PLAY_TYPE_VID = 1;
        //使用url 播放
        public static int PLAY_TYPE_URL = 2;
            //横屏
        public static int PLAY_MODE_LANDSCAPE=3;
        //竖屏
        public static int PLAY_MODE_PORTRAIR=4;
    }

    public interface Receiver {
        String TOKEN = "token_receiver";
    }
}
