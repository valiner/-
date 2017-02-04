package com.example.Tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Administrator
 * @创建时间 2015-7-4 上午10:40:52
 * @描述 TODO
 *
 * @ svn提交者：$Author: gd $
 * @ 提交时间: $Date: 2015-07-07 09:14:24 +0800 (Tue, 07 Jul 2015) $
 * @ 当前版本: $Rev: 36 $
 */
public class SpTools
{
	/**
	 * @param key
	 *        关键字
	 * @param value
	 *       对应的值
	 */
	public static void setBoolean(Context context,String key,boolean value){
		SharedPreferences sp = context.getSharedPreferences("is", Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();//提交保存键值对
		
	}
	
	/**
	 * @param context
	 * @param key
	 *        关键字
	 * @param defValue
	 *        设置的默认值
	 * @return
	 */
	public static boolean getBoolean(Context context,String key,boolean defValue){
		SharedPreferences sp = context.getSharedPreferences("is", Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	
	/**
	 * @param key
	 *        关键字
	 * @param value
	 *       对应的值
	 */
	public static void setString(Context context,String key,String value){
		SharedPreferences sp = context.getSharedPreferences("json", Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();//提交保存键值对
		
	}
	
	public static void setInt(Context context,String key,int value){
		SharedPreferences sp = context.getSharedPreferences("int", Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();//提交保存键值对
		
	}
	
	/**
	 * @param context
	 * @param key
	 *        关键字
	 * @param defValue
	 *        设置的默认值
	 * @return
	 */
	public static String getString(Context context,String key,String defValue){
		SharedPreferences sp = context.getSharedPreferences("json", Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}
	
	
	public static int getInt(Context context,String key,int defValue){
		SharedPreferences sp = context.getSharedPreferences("int", Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}
}
