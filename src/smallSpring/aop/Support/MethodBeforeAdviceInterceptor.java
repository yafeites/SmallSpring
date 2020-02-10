package smallSpring.aop.Support;

import smallSpring.aop.advice.Interface.MethodBeforeAdvice;
import smallSpring.aop.methodinterceptor.MethodInterceptor;
import smallSpring.aop.methodinvocation.MethodInvocation;

import java.lang.reflect.InvocationTargetException;

public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice=advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) {
        this.advice.Before(invocation.getMetod(),invocation.getArguments(),invocation.getThis());
        try {
            return  invocation.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;

    }
}
