package codeguru.machineadmin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import codeguru.machinelib.FragmentTabListener;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getName();

    public static final int SELECT_PICTURE = 1;

    private DetailsFragment mDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mDetailsFragment = new DetailsFragment();
        Tab detailsTab = actionBar.newTab();
        detailsTab.setText(R.string.details);
        detailsTab.setTabListener(new FragmentTabListener(mDetailsFragment,
                R.id.frame));
        actionBar.addTab(detailsTab);
    }

    public void onAddCategory(MenuItem item) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
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
                getLoaderManager().initLoader(
                        ImageLoader.IMAGE_URL_LOADER,
                        null,
                        new ImageLoader(this, mDetailsFragment
                                .getThumbnailView(), imageUri));
            }
        }
    }

}
