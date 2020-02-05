package smallSpring.aop.aopproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkDynamicAopProxy implements AopProxy , InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }

    @Override
    public Object getProxy() {
        return null;
    }
}
