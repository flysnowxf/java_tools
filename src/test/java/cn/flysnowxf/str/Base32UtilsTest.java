package cn.flysnowxf.str;

import java.io.UnsupportedEncodingException;

public class Base32UtilsTest {
	public static void main(String[] args) {
		try {
			System.out.println(Base32Utils.strToBase32("http://test.com"));
			System.out.println(Base32Utils.base32ToStr("nb2hi4b2f4xxizltoqxgg33n"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
