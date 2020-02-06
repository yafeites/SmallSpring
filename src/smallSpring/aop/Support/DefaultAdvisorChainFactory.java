package smallSpring.aop.Support;

import smallSpring.aop.advice.Interface.Advice;
import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.advisor.PointCutAdvisor;
import smallSpring.aop.methodinterceptor.MethodInterceptor;
import smallSpring.aop.methodmatcher.MethodMatcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DefaultAdvisorChainFactory implements AdvisorChainFactory {
    @Override
    public List<Object> getInterceptorAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass) {
        List<Object>interceptorlists=new ArrayList<>(config.getAdvisors().size());
        AdvisorAdapterRegistry registry=new DefaultAdvisorAdapterRegister();
        for(Advisor advisor:config.getAdvisors())
        {
            if(advisor instanceof PointCutAdvisor)
            {
                PointCutAdvisor pointCutAdvisor=(PointCutAdvisor)advisor;
                MethodInterceptor[]interceptors=registry.getInterceptors(advisor);
                MethodMatcher mm=pointCutAdvisor.getPointCut().getMethodMatcher();
                if(mm.matches(method,targetClass));
            }
        }
    }


}
