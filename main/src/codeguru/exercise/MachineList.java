package codeguru.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MachineList extends ActionBarActivity {

    private static String TAG = MachineList.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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

}
