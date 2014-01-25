package codeguru.exercise.provider;

import android.content.ContentResolver;
import android.net.Uri;

public class MachineContract {

    public static final String DB_NAME = "machines.db";

    public static final int SCHEMA_VERSION = 1;

    public static final String MACHINES_TABLE = "machines";

    public static final String AUTHORITY = "codeguru.exercise.provider";

    public static final Uri MACHINES_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY).path(MACHINES_TABLE).build();

    public static final String MACHINE_LIST_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/machine";

    public static final String MACHINE_ITEM_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/machine";

    public static final String ID_COL = "_id";

    private MachineContract() {
    }

}
