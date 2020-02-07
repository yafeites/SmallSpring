package smallSpring.aop.aopproxy;

import smallSpring.aop.Support.AdvisedSupport;
import smallSpring.aop.methodinterceptor.CGlibMethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import smallSpring.aop.methodinvocation.ReflectiveMethodInvocation;

public class Cglib2AopProxy implements  AopProxy  {
    AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
        Class<?>[]Interfaces=advised.getTargetSource().getTargetClass().getInterfaces();
        enhancer.setInterfaces(Interfaces);
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        Object enhanced = enhancer.create();
        return enhanced;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private AdvisedSupport advised;
        private DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            Object target=advised.getTargetSource().getTarget();
            List<?>chain=advised.getIntetceptorsAndDynamicInterceptionAdvice(method
                    ,target.getClass());
            return new CglibMethodInvocation(args,target,method,proxy,chain,methodProxy).proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        @Override
        protected Object invokeJoinPoint() throws Throwable {
            return this.methodProxy.invoke(this.target,this.arguments);
        }

        public CglibMethodInvocation(Object[] arguments, Object target, Method method, Object proxy, List<?> interceptorsAndMDynamicMethodMatchers, MethodProxy methodProxy) {
            super(arguments, target, method, proxy, interceptorsAndMDynamicMethodMatchers);
            this.methodProxy = methodProxy;
        }



    }
}
