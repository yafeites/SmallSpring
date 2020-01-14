package smallSpring.factory;

import smallSpring.beandefiniton.BeanDefinition;
import smallSpring.context.AbstractApplicationContext;
import smallSpring.context.ConfigurableApplicationContext;
import smallSpring.exception.BeansException;
import smallSpring.registry.BeanDefinitionRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements ConfigurableListableBeanFactory,BeanDefinitionRegistry{
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(256);

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public void removeBeanDefinition(String beanName) {

    }

    @Override
    public BeanDefinition getMergedLocalBeanDefintion(String beanName) {
        return null;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> c) {
        return new String[0];
    }

    public DefaultListableBeanFactory() {

    }
}
