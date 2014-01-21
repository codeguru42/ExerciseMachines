package codeguru.exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DescriptionFragment extends Fragment {

    private static final String TAG = DescriptionFragment.class.getName();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        Log.d(TAG, "  parent: " + parent);
        
        return inflater.inflate(R.layout.description, parent, false);
    }

}
