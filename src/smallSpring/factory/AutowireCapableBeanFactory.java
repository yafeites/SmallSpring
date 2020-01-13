package smallSpring.factory;

import smallSpring.exception.BeansException;

public interface AutowireCapableBeanFactory {
    int AUTOWIRE_BY_TYPE = 2;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_NO = 3;

    Object initializeBean(Object existingBean,String beanName) throws BeansException;


}
