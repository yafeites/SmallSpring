package smallSpring.factory;

public interface ListableBeanFactory extends  BeanFactory {
    int getBeanDefinitionCount();
    String[] getBeanDefinitionNames();
    String[] getBeanNamesForType(Class<?>c);
}
