package smallSpring.aop.Support;

import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.methodinterceptor.MethodInterceptor;

public interface AdvisorAdapterRegistry {
    MethodInterceptor[] getInterceptors(Advisor advisor);
    void registerAdvisorAdapter(AdvisorAdapter adapter);
}
