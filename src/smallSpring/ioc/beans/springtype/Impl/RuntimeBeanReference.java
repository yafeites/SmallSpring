package smallSpring.ioc.beans.springtype.Impl;

import smallSpring.ioc.beans.springtype.BeanReference;

public class RuntimeBeanReference implements BeanReference {
    private final String beanName;
    @Override
    public String getBeanName() {
        return beanName;
    }
    public RuntimeBeanReference(String beanName) {
       this.beanName=beanName;
    }

}
