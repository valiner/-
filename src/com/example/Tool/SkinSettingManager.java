package com.example.Tool;

import com.example.saitingbignews.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SkinSettingManager {

	private final static String SKIN_PREF = "skinSetting";
	private SharedPreferences skinSettingPreference;
	private String key = "skin_type";
	private Editor editor;
	private int[] skinResources = { R.style.normalTheme, R.style.nightTheme };
	private Activity mActivity;

	public SkinSettingManager(Activity activity) {
		this.mActivity = activity;
		skinSettingPreference = mActivity.getSharedPreferences(SKIN_PREF, 3);
	}

	/**
	 * 获取当前程序的皮肤序号
	 * 
	 * @return
	 */
	public int getSkinType() {

		return skinSettingPreference.getInt(key, 0);
	}

	/**
	 * 把皮肤序号写到全局设置里去
	 * 
	 * @param j
	 */
	public void setSkinType(int j) {

		editor = skinSettingPreference.edit();
		editor.putInt(key, j);
		editor.commit();
	}

	/**
	 * 获取当前皮肤的style
	 * 
	 * @return
	 */
	public int getCurrentSkinRes() {

		int getSkinLen = getSkinType();

		return skinResources[getSkinLen];
	}

	/**
	 * 用于切换皮肤
	 */
	public int toggleSkins() {

		int skinType = getSkinType();

		if (skinType == 0) {
			skinType = 1;
		} else {
			skinType = 0;
		}
		setSkinType(skinType);
		return getCurrentSkinRes();

	}

}