package smallSpring.aop.methodmatcher;

import smallSpring.aop.classfilter.TrueClassFilter;

import java.lang.reflect.Method;

public class TrueMethodMatcher implements  MethodMatcher{
    public  static  final TrueMethodMatcher INSTANCE=new TrueMethodMatcher();
    private TrueMethodMatcher()
    {

    }
    @Override
    public boolean matches(Method method, Class targetClass) {
        return true;
    }
}
