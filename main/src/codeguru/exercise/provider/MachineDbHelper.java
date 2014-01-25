package codeguru.exercise.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MachineProvider extends SQLiteOpenHelper {

    public MachineProvider(Context context) {
        super(context, MachineContract.DB_NAME, null,
                MachineContract.SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing
    }

}
