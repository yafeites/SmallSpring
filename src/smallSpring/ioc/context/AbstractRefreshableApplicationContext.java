package smallSpring.ioc.context;

import smallSpring.ioc.factory.ConfigurableListableBeanFactory;
import smallSpring.ioc.factory.DefaultListableBeanFactory;

public abstract  class AbstractRefreshableApplicationContext extends  AbstractApplicationContext{
    public String getConfigLocations() {
        return configLocations;
    }

    public void setConfigLocations(String configLocations) {
        this.configLocations = configLocations;
    }

    private String configLocations;
    private DefaultListableBeanFactory beanFactory;
    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory=beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) ;

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
    @Override
    public final ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }
    protected String getConfigLocation()
    {
        return this.configLocations;
    }
}
