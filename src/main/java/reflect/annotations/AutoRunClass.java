package reflect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Retention 註解保留級別
 *
 * RetentionPolicy.SOURCE   註解僅保留於源代碼，不會被編譯到字節碼文件中
 * RetentionPolicy.CLASS    註解可被編譯器寫入字節碼文件中，但不可被反射機制使用(不可運行中使用)
 * RetentionPolicy.RUNTIME  註解可被編譯器寫入字節碼文件中，且可以被反射機制使用(可以運行中使用)
 *
 * 若不指定Retention，默認為RetentionPolicy.CLASS
 */
public @interface AutoRunClass {

}
