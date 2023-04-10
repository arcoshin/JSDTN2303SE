package exception;

/**
 * 面試題
 */
public class FinallyDemo3 {
    /**
     * 面試題 : 下列代碼執行完畢的結果?
     */
    public static void main(String[] args) {
        /**
         * 3,3,3
         */
        System.out.println(test("0")+","+test(null)+","+test(""));

    }

    private static int test(String str) {
        /**
         * TryCatch在執行return要返回之前，仍然會先無條件強制執行finally，此時底層運行的返回值會被覆蓋成3
         * 執行完finally塊中的return則確定結束方法，此時返回值的3會返回
         */
        try {
            return str.charAt(0) - '0';
        } catch (NullPointerException e) {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    /**
     * 面試題 : final、finally、finalize的差異
     *
     * final關鍵字:
     * 修飾變量則不可改變
     * 修飾方法則不可被重寫
     * 修飾類則不可被繼承
     * final static 修飾變量則成為常量
     *
     * finally塊
     * 屬於異常處理機制中的一部分
     * try & catch ......finally
     * 其中無論try塊中是否出現異常，finally終將被執行
     *
     * finalize方法
     * JAVA回收機制中，GC在釋放一個內存對象時會調用的方法
     * finalize是定義於Object類的方法，因此所有類都有這個方法
     * 可以理解為被清除前的"遺言"
     */



}
