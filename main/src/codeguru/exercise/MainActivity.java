package codeguru.exercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onScanClick(View view) {
        Log.d(TAG, "onScanClick()");
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onBrowseClick(View view) {
        Log.d(TAG, "onBrowseClick()");
        Intent intent = new Intent(this, MachineList.class);
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
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
