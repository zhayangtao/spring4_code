package redis;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;

/**
 * Created by shliangyan on 2017/9/29.
 */
public class CacheLockInterceptor implements InvocationHandler {
    public static int ERROR_COUNT = 0;
    private Object object;

    public CacheLockInterceptor(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        //如果没有cacheLock注解，pass
        if (cacheLock == null) {
            System.out.println("no cacheLock");
            return method.invoke(object, args);
        }
        //获得方法中参数的注解
        Annotation[][] annotations = method.getParameterAnnotations();

        return null;
    }

    private Object getLockedObject(Annotation[][] annotations, Object[] args) throws Exception {
        if (args == null || args.length == 0) {
            throw new Exception("方法参数为空，没有被锁定的对象");
        }
        if (annotations == null || annotations.length == 0) {
            throw new Exception("没有被注解的参数");
        }

        //不支持多个参数加锁，只支持第一个注解为lockedObject或者lockedComplexObject的参数
        int index = -1;
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                if (annotations[i][j] instanceof LockedComplexObject) {
                    index = i;
                    try {
                        return args[i].getClass().getField(((LockedComplexObject) annotations[i][j]).field());
                    } catch (NoSuchFieldException | SecurityException e) {
                        throw new Exception("注解对象中没有该属性" + ((LockedComplexObject) annotations[i][j]).field());
                    }
                }
                if (annotations[i][j] instanceof LockedObject) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                break;
            }

        }
        if (index == -1) {
            throw new Exception("请指定被锁定参数");
        }
        return args[index];
    }
}
