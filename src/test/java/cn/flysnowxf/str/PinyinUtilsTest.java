package cn.flysnowxf.str;

import junit.framework.Assert;

import org.junit.Test;

public class PinyinUtilsTest {
	@Test
	public void tranStrToPinyin() {
		String str = "我们在希望的田野上!";
		String pinyin = "womenzaixiwangdetianyeshang!";
		Assert.assertEquals(pinyin, PinyinUtils.tranStrToPinyin(str));
	}
}
