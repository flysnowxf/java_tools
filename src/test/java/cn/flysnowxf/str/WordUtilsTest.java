package cn.flysnowxf.str;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class WordUtilsTest {
	@Test
	public void participle() {
		String word = "中国人民共和国";
		List<String> wordList = WordUtils.participle(word);
		Assert.assertEquals(2, wordList.size());
	}
	
	@Test
	public void getKeywordsByOr() {
		String word = "我们在希望的田野上";
		String keyword = WordUtils.getKeywordsByOr(word);
		Assert.assertEquals("(在 OR 希望 OR 野上 OR 的 OR 我们 OR 田)", keyword);
	}
}
