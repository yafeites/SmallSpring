package smallSpring.ioc.factory;

import smallSpring.ioc.beanpostprocessor.BeanPostProcessor;

public interface ConfiguableBeanFactory extends BeanFactory {
    void addPostProcessors(BeanPostProcessor beanPostProcessor);
}
