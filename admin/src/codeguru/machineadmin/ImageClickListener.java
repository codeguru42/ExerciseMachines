package codeguru.machineadmin;

import android.view.View;
import com.squareup.picasso.Target;

public class ImageClickListener implements View.OnClickListener {

    private final MainActivity mActivity;

    private final Target mTarget;

    public ImageClickListener(MainActivity activity, Target target) {
        mActivity = activity;
        mTarget = target;
    }

    @Override
    public void onClick(View v) {
        mActivity.onGetImage(mTarget);
    }

}
