package smallSpring.ioc.factory;

public interface ListableBeanFactory extends  BeanFactory {
    int getBeanDefinitionCount();
    String[] getBeanDefinitionNames();
    String[] getBeanNamesForType(Class<?>c);
}
