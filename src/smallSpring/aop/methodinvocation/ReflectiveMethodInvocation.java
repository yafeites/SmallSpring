package smallSpring.aop.methodinvocation;

import smallSpring.aop.classfilter.ClassFilter;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ReflectiveMethodInvocation  implements  ProxyMethodInvocation{
    Object []arguments;
    Object target;
    Class<?> targetClass;
    Method method;
    Proxy proxy;
    List<?> interceptorsAndMDynamicMethodMatchers;
    @Override
    public Method getMetod() {
        return method;
    }

    public ReflectiveMethodInvocation(Object[] arguments, Object target, Class<?> targetClass, Method method, Proxy proxy, List<?> interceptorsAndMDynamicMethodMatchers) {
        this.arguments = arguments;
        this.target = target;
        this.targetClass = targetClass;
        this.method = method;
        this.proxy = proxy;
        this.interceptorsAndMDynamicMethodMatchers = interceptorsAndMDynamicMethodMatchers;
    }

    @Override
    public Proxy getProxy() {
        return proxy;

    }
}
