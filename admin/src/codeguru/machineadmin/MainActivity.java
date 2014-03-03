package codeguru.machineadmin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onSave(MenuItem item) {
        Toast.makeText(this, "Saving...not really", Toast.LENGTH_LONG).show();
    }

    public void onGetImage(View view) {
        Toast.makeText(this, "Image clicked", Toast.LENGTH_LONG).show();
    }

}
