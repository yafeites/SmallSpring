package smallSpring.aop.methodinvocation;

import java.lang.reflect.Method;

public interface MethodInvocation {
    Method getMetod();
    Object[] getArguments();
    Object getThis();
    Object proceed() throws Throwable;

}
