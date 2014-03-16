package codeguru.machineadmin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.loopj.android.image.SmartImageView;

public class CategoryActivity extends ActionBarActivity {

    private SmartImageView mThumbnail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        mThumbnail = (SmartImageView) findViewById(R.id.thumbnail);
    }

}
