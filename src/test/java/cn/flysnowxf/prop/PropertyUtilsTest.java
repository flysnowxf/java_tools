package cn.flysnowxf.prop;

import junit.framework.Assert;

import org.junit.Test;

public class PropertyUtilsTest {
	@Test
	public void getString() {
		Assert.assertEquals("https://github.com/flysnowxf/java_tools", PropertyUtils.getString("url"));
	}
	
	@Test
	public void getWeakString() {
		Assert.assertEquals("flysnowxf", PropertyUtils.getWeakString("test.properties", "name"));
	}
}
