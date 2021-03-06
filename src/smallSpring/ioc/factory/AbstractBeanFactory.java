package smallSpring.ioc.factory;

import smallSpring.ioc.beandefiniton.RootBeanDefinition;
import smallSpring.ioc.beanpostprocessor.BeanPostProcessor;
import smallSpring.ioc.beans.factorybean.FactoryBean;
import smallSpring.ioc.exception.BeanCreationException;
import smallSpring.ioc.exception.BeansException;
import smallSpring.ioc.registry.DefaultSingletonBeanRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public  abstract  class AbstractBeanFactory  extends DefaultSingletonBeanRegistry implements  ConfiguableBeanFactory{
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private ClassLoader beanClassLoader=Thread.currentThread().getContextClassLoader();
    public final Map<String, RootBeanDefinition> MergedBeanDefinitions =
            new ConcurrentHashMap<String, RootBeanDefinition>(256);
    private final Map<Class<?>,Set<String>>classBeanDefinitionMap
            =new ConcurrentHashMap<Class<?>, Set<String>>(256);

    @Override
    public void addPostProcessors(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name);
    }
    protected  <T> T doGetBean(final String beanName)
    {
//        从缓存中获取
        Object sharedInstance=getSingleton(beanName);
        Object bean;
        if(sharedInstance!=null)
        {
            bean=getObjectForBeanInstance(sharedInstance, beanName);
        }
//        缓存中不存在创建一个全新的Bean
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
        Set<String>set=new HashSet<>();
        for(Map.Entry<Class<?>,Set<String>> entry:classBeanDefinitionMap.entrySet())
        {
//           如果cls是此类的父类,则加入
            if(cls.isAssignableFrom(entry.getKey()))
            {
                    set.addAll(entry.getValue());
            }
        }
       return set ;
   }
   public void DosetBeanDefintionByType(String name,Class<?>c)
   {

       if(!classBeanDefinitionMap.containsKey(c))
       {
           classBeanDefinitionMap.put(c,(new TreeSet<String>()));
       }
       classBeanDefinitionMap.get(c).add(name);
   }
    protected RootBeanDefinition getMergedLocalBeanDefintion(String beanName) throws BeansException {
        return MergedBeanDefinitions.get(beanName);
    }
    protected void removeMergedLocalBeanDefintion(String beanName) throws BeansException {
         MergedBeanDefinitions.remove(beanName);
    }
    protected  void registerMergedLocalBeanDefintion(String beanName,RootBeanDefinition beanDefinition)
    {
       MergedBeanDefinitions.put(beanName,beanDefinition);
    }

    protected  int getMergedLocalBeanDefintionCount()
    {
        return MergedBeanDefinitions.size();
    }
    protected  String[] getMergedLocalBeanName()
    {
        String[]res=new String[getMergedLocalBeanDefintionCount()];
        int i=0;
        for(String s:MergedBeanDefinitions.keySet())
        {
                res[i]=s;
                i++;
        }
        return res;
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
