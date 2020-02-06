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

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advised.getTargetSource().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        Object enhanced = enhancer.create();
        return enhanced;
    }

    private static class DynamicAdvisedInterceptor implements CGlibMethodInterceptor {

        private AdvisedSupport advised;
        private DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

            return new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy).proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        @Override
        protected Object invokeJoinPoint() throws InvocationTargetException, IllegalAccessException {
            return this.methodProxy.invoke(this.target,this.arguments);
        }

        public CglibMethodInvocation(Object[] arguments, Object target, Class<?> targetClass, Method method, Proxy proxy, List<?> interceptorsAndMDynamicMethodMatchers, MethodProxy methodProxy) {
            super(arguments, target, targetClass, method, proxy, interceptorsAndMDynamicMethodMatchers);
            this.methodProxy = methodProxy;
        }


        @Override
        public Object proceed()   {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
