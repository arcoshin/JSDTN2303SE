package map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("國文", 99);
        map.put("英文", 98);
        map.put("數學", 97);
        map.put("社會", 96);
        map.put("自然", 99);

        /**
         * 輸出可發現無序結構
         */
        System.out.println("map = " + map);//{國文=99, 英文=98, 社會=96, 數學=97, 自然=99}

        /**
         * 若有遍歷順序的需求可以使用LinkedHashMap<>();
         * 內部建立了鍊表機制可以依照輸入順序進行存放
         */
        Map<String, Integer> mapLinked = new LinkedHashMap<>();
        mapLinked.put("國文", 99);
        mapLinked.put("英文", 98);
        mapLinked.put("數學", 97);
        mapLinked.put("社會", 96);
        mapLinked.put("自然", 99);
        System.out.println("mapLinked = " + mapLinked);

        /**
         * put有返回值value的作用
         * map中禁止存放重複的key
         * 因此若put重複的key則會覆蓋數據，並將被覆蓋的value值返回。
         */
        Integer returnBack1 = map.put("社會", 100);
        System.out.println(returnBack1);//返回被覆蓋的96
        Integer returnBack2 = map.put("生化", 99);
        System.out.println(returnBack2);//數據未重複，返回null

        /**
         * 注意細節
         * 當使用基本類型接收map中的包裝類數據時，會產生空指針異常
         * 原因在於map.put數據未重複時會返回null
         * 而因為泛型，此時輸出的默認類行為Integer
         * 輸出類型為Integer而接收類型卻為int時
         * 會觸發編譯器的自動拆箱
         * 此時會默認在value後面補上int.value
         * int value = map.put("國防", 100).intValue();
         * 而當數據未重複而使put方法返回null時
         * null調用intValue()方法就會運行中異常:空指針
         */
//        int value = map.put("國防", 100).intValue();
//        System.out.println(value);//運行中錯誤:NullPointerException

        /**
         * get(key)
         * 透過key值查找對應的值
         * 若查無則返回null
         */
        Integer en = map.get("英文");
        System.out.println("英文:" + en);
        Integer un = map.get("音樂");
        System.out.println("音樂:" + un);

        /**
         * 長度:size()
         * 是否包含Key:containsKey()
         * 是否包含Value:containsValue()
         */
        int size = map.size();
        System.out.println("len:" + size);

        boolean hasNature = map.containsKey("自然");
        System.out.println("Key中是否包含自然:" + hasNature);

        boolean has101 = map.containsValue(101);
        System.out.println("Value中是否包含101:" + has101);

        /**
         * remove(key)
         * 刪除當前map中key所對應的整個鍵值對，並將其對應的value值返回
         * 查無則返回null
         */
        Integer nature = map.remove("自然");
        System.out.println(nature);
        Integer lonely = map.remove("移除了個寂寞");
        System.out.println(lonely);


    }
}
