package smallSpring.aop.methodinvocation;

import smallSpring.aop.classfilter.ClassFilter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ReflectiveMethodInvocation  implements  ProxyMethodInvocation{
    protected Object []arguments;
    protected Object target;
    protected Class<?> targetClass;
    protected Method method;
    protected Proxy proxy;
    protected List<?> interceptorsAndMDynamicMethodMatchers;
    protected  Object invokeJoinPoint() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target,arguments);
    }
    @Override
    public Method getMetod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public Object proceed() {
        return null;
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
