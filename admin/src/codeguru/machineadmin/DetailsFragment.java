package codeguru.machineadmin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.loopj.android.image.SmartImageView;

public class DetailsFragment extends Fragment {

    private static final String TAG = DetailsFragment.class.getName();

    private SmartImageView mThumbnail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, null);
        mThumbnail = (SmartImageView) view.findViewById(R.id.thumbnail);

        return view;
    }

    public SmartImageView getThumbnailView() {
        return mThumbnail;
    }

}
