package smallSpring.registry;

import smallSpring.exception.BeanCurrentlyInCreationException;
import smallSpring.factory.ObjectFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanFactory {
    private final Set<String> registeredSingletons = new LinkedHashSet<String>(256);

    private static final Object NULL_OBJECT = new Object();

    private final Map<String,Object> singletonObjects=new ConcurrentHashMap<>();

    private  final Map<String,Object> earlySingletonObjects=new ConcurrentHashMap<>();

    private  final Map<String, ObjectFactory<?>>singletonFactories=new HashMap<String, ObjectFactory<?>>(16);

    private final Set<String> singletonsCurrentlyInCreation =
            Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>(16));

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {

    }

    @Override
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }
    protected void beforeSingletonCreation(String beanName) {
        if (!this.singletonsCurrentlyInCreation.add(beanName)) {
            throw new BeanCurrentlyInCreationException(beanName);
        }
    }


    private Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            synchronized (this.singletonObjects) {
                singletonObject = this.earlySingletonObjects.get(beanName);
                if (singletonObject == null && allowEarlyReference) {
                    ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                    if (singletonFactory != null) {
                        singletonObject = singletonFactory.getObject();
                        this.earlySingletonObjects.put(beanName, singletonObject);
                        this.singletonFactories.remove(beanName);
                    }
                }
            }
        }
        return (singletonObject != NULL_OBJECT ? singletonObject : null);
    }
    private  Object getSingleton(String beanName,ObjectFactory<?> singletonFactory)
    {
        synchronized (this.singletonObjects) {
            Object singletonObject=this.singletonObjects.get(beanName);;
            if(singletonObject==null)
            {
                singletonObject = singletonFactory.getObject();
                addSingleton(beanName, singletonObject);
            }
            return singletonObject==NULL_OBJECT? null: singletonObject;
        }
    }

    private void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, (singletonObject != null ? singletonObject : NULL_OBJECT));
            this.singletonFactories.remove(beanName);
            this.earlySingletonObjects.remove(beanName);
            this.registeredSingletons.add(beanName);
        }

    }

    private boolean isSingletonCurrentlyInCreation(String beanName) {
        return  singletonsCurrentlyInCreation.contains(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return this.singletonObjects.containsKey(beanName);
    }
}
