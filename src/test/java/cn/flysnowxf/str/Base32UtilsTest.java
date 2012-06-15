package cn.flysnowxf.str;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

public class Base32UtilsTest {
	@Test
	public void test() {
		try {
			String str = "http://test.com";
			String base32 = "nb2hi4b2f4xxizltoqxgg33n";
			Assert.assertEquals(base32, Base32Utils.strToBase32("http://test.com"));
			Assert.assertEquals(str, Base32Utils.base32ToStr(base32));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
