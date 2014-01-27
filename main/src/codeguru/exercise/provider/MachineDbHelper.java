package codeguru.exercise.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MachineDbHelper extends SQLiteOpenHelper {

    private static final String TAG = MachineDbHelper.class.getName();

    private static final String SQL_CREATE_FILE = "create.sql";

    private final Context context;

    public MachineDbHelper(Context context) {
        super(context, MachineContract.DB_NAME, null,
                MachineContract.SCHEMA_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate()");

        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();

        try {
            in = new BufferedReader(new InputStreamReader(context.getAssets()
                    .open(SQL_CREATE_FILE)));

            while (in.ready()) {
                sb.append(in.readLine());
            }
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage(), ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Log.e(TAG, ex.getMessage(), ex);
                }
            }
        }

        String[] sqls = sb.toString().split(";");

        for (String sql : sqls) {
            Log.d(TAG, sql);
            if (!TextUtils.isEmpty(sql.trim())) {
                db.execSQL(sql);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing
    }

}
