package smallSpring.beans.wrapper;

public class BeanWrapperImpl implements  BeanWrapper {
    Object wrapperObject;

    @Override
    public Object getWrappedInstance() {
        return this.wrapperObject;
    }

    public BeanWrapperImpl(Object wrapperObject) {
        this.wrapperObject = wrapperObject;
    }

    @Override
    public Class<?> getWrappedClass() {
        return this.wrapperObject!=null? wrapperObject.getClass():null;
    }
}
