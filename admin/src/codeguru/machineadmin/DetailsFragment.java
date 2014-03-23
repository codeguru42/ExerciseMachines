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
import android.widget.Spinner;
import android.widget.Toast;
import com.loopj.android.image.SmartImageView;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class DetailsFragment extends Fragment {

    private static final String TAG = DetailsFragment.class.getName();

    private EditText mNameText;

    private Spinner mCategorySpinner;

    private EditText mTipsText;

    private SmartImageView mThumbnail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, null);

        mNameText = (EditText) view.findViewById(R.id.name);
        mCategorySpinner = (Spinner) view.findViewById(R.id.category);
        mTipsText = (EditText) view.findViewById(R.id.tips);
        mThumbnail = (SmartImageView) view.findViewById(R.id.thumbnail);

        Parse.initialize(getActivity(),
                "6eINSAhUl3T55fMTGaOG7XdIj0KvORFN4b3PPADw",
                "WQuU2hGwbuAapiJJuyEIpX2bg086liWCzpvFREZS");

        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(
                getActivity(), "Category");
        adapter.setTextKey("name");
        mCategorySpinner.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.machine, menu);
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

    public SmartImageView getThumbnailView() {
        return mThumbnail;
    }

    private void onSave() {
        boolean good = true;
        String tips = mTipsText.getText().toString();
        if (TextUtils.isEmpty(tips)) {
            mTipsText.setError(getString(R.string.muscles_error));
            mTipsText.requestFocus();
            good = false;
        }

        String name = mNameText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            mNameText.setError(getString(R.string.name_error));
            mNameText.requestFocus();
            good = false;
        }

        ParseObject category = (ParseObject) mCategorySpinner.getSelectedItem();

        Log.d(TAG, "good=" + good);
        if (good) {
            ParseObject machine = new ParseObject("Machine");
            machine.put("name", name);
            machine.put("tips", tips);
            machine.put("category", category);
            machine.saveInBackground();

            mNameText.setText("");
            mTipsText.setText("");
            mNameText.requestFocus();
            Toast.makeText(getActivity(), getString(R.string.category_saved),
                    Toast.LENGTH_LONG).show();
        }
    }
}
