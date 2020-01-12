package smallSpring.beans.propertyvalue;

public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public PropertyValue(PropertyValue pv) {
     this.name=pv.name;
     this.value=pv.value;
    }


    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
