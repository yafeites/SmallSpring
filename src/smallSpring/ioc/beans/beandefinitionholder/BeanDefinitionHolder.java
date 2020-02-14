package smallSpring.ioc.beans.beandefinitionholder;

import smallSpring.ioc.beandefiniton.BeanDefinition;
import smallSpring.ioc.beandefiniton.RootBeanDefinition;

public class BeanDefinitionHolder {

    private final RootBeanDefinition beanDefinition;

    private final String beanName;

    public RootBeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public String getBeanName() {
        return beanName;
    }

    public BeanDefinitionHolder(RootBeanDefinition beanDefinition, String beanName) {
        this.beanDefinition=beanDefinition;
        this.beanName=beanName;
    }
}
