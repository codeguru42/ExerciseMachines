package codeguru.exercise;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import codeguru.exercise.provider.MachineContract;

public class ImagesFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ImagesFragment.class.getName();

    private static final int IMAGES_LOADER = 1;

    private ViewPager pager;
    private ImagePagerAdapter imageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate()");
        getLoaderManager().initLoader(IMAGES_LOADER, getArguments(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");

        View view = inflater.inflate(R.layout.images, parent, false);
        pager = (ViewPager) view.findViewById(R.id.pager);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader()");

        String idKey = getString(R.string.machine_id);
        long machineId = args.getLong(idKey);
        String[] selArgs = new String[] { Long.toString(machineId) };
        return new CursorLoader(this.getActivity(), MachineContract.IMAGES_URI,
                MachineContract.IMAGES_PROJECTION,
                MachineContract.MACHINE_ID_SELECTION, selArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "onLoadFinished()");

        imageAdapter = new ImagePagerAdapter(getActivity());
        while (cursor.moveToNext()) {
            String imageUri = cursor.getString(cursor
                    .getColumnIndex(MachineContract.IMAGE_URI));
            imageAdapter.addImageUri(imageUri);

            Log.d(TAG, "Added image: " + imageUri);
        }

        pager.setAdapter(imageAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // do nothing
    }

}
