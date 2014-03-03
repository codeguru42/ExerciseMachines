package codeguru.machineadmin;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MainActivity.class.getName();

    private static final int SELECT_PICTURE = 1;

    private static final int IMAGE_URL_LOADER = 0;

    private Uri mImageUri;

    private SmartImageView thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbnail = (SmartImageView) findViewById(R.id.thumbnail);
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
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                mImageUri = data.getData();

                Log.d(TAG, mImageUri.toString());

                getLoaderManager().initLoader(IMAGE_URL_LOADER, null, this);
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case IMAGE_URL_LOADER:
                String[] projection = { MediaStore.Images.Media.DATA };
                return new CursorLoader(this, mImageUri, projection, null,
                        null, null);

            default:
                Log.e(TAG, "Invalid Loader ID: " + id);
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        String imagePath = mImageUri.getPath();

        if (data != null) {
            int columnIndex = data
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            data.moveToFirst();
            imagePath = data.getString(columnIndex);
        }

        Log.d(TAG, imagePath);

        thumbnail.setImageUrl("file:///" + imagePath);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Do nothing
    }
}
