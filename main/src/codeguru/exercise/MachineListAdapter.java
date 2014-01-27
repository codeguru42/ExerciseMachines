package codeguru.exercise;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import codeguru.exercise.provider.MachineContract;
import java.io.IOException;
import java.io.InputStream;

public class MachineListAdapter extends SimpleCursorAdapter {

    private static final String TAG = MachineListAdapter.class.getName();

    private final Context context;

    public MachineListAdapter(Context context, int layout, Cursor c,
            String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);

        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView()");

        View v = super.getView(position, convertView, parent);
        Cursor c = getCursor();
        String thumbPath = c.getString(c
                .getColumnIndex(MachineContract.MACHINE_THUMB));

        Log.d(TAG, "  thumbPath=" + thumbPath);

        InputStream in = null;

        try {
            in = context.getAssets().open(thumbPath);

            Drawable thumbnail = new BitmapDrawable(context.getResources(), in);

            Log.d(TAG, "  thumbnail=" + thumbnail);

            TextView machineText = (TextView) v
                    .findViewById(android.R.id.text1);
            machineText.setCompoundDrawablesWithIntrinsicBounds(thumbnail,
                    null, null, null);

        } catch (IOException e) {
            Log.e(TAG, "Unable to load image: " + thumbPath, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing file: " + thumbPath, e);
                }
            }
        }

        return v;
    }
}
