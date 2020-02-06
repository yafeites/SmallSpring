package smallSpring.aop.Support;

import smallSpring.aop.advice.Interface.Advice;
import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.methodinterceptor.MethodInterceptor;

public interface AdvisorAdapter {
    boolean supportAdvice(Advice advice);

    MethodInterceptor getInterceptor(Advisor advisor);
}
