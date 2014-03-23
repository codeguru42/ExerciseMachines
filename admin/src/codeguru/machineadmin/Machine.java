package codeguru.machineadmin;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Machine")
public class Machine extends ParseObject {

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getTips() {
        return getString("tips");
    }

    public void setTips(String tips) {
        put("tips", tips);
    }

    public Category getCategory() {
        return (Category) getParseObject("category");
    }

    public void setCategory(Category category) {
        put("category", category);
    }

}
