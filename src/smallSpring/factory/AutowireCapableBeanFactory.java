package smallSpring.factory;

import smallSpring.exception.BeansException;

public interface AutowireCapableBeanFactory {
    Object initializeBean(Object existingBean,String beanName) throws BeansException;


}
