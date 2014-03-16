package codeguru.machineadmin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.loopj.android.image.SmartImageView;

public class CategoryActivity extends ActionBarActivity {

    private SmartImageView mThumbnail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        mThumbnail = (SmartImageView) findViewById(R.id.thumbnail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onSave(MenuItem menuItem) {
        Toast.makeText(this, "Saving...(not really)", Toast.LENGTH_LONG).show();
    }

}
