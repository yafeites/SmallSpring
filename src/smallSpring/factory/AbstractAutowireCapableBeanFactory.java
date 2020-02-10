package smallSpring.factory;

import smallSpring.aop.beanfactoryaware.BeanFactoryAware;
import smallSpring.beandefiniton.BeanDefinition;
import smallSpring.beandefiniton.RootBeanDefinition;
import smallSpring.beanpostprocessor.BeanPostProcessor;
import smallSpring.beanpostprocessor.InstantiationAwareBeanPostProcessor;
import smallSpring.beans.propertyvalue.MutablePropertyValues;
import smallSpring.beans.propertyvalue.PropertyValue;
import smallSpring.beans.propertyvalue.PropertyValues;
import smallSpring.beans.reslover.BeanDefinitionValueResolver;
import smallSpring.beans.wrapper.BeanWrapper;
import smallSpring.beans.wrapper.BeanWrapperImpl;
import smallSpring.exception.BeanCreationException;
import smallSpring.exception.BeansException;
import smallSpring.strategy.InstantiationStrategy;
import smallSpring.strategy.SimpleInstantiationStrategy;

import java.beans.Beans;
import java.beans.PropertyDescriptor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract  class AbstractAutowireCapableBeanFactory extends  AbstractBeanFactory implements  AutowireCapableBeanFactory{
    private InstantiationStrategy instantiationStrategy =new SimpleInstantiationStrategy();
    protected void populateBean(String beanName, RootBeanDefinition mbd, BeanWrapper bw)
    {
        PropertyValues pvs = mbd.getPropertyValues();
        if (mbd.getResolvedAutowireMode() == RootBeanDefinition.AUTOWIRE_BY_NAME ||
                mbd.getResolvedAutowireMode() == RootBeanDefinition.AUTOWIRE_BY_TYPE) {
            MutablePropertyValues newPvs = new MutablePropertyValues(pvs);

            // Add property values based on autowire by name if applicable.
            if (mbd.getResolvedAutowireMode() == RootBeanDefinition.AUTOWIRE_BY_NAME) {
                autowireByName(beanName, mbd, bw, newPvs);
            }

            // Add property values based on autowire by type if applicable.
            if (mbd.getResolvedAutowireMode() == RootBeanDefinition.AUTOWIRE_BY_TYPE) {
                autowireByType(beanName, mbd, bw, newPvs);
            }
            pvs = newPvs;
        }
        applyPropertyValues(beanName,mbd,bw,pvs);
    }

     void autowireByName(String beanName, RootBeanDefinition mbd, BeanWrapper bw, MutablePropertyValues newPvs) {
//        非基本参数并且并未直接设置参数对应的值的抽取
        String[] propertyNames = unsatisfiedNonSimpleProperties(mbd, bw);
        for (String propertyName : propertyNames) {
            if (containsBean(propertyName)&&beanName!=propertyName) {
                newPvs.add(propertyName, getBean(propertyName));

            }
            }
        }



    private void autowireByType(String beanName, RootBeanDefinition mbd, BeanWrapper bw, MutablePropertyValues newPvs) {
        //        非基本参数并且并未直接设置参数对应的值的抽取
        String[] propertyNames = unsatisfiedNonSimpleProperties(mbd, bw);
        for (String propertyName : propertyNames) {
            PropertyDescriptor pd=bw.getPropertyDescriptor(propertyName);
            Set<String>TypeSet=getBeanDefintionByType(pd.getPropertyType());
            for(String name:TypeSet)
            {
                if(name!=beanName) {
                    newPvs.add(propertyName, getBean(name));
                    break;
                }
            }

        }
    }
    protected  Object createBean(String beanName, RootBeanDefinition mbd)
    {
        Object bean = resolveBeforeInstantiation(beanName, mbd);
        if (bean != null) {
            return bean;
        }
        Object beanInstance = doCreateBean(beanName, mbd );
        return  beanInstance;
    }

    private Object resolveBeforeInstantiation(String beanName, RootBeanDefinition mbd) {
        Object bean=null;
        Class<?>targetType=mbd.getBeanClass();
        bean=applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
        return bean;
    }

    private Object applyBeanPostProcessorsBeforeInstantiation(Class<?> targetType, String beanName) {
        for(BeanPostProcessor bp:getBeanPostProcessors())
        {
            if(bp instanceof InstantiationAwareBeanPostProcessor)
            {
                Object res=((InstantiationAwareBeanPostProcessor) bp).postProcessBeforeInstantiation(targetType,beanName);
                return res;
            }
        }
        return  null;
    }


    protected  Object doCreateBean(String beanName,RootBeanDefinition mbd)
    {

        BeanWrapper instanceWrapper = null;
//        创建包装bean的类
        instanceWrapper = createBeanInstance(beanName, mbd);
        final Object bean = (instanceWrapper != null ? instanceWrapper.getWrappedInstance() : null);
//        增添单例Bean构造工厂，用于三层缓存
        if(mbd.isSingleton())
        {
            addSingletonFactory(beanName, new ObjectFactory<Object>() {
                @Override
                public Object getObject() throws BeansException {
                    return bean;
                }
            });
        }

        //给bean添加参数
    populateBean(beanName, mbd, instanceWrapper);

//    bean最后初始化
        Object finalObject=initializeBean(bean,beanName);

         return finalObject;
    }

    private String[] unsatisfiedNonSimpleProperties(RootBeanDefinition mbd, BeanWrapper bw) {
        Set<String> result = new TreeSet<String>();
        PropertyValues pvs = mbd.getPropertyValues();
        PropertyDescriptor[] pds = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (pd.getWriteMethod() != null && !pvs.contains(pd.getName()) &&
                    (!pd.getPropertyType().isPrimitive())||(pd.getPropertyType().isArray()&&
                    pd.getPropertyType().getComponentType().isPrimitive()) ) {
                result.add(pd.getName());
            }
        }
        return result.toArray(new String[result.size()]);
    }

    protected  void applyPropertyValues(String beanName, BeanDefinition mbd, BeanWrapper bw, PropertyValues pvs)
    {
        List<PropertyValue> deepCopy = new ArrayList<PropertyValue>(pvs.getPropertyValues().length);
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this, beanName, mbd, (BeanWrapperImpl)bw);
        for (PropertyValue pv : pvs.getPropertyValues()) {
            if (pv.isConverted()) {
                deepCopy.add(pv);
            }
            else
            {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
//                将xml语义类型转成Java类型,例如ref转成实际引用
                Object resolvedValue = valueResolver.resolveValueIfNecessary(pv, originalValue);
//                将 value 转成被用的基本类型
              Object convertedValue  = convertForProperty(resolvedValue, propertyName, (BeanWrapperImpl)bw);
                    pv.setConvertedValue(convertedValue);
                deepCopy.add(pv);
            }

        }
        bw.setPropertyValuetoBean(new MutablePropertyValues(deepCopy));
    }

    protected  Object convertForProperty(Object value, String propertyName, BeanWrapperImpl bw)
    {

        return bw.convertForProperty(value,propertyName);

    }

    protected  BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd)
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

    @Override
    public Object initializeBean(Object bean, String beanName) throws BeansException {
//        todo
        Object result=bean;
        for (BeanPostProcessor beanProcessor : getBeanPostProcessors()) {
            result= beanProcessor.postProcessBeforeInitialization(bean, beanName);
        }
        invokeAwareMethods(beanName,bean);
        for (BeanPostProcessor beanProcessor : getBeanPostProcessors()) {
            result= beanProcessor.postProcessAfterInitialization(bean, beanName);
        }
        return  result;
    }
    //如果是特殊Bean设置相关参数
     void invokeAwareMethods(String beanName, Object bean)
     {
      if(bean instanceof BeanFactoryAware)
      {
          ((BeanFactoryAware)bean).setFactory(this);
      }
     }


    //   初始化BeanWrapper
    protected  void initBeanWrapper(BeanWrapper bw)
    {

    }


}
