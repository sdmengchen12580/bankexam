package com.udit.frame.common.texthtml;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.udit.frame.common.baseImage.SpaceImageDetailActivity;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyLogUtils;

import java.util.ArrayList;


@SuppressLint("AppCompatCustomView")
public class HtmlTextView extends TextView {
    private final String TAG = this.getClass().getSimpleName();
    private Spanned spanned;

    private Context mContext;

    public HtmlTextView(Context context) {
        super(context);
        this.mContext = context;
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    HtmlTagHandler tagHandler;

    UrlImageGetter imgGetter;

    boolean flag;
    /**
     * Parses String containing HTML to Android's Spannable format and displays
     * it in this TextView.
     *
     * @param html String containing HTML, for example: "<b>Hello world!</b>"
     */
    public void setHtmlFromString(String html, boolean useLocalDrawables, final boolean flag) {
        //Html.ImageGetter imgGetter;
      /*  if (useLocalDrawables) {
            imgGetter = new LocalImageGetter(getContext());
        } else {*/
        imgGetter = new UrlImageGetter(this, getContext());

        this.flag = flag;

        /*}*/
        tagHandler = new HtmlTagHandler(getContext());
        // this uses Android's Html class for basic parsing, and HtmlTagHandler

        spanned = Html.fromHtml(html, imgGetter, tagHandler);

     /*   if (spanned instanceof SpannableStringBuilder) {
            ImageSpan[] imageSpans =  spanned.getSpans(0, spanned.length(), ImageSpan.class);
            for (ImageSpan imageSpan : imageSpans) {
                int start = spanned.getSpanStart(imageSpan);
                int end = spanned.getSpanEnd(imageSpan);
                Drawable d = imageSpan.getDrawable();
                FileImageSpan newImageSpan = new FileImageSpan(d, ImageSpan.ALIGN_BASELINE);
                ((SpannableStringBuilder) spanned).setSpan(newImageSpan, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                ((SpannableStringBuilder) spanned).removeSpan(imageSpan);
            }
        }
*/
        setText(spanned);


       // setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // make links work
        setMovementMethod(LinkMovementMethod.getInstance());


        // no flickering when clicking textview for Android < 4, but overriders
        // color...
        // text.setTextColor(getResources().getColor(android.R.color.secondary_text_dark_nodisable));


        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!flag)
                    return false;
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        MyLogUtils.e(TAG,"ACTION_DOWN");
                        DownX = event.getX();
                        DownY = event.getY();
                        moveX = 0;
                        moveY = 0;
                        currentMs = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        MyLogUtils.e(TAG,"ACTION_MOVE");
                        moveX +=Math.abs(event.getX()-DownX);
                        moveY +=Math.abs(event.getY()-DownY);
                        DownX = event.getX();
                        DownY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:

                        MyLogUtils.e(TAG,"ACTION_UP:"+flag);

                        long moveTime = System.currentTimeMillis() - currentMs;
                        if(moveTime>2000&& (moveX>20|| moveY>20))
                        {
                            return false;
                        }
                        else
                        {
                            MyLogUtils.e(TAG,"ACTION_UP_X:"+moveX+" moveY:"+moveY+" moveTime:"+moveTime);
                            CharSequence text = getText();
                            Spannable sText = Spannable.Factory.getInstance().newSpannable(text);
                            TextView widget = HtmlTextView.this;
                            int x = (int) event.getX();
                            int y = (int) event.getY();

                            x -= widget.getTotalPaddingLeft();
                            y -= widget.getTotalPaddingTop();

                            x += widget.getScrollX();
                            y += widget.getScrollY();

                            Layout layout = widget.getLayout();
                            int line = layout.getLineForVertical(y);
                            int off = layout.getOffsetForHorizontal(line, x);

                            ImageSpan[] imageSpans = sText.getSpans(off, off, ImageSpan.class);
                            MyLogUtils.e(TAG, "URL" + x + "  " + y + "   off:" + off + "   line:" + line);

                            MyLogUtils.e(TAG, "URL_html_event" + (imageSpans == null ? "null" : "not null:" + imageSpans.length));

                            if (imageSpans.length != 0) {

                                ArrayList<String> list = new ArrayList<>();
                                for(int i = 0;i<imageSpans.length;i++)
                                {
                                    MyLogUtils.e(TAG,imageSpans[i].getSource());
                                    list.add(imageSpans[i].getSource());
                                }
                                SpaceImageDetailActivity.startSpaceImageDetailActivity((BaseActivity<?>) mContext,list);
                                return true;
                            }
                            else
                                return false;
                        }

                    default:
                        return false;
                }

                return false;
            }
        });
    }
    private float ITEM_HEIGHT = 120;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      /*  setIncludeFontPadding(false);
        setGravity(Gravity.CENTER_VERTICAL);
        setLineSpacing(ITEM_HEIGHT, 0);
        int top = (int) ((ITEM_HEIGHT - getTextSize()) * 0.5f);
        setPadding(getPaddingLeft(), top, getPaddingRight(), -top);
        requestLayout();
        invalidate();*/
      /*  TextPaint paint = new TextPaint();
        StaticLayout staticLayout = new StaticLayout(spanned,paint,canvas.getWidth(), Layout.Alignment.ALIGN_CENTER,1.0f,0.0f,false);
        staticLayout.draw(canvas);
        canvas.restore();*/

    }
    private float DownX = 0f;
    private float DownY = 0f;

    private float moveX = 0f;

    private float moveY = 0f;

    private long currentMs = 0L;


   @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(!flag)
            return false;
       else
        return super.onTouchEvent(event);





    }

    @Override
    protected void dispatchVisibilityChanged(@NonNull View changedView,
                                             int visibility) {
        super.dispatchVisibilityChanged(changedView, visibility);

        if (visibility == View.GONE) {
            MyLogUtils.e(TAG, "dispatchVisibilityChanged:" + visibility);
            this.destroyDrawingCache();
        }

    }

    /*  @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        String url =  tagHandler.getUrl();
        MyLogUtils.e(TAG,"URL_html_event"+(url==null?"null":"not null"));

        if(url!=null)
        {
            return super.dispatchTouchEvent(event);
        }
        else
            return false;
       *//* MyLogUtils.e(TAG,"URL_  TEXT:"+text);
        if(text.toLowerCase().equals("img"))
        {
            return true;
        }
        else
            return false;*//*
    }*/

  /*  @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }*/
}
