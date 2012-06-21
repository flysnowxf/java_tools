package cn.flysnowxf.str;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class WordUtilsTest {
	@Test
	public void participle() {
		String word = "我们在希望的田野上";
		List<String> wordList = WordUtils.participle(word);
		Assert.assertEquals(6, wordList.size());
	}
	
	@Test
	public void getKeywordsByOr() {
		String word = "我们在希望的田野上";
		String keyword = WordUtils.getKeywordsByOr(word);
		Assert.assertEquals("(田野 OR 在 OR 希望 OR 野上 OR 的 OR 我们)", keyword);
	}
}
