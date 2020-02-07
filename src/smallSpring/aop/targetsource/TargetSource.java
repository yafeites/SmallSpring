package smallSpring.aop.targetsource;

import smallSpring.aop.classfilter.ClassFilter;

public interface TargetSource {
    Object getTarget();
    Class<?>getTargetClass();

}
