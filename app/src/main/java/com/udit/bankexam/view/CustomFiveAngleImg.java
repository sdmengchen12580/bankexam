package com.udit.bankexam.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.udit.frame.utils.Utils;

public class CustomFiveAngleImg extends AppCompatImageView {
  int corner;
  
  float height;
  
  float width;
  
  public CustomFiveAngleImg(Context paramContext) {
    this(paramContext, null);
    init(paramContext, null);
  }
  
  public CustomFiveAngleImg(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
    init(paramContext, paramAttributeSet);
  }
  
  public CustomFiveAngleImg(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet) {
    this.corner = Utils.dip2px(paramContext, 5.0F);
    if (Build.VERSION.SDK_INT < 18)
      setLayerType(1, null); 
    setScaleType(ScaleType.FIT_XY);
  }
  
  protected void onDraw(Canvas paramCanvas) {
    if (this.width >= this.corner && this.height > this.corner) {
      Path path = new Path();
      path.moveTo(this.corner, 0.0F);
      path.lineTo(this.width - this.corner, 0.0F);
      path.quadTo(this.width, 0.0F, this.width, this.corner);
      path.lineTo(this.width, this.height - this.corner);
      path.quadTo(this.width, this.height, this.width - this.corner, this.height);
      path.lineTo(this.corner, this.height);
      path.quadTo(0.0F, this.height, 0.0F, this.height - this.corner);
      path.lineTo(0.0F, this.corner);
      path.quadTo(0.0F, 0.0F, this.corner, 0.0F);
      paramCanvas.clipPath(path);
    } 
    super.onDraw(paramCanvas);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.width = getWidth();
    this.height = getHeight();
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\CustomFiveAngleImg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */