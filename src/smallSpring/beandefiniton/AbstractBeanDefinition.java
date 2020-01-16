package smallSpring.beandefiniton;

import smallSpring.beans.propertyvalue.MutablePropertyValues;
import smallSpring.beans.propertyvalue.PropertyValues;
import smallSpring.factory.AutowireCapableBeanFactory;
import smallSpring.resource.Resource;

import java.awt.font.ShapeGraphicAttribute;
import java.awt.font.TextLayout;

public class AbstractBeanDefinition implements   BeanDefinition {
    private static final String SCOPE_SINGLETON ="singleton" ;
    private static final String SCOPE_DEFAULT = "";
    private static final String SCOPE_PROTOTYPE = "prototype";
    private int autowireMode = AUTOWIRE_NO;

    public static final int AUTOWIRE_BY_TYPE = AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;
    public static final int AUTOWIRE_BY_NAME = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;
    public static final int AUTOWIRE_NO = AutowireCapableBeanFactory.AUTOWIRE_NO;
    private MutablePropertyValues mutablePropertyValues;
    private Resource resource;
    private String scope=SCOPE_DEFAULT;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public AbstractBeanDefinition() {
        setPropertyValues(null);
    }

    public MutablePropertyValues getPropertyValues() {
        return mutablePropertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.mutablePropertyValues = (propertyValues != null ? propertyValues : new MutablePropertyValues());
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


    public void setAutowireMode(int autowireMode) {
        this.autowireMode = autowireMode;
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

    public int getResolvedAutowireMode() {
        return this.autowireMode;
    }
}
