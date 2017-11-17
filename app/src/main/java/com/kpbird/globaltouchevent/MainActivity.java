package com.kpbird.globaltouchevent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Intent globalService;
	GlobalTouchService mTouchService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		globalService = new Intent(this,GlobalTouchService.class);


		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {


				Intent i = new Intent(Intent.ACTION_MAIN);
				i.addCategory(Intent.CATEGORY_HOME);
				startActivity(i);
			}
		},2000 );

	}

	@Override
	public void onResume() {
		super.onResume();  // Always call the superclass method first
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {


				Intent i = new Intent(Intent.ACTION_MAIN);
				i.addCategory(Intent.CATEGORY_HOME);
				startActivity(i);
			}
		},2000 );
	}



	public void buttonClicked(View v){
		
		if(v.getTag() == null){
			startService(globalService);
			v.setTag("on");
			((Button)v).setText("Stop Service");
			Toast.makeText(this, "Start Service", Toast.LENGTH_SHORT).show();
		}
		else{
			stopService(globalService);
			v.setTag(null);
			((Button)v).setText("Start Service");
			Toast.makeText(this, "Stop Service", Toast.LENGTH_SHORT).show();
		}
		
	}



}
