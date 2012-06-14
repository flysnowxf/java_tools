package cn.flysnowxf.algorithm;

import java.util.HashMap;
import java.util.Map;

public class RandomEngineTest {
    public static void main(String[] args) {
         Map<String, Integer> keyChanceMap = new HashMap<String, Integer>();
         keyChanceMap.put("a", 30);
         keyChanceMap.put("b", 10);
         keyChanceMap.put("c", 40);
         keyChanceMap.put("d", 20);
         
         Map<String, Integer> count = new HashMap<String, Integer>();
         for (int i = 0; i < 100000; i++) {
              String key = RandomEngine.chanceSelect(keyChanceMap);
              if(count.containsKey(key)) {
                   count.put(key, count.get(key) + 1);
              }
              else {
                   count.put(key, 1);
              }
         }
         
         // print
         for (String key : count.keySet()) {
              System.out.println(key + ":" + count.get(key));
         }
    }
}
