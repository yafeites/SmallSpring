package smallSpring.beans.propertyvalue;

import java.util.ArrayList;
import java.util.List;

public class MutablePropertyValues implements PropertyValues {
    private final List<PropertyValue> propertyValueList;
    public MutablePropertyValues() {

        this.propertyValueList = new ArrayList<PropertyValue>(0);
    }

    public MutablePropertyValues(PropertyValues original) {
        // We can optimize this because it's all new:
        // There is no replacement of existing property values.
        if (original != null) {
            PropertyValue[] pvs = original.getPropertyValues();
            this.propertyValueList = new ArrayList<PropertyValue>(pvs.length);
            for (PropertyValue pv : pvs) {
                this.propertyValueList.add(new PropertyValue(pv));
            }
        }
        else {
            this.propertyValueList = new ArrayList<PropertyValue>(0);
        }
    }

    @Override
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[this.propertyValueList.size()]);
    }

    @Override
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
