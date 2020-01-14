package smallSpring.beans.cachedintrospectionresults;

import smallSpring.exception.BeansException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.LinkedHashMap;
import java.util.Map;

public class CachedIntrospectionResults {
    private final Map<String, PropertyDescriptor> propertyDescriptorCache;
    private final BeanInfo beanInfo;

    private CachedIntrospectionResults(Class<?> beanClass) throws BeansException, IntrospectionException {
        this.propertyDescriptorCache = new LinkedHashMap<String, PropertyDescriptor>();
        BeanInfo beanInfo= Introspector.getBeanInfo(beanClass);
        this.beanInfo = beanInfo;
        PropertyDescriptor[] pds = this.beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor pd:pds)
        {
            this.propertyDescriptorCache.put(pd.getName(),pd);
        }

    }
    static CachedIntrospectionResults forClass(Class<?> beanClass) throws BeansException, IntrospectionException {
        CachedIntrospectionResults results=new CachedIntrospectionResults(beanClass);
    }
}
