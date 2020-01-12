package smallSpring.registry;

public interface SingletonBeanFactory {
    void registerSingleton(String beanName,Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);


}
