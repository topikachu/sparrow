package net.topikachu.dyna.entity.dynar;

import org.eclipse.persistence.internal.dynamic.DynamicEntityImpl;
import org.eclipse.persistence.internal.dynamic.DynamicPropertiesManager;

/**
 * Created by gongy on 2017/1/22.
 */
public class Customer extends DynamicEntityImpl {
    public static DynamicPropertiesManager DPM = new
            DynamicPropertiesManager();

    @Override
    public DynamicPropertiesManager fetchPropertiesManager() {
        return DPM;
    }

    public String id() {
        return get("id");
    }

    public String getCusField1() {
        return get("cusField1");
    }

    public String getFirstName() {
        return get("firstName");
    }

    public String getLastName() {
        return get("lastName");
    }

    public Address getAddress() {
        return get("address");
    }
}
