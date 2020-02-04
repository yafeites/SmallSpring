package smallSpring.aop.classfilter;

public interface ClassFilter {
    public  boolean matches(Class c);
    ClassFilter TRUE=TrueClassFilter.INSTANCE;
}
