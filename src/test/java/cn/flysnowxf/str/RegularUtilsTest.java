package cn.flysnowxf.str;

import junit.framework.Assert;

import org.junit.Test;

public class RegularUtilsTest {
	@Test
	public void isIp() {
		String ip = "192.168.0.1";
		Assert.assertTrue(RegularUtils.isIp(ip));
		
		ip = "10.355.10.2";
		Assert.assertFalse(RegularUtils.isIp(ip));
		
		ip = "10.355.2";
		Assert.assertFalse(RegularUtils.isIp(ip));
	}
	
	@Test
	public void isEmail() {
		String email = "flysnowxf@gmail.com";
		Assert.assertTrue(RegularUtils.isEmail(email));
		
		email = "flysnow=xf@gmail.com";
		Assert.assertFalse(RegularUtils.isEmail(email));
		
		email = "flysnowxf@gmailcom";
		Assert.assertFalse(RegularUtils.isEmail(email));
	}
}
