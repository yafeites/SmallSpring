package smallSpring.aop.PointCut;

import smallSpring.aop.classfilter.ClassFilter;
import smallSpring.aop.methodmatcher.MethodMatcher;

public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
    Pointcut TRUE = TruePointcut.INSTANCE;

}
