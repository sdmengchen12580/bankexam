package com.udit.frame.common.baseImage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.frame.R;
import com.udit.frame.common.texthtml.HtmlTextView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCommonUtils;
import com.udit.frame.utils.MyLogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class SpaceImageDetailActivity extends Activity implements View.OnTouchListener {

	private final String TAG = this.getClass().getSimpleName();

	private String mDatas;
	//private int mPosition;
	private int mLocationX;
	private int mLocationY;
	private int mWidth;
	private int mHeight;


	ViewPager viewPager_img;


	List<SmoothImageView>  mImageViews;

	private DisplayMetrics displayMetrics;

	public static void startSpaceImageDetailActivity(BaseActivity<?> mActivity,ArrayList<String> list)
	{
		Intent intent = new Intent(mActivity,SpaceImageDetailActivity.class);
		intent.putStringArrayListExtra("list_photo",list);
		mActivity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		initData();

	}

	private void initData() {
		viewPager_img = (ViewPager) findViewById(R.id.viewPager_img);

		Object object_list = getIntent().getExtras().getSerializable("list_photo");
		displayMetrics =  MyCommonUtils.getscreen(this);
		if(object_list!=null)
		{
			List<String> list = (List<String>) object_list;

			mImageViews = new ArrayList<>();


			for(String s:list)
			{

				String img_path = s;
				SmoothImageView imageView = new SmoothImageView(this);
				imageView.setClickable(true);
				imageView.transformIn();
				MyLogUtils.e(TAG,"path:"+img_path);
				imageView.setLayoutParams(new ViewGroup.LayoutParams(
						displayMetrics.widthPixels, displayMetrics.heightPixels));
				imageView.setScaleType(ScaleType.FIT_CENTER);
				imageView.setOnTouchListener(this);

				ImageLoader.getInstance().displayImage(img_path, imageView);
				mImageViews.add(imageView);

			}
			viewPager_img.setAdapter(new MyAdapter());
		}
		else
		{
			HashMap<String,String> map = (HashMap<String, String>) getIntent().getExtras().getSerializable("map_photo");

			mImageViews = new ArrayList<>();


			if(map!=null)
			{
				Iterator<String> iterator = map.keySet().iterator();

				while(iterator.hasNext())
				{
					String img_name = iterator.next();
					String img_path = map.get(img_name);
					SmoothImageView imageView = new SmoothImageView(this);
					imageView.setClickable(true);
					imageView.transformIn();
					imageView.setOnTouchListener(this);
				/*imageView.setLayoutParams(new ViewGroup.LayoutParams(
						WindowUtil.screenWidth, WindowUtil.screenHeight));*/
					imageView.setScaleType(ScaleType.FIT_CENTER);

					if(img_path.contains("http"))
					{
						ImageLoader.getInstance().displayImage(img_path, imageView);
					}
					else
					{
						ImageLoader.getInstance().displayImage("file://"+img_path, imageView);
					}

					mImageViews.add(imageView);

				}

				//设置Adapter
				viewPager_img.setAdapter(new MyAdapter());

			/*//设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
			viewPager_img.setCurrentItem((mImageViews.length) * 5);*/
			}
		}



	}


	private float DownX = 0f;
	private float DownY = 0f;

	private float moveX = 0f;

	private float moveY = 0f;

	private long currentMs = 0L;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
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
				MyLogUtils.e(TAG,"ACTION_UP");
				long moveTime = System.currentTimeMillis() - currentMs;
				if(moveTime>2000&& (moveX>20|| moveY>20))
				{
					return false;
				}
				else
				{
					finish();
					return true;
				}
			default:
				break;
		}
		return false;
	}

	public class MyAdapter extends PagerAdapter
	{

		@Override
		public int getCount() {
			return mImageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup  container, int position, Object object) {
			container.removeView(mImageViews.get(position));

		}

		/**
		 * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
		 */
		@Override
		public Object instantiateItem(ViewGroup  container, int position) {
			container.addView(mImageViews.get(position));
			return mImageViews.get(position);
		}



	}

	@Override
	public void onBackPressed() {
		finish();
		/*imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
			@Override
			public void onTransformComplete(int mode) {
				if (mode == 2) {
					finish();
				}
			}
		});
		imageView.transformOut();*/
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			overridePendingTransition(0, 0);
		}
	}

}
