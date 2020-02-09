package smallSpring.aop.autoproxycreator;

import smallSpring.aop.Support.ProxyFactory;
import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.beanfactoryaware.BeanFactoryAware;
import smallSpring.aop.methodinterceptor.MethodInterceptor;
import smallSpring.aop.targetsource.CustomClassTargetSource;
import smallSpring.aop.targetsource.SingletonTargetSource;
import smallSpring.aop.targetsource.TargetSource;
import smallSpring.beanpostprocessor.BeanPostProcessor;
import smallSpring.beanpostprocessor.InstantiationAwareBeanPostProcessor;
import smallSpring.exception.BeansException;
import smallSpring.factory.BeanFactory;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class AdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
   BeanFactory beanFactory;

    public String getInterceptorName() {
        return interceptorName;
    }

    public void setInterceptorName(String interceptorNames) {
        this.interceptorName = interceptorNames;
    }

    String interceptorName;

    public String getCls() {
        return ProxyCls;
    }

    public void setCls(String cls) {
        this.ProxyCls = cls;
    }

    String ProxyCls;
    @Override

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return  bean;
    }
    Advisor[]resloveInterceptorNames()
    {
        List<Advisor> advisors=new ArrayList<>();

        {
            Object bean=this.beanFactory.getBean(interceptorName);
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

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        if ( Advisor.class.isAssignableFrom(beanClass)) {
            return null;
        }
        if ( MethodInterceptor.class.isAssignableFrom(beanClass)) {
            return null;
        }
        if(BeanPostProcessor.class.isAssignableFrom(beanClass))
        {
            return  null;
        }
        try {
            if(beanClass==Class.forName(ProxyCls))
            {
                Object proxy=crateProxy(new CustomClassTargetSource(beanClass));
                return proxy;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Object postProcessAfterInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }
}
