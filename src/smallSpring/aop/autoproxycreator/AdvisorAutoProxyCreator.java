package smallSpring.aop.autoproxycreator;

import smallSpring.aop.Support.ProxyFactory;
import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.beanfactoryaware.BeanFactoryAware;
import smallSpring.aop.methodinterceptor.MethodInterceptor;
import smallSpring.aop.targetsource.SingletonTargetSource;
import smallSpring.aop.targetsource.TargetSource;
import smallSpring.beanpostprocessor.BeanPostProcessor;
import smallSpring.exception.BeansException;
import smallSpring.factory.BeanFactory;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class AdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {
   BeanFactory beanFactory;

    public String[] getInterceptorNames() {
        return interceptorNames;
    }

    public void setInterceptorNames(String[] interceptorNames) {
        this.interceptorNames = interceptorNames;
    }

    String[]interceptorNames;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Advisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        Object proxy=crateProxy(new SingletonTargetSource(bean));
        return proxy;

    }
    Advisor[]resloveInterceptorNames()
    {
        List<Advisor> advisors=new ArrayList<>();
        for(String beanName:interceptorNames)
        {
            Object bean=this.beanFactory.getBean(beanName);
            advisors.add((Advisor) bean);
        }
        return advisors.toArray(new Advisor[advisors.size()]);
    }
   Object crateProxy( TargetSource targetSource)
   {
       ProxyFactory proxyFactory=new ProxyFactory();
       proxyFactory.setTargetSource(targetSource);
       Advisor[] advisors=resloveInterceptorNames();
       proxyFactory.setAdvisors(advisors);
      return proxyFactory.getProxy();
   }
    @Override
    public void setFactory(BeanFactory beanFactory) {
            this.beanFactory=beanFactory;
    }

}
