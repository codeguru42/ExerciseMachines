package codeguru.exercise;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import codeguru.exercise.provider.MachineContract;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MachineList extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static String TAG = MachineList.class.getName();

    private static int LIST_LOADER = 0;

    private CursorAdapter adapter;

    private final OnItemClickListener onMachineClick = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            Intent intent = new Intent(getActivity(), MachineDetails.class);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.machine_list, container, false);

        ProgressBar progressBar = (ProgressBar) view
                .findViewById(android.R.id.empty);
        AbsListView listView = (AbsListView) view
                .findViewById(android.R.id.list);
        listView.setEmptyView(progressBar);

        String[] fromColumns = { MachineContract.MACHINE_NAME };
        int[] toViews = { R.id.machine_text };

        adapter = new MachineListAdapter(getActivity(), R.layout.row, null,
                fromColumns, toViews, 0);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onMachineClick);

        getActivity().getSupportLoaderManager().initLoader(LIST_LOADER, null,
                this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
            Intent resultIntent) {
        if (resultCode == Activity.RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(
                    requestCode, resultCode, resultIntent);
            if (scanResult != null) {
                Log.d(TAG, "Barcode scanned");
                Log.d(TAG, scanResult.toString());
                Intent intent = new Intent(getActivity(), MachineDetails.class);
                // Put extras to indicate which machine to load
                startActivity(intent);
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader()");
        return new CursorLoader(getActivity(), MachineContract.MACHINES_URI,
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
