package smallSpring.beans.wrapper;

import smallSpring.beans.cachedintrospectionresults.CachedIntrospectionResults;
import smallSpring.beans.converter.TypeConverter;
import smallSpring.beans.propertyvalue.MutablePropertyValues;
import smallSpring.beans.propertyvalue.PropertyValue;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

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
    private Object convert(Object value,Class<?>targetClass)
    {
        String TextValue=null;
        if(value.getClass()!=String.class)
        {
            try
            {
                TextValue =(String)value;
            }
            catch ( Exception e)
            {
//                System.out.println(value.getClass());
                return  value;
            }
        }
        else
        {
            TextValue=value.toString();
        }
        if (Byte.class == targetClass||targetClass==byte.class) {
            return  Byte.valueOf(TextValue);
        }

        else if (Short.class == targetClass||targetClass==short.class) {
            return  Short.valueOf(TextValue);
        }
        else if (Integer.class == targetClass||targetClass==int.class) {
            return  Integer.valueOf(TextValue);
        }
        else if (Long.class == targetClass||targetClass==long.class) {
            return Long.valueOf(TextValue);
        }
        else if (BigInteger.class == targetClass) {
            return new BigInteger(TextValue);
        }
        else if (Float.class == targetClass||targetClass==float.class) {
            return Float.valueOf(TextValue);
        }
        else if (Double.class == targetClass||targetClass==double.class) {
            return Double.valueOf(TextValue);
        }
        else if (BigDecimal.class == targetClass || Number.class == targetClass) {
            return new BigDecimal(TextValue);
        }
        else  return   value;
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
    //
    public Object convertForProperty(Object value, String propertyName)
    {
        CachedIntrospectionResults cachedIntrospectionResults = getCachedIntrospectionResults();
        PropertyDescriptor pd = cachedIntrospectionResults.getPropertyDescriptor(propertyName);
       return  convert(value,pd.getWriteMethod().getParameterTypes()[0]);
    }

    private void setPropertyValuetoBean(String name, Object value) {
        PropertyDescriptor pd=getCachedIntrospectionResults().getPropertyDescriptor(name);
        Method writeMethod=pd.getWriteMethod();
        try {
//            System.out.println(value.getClass());
            writeMethod.invoke(wrapperObject,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
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
