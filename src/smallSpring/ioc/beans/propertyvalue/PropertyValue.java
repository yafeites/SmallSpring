package smallSpring.ioc.beans.propertyvalue;

public class PropertyValue {

    private final String name;

    private final Object value;

    private Object convertedValue;
    private boolean converted = false;
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

    public synchronized  boolean isConverted() {
        return converted;
    }

    public synchronized Object getConvertedValue() {
        return this.convertedValue;
    }

    public  synchronized  void setConvertedValue(Object convertedValue) {
        this.convertedValue=convertedValue;
        converted=true;
    }
}
