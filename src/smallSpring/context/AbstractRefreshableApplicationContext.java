package smallSpring.context;

import smallSpring.factory.DefaultListableBeanFactory;

public abstract  class AbstractRefreshableApplicationContext extends  AbstractApplicationContext{
    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
    }

    private void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
}
