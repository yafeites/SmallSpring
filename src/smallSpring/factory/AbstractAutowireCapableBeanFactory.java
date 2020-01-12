package smallSpring.factory;

import smallSpring.beandefiniton.BeanDefinition;
import smallSpring.beandefiniton.RootBeanDefinition;
import smallSpring.beans.propertyvalue.PropertyValues;
import smallSpring.beans.wrapper.BeanWrapper;
import smallSpring.beans.wrapper.BeanWrapperImpl;
import smallSpring.exception.BeanCreationException;
import smallSpring.strategy.InstantiationStrategy;
import smallSpring.strategy.SimpleInstantiationStrategy;

import java.beans.Beans;
import java.security.AccessController;
import java.security.PrivilegedAction;

public abstract  class AbstractAutowireCapableBeanFactory extends  AbstractBeanFactory implements  AutowireCapableBeanFactory{
    private InstantiationStrategy instantiationStrategy =new SimpleInstantiationStrategy();
    protected void populateBean(String beanName, RootBeanDefinition mbd, BeanWrapper bw)
    {
// TODO
    }
    protected  void applyPropertyValues(String beanName, BeanDefinition mbd, BeanWrapper bw, PropertyValues pvs)
    {

    }
    protected  BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd, Object[] args)
    {
        return instantiateBean(beanName, mbd);
    }
    //初始化Bean,创建出WrapperImpl
    protected  BeanWrapper instantiateBean(String beanName, RootBeanDefinition mbd)
    {
        try {
            Object beanInstance;
                beanInstance = getInstantiationStrategy().instantiate(mbd, beanName);
            BeanWrapper bw = new BeanWrapperImpl(beanInstance);
            initBeanWrapper(bw);
            return bw;
        }
        catch (Throwable ex) {
            throw new BeanCreationException(beanName);
        }
    }

    private InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }

    //   初始化BeanWrapper
    protected  void initBeanWrapper(BeanWrapper bw)
    {

    }


}
