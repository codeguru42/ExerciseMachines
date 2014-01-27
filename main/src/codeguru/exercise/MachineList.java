package codeguru.exercise;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import codeguru.exercise.provider.MachineContract;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MachineList extends ActionBarActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static String TAG = MachineList.class.getName();

    private static int LIST_LOADER = 0;

    private CursorAdapter adapter;

    private final OnItemClickListener onMachineClick = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            Intent intent = new Intent(MachineList.this, MachineDetails.class);
            String idKey = getString(R.string.machine_id);
            String nameKey = getString(R.string.machine_name);
            String descKey = getString(R.string.machine_desc);

            Cursor c = adapter.getCursor();
            String machineName = c.getString(c
                    .getColumnIndex(MachineContract.MACHINE_NAME));
            String machineDesc = c.getString(c
                    .getColumnIndex(MachineContract.MACHINE_DESC));

            intent.putExtra(idKey, id);
            intent.putExtra(nameKey, machineName);
            intent.putExtra(descKey, machineDesc);
            startActivity(intent);
        }

    };

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

        adapter = new MachineListAdapter(this,
                android.R.layout.simple_list_item_1, null, fromColumns,
                toViews, 0);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onMachineClick);

        getSupportLoaderManager().initLoader(LIST_LOADER, null, this);
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
        return new CursorLoader(this, MachineContract.MACHINES_URI,
                MachineContract.MACHINE_PROJECTION, null, null, null);
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
