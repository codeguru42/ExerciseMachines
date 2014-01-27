package codeguru.exercise.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import codeguru.exercise.R;

public class MachineProvider extends ContentProvider {

    private static final String TAG = MachineProvider.class.getName();

    private static final int ALL_MACHINES = 1;
    private static final int MACHINE_ID = 2;
    private static final int ALL_IMAGES = 3;

    private static final UriMatcher uriMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(MachineContract.AUTHORITY,
                MachineContract.MACHINES_TABLE, ALL_MACHINES);
        uriMatcher.addURI(MachineContract.AUTHORITY,
                MachineContract.MACHINES_TABLE + "/#", MACHINE_ID);
        uriMatcher.addURI(MachineContract.AUTHORITY,
                MachineContract.IMAGES_TABLE, ALL_IMAGES);
    }

    private SQLiteOpenHelper dbHelper;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("delete not supported");
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ALL_MACHINES:
                return MachineContract.MACHINE_LIST_MIME_TYPE;

            case MACHINE_ID:
                return MachineContract.MACHINE_ITEM_MIME_TYPE;

            case ALL_IMAGES:
                return MachineContract.IMAGE_LIST_MIME_TYPE;

            default:
                String error = this.getContext().getString(
                        R.string.invalid_uri_error, uri.toString());
                throw new IllegalArgumentException(error);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("insert not supported");
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate()");

        dbHelper = new MachineDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query()");

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)) {
            case ALL_MACHINES:
                return db.query(MachineContract.MACHINES_TABLE, projection,
                        selection, selectionArgs, null, null, sortOrder);

            case MACHINE_ID:
                String where = this.getWhereWithId(selection);
                long id = ContentUris.parseId(uri);
                String[] whereArgs = this.getWhereArgsWithId(selectionArgs, id);
                return db.query(MachineContract.MACHINES_TABLE, projection,
                        where, whereArgs, null, null, sortOrder);

            case ALL_IMAGES:
                return db.query(MachineContract.IMAGES_TABLE, projection,
                        selection, selectionArgs, null, null, sortOrder);

            default:
                String error = this.getContext().getString(
                        R.string.invalid_uri_error, uri.toString());
                throw new IllegalArgumentException(error);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        throw new UnsupportedOperationException("update not supported");
    }

    private String getWhereWithId(String selection) {
        String idSelection = MachineContract.ID_COL + " = ?";
        return TextUtils.isEmpty(selection) ? idSelection : idSelection
                + " AND (" + selection + ")";

    }

    private String[] getWhereArgsWithId(String[] selectionArgs, long id) {
        int argCount = selectionArgs == null ? 1 : selectionArgs.length + 1;
        String[] whereArgs = new String[argCount];
        whereArgs[0] = Long.toString(id);

        if (selectionArgs != null) {
            for (int i = 0; i < selectionArgs.length; ++i) {
                whereArgs[i + 1] = selectionArgs[i];
            }
        }

        return whereArgs;
    }

}
