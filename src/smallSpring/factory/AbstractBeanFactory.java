package smallSpring.factory;

import smallSpring.beandefiniton.RootBeanDefinition;
import smallSpring.registry.DefaultSingletonBeanRegistry;

public class AbstractBeanFactory  extends DefaultSingletonBeanRegistry implements  BeanFactory{
    private ClassLoader beanClassLoader=Thread.currentThread().getContextClassLoader();
    @Override
    public Object getBean(String name) throws Exception {
        return doGetBean(name);
    }
    protected  <T> T doGetBean(final String name)
    {
        Object sharedInstance=getSingleton(name);
        Object bean;
        if(sharedInstance!=null)
        {
            bean=getObjectForBeanInstance(sharedInstance, name, name, null);
        }

    }

    protected Object getObjectForBeanInstance(Object sharedInstance, String name, String name1, RootBeanDefinition mbd) {
        if()
            // TODO
            


    }

    public ClassLoader getBeanClassLoader() {
        return  beanClassLoader;
    }
}
