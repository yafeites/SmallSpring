package smallSpring.aop.methodinvocation;

import smallSpring.aop.classfilter.ClassFilter;
import smallSpring.aop.methodinterceptor.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ReflectiveMethodInvocation  implements  ProxyMethodInvocation{
    protected Object []arguments;
    protected Object target;
//    protected Class<?> targetClass;
    protected Method method;
    protected Object proxy;
    protected List<?> interceptorsAndMDynamicMethodMatchers;
    protected  Object invokeJoinPoint() throws Throwable {
        return method.invoke(target,arguments);
    }
    private  int currentInterceptorIndex=-1;
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


    public Object proceed() throws Throwable {
        if(this.currentInterceptorIndex==this.interceptorsAndMDynamicMethodMatchers.size()-1)
        {
            return  invokeJoinPoint();
        }
        Object advice=this.interceptorsAndMDynamicMethodMatchers.get(++this.currentInterceptorIndex);

        return ((MethodInterceptor)advice).invoke(this);
    }

    public ReflectiveMethodInvocation(Object[] arguments, Object target, Method method, Object proxy, List<?> interceptorsAndMDynamicMethodMatchers) {
        this.arguments = arguments;
        this.target = target;
//        this.targetClass = targetClass;
        this.method = method;
        this.proxy = proxy;
        this.interceptorsAndMDynamicMethodMatchers = interceptorsAndMDynamicMethodMatchers;
    }

    @Override
    public Object getProxy() {
        return proxy;
    }
}
