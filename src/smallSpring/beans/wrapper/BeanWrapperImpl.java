package smallSpring.beans.wrapper;

import smallSpring.beans.converter.TypeConverter;

public class BeanWrapperImpl implements  BeanWrapper  , TypeConverter {
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
