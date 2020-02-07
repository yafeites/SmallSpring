package smallSpring.aop.aopproxy;

import smallSpring.aop.Support.AdvisedSupport;
import smallSpring.aop.classfilter.ClassFilter;
import smallSpring.aop.methodinterceptor.MethodInterceptor;
import smallSpring.aop.methodinvocation.MethodInvocation;
import smallSpring.aop.methodinvocation.ReflectiveMethodInvocation;
import smallSpring.aop.targetsource.TargetSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class JdkDynamicAopProxy implements AopProxy , InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    AdvisedSupport advised;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TargetSource targetSource=this.advised.getTargetSource();
        Object target=targetSource.getTarget();
        Class<?>targetClass=targetSource.getTargetClass();
        MethodInvocation methodInvocation;
        List<?> chain=this.advised.getIntetceptorsAndDynamicInterceptionAdvice(method,targetClass);
        methodInvocation=new ReflectiveMethodInvocation(args,target,method,proxy,chain);
        return methodInvocation.proceed();
    }

    @Override
    public Object getProxy() {
        Class<?>[]Interfaces=advised.getTargetSource().getTargetClass().getInterfaces();
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),Interfaces,this);
    }

}
