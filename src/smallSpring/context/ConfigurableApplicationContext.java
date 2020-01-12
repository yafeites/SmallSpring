package smallSpring.context;

import smallSpring.exception.BeansException;
import smallSpring.factory.BeanFactory;

public interface ConfigurableApplicationContext {
    void refresh() throws BeansException;
    BeanFactory getBeanFactory();
}
