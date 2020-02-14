package smallSpring.ioc.strategy;

import smallSpring.ioc.beandefiniton.RootBeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(RootBeanDefinition bd,String beanName) throws NoSuchMethodException;

}
