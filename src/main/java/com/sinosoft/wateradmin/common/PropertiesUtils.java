package com.sinosoft.wateradmin.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesUtils {

	private static String fileName = "/config";
	private static ResourceBundle rb = ResourceBundle.getBundle(fileName,
			Locale.getDefault());

	/**
	 * 从配置文件得到value
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return rb.getString(key);
	}

	public static int getInt(String key) {
		return Integer.parseInt(rb.getString(key));
	}

	public static float getFloat(String key) {
		return Float.parseFloat(rb.getString(key));
	}
}
