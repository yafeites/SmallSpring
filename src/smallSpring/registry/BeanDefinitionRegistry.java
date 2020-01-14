package smallSpring.registry;

import smallSpring.beandefiniton.BeanDefinition;

public interface BeanDefinitionRegistry  {
    void removeBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName);


    String[] getBeanDefinitionNames();

    int getBeanDefinitionCount();
}
