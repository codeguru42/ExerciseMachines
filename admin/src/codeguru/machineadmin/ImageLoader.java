package codeguru.machineadmin;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.File;

public class ImageLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ImageLoader.class.getName();

    public static final int IMAGE_URL_LOADER = 0;

    private final Context mContext;

    private final Target mTarget;

    private final Uri mImageUri;

    public ImageLoader(Context context, Uri imageUri, Target target) {
        mContext = context;
        mTarget = target;
        mImageUri = imageUri;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader()");

        String[] projection = { MediaStore.Images.Media.DATA };
        return new CursorLoader(this.mContext, mImageUri, projection, null,
                null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished()");

        String imagePath = mImageUri.getPath();

        if (data != null) {
            int columnIndex = data
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            data.moveToFirst();
            imagePath = data.getString(columnIndex);
        }

        Log.d(TAG, imagePath);

        Picasso.with(mContext).load(new File(imagePath)).into(mTarget);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Do nothing
        Log.d(TAG, "onLoaderReset()");
    }

}
