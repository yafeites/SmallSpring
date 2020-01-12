package smallSpring.strategy;

import smallSpring.beandefiniton.RootBeanDefinition;
import smallSpring.exception.BeanInstantiationException;

import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(RootBeanDefinition bd, String beanName)  {
        final Class<?> clazz = bd.getBeanClass();
        if (clazz.isInterface()) {
            throw new BeanInstantiationException(clazz, "Specified class is an interface");
        }
        try {
                return clazz.getDeclaredConstructor((Class[])null).newInstance();
            }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
         catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
