package codeguru.exercise;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private final int[] imageIds = new int[] { R.drawable.ib01,
            R.drawable.ib02, R.drawable.ib03 };
    private Context context;

    public ImagePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        String imageIdKey = context.getString(R.string.image_id);
        Bundle args = new Bundle();
        args.putInt(imageIdKey, imageIds[position]);

        Fragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

}
