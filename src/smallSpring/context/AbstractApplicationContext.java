package smallSpring.context;

import smallSpring.exception.BeansException;
import smallSpring.factory.AbstractAutowireCapableBeanFactory;
import smallSpring.factory.BeanFactory;
import smallSpring.factory.ConfigurableListableBeanFactory;
import smallSpring.factory.DefaultListableBeanFactory;

public abstract class AbstractApplicationContext  implements  ConfigurableApplicationContext{


    @Override
    public void refresh() throws BeansException {
        ConfigurableListableBeanFactory beanFactory=obtainFreshBeanFactory();
        finishBeanFactoryInitialization(beanFactory);
    }

    protected  ConfigurableListableBeanFactory obtainFreshBeanFactory()
    {
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory=getBeanFactory();
        return beanFactory;
    }

    protected abstract void refreshBeanFactory();

    @Override
    public abstract ConfigurableListableBeanFactory getBeanFactory() ;
}
