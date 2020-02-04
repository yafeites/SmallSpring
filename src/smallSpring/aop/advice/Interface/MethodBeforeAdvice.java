package smallSpring.aop.advice.Interface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends MethodAdvice {
  void Before(Method method, Object[] args, Object target) throws InvocationTargetException, IllegalAccessException;

}
