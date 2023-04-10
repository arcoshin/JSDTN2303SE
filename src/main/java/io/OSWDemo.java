package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * OutputStreamWriter:轉換字符輸出流
 */
class OSWDemo {
    public static void main(String[] args) {
        try (
                FileOutputStream fos = new FileOutputStream("demo/osw.obj");
                /**
                 * 輸出時仍然建議一律使用UTF_8格式傳輸
                 * 重載方法雖可不寫，但就會使用系統默認的編碼格式
                 * 會造成"一次編譯，到處改"，讓JAVA跨平台的優勢大大降低
                 * 跨平台優勢 :　應主動上刻意減少需要使用到系統默認設定來運作的代碼
                 */
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        ) {
            osw.write("夜空中最亮的星，能否聽清\r");
            osw.write("那仰望的人心底的孤獨和嘆息\n");
            osw.write("。\n");
            System.out.println("寫出完畢");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
