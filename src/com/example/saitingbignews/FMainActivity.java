package com.example.saitingbignews;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.Tool.Newsgg;
import com.example.Tool.Newsgg.Result;
import com.example.Tool.SkinSettingManager;
import com.example.Tool.SpTools;
import com.example.Tool.Newsgg.Listt;
import com.example.saitingbignews.FMainActivity.MyAdapter;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.TabPageIndicator;

import android.R.bool;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ResourceAsColor")
public class FMainActivity extends FragmentActivity {
	// 头条,财经,体育,娱乐,军事,教育,科技,NBA,股票,星座,女性,健康,育儿

	private ListView lv;
	private String jsonP;
	private int size;
	String httpUrl = "http://api.jisuapi.com/news/get?channel=%e8%b4%a2%e7%bb%8f&num=10&appkey=435466cfd8ab6712";
	private static final String[] TITLE = new String[] { "财经", "体育", "娱乐",
			"军事", "教育", "科技", "NBA", "股票", "星座", "女性", "健康", "育儿" };
	private static final String[] FEILEI = new String[] { "%e8%b4%a2%e7%bb%8f",
			"%e4%bd%93%e8%82%b2", "%e5%a8%b1%e4%b9%90", "%e5%86%9b%e4%ba%8b",
			"%e6%95%99%e8%82%b2", "%e7%a7%91%e6%8a%80", "NBA",
			"%e8%82%a1%e7%a5%a8", "%e6%98%9f%e5%ba%a7", "%e5%a5%b3%e6%80%a7",
			"%e5%81%a5%e5%ba%b7", "%e8%82%b2%e5%84%bf" };

	private Gson gson;
	private ViewPager pager;
	private MyAdapter myaAdapter;
	boolean istv = false;

	public static List<Newsgg.Listt> list = new ArrayList<Newsgg.Listt>();
	private BitmapUtils bitmapUtils;
	private ImageButton i_btn;
	TabPageIndicator indicator;
	private LinearLayout lin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_fmain);

		bitmapUtils = new BitmapUtils(this);
		bitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);

		gson = new Gson();

		// request(httpUrl);
		myaAdapter = new MyAdapter();

		pager = (ViewPager) findViewById(R.id.pager);
		lin = (LinearLayout) findViewById(R.id.lin);
		i_btn = (ImageButton) findViewById(R.id.ibtn_1);
		i_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FMainActivity.this, Setting.class);
				startActivity(intent);

			}
		});
		FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(
				getSupportFragmentManager());
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(1);
		System.out.println("主线程呀");

		// 实例化TabPageIndicator，然后与ViewPager绑在一起（核心步骤）
		indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		// 如果要设置监听ViewPager中包含的Fragment的改变(滑动切换页面)，使用OnPageChangeListener为它指定一个监听器，那么不能像之前那样直接设置在ViewPager上了，而要设置在Indicator上，
		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// http://api.jisuapi.com/news/get?channel=%e5%a4%b4%e6%9d%a1&num=10&appkey=435466cfd8ab6712

				System.out.println("arg" + arg0);
				// System.out.println(jsonP);

				// System.out.println("不会吧~~~~~~~~~~~");
				// newsgg = gson.fromJson(jsonP, Newsgg.class);
				// Toast.makeText(getApplicationContext(),
				// TITLE[arg0],Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	@Override
	protected void onResume() {
		myaAdapter.notifyDataSetChanged();

		boolean is = false;
		is = SpTools.getBoolean(FMainActivity.this, "is", false);
		//System.out.println(is);
		if (is) {
			// System.out.println("pppppppppppppppppppaaaaaaaaaaaaa");
		lin.setBackgroundColor(R.color.night);
		} else {
			// System.out.println("ssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaa");
			lin.setBackgroundColor(Color.WHITE);
		}
		super.onResume();
	}

	/**
	 * 定义ViewPager的适配器
	 */
	class TabPageIndicatorAdapter extends FragmentPagerAdapter {
		// public TabPageIndicatorAdapter(FragmentManager fm) {
		// super(fm);
		// }

		public TabPageIndicatorAdapter(FragmentManager fm) {

			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// 新建一个Fragment来展示ViewPager item的内容，并传递参数
			// System.out.println(jsonP);

			Fragment fragment = new ItemFragment(position);
			System.out.println("2cime");

			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			return TITLE[position % TITLE.length];
		}

		@Override
		public int getCount() {
			return TITLE.length;
		}
	}

	@SuppressLint("ValidFragment")
	public class ItemFragment extends Lan {

		private Newsgg newsgg = null;

		private boolean isPrepared;

		int position;

		

		private String str;
		/*
		 * 
		 * private RelativeLayout re;
		 * 
		 * @Override public void onCreate(Bundle savedInstanceState) { // TODO
		 * Auto-generated method stub themes = new
		 * SkinSettingManager(FMainActivity.this).getCurrentSkinRes();
		 * FMainActivity.this.setTheme(themes);
		 * super.onCreate(savedInstanceState);
		 * 
		 * }
		 */

		private SwipeRefreshLayout swipeRefreshLayout;

		public ItemFragment(int i) {
			position = i;
		}

		private void setData() {
			// TODO Auto-generated method stub
			list = newsgg.result.list;
			myaAdapter.notifyDataSetChanged();
			System.out.println("ggggggggggggggggggggggg");

		}

		private Newsgg parseJson(String jsonData) {
			// 解析json数据

			Newsgg newsgg1 = gson.fromJson(jsonData, Newsgg.class);
			return newsgg1;
		}

		public void request(final String httpUrl) {

			HttpUtils httpUtils = new HttpUtils();
			httpUtils.send(HttpMethod.GET, httpUrl,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							// TODO Auto-generated method stub
							System.out.println("不给联网的呀");
							jsonP = responseInfo.result;
							SpTools.setString(FMainActivity.this, "url"
									+ position, jsonP);
							newsgg = parseJson(jsonP);
							setData();

							// System.out.println(jsonP);
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// TODO Auto-generated method stub
							Toast.makeText(FMainActivity.this,"请联网-木有网络", 0).show();

						}

					});

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			// 动态找到布局文件，再从这个布局中find出TextView对象

			View contextView = inflater.inflate(R.layout.activity_main,
					container, false);

			swipeRefreshLayout = (SwipeRefreshLayout) contextView
					.findViewById(R.id.swipe_container);
			// 设置刷新时动画的颜色，可以设置4个
			swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light,
					android.R.color.holo_red_light,
					android.R.color.holo_orange_light,
					android.R.color.holo_green_light);
			swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

				@Override
				public void onRefresh() {
					// TODO Auto-generated method stub
					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							String str1 = "http://api.jisuapi.com/news/get?channel="
									+ FEILEI[position]
									+ "&num=10&appkey=435466cfd8ab6712";
							request(str1);
							lv.setAdapter(null);
							lv.setAdapter(myaAdapter);
							System.out.println("number" + position);
							Toast.makeText(FMainActivity.this, "刷新完毕", 0)
									.show();
							swipeRefreshLayout.setRefreshing(false);
						}
					}, 6000);
				}
			});

			System.out.println("cccccccccccccccccccc");

			lv = (ListView) contextView.findViewById(R.id.lv);
			isPrepared = true;
			lazyLoad();

			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub

					Intent newsActivity = new Intent(FMainActivity.this,
							BigNews.class);
					newsActivity.putExtra("url", list.get(arg2).url);
					startActivity(newsActivity);

				}
			});

			// String json = mBundle.getString("arg");
			// System.out.println(json);
			// newsgg = gson.fromJson(json, Newsgg.class);
			// System.out.println(newsgg.result.list.get(0).title);
			// mTextView.setText(title);

			return contextView;
		}

		@Override
		public void onResume() {
			super.onResume();
			myaAdapter.notifyDataSetChanged();

			boolean is = false;
			is = SpTools.getBoolean(FMainActivity.this, "is", false);
			System.out.println(is);
			if (is) {
				// System.out.println("pppppppppppppppppppaaaaaaaaaaaaa");
				swipeRefreshLayout.setBackgroundColor(R.color.night);
			} else {
				// System.out.println("ssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaa");
				swipeRefreshLayout.setBackgroundColor(Color.WHITE);
			}
			// FMainActivity.this.setTheme(new
			// SkinSettingManager(FMainActivity.this).getCurrentSkinRes());
			// setContentView(R.layout.activity_main);
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
		}
		
		
		public void requestNet(){
			str = "http://api.jisuapi.com/news/get?channel="
					+ FEILEI[position]
					+ "&num=10&appkey=435466cfd8ab6712";
			request(str);
			lv.setAdapter(null);
			lv.setAdapter(myaAdapter);
			System.out.println("number" + position);
		}
		
		

		@Override
		protected void lazyLoad() {
			// TODO Auto-generated method stub
			if (!isPrepared || !isVisible) {

			} else {
				String cache = SpTools.getString(FMainActivity.this, "url"+ position, "");
				if (!TextUtils.isEmpty(cache)) {
					newsgg = parseJson(cache);
					setData();
					lv.setAdapter(myaAdapter);
				} else {
						requestNet();
					   }

			}

		}

	}

	public class MyAdapter extends BaseAdapter {

		public TextView tv_title;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
			if (convertView == null) {
				v = View.inflate(FMainActivity.this,
						R.layout.tpi_news_listview_item, null);
			} else {
				v = convertView;
			}
			tv_title = (TextView) v
					.findViewById(R.id.tv_tpi_news_listview_item_title);
			TextView tv1 = (TextView) v
					.findViewById(R.id.tv_tpi_news_listview_item_time);
			ImageView iv = (ImageView) v
					.findViewById(R.id.iv_tpi_news_listview_item_pic);

			tv_title.setText(list.get(position).title);
			tv1.setText(list.get(position).time);
			bitmapUtils.display(iv, list.get(position).pic);

			System.out.println("istv" + istv);

			size = SpTools.getInt(FMainActivity.this, "title_size", 1);
			switch (size) {
			case 0:
				System.out.println("0!!!!!!!!!!!!!!!!!");
				tv_title.setTextSize(25);
				break;
			case 1:
				System.out.println("1!!!!!!!!!!!!!!!!!!!!1");
				tv_title.setTextSize(20);
				break;
			case 2:
				System.out.println("3!!!!!!!!!!!!!!!!!!!!!!!!!");
				tv_title.setTextSize(15);
				break;
			}

			return v;
		}

	}

}
