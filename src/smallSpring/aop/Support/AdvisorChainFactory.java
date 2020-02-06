package smallSpring.aop.Support;

import smallSpring.aop.advice.Interface.Advice;

import java.lang.reflect.Method;
import java.util.List;

public interface AdvisorChainFactory {
    List<Object> getInterceptorAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?>targetClass);
}
