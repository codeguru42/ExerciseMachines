package codeguru.exercise;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import codeguru.exercise.provider.MachineContract;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MachineList extends ActionBarActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static String TAG = MachineList.class.getName();

    private static final String[] PROJECTION = new String[] {
            MachineContract.ID_COL, MachineContract.MACHINE_NAME };

    private CursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ProgressBar progressBar = (ProgressBar) findViewById(android.R.id.empty);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setEmptyView(progressBar);

        String[] fromColumns = { MachineContract.MACHINE_NAME };
        int[] toViews = { android.R.id.text1 };

        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, null, fromColumns,
                toViews, 0);
        listView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onScanClick(MenuItem menuItem) {
        Log.d(TAG, "onScanClick()");
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
            Intent resultIntent) {
        if (resultCode == RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(
                    requestCode, resultCode, resultIntent);
            if (scanResult != null) {
                Log.d(TAG, "Barcode scanned");
                Log.d(TAG, scanResult.toString());
                Intent intent = new Intent(this, MachineDetails.class);
                // Put extras to indicate which machine to load
                startActivity(intent);
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader()");
        return new CursorLoader(this, MachineContract.MACHINES_URI, PROJECTION,
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "onLoadFinished()");
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset()");
        adapter.swapCursor(null);
    }

}
