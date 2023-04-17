package map;

import java.util.*;

public class MapDemo2 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("國文", 99);
        map.put("英文", 98);
        map.put("數學", 97);
        map.put("社會", 96);
        map.put("自然", 99);

        /**
         * 遍歷Key --->Set(不可重複集合)
         */
        Set<String> set = map.keySet();
        for (String s : set) {
            System.out.println("key:" + s);
        }

        /**
         * 遍歷value --->List(value可能會重複)
         */
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println("value:" + value);
        }

        /**
         * 遍歷每組鍵值對
         */
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
//        for (Map.Entry<String, Integer> entry : entrySet) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
        entrySet.forEach(System.out::println);

        /**
         * map也支持forEach方法
         */
        map.forEach((k,v) -> System.out.println(k + "," + v));
    }
}
