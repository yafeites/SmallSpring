package smallSpring.beandefiniton;

import smallSpring.beans.propertyvalue.MutablePropertyValues;
import smallSpring.beans.propertyvalue.PropertyValues;
import smallSpring.resource.Resource;

import java.awt.font.ShapeGraphicAttribute;
import java.awt.font.TextLayout;

public class AbstractBeanDefiniton implements   BeanDefinition {
    private static final String SCOPE_SINGLETON ="singleton" ;
    private static final String SCOPE_DEFAULT = "";
    private static final String SCOPE_PROTOTYPE = "prototype";

    private MutablePropertyValues mutablePropertyValues;
    private Resource resource;
    private String scope=SCOPE_DEFAULT;

    public PropertyValues getPropertyValues() {
        return mutablePropertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.mutablePropertyValues = propertyValues;
    }

    private Class beanClass;

    private String beanClassName;



    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSingleton() {

        return SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
    }

    @Override
    public boolean isPrototype() {
        {
            return SCOPE_PROTOTYPE.equals(scope);
        }
    }
}
