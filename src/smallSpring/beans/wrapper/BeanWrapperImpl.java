package smallSpring.beans.wrapper;

import smallSpring.beans.converter.TypeConverter;
import smallSpring.beans.propertydescriptor.PropertyDescriptor;
import smallSpring.beans.propertyvalue.MutablePropertyValues;
import smallSpring.beans.propertyvalue.PropertyValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanWrapperImpl implements  BeanWrapper  , TypeConverter {
    Object wrapperObject;

    @Override
    public Object getWrappedInstance() {
        return this.wrapperObject;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return new PropertyDescriptor[0];
    }

    public BeanWrapperImpl(Object wrapperObject) {
        this.wrapperObject = wrapperObject;
    }

    @Override
    public Class<?> getWrappedClass() {
        return this.wrapperObject!=null? wrapperObject.getClass():null;
    }

    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType) {
        return requiredType.cast(value);
    }

    @Override
    public void setPropertyValues(MutablePropertyValues mutablePropertyValues) {

        for(PropertyValue pv:mutablePropertyValues.getPropertyValues())
        {
                Method declaredMethod = null;
                try {
                    declaredMethod = pv.getName().getClass().getDeclaredMethod(
                            "set" + pv.getName().substring(0, 1).toUpperCase()
                                    + pv.getName().substring(1));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                declaredMethod.setAccessible(true);

                try {
                    declaredMethod.invoke(wrapperObject,pv.getValue());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
        }
    }
}
