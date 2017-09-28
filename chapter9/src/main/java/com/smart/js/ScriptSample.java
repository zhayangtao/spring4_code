package com.smart.js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class ScriptSample {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String scriptText = "function sum(a,b) {return a+b;}";
        engine.eval(scriptText);
        Invocable invocable = (Invocable) engine;
        Object result = invocable.invokeFunction("sum", 100, 99);
        System.out.println(result);
    }
}
