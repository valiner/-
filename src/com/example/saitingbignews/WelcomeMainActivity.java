package com.example.saitingbignews;

import java.util.Timer;
import java.util.TimerTask;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class WelcomeMainActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome_main);
		
		
		 Timer timer = new Timer();
	        TimerTask task = new TimerTask(){
	         @Override
	         public void run() {
	         Intent intent = new Intent(WelcomeMainActivity.this, FMainActivity.class); 
	         startActivity(intent);
	         WelcomeMainActivity.this.finish();
	         }
	        };
	        timer.schedule(task, 2000);
	    }
		
	

}
