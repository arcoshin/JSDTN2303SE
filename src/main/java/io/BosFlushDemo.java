package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 緩衝輸出流的寫緩衝問題
 */
public class BosFlushDemo {
    public static void main(String[] args) {
        try (
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("demo/bos.txt"));
        ) {
            String line = "SuperIdol的笑容都沒你的甜";
            byte[] data = line.getBytes(StandardCharsets.UTF_8);
            bos.write(data);
            /**
             * void flush:強行將緩衝區內未滿的數據一次輸出
             * flush 沖水的意思
             *
             * flush方法是被定義在Flushable接口中的
             * 而OutPutStream這個超類實現了這個接口，這意味著所有字節輸出流都具有這個方法
             *
             * 除了緩衝流之外，其他的高級流flush方法都只有一個作用:
             * 就是調用他連接的其他流的flush方法，將flush動作傳遞下去------
             *
             * 最終傳遞給緩沖流用於清空緩衝區
             */
            bos.flush();
            System.out.println("寫出完畢!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
