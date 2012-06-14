package cn.flysnowxf.str;


public class RegularUtils {
	private static final String ipPattern = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
	
	/**
	 * 判断ip
	 */
	public static boolean isIp(String ip) {
		if(ip == null)
			return false;
		
		return ip.matches(ipPattern);
	}
}
