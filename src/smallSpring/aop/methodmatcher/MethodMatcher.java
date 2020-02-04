package smallSpring.aop.methodmatcher;

import java.lang.reflect.Method;

public interface  MethodMatcher {
    boolean matches(Method method, Class targetClass);
    MethodMatcher TRUE=TrueMethodMatcher.INSTANCE;

}
