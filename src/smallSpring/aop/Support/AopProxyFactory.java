package smallSpring.aop.Support;

import smallSpring.aop.aopproxy.AopProxy;

public interface AopProxyFactory {
    AopProxy createAopProxy(AdvisedSupport config);
}
