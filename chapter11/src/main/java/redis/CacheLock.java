package redis;

import java.lang.annotation.*;

/**
 * Created by shliangyan on 2017/9/29.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    String lockedPrefix() default "";// redis锁key的前缀
    long timeOut() default 2000;//伦训锁的时间
    int expireTime() default 1000;//key存活时间
}
