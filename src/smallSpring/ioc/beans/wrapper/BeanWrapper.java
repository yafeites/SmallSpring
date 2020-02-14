package smallSpring.ioc.beans.wrapper;


import smallSpring.ioc.beans.propertyvalue.MutablePropertyValues;

import java.beans.PropertyDescriptor;


public interface BeanWrapper {
    Object getWrappedInstance();

    Class<?> getWrappedClass();

    void setPropertyValuetoBean(MutablePropertyValues mutablePropertyValues);
    PropertyDescriptor getPropertyDescriptor(String name);
    PropertyDescriptor[] getPropertyDescriptors();


}
