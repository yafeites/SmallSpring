package smallSpring.aop.advisor;

import smallSpring.aop.PointCut.Pointcut;

public interface PointCutAdvisor  extends Advisor{
    Pointcut getPointCut();
}
