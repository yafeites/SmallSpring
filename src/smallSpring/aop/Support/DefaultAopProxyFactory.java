package smallSpring.aop.Support;

import smallSpring.aop.aopproxy.AopProxy;
import smallSpring.aop.aopproxy.Cglib2AopProxy;
import smallSpring.aop.aopproxy.JdkDynamicAopProxy;

public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport config) {
       if(config.getTargetSource().getTargetClass().isInterface())
       {
           return new JdkDynamicAopProxy(config);
       }
       return new Cglib2AopProxy(config);
    }
}
