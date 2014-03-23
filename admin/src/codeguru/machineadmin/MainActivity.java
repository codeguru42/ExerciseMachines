package codeguru.machineadmin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import codeguru.machinelib.FragmentTabListener;
import com.loopj.android.image.SmartImageView;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getName();

    public static final int SELECT_PICTURE = 1;

    private DetailsFragment mDetailsFragment;

    private CategoryFragment mCategoryFragment;

    private SmartImageView mThumbnail;

    private int mLoaderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mDetailsFragment = new DetailsFragment();
        Tab detailsTab = actionBar.newTab();
        detailsTab.setText(R.string.machine_title);
        detailsTab.setTabListener(new FragmentTabListener(mDetailsFragment,
                R.id.frame));
        actionBar.addTab(detailsTab);

        mCategoryFragment = new CategoryFragment();
        Tab categoryTab = actionBar.newTab();
        categoryTab.setText(R.string.category_title);
        categoryTab.setTabListener(new FragmentTabListener(mCategoryFragment,
                R.id.frame));
        actionBar.addTab(categoryTab);

        mLoaderId = 0;
    }

    public void onGetImage(View view) {
        mThumbnail = (SmartImageView) view;
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
                getLoaderManager().initLoader(mLoaderId++, null,
                        new ImageLoader(this, mThumbnail, imageUri));
            }
        }
    }

}
