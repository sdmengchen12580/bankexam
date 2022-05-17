package com.udit.bankexam.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import android.view.View;

public class SwitchButton extends AppCompatCheckBox {

    private static final int DEFAULT_HEIGHT = 125;

    private static final int DEFAULT_WIDTH = 200;

    private static final String TAG = "SwitchButton";

    private CheakCallBack cheakCallBack;

    private boolean isCheckEd = false;

    private long mAnimateDuration = 300L;

    private int mBackgroundColorChecked = -13253802;

    private int mBackgroundColorUnchecked = -3355444;

    private float mButtonCenterXOffset;

    private int mButtonColor = -1;

    private float mColorGradientFactor = 1.0F;

    private Paint mPaint;

    private RectF mRectF;

    public SwitchButton(Context paramContext) {
        this(paramContext, null);
    }

    public SwitchButton(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public SwitchButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        setButtonDrawable(null);
        setBackgroundResource(0);
        setChecked(this.isCheckEd);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mRectF = new RectF();
        setOnClickListener(new OnClickListener() {
            public void onClick(View param1View) {
                if (SwitchButton.this.cheakCallBack != null) {
                    SwitchButton.this.isCheckEd = !SwitchButton.this.isCheckEd;
                    SwitchButton.this.cheakCallBack.isChecked(SwitchButton.this.isCheckEd);
                }
                SwitchButton.this.startAnimate();
            }
        });
    }

    private int getCurrentColor(float paramFloat, int paramInt1, int paramInt2) {
        int m = Color.red(paramInt1);
        int j = Color.blue(paramInt1);
        int i = Color.green(paramInt1);
        paramInt1 = Color.alpha(paramInt1);
        int i1 = Color.red(paramInt2);
        int n = Color.blue(paramInt2);
        int k = Color.green(paramInt2);
        paramInt2 = Color.alpha(paramInt2);
        m = (int) (m + (i1 - m) * paramFloat);
        j = (int) (j + (n - j) * paramFloat);
        i = (int) (i + (k - i) * paramFloat);
        return Color.argb((int) (paramInt1 + paramFloat * (paramInt2 - paramInt1)), m, i, j);
    }

    private void startAnimate() {
        float f1 = (getMeasuredHeight() - this.mPaint.getStrokeWidth() * 4.0F) / 2.0F;
        float f2 = getMeasuredWidth();
        float f3 = this.mPaint.getStrokeWidth();
        float f4 = this.mPaint.getStrokeWidth();
        float f5 = this.mPaint.getStrokeWidth();
        float f6 = this.mPaint.getStrokeWidth();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, "buttonCenterXOffset", new float[]{f2 - f3 - f4 - f1 - f5 + f6 + f1, 0.0F});
        objectAnimator1.setDuration(this.mAnimateDuration);
        objectAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
                SwitchButton.this.invalidate();
            }
        });
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(this, "colorGradientFactor", new float[]{0.0F, 1.0F});
        objectAnimator2.setDuration(this.mAnimateDuration);
        animatorSet.play(objectAnimator1).with(objectAnimator2);
        animatorSet.start();
    }

    protected void onDraw(Canvas paramCanvas) {
        float f1;
        super.onDraw(paramCanvas);
        this.mPaint.setStrokeWidth(getMeasuredWidth() / 40.0F);
        if (isChecked()) {
            this.mPaint.setColor(getCurrentColor(this.mColorGradientFactor, this.mBackgroundColorUnchecked, this.mBackgroundColorChecked));
        } else {
            this.mPaint.setColor(getCurrentColor(this.mColorGradientFactor, this.mBackgroundColorChecked, this.mBackgroundColorUnchecked));
        }
        this.mRectF.set(this.mPaint.getStrokeWidth(), this.mPaint.getStrokeWidth(), getMeasuredWidth() - this.mPaint.getStrokeWidth(), getMeasuredHeight() - this.mPaint.getStrokeWidth());
        paramCanvas.drawRoundRect(this.mRectF, getMeasuredHeight(), getMeasuredHeight(), this.mPaint);
        this.mPaint.setColor(this.mButtonColor);
        float f2 = (getMeasuredHeight() - this.mPaint.getStrokeWidth() * 4.0F) / 2.0F;
        if (isChecked()) {
            f1 = getMeasuredWidth() - f2 - this.mPaint.getStrokeWidth() - this.mPaint.getStrokeWidth() - this.mButtonCenterXOffset;
        } else {
            f1 = this.mPaint.getStrokeWidth() + f2 + this.mPaint.getStrokeWidth() + this.mButtonCenterXOffset;
        }
        paramCanvas.drawCircle(f1, getMeasuredHeight() / 2.0F, f2, this.mPaint);
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        int j = MeasureSpec.getMode(paramInt1);
        paramInt1 = MeasureSpec.getSize(paramInt1);
        int i = MeasureSpec.getMode(paramInt2);
        paramInt2 = MeasureSpec.getSize(paramInt2);
        if (j != MeasureSpec.EXACTLY)
            paramInt1 = getPaddingLeft() + 200 + getPaddingRight();
        if (i != MeasureSpec.EXACTLY)
            paramInt2 = getPaddingTop() + 125 + getPaddingBottom();
        setMeasuredDimension(paramInt1, paramInt2);
    }

    public void setAnimateDuration(long paramLong) {
        this.mAnimateDuration = paramLong;
    }

    public void setBackgroundColorChecked(int paramInt) {
        this.mBackgroundColorChecked = paramInt;
    }

    public void setBackgroundColorUnchecked(int paramInt) {
        this.mBackgroundColorUnchecked = paramInt;
    }

    public void setButtonCenterXOffset(float paramFloat) {
        this.mButtonCenterXOffset = paramFloat;
    }

    public void setButtonColor(int paramInt) {
        this.mButtonColor = paramInt;
    }

    public void setCheakCallBack(CheakCallBack paramCheakCallBack, boolean paramBoolean) {
        this.isCheckEd = paramBoolean;
        this.cheakCallBack = paramCheakCallBack;
        setChecked(paramBoolean);
    }

    public void setColorGradientFactor(float paramFloat) {
        this.mColorGradientFactor = paramFloat;
    }

    public static interface CheakCallBack {
        void isChecked(boolean param1Boolean);
    }
}
