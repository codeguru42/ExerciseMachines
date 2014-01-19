package codeguru.exercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getName();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onScanClick(View view) {
        Log.d(TAG, "onScanClick()");
    }
    
    public void onBrowseClick(View view) {
    	Log.d(TAG, "onBrowseClick()");
    	Intent intent = new Intent(this, MachineList.class);
    	startActivity(intent);
    }
    
}
