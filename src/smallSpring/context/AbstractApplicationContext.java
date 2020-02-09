package smallSpring.context;

import smallSpring.beandefiniton.RootBeanDefinition;
import smallSpring.beans.delegate.PostProcessorRegistrationDelegate;
import smallSpring.exception.BeansException;
import smallSpring.factory.AbstractAutowireCapableBeanFactory;
import smallSpring.factory.BeanFactory;
import smallSpring.factory.ConfigurableListableBeanFactory;
import smallSpring.factory.DefaultListableBeanFactory;
import smallSpring.resourceloader.DefaultResourceLoader;

public abstract class AbstractApplicationContext   extends DefaultResourceLoader implements  ConfigurableApplicationContext{


    @Override
    public void refresh() throws BeansException {
//        增添BeanFactory
        ConfigurableListableBeanFactory beanFactory=obtainFreshBeanFactory();
//         注册beanProcesssors
        registerBeanPostProcessors(beanFactory);
//        结束Factory的构建
        finishBeanFactoryInitialization(beanFactory);
    }

    protected  void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory)
    {
        PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory,this);
    }

    protected  void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory)
    {
        beanFactory.preInstantiateSingletons();
    }

//    private void preInstantiateSingletons() {
//
//
//    }

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
