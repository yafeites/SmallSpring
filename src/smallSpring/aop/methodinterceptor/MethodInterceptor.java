package smallSpring.aop.methodinterceptor;

import smallSpring.aop.methodinvocation.MethodInvocation;

public interface MethodInterceptor {
    Object invoke(MethodInvocation invocation);
}
