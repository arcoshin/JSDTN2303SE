package io;

/**
 * 向文件寫入a-z
 */
class Test2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String str = "a";
        for (int i = 0; i < 1000000; i++) {
            str += i;
        }
        System.out.println("程序運行中");
        System.out.println(str);

        long end = System.currentTimeMillis();

        System.out.println("程序結束，共費時" + (end - start) + "毫秒");

    }
}
