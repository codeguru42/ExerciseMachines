package codeguru.machineadmin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    public static final int SELECT_PICTURE = 1;

    private SmartImageView mThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mThumbnail = (SmartImageView) findViewById(R.id.thumbnail);
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
                Uri imageUri = data.getData();
                getLoaderManager().initLoader(ImageLoader.IMAGE_URL_LOADER,
                        null, new ImageLoader(this, mThumbnail, imageUri));
            }
        }
    }

}
