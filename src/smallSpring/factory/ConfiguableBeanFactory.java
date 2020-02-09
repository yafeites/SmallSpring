package smallSpring.factory;

import smallSpring.aop.beanfactoryaware.BeanFactoryAware;
import smallSpring.beanpostprocessor.BeanPostProcessor;

public interface ConfiguableBeanFactory extends BeanFactory {
    void addPostProcessors(BeanPostProcessor beanPostProcessor);
}
