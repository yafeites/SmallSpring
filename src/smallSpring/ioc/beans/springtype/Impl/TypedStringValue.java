package smallSpring.ioc.beans.springtype.Impl;



public class TypedStringValue {
    private String value;

    private volatile Object targetType;

    public void setTargetTypeName(String targetTypeName) {
        TargetTypeName = targetTypeName;
    }

    private  String TargetTypeName;
    public TypedStringValue(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getTargetType() {
        return targetType;
    }

    public void setTargetType(Object targetType) {
        this.targetType = targetType;
    }

    public Class<?> resloveTargetType(ClassLoader beanClassLoader) {
//        try {
//            return beanClassLoader==null? beanClassLoader.loadClass(TargetTypeName) : Class.forName(TargetTypeName);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    private String getTargetTypeName() {
        return  TargetTypeName;
    }
}
