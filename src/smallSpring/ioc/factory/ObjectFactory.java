package smallSpring.ioc.factory;
import smallSpring.ioc.exception.BeansException;

public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
