package smallSpring.beans.classUtils.Impl;

public class TypedStringValue {
    private String value;

    private volatile Object targetType;
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
}
