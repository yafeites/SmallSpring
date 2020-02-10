package smallSpring.aop.advisor;

import smallSpring.aop.PointCut.Pointcut;
import smallSpring.aop.advice.Interface.Advice;

public class DefaultPointCutAdvisor implements  PointCutAdvisor {
    private Pointcut pointcut=Pointcut.TRUE;
    private Advice advice;

    public DefaultPointCutAdvisor(Advice advice) {
        this.advice = advice;
    }

    public DefaultPointCutAdvisor(Pointcut pointcut, Advice advice) {
        this.pointcut = pointcut;
        this.advice = advice;
    }

    public DefaultPointCutAdvisor() {
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
