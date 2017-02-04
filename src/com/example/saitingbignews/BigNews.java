package com.example.saitingbignews;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.Tool.SkinSettingManager;
import com.example.Tool.SpTools;

import android.net.sip.SipSession.Listener;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.TextSize;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BigNews extends Activity {

	private WebView wv;
	private ImageButton ib_1;
	private ImageButton ib_2;
	private TextView tv;
	private ProgressBar pb;
	private WebSettings settings;
	int textSizeIndex = 2;// 0. 超大号 1,大号  2 正常  3 小号  4 超小号
	private AlertDialog dialog;
	private LinearLayout lin2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_big_news);
		
        initView();
		
		initData();
		
		initEvent(); 
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		

	}
	
	public void changcolor(){

		boolean is = false;
		is = SpTools.getBoolean(BigNews.this, "is", false);
		System.out.println(is);
		if (is) {
			// System.out.println("pppppppppppppppppppaaaaaaaaaaaaa");
			// wv.setBackgroundColor(getResources().getColor(R.color.night));
			wv.loadUrl("javascript:load_night()");

		  
			 //wv.setBackgroundColor(Color.parseColor("#000000")); 
		} else {
			// System.out.println("ssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaa");
			wv.loadUrl("javascript:load_day()");
		}
	}
	


	private void initEvent() {
		OnClickListener Listener =new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.ib_base_content_back:
					finish();
					
					break;

				case R.id.ib_base_content_textsize:
					
					showChangeTextSizeDialog();
					break;
				}
				
			}
		};
		wv.setWebViewClient(new WebViewClient(){

			/* (non-Javadoc)
			 * @see android.webkit.WebViewClient#onPageFinished(android.webkit.WebView, java.lang.String)
			 * 页面加载完成的事件处理
			 */
			@Override
			public void onPageFinished(WebView view, String url) {
				//隐藏进度条
				pb.setVisibility(View.GONE);
				super.onPageFinished(view, url);
			}
			
		});
	
		
		
		// TODO Auto-generated method stub
		ib_1.setOnClickListener(Listener);
		ib_2.setOnClickListener(Listener);
		
		
	}
	
	private void showChangeTextSizeDialog() {
		AlertDialog.Builder ab = new AlertDialog.Builder(BigNews.this);
		ab.setTitle("改变字体大小");
		String[] textSize = new String[]{"超大号","大号","正常","小号","超小号"};
		
		ab.setSingleChoiceItems(textSize, textSizeIndex, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				textSizeIndex = which;
				SpTools.setInt(BigNews.this, "size", textSizeIndex);
				boolean isok = true;
				setTextSize(textSizeIndex,isok);
				
				
			}
		});
		dialog = ab.create();
		dialog.show();
	}
	
	private void setTextSize(int s,boolean i) {
		switch (s) {
		case 0:// 超大号
			settings.setTextSize(TextSize.LARGEST);
			break;
		case 1: // 大号
			settings.setTextSize(TextSize.LARGER);
			break;
		case 2: // 正常
			settings.setTextSize(TextSize.NORMAL);
			break;
		case 3: // 小号
			settings.setTextSize(TextSize.SMALLER);
			break;
		case 4: // 最小号
			settings.setTextSize(TextSize.SMALLEST);
			break;
		default:
			break;
		}
		if(i){
		dialog.dismiss();
		  }
	}

	private void initView() {
		// TODO Auto-generated method stub
		int s = SpTools.getInt(BigNews.this, "size", 2);
		boolean b = false;
		lin2 = (LinearLayout) findViewById(R.id.lin2);
		wv = (WebView) findViewById(R.id.wv);
		ib_1 = (ImageButton) findViewById(R.id.ib_base_content_back);
		ib_2 = (ImageButton) findViewById(R.id.ib_base_content_textsize);
		tv = (TextView) findViewById(R.id.tv_base_content_title);
		pb = (ProgressBar) findViewById(R.id.pb);
		
		settings = wv.getSettings();
		settings.setBuiltInZoomControls(true);
		//settings.setJavaScriptEnabled(true);//可以去编译javsscript脚本
		settings.setUseWideViewPort(true);
		setTextSize(s,b);
	}

	private void initData() {
		// TODO Auto-generated method stub
		
		String str = getIntent().getStringExtra("url");
		if(TextUtils.isEmpty(str)){
			Toast.makeText(this, "404", 0).show();
			
		}else{
			
			wv.loadUrl(str);
			changcolor();
		}
		
	}



}
