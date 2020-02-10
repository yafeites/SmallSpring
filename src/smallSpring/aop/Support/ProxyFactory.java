package smallSpring.aop.Support;

public class ProxyFactory extends AdvisedSupport {
    public Object getProxy()
    {
        return getAopProxyFactory().createAopProxy(this).getProxy();
    }

}
