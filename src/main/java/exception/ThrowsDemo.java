package exception;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 當子類重寫超類含有throws聲明異常拋出的方法時，對throws的重寫規則
 */
public class ThrowsDemo {
    public void doSome() throws IOException, AWTException {

    }
}

class SubClass extends ThrowsDemo{
    /**
     * 允許拋相同異常
     */
//    public void doSome() throws IOException, AWTException {
//
//    }

    /**
     * 允許完全不拋異常
     */
//    public void doSome() {
//
//    }

    /**
     * 允許拋出部分異常
     */
//    public void doSome() throws IOException {
//
//    }

    /**
     * 允許拋出超類方法聲明拋出的異常的子類異常
     */
//    public void doSome() throws FileNotFoundException{
//
//    }

    /**
     * 不允許拋出額外異常
     */
//    public void doSome() throws SQLException{
//
//    }

    /**
     * 不允許拋出的異常範圍比超類大
     */
//    public void doSome() throws Exception {
//
//    }
}