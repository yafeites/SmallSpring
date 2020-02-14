package smallSpring.ioc.context;

import smallSpring.ioc.exception.BeansException;
import smallSpring.ioc.factory.BeanFactory;

public interface ConfigurableApplicationContext {
    void refresh() throws BeansException;
    BeanFactory getBeanFactory();
}
