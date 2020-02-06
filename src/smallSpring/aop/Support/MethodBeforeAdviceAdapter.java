package smallSpring.aop.Support;

import smallSpring.aop.advice.Interface.Advice;
import smallSpring.aop.advice.Interface.MethodBeforeAdvice;
import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.methodinterceptor.MethodInterceptor;

public class MethodBeforeAdviceAdapter implements  AdvisorAdapter {
    @Override
    public boolean supportAdvice(Advice advice) {
        return  advice instanceof MethodBeforeAdvice;
    }

    @Override
    public MethodInterceptor getInterceptor(Advisor advisor) {
        MethodBeforeAdvice methodBeforeAdvice=(MethodBeforeAdvice)advisor.getAdvice();

        return  new MethodBeforeAdviceInterceptor(methodBeforeAdvice);
    }
}
