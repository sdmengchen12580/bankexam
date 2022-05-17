package com.udit.bankexam.ui.exam.holder;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.ui.exam.JiuCuoActivity;
import com.udit.bankexam.ui.exam_err.ExamErrTitleActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import org.w3c.dom.Text;

/**
 * Created by zb on 2017/5/10.
 */

public class HolderWindow {

    private final String TAG = this.getClass().getSimpleName();

    private static HolderWindow HolderWindow;

    public static HolderWindow getIntance(Context context, ChangeTheme changeTheme, ChangeSizeType changeSizeType) {
        if (HolderWindow == null)
            HolderWindow = new HolderWindow(context, changeSizeType, changeTheme);

        return HolderWindow;
    }

    private View mView_main;

    private Context mContext;

    private PopupWindow window_gengduo;

    private LinearLayout ll_pop_gengduo;
    private LinearLayout ll_fengxiang;
    private ImageView image_share, image_jiucuo;
    private TextView text_share;

    private TextView text_jiucuo;

    private RadioGroup text_zt_group;
    private RadioButton text_zt_one;
    private RadioButton text_zt_two;
    private RadioButton text_zt_three;
    private ImageView img_night;
    private ImageView img_day;


    private ChangeSizeType changeSizeType;

    private ChangeTheme changeTheme;

    private FenXiangListener fenXiangListener;

    private JiCuoListener jiuCuoListener;

    private TextSize mCurrent_TextSize;

    public HolderWindow(Context context, ChangeSizeType changeSizeType, ChangeTheme changeTheme) {
        this.mContext = context;
        this.changeSizeType = changeSizeType;
        this.changeTheme = changeTheme;
        String size = SaveUtils.getExamTextSize(context);
        if (MyCheckUtils.isEmpty(size))
            mCurrent_TextSize = TextSize.SIZE_TWO;
        else
            mCurrent_TextSize = TextSize.getTextSize(size);


        init();
        changeSizeTypeLocal(mCurrent_TextSize);
        //text_zt_group.check(R.id.text_zt_three);
    }

    public void setFenXiangListener(FenXiangListener fenXiangListener) {
        this.fenXiangListener = fenXiangListener;
    }

    public void init() {
        View popupWindow_view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_gengduo, null, false);

        window_gengduo = new PopupWindow(popupWindow_view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        ViewUtils.initHolderView(this, popupWindow_view, R.id.class);

        window_gengduo.setBackgroundDrawable(new BitmapDrawable());
        window_gengduo.setOutsideTouchable(true);
        MyGengDuoGongClick click = new MyGengDuoGongClick();
        ll_fengxiang.setOnClickListener(click);
        text_zt_one.setOnClickListener(click);
        text_zt_two.setOnClickListener(click);
        text_zt_three.setOnClickListener(click);
//        img_night.setOnClickListener(click);
//        img_day.setOnClickListener(click);
        text_jiucuo.setOnClickListener(click);
        window_gengduo.dismiss();
    }

    public PopupWindow getWindow_gengduo() {
        return window_gengduo;
    }

    public void initTheme() {
        //更多
        ll_pop_gengduo.setBackgroundColor(mContext.getResources().getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_caozuo)));
        //分享 图标
        image_share.setImageDrawable(mContext.getResources().getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.day_shiti_icon_share)));
        //分享文字
        text_share.setTextColor(mContext.getResources().getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_share)));
        image_jiucuo.setImageDrawable(mContext.getResources().getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.icon_jiucuo)));
        text_jiucuo.setTextColor(mContext.getResources().getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_share)));
        //夜间模式
        img_night.setImageDrawable(mContext.getResources().getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.subject_switch_day_left)));
        //白天模式
        img_day.setImageDrawable(mContext.getResources().getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.subject_switch_day_right)));
        //字符
        text_zt_one.setTextColor(mContext.getResources().getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_radio_exam_text)));
        text_zt_two.setTextColor(mContext.getResources().getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_radio_exam_text)));
        text_zt_three.setTextColor(mContext.getResources().getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_radio_exam_text)));
        changeSizeTypeLocal(mCurrent_TextSize);
    }

    public TextSize getmCurrent_TextSize() {
        return mCurrent_TextSize;
    }

    public void changeSizeTypeLocal(TextSize size) {
        if (size == TextSize.SIZE_ONE) {
            text_zt_group.check(R.id.text_zt_one);
//            text_zt_one.setChecked(true);
//            text_zt_two.setChecked(false);
//            text_zt_three.setChecked(false);
            mCurrent_TextSize = TextSize.SIZE_ONE;
            changeSizeType.ChangeTextSize(TextSize.SIZE_ONE);
        } else if (size == TextSize.SIZE_TWO) {
            text_zt_group.check(R.id.text_zt_two);
           /* text_zt_one.setChecked(false);
            text_zt_two.setChecked(true);
            text_zt_three.setChecked(false);*/
            mCurrent_TextSize = TextSize.SIZE_TWO;
            changeSizeType.ChangeTextSize(TextSize.SIZE_TWO);
        } else if (size == TextSize.SIZE_THREE) {
            text_zt_group.check(R.id.text_zt_three);
           /* text_zt_one.setChecked(false);
            text_zt_two.setChecked(false);
            text_zt_three.setChecked(true);*/
            mCurrent_TextSize = TextSize.SIZE_THREE;
            changeSizeType.ChangeTextSize(TextSize.SIZE_THREE);
        }
    }


    public enum TextSize {
        SIZE_ONE("size_one", 14f),
        SIZE_TWO("size_two", 16f),
        SIZE_THREE("size_three", 18f);

        String name;
        float size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getSize() {
            return size;
        }

        public void setSize(float size) {
            this.size = size;
        }

        TextSize(String name, float size) {
            this.name = name;
            this.size = size;
        }

        public static TextSize getTextSize(String name) {
            for (TextSize size : TextSize.values()) {
                if (size.name.equals(name))
                    return size;
            }
            return null;
        }
    }

    public interface ChangeSizeType {
        public void ChangeTextSize(TextSize size);
    }

    public interface ChangeTheme {
        public void ChangeTheme(ThemeManager.ThemeMode mode);

    }

    public interface FenXiangListener {
        public void fenxiang();
    }

    public interface JiCuoListener {
        public void jiucuo();
    }

    public void setJiuCuoListener(JiCuoListener jiuCuoListener) {
        this.jiuCuoListener = jiuCuoListener;
    }

    private class MyGengDuoGongClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.text_jiucuo:
                    //纠错

                    jiuCuoListener.jiucuo();

                    break;
                case R.id.ll_fengxiang:
                    MyLogUtils.e(TAG, "分享");
                    fenXiangListener.fenxiang();

                    //   window_gengduo.dismiss();
                    break;
                case R.id.text_zt_one:
                    MyLogUtils.e(TAG, "字体 一号");

                    changeSizeTypeLocal(TextSize.SIZE_ONE);
                    // changeSizeType.ChangeTextSize(TextSize.SIZE_ONE);

                    break;
                case R.id.text_zt_two:
                    MyLogUtils.e(TAG, "字体 二号");
                    changeSizeTypeLocal(TextSize.SIZE_TWO);
                    // changeSizeType.ChangeTextSize(TextSize.SIZE_TWO);


                    break;
                case R.id.text_zt_three:
                    MyLogUtils.e(TAG, "字体 三号");
                    changeSizeTypeLocal(TextSize.SIZE_THREE);
                    // changeSizeType.ChangeTextSize(TextSize.SIZE_THREE);

                    break;
                case R.id.img_night:
                    MyLogUtils.e(TAG, "黑夜模式");
                    // ThemeManager.setThemeMode(ThemeManager.ThemeMode.NIGHT);
                    changeTheme.ChangeTheme(ThemeManager.ThemeMode.NIGHT);

                    break;
                case R.id.img_day:
                    MyLogUtils.e(TAG, "白天模式");
                    changeTheme.ChangeTheme(ThemeManager.ThemeMode.DAY);

                    // ThemeManager.setThemeMode(ThemeManager.ThemeMode.DAY);
                    break;
                default:
                    break;
            }
        }
    }


}
