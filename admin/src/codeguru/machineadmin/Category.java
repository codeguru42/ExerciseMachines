package codeguru.machineadmin;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Category")
public class Category extends ParseObject {

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getMuscles() {
        return getString("muscles");
    }

    public void setMuscles(String muscles) {
        put("muscles", muscles);
    }

}
