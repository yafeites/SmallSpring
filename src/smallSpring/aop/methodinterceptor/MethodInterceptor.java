package smallSpring.aop.methodinterceptor;

import smallSpring.aop.methodinvocation.MethodInvocation;

import java.lang.reflect.InvocationTargetException;

public interface MethodInterceptor {
    Object invoke(MethodInvocation invocation) throws InvocationTargetException, IllegalAccessException;
}
