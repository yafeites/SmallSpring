package smallSpring.aop.autoproxycreator;

import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.beanfactoryaware.BeanFactoryAware;
import smallSpring.aop.methodinterceptor.MethodInterceptor;
import smallSpring.beanpostprocessor.BeanPostProcessor;
import smallSpring.exception.BeansException;
import smallSpring.factory.BeanFactory;

public class AdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Advisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
    }

    @Override
    public void setFactory(BeanFactory beanFactory) {

    }
    public
}
