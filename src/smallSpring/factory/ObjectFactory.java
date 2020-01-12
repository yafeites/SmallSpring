package smallSpring.factory;
import smallSpring.exception.BeansException;

public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
