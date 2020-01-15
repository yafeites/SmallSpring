package smallSpring.factory;

import smallSpring.exception.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;

}
