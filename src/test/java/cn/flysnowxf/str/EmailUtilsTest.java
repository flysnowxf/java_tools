package cn.flysnowxf.str;

import junit.framework.Assert;

import org.junit.Test;

public class EmailUtilsTest {
	@Test
	public void checkFormat() {
		String email = "123@123.com";
		Assert.assertTrue(EmailUtils.checkFormat(email));
	}
	
	@Test
	public void checkCheckMx() {
		String email = "123@123456abc456.com";
		Assert.assertFalse(EmailUtils.speedCheckMx(email));
	}
	
	@Test
	public void checkUser() {
		String email = "123error@123456.com";
		Assert.assertFalse(EmailUtils.speedCheckMx(email));
	}
}
