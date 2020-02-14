package smallSpring.ioc.beans.cachedintrospectionresults;

import smallSpring.ioc.exception.BeansException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CachedIntrospectionResults {
    private final Map<String, PropertyDescriptor> propertyDescriptorCache;
    private final BeanInfo beanInfo;
    static final ConcurrentMap<Class<?>, CachedIntrospectionResults> ClassCache =

            new ConcurrentHashMap<Class<?>, CachedIntrospectionResults>(64);
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
   public static CachedIntrospectionResults forClass(Class<?> beanClass) throws BeansException, IntrospectionException {
        CachedIntrospectionResults results = ClassCache.get(beanClass);
        if(results!=null)
        {
            return results;
        }
         results=new CachedIntrospectionResults(beanClass);
        ClassCache.putIfAbsent(beanClass,results);
        return results;
    }


    public PropertyDescriptor getPropertyDescriptor(String name) {
        return propertyDescriptorCache.get(name);
    }
    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] pds = new PropertyDescriptor[this.propertyDescriptorCache.size()];
        int i=0;
        for(PropertyDescriptor pd:this.propertyDescriptorCache.values())
        {
            pds[i]=pd;
            i++;
        }
        return pds;
    }
}
