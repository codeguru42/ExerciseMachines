package codeguru.machineadmin;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class MachineAdminApp extends Application {

    public static final String PARSE_APPLICATION_ID = "6eINSAhUl3T55fMTGaOG7XdIj0KvORFN4b3PPADw";

    public static final String PARSE_CLIENT_KEY = "WQuU2hGwbuAapiJJuyEIpX2bg086liWCzpvFREZS";

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Category.class);
        Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
    }

}
