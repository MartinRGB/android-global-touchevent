package com.kpbird.globaltouchevent;

import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class GlobalTouchService extends Service implements OnTouchListener{

	private String TAG = this.getClass().getSimpleName();
	// window manager 
	private WindowManager mWindowManager;
	// linear layout will use to detect touch event
	private LinearLayout touchLayout;
	private LinearLayout squareLayout;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		// create linear layout


		touchLayout = new LinearLayout(this);
		// set layout width 30 px and height is equal to full screen
		LayoutParams lp = new LayoutParams(100, LayoutParams.MATCH_PARENT);
		touchLayout.setLayoutParams(lp);
		// set color if you want layout visible on screen
//		touchLayout.setBackgroundColor(Color.CYAN); 
		// set on touch listener
		touchLayout.setOnTouchListener(this);
		touchLayout.setBackgroundColor(Color.RED);


		squareLayout = new LinearLayout(this);
		LayoutParams lp0 = new LayoutParams(100, 100);
		squareLayout.setLayoutParams(lp0);
		squareLayout.setBackgroundColor(Color.BLUE);

		// fetch window manager object 
		 mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		 // set layout parameter of window manager
		 WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
	        		30, // width of layout 30 px
	        		WindowManager.LayoutParams.MATCH_PARENT, // height is equal to full screen
	                WindowManager.LayoutParams.TYPE_PHONE, // Type Ohone, These are non-application windows providing user interaction with the phone (in particular incoming calls).
	                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // this window won't ever get key input focus  
	                PixelFormat.TRANSLUCENT);      
         mParams.gravity = Gravity.LEFT | Gravity.TOP;   
 		Log.i(TAG, "add View");

		WindowManager.LayoutParams mParams2 = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.MATCH_PARENT, // width of layout 30 px
				30, // height is equal to full screen
				WindowManager.LayoutParams.TYPE_PHONE, // Type Ohone, These are non-application windows providing user interaction with the phone (in particular incoming calls).
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // this window won't ever get key input focus
				PixelFormat.TRANSLUCENT);

		mWindowManager.addView(touchLayout, mParams);
		mWindowManager.addView(squareLayout,mParams2);
		
	}
	

	@Override
	public void onDestroy() {
		 if(mWindowManager != null) {
	            if(touchLayout != null) mWindowManager.removeView(touchLayout);
			 	if(squareLayout != null) mWindowManager.removeView(squareLayout);
	        }
		super.onDestroy();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP)
			Log.i(TAG, "Action :" + event.getAction() + "\t X :" + event.getRawX() + "\t Y :"+ event.getRawY());

		if(event.getAction() == MotionEvent.ACTION_UP)
			if(event.getRawY() < 960){
				ObjectAnimator mAnimator = ObjectAnimator.ofFloat(squareLayout,"translationX",squareLayout.getTranslationX(),1200);
				mAnimator.setDuration(500);
				mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
				mAnimator.start();
				//listener.onTriggerAnimation(true);
			}



		if(event.getAction() == MotionEvent.ACTION_MOVE)
			Log.i(TAG, "Action :" + event.getAction() + "\t X :" + event.getRawX() + "\t Y :"+ event.getRawY());
			squareLayout.setTranslationX(1920/2 - event.getY());
		
		return true;
	}


//	public interface SimpleEventListener
//	{
//		void onTriggerAnimation(boolean boo);
//		void onTriggerMenu(boolean boo);
//	}
//	//实例化一个监听器的数值为空
//	private SimpleEventListener listener;
//
//	public void setOnGloabalEventListener(SimpleEventListener listener)
//	{
//		this.listener = listener;
//	}




}
