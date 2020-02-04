package smallSpring.aop.methodinvocation;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface  ProxyMethodInvocation extends  MethodInvocation {
    Method getMetod();
    Proxy getProxy();
}
