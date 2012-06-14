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
}
