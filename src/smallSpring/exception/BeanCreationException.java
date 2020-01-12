package smallSpring.exception;

public class BeanCreationException extends BeansException{
    public BeanCreationException (String beanName) {
    super(beanName);
    }
}
