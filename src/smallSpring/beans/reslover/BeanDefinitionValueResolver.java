package smallSpring.beans.reslover;

import smallSpring.beandefiniton.BeanDefinition;
import smallSpring.beans.classUtils.Impl.RuntimeBeanReference;
import smallSpring.beans.classUtils.Impl.TypedStringValue;
import smallSpring.beans.converter.TypeConverter;
import smallSpring.factory.AbstractBeanFactory;


public class BeanDefinitionValueResolver {
    private final AbstractBeanFactory beanFactory;

    private final String beanName;

    private final BeanDefinition beanDefinition;

    private final TypeConverter typeConverter;
    public BeanDefinitionValueResolver(
            AbstractBeanFactory beanFactory, String beanName, BeanDefinition beanDefinition, TypeConverter typeConverter) {

        this.beanFactory = beanFactory;
        this.beanName = beanName;
        this.beanDefinition = beanDefinition;
        this.typeConverter=typeConverter;

    }
    public Object resolveValueIfNecessary(Object argName, Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            return resolveReference(argName, ref);
        }
        else if (value instanceof TypedStringValue) {
            TypedStringValue typedStringValue = (TypedStringValue) value;
           Object valueObject=value;
            try {
                Class<?> resolvedTargetType = resolveTargetType(typedStringValue);
                if (resolvedTargetType != null) {
                    return this.typeConverter.convertIfNecessary(valueObject, resolvedTargetType);
                }
                else {
                    return valueObject;
                }
            }
        }
        else  return  null;
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
