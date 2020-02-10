package smallSpring.beanpostprocessor;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
            Object postProcessBeforeInstantiation(Class<?>beanClass,String beanName) ;
    Object postProcessAfterInstantiation(Class<?>beanClass,String beanName);
}
