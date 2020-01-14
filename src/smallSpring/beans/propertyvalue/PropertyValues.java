package smallSpring.beans.propertyvalue;

public interface PropertyValues {
    PropertyValue[] getPropertyValues();

    PropertyValue getPropertyValue(String propertyName);

    boolean contains(String name);

    public void add(String propertyName, Object bean) ;
}
