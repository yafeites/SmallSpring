package smallSpring.aop.Support;

import smallSpring.aop.advisor.Advisor;

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
}
