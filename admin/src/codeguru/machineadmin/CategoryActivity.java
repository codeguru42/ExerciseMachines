package codeguru.machineadmin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.loopj.android.image.SmartImageView;
import com.parse.Parse;
import com.parse.ParseObject;

public class CategoryActivity extends ActionBarActivity {

    private static final String TAG = CategoryActivity.class.getName();

    private static final int SELECT_PICTURE = 1;

    private SmartImageView mThumbnail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mThumbnail = (SmartImageView) findViewById(R.id.thumbnail);

        Parse.initialize(this, "6eINSAhUl3T55fMTGaOG7XdIj0KvORFN4b3PPADw",
                "WQuU2hGwbuAapiJJuyEIpX2bg086liWCzpvFREZS");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onSave(MenuItem menuItem) {
        boolean good = true;
        EditText musclesText = (EditText) findViewById(R.id.muscles);
        String muscles = musclesText.getText().toString();
        if (TextUtils.isEmpty(muscles)) {
            musclesText.setError(getString(R.string.muscles_error));
            musclesText.requestFocus();
            good = false;
        }

        EditText nameText = (EditText) findViewById(R.id.name);
        String name = nameText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            nameText.setError(getString(R.string.name_error));
            nameText.requestFocus();
            good = false;
        }

        Log.d(TAG, "good=" + good);
        if (good) {
            ParseObject category = new ParseObject("Category");
            category.put("name", name);
            category.put("muscles", muscles);
            category.saveInBackground();

            nameText.setText("");
            musclesText.setText("");
            nameText.requestFocus();
            Toast.makeText(this, getString(R.string.category_saved),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onGetImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                MainActivity.SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri imageUri = data.getData();
                getLoaderManager().initLoader(ImageLoader.IMAGE_URL_LOADER,
                        null, new ImageLoader(this, mThumbnail, imageUri));
            }
        }
    }

}
