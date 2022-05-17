package com.udit.bankexam.constant;

public interface IHTTP {

    //-----------------------fixme start 新的接口
    public String IP = "http://yk.yinhangzhaopin.com";//旧接口原来的线上地址
//    public String IP = "http://yk-test.project.njagan.org";//新接口现在的测试地址
    public String PROJECT = IP + "/bshApp/AppAction?";//现在的拼接地址

//    public String AGANYUN_IP = "http://ykv2-test.project.njagan.org/dw.yikao/api/";//新模块的地址
    public String AGANYUN_IP = "http://yk.yinhangzhaopin.com/dw.yikao/api/";//新模块的地址
    public String NEW_LOGIN_IN = AGANYUN_IP + "yikao/uc/login";//新登录
    public String NEW_LOGIN_OUT = AGANYUN_IP + "yikao/uc/logout";//新退出登录
    public String QUES_TYPE = AGANYUN_IP + "yikao/yk-question/getQuestionCats";//问题分类
    public String QUES_LIST = AGANYUN_IP + "yikao/yk-question/list";//问题列表
    public String QUES_ADD = AGANYUN_IP + "yikao/yk-question/addQuestion";//问题新增
    public String QUES_MY = AGANYUN_IP + "yikao/yk-question/myList";//问题我的
    public String QUES_DETAIL = AGANYUN_IP + "yikao/yk-question/detail";//问题详情
    public String QUES_COMMENT = AGANYUN_IP + "yikao/yk-question-answer/list";//评论
    public String QUES_COMMENT_MY = AGANYUN_IP + "yikao/yk-question-answer/addQuestionAnswer";//我發表评论
    public String QUES_ZAN = AGANYUN_IP + "yikao/yk-question-answer/praise";//评论点赞
    public String QUES_ZAN_CANCEL = AGANYUN_IP + "yikao/yk-question-answer/praiseCancel";//评论点赞取消
    public String QUES_COMMENT_DELECT = AGANYUN_IP + "yikao/yk-question-answer/del";//评论删除
    public String QIANDAO = AGANYUN_IP + "yikao/uc/signIn";//签到
    public String UP_IMG = AGANYUN_IP + "yikao/uc/setAvatar";//上传头像
    public String UP_IMG_NATIVE = AGANYUN_IP + "comm/file/uploadFile";//上传头像本地
    public String UP_GET_ZZ_INFO = AGANYUN_IP + "yikao/index/getIndex";//获取招聘数据
    public String APP_ID = "767348035122757632";

    //-----------------------fixme end 新的接口

    public String VIDEO_SHARE_IP = "http://my.polyv.net/front/video/preview?vid=";

    public String IP_ = "http://yk.yinhangzhaopin.com";

    public String ZHIBO_SHARE_IP = IP + "/bshWeb/liveclass/viewliveclass.jsp?LiveID=";

    public String ZIXUN_SHARE_IP = IP + "/bshWeb/zixun/zixunDetail.jsp?ID=";


    // public String IP = "http://120.26.198.113";
    //  public String IP = "http://115.29.205.68:8080";

    public String doCheckWxRegisterPhone = "doCheckWxRegisterPhone";

    //短信
    public String DOIDENTIFYING = "doIdentifying";

    public String doRegisterWX = "doRegisterWX";
    //注册
    public String DOREGISTER = "doRegister";

    //登录
    public String DOLOGIN = "doLogin";

    //密码找回
    public String DOGETPASS = "doGetPass";
    //首页提纲
    public String DOGETFIRST = "doGetFirst";
    //视频大类
    public String DOGETVIDEOTYPE = "doGetVideoType";

    public String doGetVideoTypeNew = "doGetVideoTypeNew";
    //视频二级分类
    public String DOGETVIDEOCATALOG = "doGetVideoCatalog";

    public String DOGETVIDEOCATALOGNEW = "doGetVideoCatalogNew";

    public String DOGETVIDEOCATALOGNEWByID = "doGetVideoCatalogNewByID";

    //视频列表
    public String DOGETVIDEO = "doGetVideo";

    //直播列表
    public String DOGETLIVE = "doGetLive";


    //添加收藏
    public String DOPUTFAVORITE = "doPutFavorite";

    //取消收藏
    public String DODELFAVORITE = "doDelFavorite";

    //获取直播课课程表
    public String DOGETLIVESCHEDULE = "doGetLiveSchedule";

    //获取老师信息
    public String DOGETTECHER = "doGetTecher";
    //获取直播课的所有老师信息
    public String DOGETLIVETECHE = "doGetLiveTeche";

    //获取支付sign
    public String DOGETPAYINFO = "doGetPayInfo";
    //支付成功返回
    public String DOPUTPURCH2 = "doPutPurch2";
    //支付
    public String DOPUTPURCH = "doPutPurch";

    //是/否 已支付
    public String DOISPAY = "doIsPay";

    //获取会员 已经付费的直播课
    String DOGETSCHEDULEZHIBO = "doGetScheduleZhibo";
    //获取会员 已经付费的直播 课程
    String DOGETSCHEDULE = "doGetSchedule";
    //开机广告图片
    String GETBOOTSCROLLPICS = "getBootScrollPics";
    //广告
    String DOGETADV = "doGetAdv";
    //获取 模块
    String GETMODULE = "getModule";


    //首页的所有题目
    String DOEXAMINTITLE = "doExaminTitle";


    //根据提纲获取到题目
    String DOOUTLINETITLE = "doOutlineTitle";


    //根据题目ID 获取到 题目详情#
    String DOGETTITLES = "doGetTitles";


    String DOGETEXAMINTITLES = "doGetExaminTitles";

    //提交答题卡
    String DOPUTEXAMINSCANTRON = "doPutExaminScantron";

    //获取试卷
    String DOGETEXAMIN = "doGetExamin";
    String DOGETSEARCH = "doGetSearch";
    String DOGETFIRSTHIS = "doGetFirstHis";
    String DOGETTYPE = "doGetType";
    String DONEWEXPRACTLIST = "doNewExPractList";


    //智能涉及的取题
    String DOGETPRACTTITLES = "doGetPractTitles";

    //智能练习
    String DONEWPRACTLIST = "doNewPractList";
    //获取购买记录
    String DOGETPURCH = "doGetPurch";
    //数据报告
    String DOGETREPPRACTICE = "doGetRepPractice";

    //首页错题
    String DOGETERRTITLE = "doGetErrTitle";
    //获取笔记
    String DOGETNOTE = "doGetNote";
    //提交笔记
    String DOPUTNOTE = "doPutNote";

    //APP 系统配置
    String DOGETPARAM = "doGetParam";

    //消息令牌
    String DOPUTMSGTOKEN = "doPutMsgToken";
    //获取所有模考大赛
    String DOGETALLMOCK = "doGetAllMock";
    //报名
    String DOPUTSIGN = "doPutSign";
    //智能联系
    String DOGETHISPRACT = "doGetHisPract";


    String DOGETALLHISPRACT = "doGetAllHisPract";
    //智能组卷
    String DOGETHISEXPRACT = "doGetHisExPract";
    //笔记
    String DOGETNOTES = "doGetNoteS";
    //获取收藏
    String DOGETFAVORITE = "doGetFavorite";

    //修改密码
    String DOUPPASS = "doUpPass";

    //修改昵称
    String DOPUTPET = "doPutPet";
    //我的消息
    String DOGETMSG = "doGetMsg";

    //广告详情
    String DOGETADVID = "doGetAdvID";

    String DOPUTPRACTSCANTRON = "doPutPractScantron";
    //智能练习/组卷
    String DOGETPRACTTITLE = "doGetPractTitle";
    String ANDROIDUPDATE = "androidUpdate";
    //节点 错题
    String DOGETERRTITLEOUT = "doGetErrTitleOut";
    String DOGETEXAMDAY = "doGetExamDay";
    String DOGETINFO = "doGetInfo";


    String getNews = "getNews";
    // String WANGSHOU = "http://ws.yinhangzhaopin.com";

    String WANGSHOU = "http://ws.yinhangzhaopin.com/plus/ajax_resume.php?username=";

    String ZIXUNIP = "http://m.yinhangzhaopin.com/";

    String DOPUTEXAMINTITLEERR = "doPutExaminTitleErr";

    String DOCARD = "doCard";

    String DOFREOUTLINE = "doFreOutline";
    String DOOUTLINETITLEHIS = "doOutlineTitleHis";
    String DOGETADDR = "doGetAddr";

    String DOPUTADDR = "doPutAddr";
    String DOGETOUTLINE = "doGetOutline";
}
