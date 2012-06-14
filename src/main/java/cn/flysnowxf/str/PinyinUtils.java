package cn.flysnowxf.str;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 字符串转拼音工具类
 * <p>Datetime   ： 2010-12-21上午10:04:39<p/>
 * <p>Description:   </p>
 */
public class PinyinUtils {
	public static String tranStrToPinyin(String baseStr) {
		if(baseStr == null)
			return null;
		
		StringBuilder pinyinStr = new StringBuilder();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		// 小写
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		// 不需要音标
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		for (int i = 0; i < baseStr.length(); i++) {
			try {
				// 获取拼音数组，多音字为多个
				String[] pinyinChars = 
					PinyinHelper.toHanyuPinyinStringArray(baseStr.charAt(i), defaultFormat);
				if(pinyinChars != null) {
					// 一般取第一个为常见拼音
					pinyinStr.append(pinyinChars[0]);
				} else {
					// 否则使用原字符
					pinyinStr.append(baseStr.charAt(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return pinyinStr.toString();
	}
}
