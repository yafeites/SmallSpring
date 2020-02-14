package smallSpring.ioc.factory;

public interface ConfigurableListableBeanFactory extends  ListableBeanFactory, AutowireCapableBeanFactory,ConfiguableBeanFactory{
    void preInstantiateSingletons();

}
