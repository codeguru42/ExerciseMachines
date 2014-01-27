package codeguru.exercise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private static final String TAG = ImagePagerAdapter.class.getName();

    private final List<String> imageUris = new ArrayList<String>();
    private final Context context;

    public ImagePagerAdapter(Context context) {
        this.context = context;
    }

    public void addImageUri(String uri) {
        imageUris.add(uri);
    }

    @Override
    public int getCount() {
        return imageUris.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        InputStream in = null;
        String imagePath = imageUris.get(position);
        try {
            in = context.getAssets().open(imagePath);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.e(TAG, "Unable to load image: " + imagePath, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing file: " + imagePath, e);
                }
            }
        }
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
