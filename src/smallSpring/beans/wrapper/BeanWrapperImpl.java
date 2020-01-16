package smallSpring.beans.wrapper;

import smallSpring.beans.cachedintrospectionresults.CachedIntrospectionResults;
import smallSpring.beans.converter.TypeConverter;
import smallSpring.beans.propertyvalue.MutablePropertyValues;
import smallSpring.beans.propertyvalue.PropertyValue;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanWrapperImpl implements  BeanWrapper  , TypeConverter {
    Object wrapperObject;
    private CachedIntrospectionResults cachedIntrospectionResults;

    @Override
    public Object getWrappedInstance() {
        return this.wrapperObject;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getCachedIntrospectionResults().getPropertyDescriptors();
    }

    @Override
    public PropertyDescriptor getPropertyDescriptor(String name) {
        return getCachedIntrospectionResults().getPropertyDescriptor(name);
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
    public void setPropertyValuetoBean(MutablePropertyValues mutablePropertyValues) {

        for(PropertyValue pv:mutablePropertyValues.getPropertyValues())
        {
            if(pv.isConverted())
            {
                setPropertyValuetoBean(pv.getName(),pv.getConvertedValue());
            }
            else
            {
                setPropertyValuetoBean(pv.getName(), pv.getValue());
            }

        }
    }
    public Object convertForProperty(Object value, String propertyName)
    {
        CachedIntrospectionResults cachedIntrospectionResults = getCachedIntrospectionResults();
        PropertyDescriptor pd = cachedIntrospectionResults.getPropertyDescriptor(propertyName);

    }

    private void setPropertyValuetoBean(String name, Object value) {
        PropertyDescriptor pd=getCachedIntrospectionResults().getPropertyDescriptor(name);
        Method writeMethod=pd.getWriteMethod();
        try {
            writeMethod.invoke(wrapperObject,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private CachedIntrospectionResults getCachedIntrospectionResults() {
        if (this.cachedIntrospectionResults == null) {
            try {
                this.cachedIntrospectionResults = CachedIntrospectionResults.forClass(getWrappedClass());
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
        return this.cachedIntrospectionResults;
    }
}
