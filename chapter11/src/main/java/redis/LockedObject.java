package redis;

import java.lang.annotation.*;

/**
 * Created by shliangyan on 2017/9/29.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {
}
