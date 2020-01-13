package smallSpring.beans.reslover;

import smallSpring.beandefiniton.BeanDefinition;
import smallSpring.beans.classUtils.Impl.RuntimeBeanReference;
import smallSpring.factory.AbstractBeanFactory;
import sun.plugin.com.TypeConverter;

public class BeanDefinitionValueResolver {
    private final AbstractBeanFactory beanFactory;

    private final String beanName;

    private final BeanDefinition beanDefinition;

    public BeanDefinitionValueResolver(
            AbstractBeanFactory beanFactory, String beanName, BeanDefinition beanDefinition, TypeConverter typeConverter) {

        this.beanFactory = beanFactory;
        this.beanName = beanName;
        this.beanDefinition = beanDefinition;

    }
    public Object resolveValueIfNecessary(Object argName, Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            return resolveReference(argName, ref);
        }
        else if (value instanceof TypedStringValue) {

        }
    }

    private Object resolveReference(Object argName, RuntimeBeanReference ref) {
        String refName = ref.getBeanName();
        Object bean = null;
        try {
            bean = this.beanFactory.getBean(refName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bean;

    }

}
