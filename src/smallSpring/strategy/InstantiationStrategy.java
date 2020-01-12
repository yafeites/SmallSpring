package smallSpring.strategy;

import smallSpring.beandefiniton.RootBeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(RootBeanDefinition bd,String beanName) throws NoSuchMethodException;

}
