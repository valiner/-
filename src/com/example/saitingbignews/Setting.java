package com.example.saitingbignews;



import com.example.Tool.SkinSettingManager;
import com.example.Tool.SpTools;

import android.R.color;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings.TextSize;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Setting extends Activity {
	private int themes;


	private ImageButton ib_size;
	private ImageButton ib_genxing;
	private ImageButton ib_lianxi;
	private ImageButton ib_back;

	private ToggleButton mton;


	private LinearLayout lin;


	private LinearLayout ll_lianxi;


	private LinearLayout ll_jiance;


	private LinearLayout ll_size;
	
	int textSizeIndex=1;


	private AlertDialog dialog;


	private TextView     tv_size;


	private int size;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		
		initView();
		
		initData();
		
		initEvent();
		
		
	

	}
	
	
	private void showChangeTextSizeDialog() {
		AlertDialog.Builder ab = new AlertDialog.Builder(Setting.this);
		ab.setTitle("改变字体大小");
		String[] textSize = new String[]{"大号","正常","小号"};	
		ab.setSingleChoiceItems(textSize, textSizeIndex, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				textSizeIndex = which;
				SpTools.setInt(Setting.this, "title_size", textSizeIndex);
				setTextSize(textSizeIndex);
				dialog.dismiss();
				
				
				
			}
		});
		dialog = ab.create();
		dialog.show();
		
	}
	
	private void setTextSize(int s) {
		switch (s) {
		case 0://
			tv_size.setText("大");
			break;
		case 1: // 
			tv_size.setText("中");
			break;
		case 2: // 
			tv_size.setText("小");
			break;
		default:
			break;
		}	
		
	}
	
	
/*	public void night(View v){
		themes = new SkinSettingManager(Setting.this).toggleSkins();
		Setting.this.setTheme(themes);
		setContentView(R.layout.activity_setting); 
		SpTools.setBoolean(Setting.this, "is",true);
		Toast.makeText(Setting.this, "sss", 0).show();
	}
	*/

	@SuppressLint("ResourceAsColor") private void initEvent() {
	ib_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();
			}
		});
	
		
		// TODO Auto-generated method stub
	OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch (v.getId()) {
			case R.id.ll_jiance:
				Toast.makeText(Setting.this, "已是最新版", 0).show();
				break;
			case R.id.ll_lianxi:
				Intent intent = new Intent(Setting.this,ConnectmeActivity.class);
				startActivity(intent);	
				break;

			case R.id.ll_size:
				showChangeTextSizeDialog();
		
				break;
				
				
			
			
			case R.id.ib_lianxi:
				
			}
			
		}
	};
		
	 ll_size.setOnClickListener(listener);
	 ll_jiance.setOnClickListener(listener);
	 ll_lianxi.setOnClickListener(listener);
    
 
	 
 mton.setOnCheckedChangeListener(new OnCheckedChangeListener() {  
		  
		    @Override  
		    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {  
		        // TODO Auto-generated method stub 
		    	
		        if(isChecked){  
		        	//themes = new SkinSettingManager(Setting.this).toggleSkins();
		    		//Setting.this.setTheme(themes);
		    		//setContentView(R.layout.activity_setting); 
		        	lin.setBackgroundColor(R.color.night);
		    		SpTools.setBoolean(Setting.this, "is",true);
		    		SpTools.setBoolean(Setting.this, "ischeck",true);
		    		SpTools.setBoolean(Setting.this, "issetting", true);
		        	System.out.println("xxuanzhong ~~~~~~~~~~~~~~~~~~");
		        }else{  
		        	//themes = new SkinSettingManager(Setting.this).toggleSkins();
		    		//Setting.this.setTheme(themes);
		    		//setContentView(R.layout.activity_setting); 
		        	lin.setBackgroundColor(color.white);
		    		SpTools.setBoolean(Setting.this, "is",false);
		    		SpTools.setBoolean(Setting.this, "ischeck",false);
		    		SpTools.setBoolean(Setting.this, "issetting",false);
		    		System.out.println("weibeixuanzhong-------------------");
		            //未选中  
		        }  
		    }  
		});
		
	}
	

	private void initData() {
		// TODO Auto-generated method stub
		
	}

	@SuppressLint("ResourceAsColor") private void initView() {
		mton = (ToggleButton) findViewById(R.id.mTogBtn);
		ib_back = (ImageButton) findViewById(R.id.ib_back);
		ll_jiance = (LinearLayout) findViewById(R.id.ll_jiance);
		ll_lianxi = (LinearLayout) findViewById(R.id.ll_lianxi);
		ll_size = (LinearLayout) findViewById(R.id.ll_size);
	    tv_size = (TextView) findViewById(R.id.tv_size);
		lin = (LinearLayout) findViewById(R.id.lin);
		//lin.setBackgroundResource(R.drawable.back);
		//lin.setBackgroundColor(R.color.night);
		boolean issetting = SpTools.getBoolean(Setting.this, "issetting", false);
		if(issetting){
			lin.setBackgroundColor(R.color.night);
			System.out.println("设置了");
		}
		else{
		lin.setBackgroundColor(color.white);
		System.out.println("违背设置");
		}
		
		boolean isChecked = SpTools.getBoolean(Setting.this, "ischeck", false);
		mton.setChecked(isChecked);
		
		size = SpTools.getInt(Setting.this, "title_size", 1);
		setTextSize(size);
	}

	

}
