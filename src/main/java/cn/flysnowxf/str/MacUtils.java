package cn.flysnowxf.str;

public class MacUtils {
	/**
	 * 转换为大写、以:分割的mac地址
	 * @param baseMac
	 * @return
	 */
	public static String transferMac(String baseMac) {
		String mac = baseMac;
		
		// 一般原始mac为17位或者12位（无分隔符）的字符串
		if(mac != null && (mac.length() == 12 || mac.length() == 17)) {
			// 小写->大写
			mac = mac.toUpperCase();
			// -替换为:
			mac = mac.replaceAll("-", ":");
			// 无分隔符使用:分割
			mac = insertSplit(mac);
		}
		
		return mac;
	}
	
	private static String insertSplit(String mac) {
		if(mac.length() == 12) {
			String split = ":";
			StringBuilder sb = new StringBuilder(mac);
			sb.insert(2, split).insert(5, split).
				insert(8, split).insert(11, split).
				insert(14, split);
			return sb.toString();
		}
		
		return mac;
	}
}
