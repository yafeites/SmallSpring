package smallSpring.ioc.factory;

import smallSpring.ioc.exception.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;

}
