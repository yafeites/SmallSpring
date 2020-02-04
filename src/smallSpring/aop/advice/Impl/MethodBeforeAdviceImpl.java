package smallSpring.aop.advice.Impl;

import smallSpring.aop.advice.Interface.MethodAdvice;
import smallSpring.aop.advice.Interface.MethodBeforeAdvice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodBeforeAdviceImpl implements MethodBeforeAdvice {

    @Override
    public void Before(Method method, Object[] args, Object target) throws InvocationTargetException, IllegalAccessException {
        System.out.println("i am here");
        method.invoke(target,args);
    }
}
