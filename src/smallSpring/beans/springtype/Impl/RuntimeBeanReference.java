package smallSpring.beans.springtype.Impl;

import smallSpring.beans.springtype.BeanReference;

public class RuntimeBeanReference implements BeanReference {
    private final String beanName;
    @Override
    public String getBeanName() {
        return null;
    }
    public RuntimeBeanReference(String beanName) {
       this.beanName=beanName;
    }

}
