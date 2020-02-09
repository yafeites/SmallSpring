package smallSpring.aop.targetsource;



public interface TargetSource {
    Object getTarget();
    Class<?>getTargetClass();

}
