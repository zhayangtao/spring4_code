package com.smart.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shliangyan on 2017/9/25.
 */
public class ReflectTest {
    public static Car initByDefaultConst() throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.smart.reflect.Car");

        Constructor constructor = clazz.getDeclaredConstructor((Class[])null);
        Car car = (Car) constructor.newInstance();

        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "bmw");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "black");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 200);
        return car;
    }

    public static void main(String[] args) throws Exception {
        Car car = initByDefaultConst();
        car.introduce();
    }
}
