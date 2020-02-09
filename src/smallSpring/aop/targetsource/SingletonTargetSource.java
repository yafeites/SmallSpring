package smallSpring.aop.targetsource;

public class SingletonTargetSource implements TargetSource {
    Object target;
    public SingletonTargetSource(Object target) {

        this.target=target;
    }


    @Override
    public Object getTarget() {
        return this.target;
    }

    @Override
    public Class<?> getTargetClass() {
        return this.target.getClass();
    }
}
