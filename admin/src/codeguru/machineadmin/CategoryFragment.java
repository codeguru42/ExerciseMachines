package codeguru.machineadmin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.parse.Parse;
import com.squareup.picasso.Target;

public class CategoryFragment extends Fragment {

    private static final String TAG = CategoryFragment.class.getName();

    private ImageView mThumbnail;

    private EditText mMusclesText;

    private EditText mNameText;

    private Target mThumbnailTarget;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Parse.initialize(getActivity(), MachineAdminApp.PARSE_APPLICATION_ID,
                MachineAdminApp.PARSE_CLIENT_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category, null);

        mNameText = (EditText) view.findViewById(R.id.name);
        mMusclesText = (EditText) view.findViewById(R.id.muscles);
        mThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        mThumbnailTarget = new ImageLoadTarget(mThumbnail);
        mThumbnail.setOnClickListener(new ImageClickListener(
                (MainActivity) getActivity(), mThumbnailTarget));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                onSave();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSave() {
        boolean good = true;
        String muscles = mMusclesText.getText().toString();
        if (TextUtils.isEmpty(muscles)) {
            mMusclesText.setError(getString(R.string.muscles_error));
            mMusclesText.requestFocus();
            good = false;
        }

        String name = mNameText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            mNameText.setError(getString(R.string.name_error));
            mNameText.requestFocus();
            good = false;
        }

        Log.d(TAG, "good=" + good);
        if (good) {
            Category category = new Category();
            category.setName(name);
            category.setMuscles(muscles);
            category.saveInBackground();

            mNameText.setText("");
            mMusclesText.setText("");
            mNameText.requestFocus();
            Toast.makeText(getActivity(), getString(R.string.category_saved),
                    Toast.LENGTH_LONG).show();
        }
    }

}
