package com.udit.frame.common.cancas;
import android.graphics.Color;
import android.graphics.YuvImage;
import android.util.Log;


public class FingerMatrix {
	public static int colorValue=Color.RED;
	private float maxX = 0;
	private float maxY = 0;
	private float minX = 0;
	private float minY = 0;
	
	
	
	
	/**
	 * 初始化数�?
	 * @param x
	 * @param y
	 */
	public void init(float x,float y){
		maxX=x;
		minX=x;
		maxY=y;
		minY=y;
	}

	public void getx(float x){
		if (x<0) {
			return;
		}else {
			if (maxX==minX) {			//��һ�ν���ʱִ��
				if (x>maxX) {
					maxX=x;
				}else if(x<minX){
					minX=x;
				}
			}else {						// �ڶ��ι���ʱִ��
				if (x>maxX&&x>minX) {
					maxX=x;
				}else if (x<minX&&x<maxX) {
					minX=x;
				}
			}
		}
	}
	
	
	public void getY(float y){
		if (y<0) {
			return;
		}else {
			if (minY==maxY) {			
				if (y<minY) {
					minY = y;
				}else if (y>maxY) {
					maxY =y;
				}	
			}else {						
				if (y>minY&&y>maxY) {
					maxY =y;
				}else if (y<maxY&&y<minY) {
					minY = y;
				}
			}
		}
	}
	
	
	public float getMaxX() {
		return maxX;
	}

	public float getMaxY() {
		return maxY;
	}

	public float getMinX() {
		return minX;
	}

	public float getMinY() {
		return minY;
	}

}
