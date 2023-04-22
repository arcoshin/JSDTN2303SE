package reflect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoRunMethod {
    /**
     * 定義註解參數
     * 類型 參數名() [default] //[]中的內容可有可無
     *
     * 當只有一個參數時，參數名推薦使用value
     * 因為註解傳參時的標準寫法為(參數名1=參數1,參數名2=參數2......)
     *
     * Ex:
     * public @interface AutoRunMethod {
     *      int count() default 1;
     *      String name();
     * }
     * 使用時 : 優點就是可讀性強，順序無所謂
     * 1.@AutoRunMethod(count=1,name="張三").......OK
     * 2.@AutoRunMethod(name="張三",count=1,)......OK
     * 3.@AutoRunMethod(name="張三")...............OK(count有默認值可省略)
     *
     *
     * Ex:
     * public @interface AutoRunMethod {
     *      int count() default 1;
     * }
     * 使用時 : 對於單一參數時，這種寫法就會很冗餘
     * @AutoRunMethod(count=1)....................OK
     *
     * 因此JAVA對於單一參數的情形推薦使用value，可以默認省略
     * Ex:
     * public @interface AutoRunMethod {
     *      int count() default 1;
     * }
     * 使用時 : 單一參數且參數名為value時，可以省略
     * @AutoRunMethod(1)..........................OK
     *
     * 錯誤範例------>多參數時即便參數名為value，也不可省略，並與順序無關
     * @AutoRunMethod(name="張三",1)..........編譯錯誤
     * @AutoRunMethod(1,name="張三")..........編譯錯誤
     */
    int value() default 1;
}
