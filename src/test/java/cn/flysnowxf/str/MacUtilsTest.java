package cn.flysnowxf.str;


import junit.framework.Assert;

import org.junit.Test;

public class MacUtilsTest {
	@Test
	public void transferMac() {
		String mac = "12acdf342fdf";
		Assert.assertEquals("12:AC:DF:34:2F:DF", MacUtils.transferMac(mac));
	}
}
