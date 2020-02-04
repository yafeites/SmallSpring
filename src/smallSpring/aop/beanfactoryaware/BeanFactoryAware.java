package smallSpring.aop.beanfactoryaware;

import smallSpring.factory.BeanFactory;

public interface BeanFactoryAware {
    void  setFactory(BeanFactory beanFactory);
}
