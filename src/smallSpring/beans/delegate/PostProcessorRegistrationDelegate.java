package smallSpring.beans.delegate;

import smallSpring.beanpostprocessor.BeanPostProcessor;
import smallSpring.context.AbstractApplicationContext;
import smallSpring.factory.ConfigurableListableBeanFactory;

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
