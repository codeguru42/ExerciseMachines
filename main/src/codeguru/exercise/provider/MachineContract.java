package codeguru.exercise.provider;

import android.content.ContentResolver;
import android.net.Uri;

public class MachineContract {

    public static final String DB_NAME = "machines.db";

    public static final int SCHEMA_VERSION = 1;

    public static final String MACHINES_TABLE = "machines";

    public static final String IMAGES_TABLE = "images";

    public static final String AUTHORITY = "codeguru.exercise.provider";

    public static final Uri MACHINES_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY).path(MACHINES_TABLE).build();

    public static final Uri IMAGES_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY).path(IMAGES_TABLE).build();

    public static final String MACHINE_LIST_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/machine";

    public static final String MACHINE_ITEM_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/machine";

    public static final String IMAGE_LIST_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/image";

    public static final String ID_COL = "_id";

    public static final String MACHINE_NAME = "name";

    public static final String MACHINE_DESC = "description";

    public static final String MACHINE_THUMB = "thumbnail";

    public static final String MACHINE_QR_CODE = "qr_code";

    public static final String MACHINE_ID = "machine_id";

    public static final String IMAGE_URI = "image_uri";

    public static final String[] MACHINE_PROJECTION = new String[] { ID_COL,
            MACHINE_NAME, MACHINE_DESC, MACHINE_THUMB, MACHINE_QR_CODE };

    public static final String[] IMAGES_PROJECTION = new String[] { ID_COL,
            MACHINE_ID, IMAGE_URI };

    public static final String MACHINE_ID_SELECTION = "machine_id = ?";

    private MachineContract() {
    }

}
