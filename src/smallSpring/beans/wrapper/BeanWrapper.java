package smallSpring.beans.wrapper;

import smallSpring.beans.propertydescriptor.PropertyDescriptor;
import smallSpring.beans.propertyvalue.MutablePropertyValues;



public interface BeanWrapper {
    Object getWrappedInstance();

    Class<?> getWrappedClass();

    void setPropertyValues(MutablePropertyValues mutablePropertyValues);
    PropertyDescriptor[] getPropertyDescriptors();
}
