package codeguru.machineadmin;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

public class ImageLoadTarget implements Target {

    private final ImageView mImage;

    public ImageLoadTarget(ImageView image) {
        mImage = image;
    }

    @Override
    public void onBitmapFailed(Drawable drawable) {
        mImage.setImageDrawable(drawable);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
        mImage.setImageBitmap(bitmap);
    }

    @Override
    public void onPrepareLoad(Drawable drawable) {
        mImage.setImageDrawable(drawable);
    }

}
