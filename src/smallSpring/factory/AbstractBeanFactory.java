package smallSpring.factory;

import smallSpring.beandefiniton.RootBeanDefinition;
import smallSpring.beanpostprocessor.BeanPostProcessor;
import smallSpring.beans.factorybean.FactoryBean;
import smallSpring.exception.BeanCreationException;
import smallSpring.exception.BeansException;
import smallSpring.registry.DefaultSingletonBeanRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public  abstract  class AbstractBeanFactory  extends DefaultSingletonBeanRegistry implements  BeanFactory{
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private ClassLoader beanClassLoader=Thread.currentThread().getContextClassLoader();
    private final Map<String, RootBeanDefinition> MergedBeanDefinitions =
            new ConcurrentHashMap<String, RootBeanDefinition>(256);
    private final Map<Class<?>,Set<String>>classBeanDefinitionMap
            =new ConcurrentHashMap<Class<?>, Set<String>>(256);
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name);
    }
    protected  <T> T doGetBean(final String beanName)
    {
        Object sharedInstance=getSingleton(beanName);
        Object bean;
        if(sharedInstance!=null)
        {
            bean=getObjectForBeanInstance(sharedInstance, beanName);
        }
        else
        {
            final RootBeanDefinition mbd = getMergedLocalBeanDefintion(beanName);
            if (mbd.isSingleton()) {
                sharedInstance = getSingleton(beanName, new ObjectFactory<Object>() {
                    @Override
                    public Object getObject() throws BeansException {

                            return createBean(beanName, mbd);


                    }
                });
                bean = getObjectForBeanInstance(sharedInstance,beanName);
            }
            else if (mbd.isPrototype()) {
                Object prototypeInstance = null;
                prototypeInstance = createBean(beanName, mbd);
                bean = getObjectForBeanInstance(sharedInstance,beanName);
            }
            else  bean=null;
        }


            return (T)bean;
    }
    protected abstract Object createBean(String beanName, RootBeanDefinition mbd)
            throws BeanCreationException;
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return  this.beanPostProcessors;
    }


    public boolean containsBean(String propertyName) {
        return  MergedBeanDefinitions.containsKey(propertyName);
    }

   public Set getBeanDefintionByType(Class<?>cls)
   {

       return classBeanDefinitionMap.get(cls);
   }
   public void setBeanDefintionByType(String name)
   {

       Class c= MergedBeanDefinitions.get(name).getBeanClass();
       if(!classBeanDefinitionMap.containsKey(c))
       {

           classBeanDefinitionMap.put(c,(new TreeSet<String>()));
       }
       classBeanDefinitionMap.get(c).add(name);
   }
    protected RootBeanDefinition getMergedLocalBeanDefintion(String beanName) throws BeansException {
        return MergedBeanDefinitions.get(beanName);
    }
    protected Object getObjectForBeanInstance(Object sharedInstance, String name ) {
        if(!(sharedInstance instanceof FactoryBean))
        {
            return  sharedInstance;
        }
        else
        {
            FactoryBean<?> factory = (FactoryBean<?>) sharedInstance;
            try {
                return  factory.getObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

         return  null;


    }


    public ClassLoader getBeanClassLoader() {
        return  beanClassLoader;
    }
}
