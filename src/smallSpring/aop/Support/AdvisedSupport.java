package smallSpring.aop.Support;

import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.classfilter.ClassFilter;
import smallSpring.aop.targetsource.SingletonTargetSource;
import smallSpring.aop.targetsource.TargetSource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AdvisedSupport  {
    public AdvisedSupport(List<Class<?>> interfaces) {
        this.interfaces = interfaces;
    }
    private AdvisorChainFactory advisorChainFactory=new DefaultAdvisorChainFactory();
    private  AopProxyFactory aopProxyFactory;
    private List<Class<?>> interfaces=new ArrayList<Class<?>>();
    TargetSource targetSource=null;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(Advisor... advisors) {
        this.advisors = Arrays.asList(advisors);
    }

    private List<Advisor> advisors=new LinkedList<>();

    public AopProxyFactory getAopProxyFactory() {
        return aopProxyFactory;
    }

    public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
        this.aopProxyFactory = aopProxyFactory;
    }

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
