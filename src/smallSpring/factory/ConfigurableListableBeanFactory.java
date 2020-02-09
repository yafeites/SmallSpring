package smallSpring.factory;

import smallSpring.beanpostprocessor.BeanPostProcessor;

public interface ConfigurableListableBeanFactory extends  ListableBeanFactory, AutowireCapableBeanFactory,ConfiguableBeanFactory{
    void preInstantiateSingletons();

}
