package smallSpring.ioc.exception;

public class BeanCurrentlyInCreationException extends BeansException {
    public BeanCurrentlyInCreationException(String beanName) {
        super(beanName);
    }
}
