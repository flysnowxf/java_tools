package cn.flysnowxf.str;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class WordUtils {
	/**
	 * 使用IK库进行分词
	 * @param word
	 * @return
	 */
	public static List<String> participle(String word) {
		Set<String> set = new HashSet<String>();
		
		StringReader reader = new StringReader(word);
		IKSegmenter seg = new IKSegmenter(reader, true);
		Lexeme lexeme = null;
		try {
			while((lexeme = seg.next()) != null){
				set.add(lexeme.getLexemeText());
	    	}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<String>(set);
	}
	
	/**
	 * 获取搜索的关键词，多个用OR分割
	 * @param word
	 * @return
	 */
	public static String getKeywordsByOr(String word) {
		try {
			List<String> wordList = participle(word);
			if(wordList.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (String w : wordList) {
					sb.append(w).append(" OR ");
				}
				// 去除结尾的OR
				sb = new StringBuilder(sb.toString().replaceAll("\\sOR\\s$", ""));
				// 加()
				sb.insert(0, "(").append(")");
				
				return sb.toString();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return word;
	}
}
