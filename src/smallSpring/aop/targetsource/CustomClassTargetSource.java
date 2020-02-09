package smallSpring.aop.targetsource;

public class CustomClassTargetSource implements TargetSource {
    Class<?>TargetClass;
    @Override
    public Object getTarget() {
        return null;
    }

    public CustomClassTargetSource(Class<?>cls) {
        TargetClass=cls;
    }

    @Override
    public Class<?> getTargetClass() {
        return TargetClass;
    }
}
