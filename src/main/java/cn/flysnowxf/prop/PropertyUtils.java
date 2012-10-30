package cn.flysnowxf.prop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;


import cn.flysnowxf.lang.ClassPathResource;

/**
 * 读取属性文件工具类 
 */
public class PropertyUtils {
	private static String propUrl = "prop.properties";
	private static Properties prop = new Properties();
	
	static {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据name获得配置文件中的值
	 * @param name
	 * @return
	 */
	public static String getString(String name) {
		return prop.getProperty(name);
	}
	
	/**
	 * 载入配置文件进缓存
	 */
	public static void load() {
		try {
			prop.clear();
			
			InputStream in = new BufferedInputStream
				(new ClassPathResource(propUrl).getInputStream());
			prop.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 每次读取配置文件获取配置项
	 * @param classPathFile
	 * @param name
	 * @return
	 */
	public static String getWeakString(String classPathFile, String name) {
		try {
			Properties prop = new Properties();
			InputStream in = new BufferedInputStream
				(new ClassPathResource(classPathFile).getInputStream());
			prop.load(in);
			in.close();
			
			return prop.getProperty(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 修改配置文件的配置项
	 * @param key
	 * @param value
	 * @param classPathFile
	 */
	public static void setProperties(String key, String value, String classPathFile) {
		try {
			Properties prop = new Properties();
			
			// if not exists,create it
			URL url = new ClassPathResource(classPathFile).getInputPath();
			File file = new File(url.toURI());
			if(!file.exists()) {
				file.createNewFile();
			}
			
			// input
			InputStream in = new FileInputStream(file);
			prop.load(in);
			in.close();
			
			// output
			OutputStream out = new FileOutputStream(file);
			prop.setProperty(key, value);
			prop.store(out, "Update " + key + "=" + value);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
