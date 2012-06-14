package cn.flysnowxf.str;


public class RegularUtils {
	private static final String IP_PATTERN = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
	private static final String EMAIL_PATTERN = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
	
	/**
	 * 判断ip
	 */
	public static boolean isIp(String ip) {
		if(ip == null)
			return false;
		
		return ip.matches(IP_PATTERN);
	}
	
	/**
	 * 判断email
	 */
	public static boolean isEmail(String email) {
		if(email == null)
			return false;
		
		return email.matches(EMAIL_PATTERN);
	}
}
