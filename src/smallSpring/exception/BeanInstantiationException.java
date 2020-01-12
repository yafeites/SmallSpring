package smallSpring.exception;

import java.beans.Beans;

public class BeanInstantiationException extends BeansException {
    private Class<?> beanClass;
    public BeanInstantiationException(Class<?> clazz, String specified_class_is_an_interface) {
        super(specified_class_is_an_interface);
        this.beanClass=clazz;
    }
}
