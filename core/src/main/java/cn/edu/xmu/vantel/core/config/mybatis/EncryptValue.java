package cn.edu.xmu.vantel.core.config.mybatis;

import java.lang.annotation.*;

/**
 * @author wl
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptValue {
    /**
     * 加密方法
     * @return
     */
    String method() default "aes";
}
