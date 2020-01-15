package smallSpring.context;

import smallSpring.beandefiniton.RootBeanDefinition;
import smallSpring.exception.BeansException;
import smallSpring.factory.AbstractAutowireCapableBeanFactory;
import smallSpring.factory.BeanFactory;
import smallSpring.factory.ConfigurableListableBeanFactory;
import smallSpring.factory.DefaultListableBeanFactory;
import smallSpring.resourceloader.DefaultResourceLoader;

public abstract class AbstractApplicationContext   extends DefaultResourceLoader implements  ConfigurableApplicationContext{


    @Override
    public void refresh() throws BeansException {
        ConfigurableListableBeanFactory beanFactory=obtainFreshBeanFactory();
        finishBeanFactoryInitialization(beanFactory);
    }

    protected  void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory)
    {
        beanFactory.preInstantiateSingletons();
    }

    private void preInstantiateSingletons() {


    }

    protected  ConfigurableListableBeanFactory obtainFreshBeanFactory()
    {
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory=getBeanFactory();
        return beanFactory;
    }

    protected abstract void refreshBeanFactory();
    public Object getBean(String name)  {
        return getBeanFactory().getBean(name);
    }
    @Override
    public abstract ConfigurableListableBeanFactory getBeanFactory() ;
}
