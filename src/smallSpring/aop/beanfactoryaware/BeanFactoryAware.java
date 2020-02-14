package smallSpring.aop.beanfactoryaware;

import smallSpring.ioc.factory.BeanFactory;

public interface BeanFactoryAware {
    void  setFactory(BeanFactory beanFactory);
}
