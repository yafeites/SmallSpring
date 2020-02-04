package smallSpring.aop.PointCut;

import smallSpring.aop.classfilter.ClassFilter;
import smallSpring.aop.methodmatcher.MethodMatcher;

import java.lang.reflect.Method;

public class TruePointcut implements  Pointcut {
    private TruePointcut() {
    }
    static  final  Pointcut INSTANCE = new TruePointcut();
    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return MethodMatcher.TRUE;
    }
}
