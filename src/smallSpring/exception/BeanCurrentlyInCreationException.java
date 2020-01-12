package smallSpring.exception;

public class BeanCurrentlyInCreationException extends BeansException {
    public BeanCurrentlyInCreationException(String beanName) {
        super(beanName);
    }
}
