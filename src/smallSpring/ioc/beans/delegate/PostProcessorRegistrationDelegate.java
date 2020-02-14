package smallSpring.ioc.beans.delegate;

import smallSpring.ioc.beanpostprocessor.BeanPostProcessor;
import smallSpring.ioc.context.AbstractApplicationContext;
import smallSpring.ioc.factory.ConfigurableListableBeanFactory;

public class PostProcessorRegistrationDelegate {
    public  static void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory,
     AbstractApplicationContext applicationContext)
    {
        String[]postProcessorNames=beanFactory.getBeanNamesForType(BeanPostProcessor.class);
        {
            for(String name: postProcessorNames)
            {
                Object bean=beanFactory.getBean(name);
                beanFactory.addPostProcessors((BeanPostProcessor) bean);
            }
        }
    }
}

