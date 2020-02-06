package smallSpring.aop.Support;

import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.classfilter.ClassFilter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdvisedSupport  {
    public AdvisedSupport(List<Class<?>> interfaces) {
        this.interfaces = interfaces;
    }
    private AdvisorChainFactory advisorChainFactory=new DefaultAdvisorChainFactory();
    private  AopProxyFactory aopProxyFactory;
    private List<Class<?>> interfaces=new ArrayList<Class<?>>();

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
    }

    private List<Advisor> advisors=new LinkedList<>();
    public AdvisedSupport() {
        this.aopProxyFactory=new DefaultAopProxyFactory();
    }
    public  AdvisorChainFactory getAdvisorChainFactory()
    {
        return this.advisorChainFactory;
    }
    public List<Object> getIntetceptorsAndDynamicInterceptionAdvice(Method method, Class<?>targetClass)
    {
        return getAdvisorChainFactory().getInterceptorAndDynamicInterceptionAdvice(this,method,targetClass);
    }
}
