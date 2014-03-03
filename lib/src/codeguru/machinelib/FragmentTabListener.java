package codeguru.machinelib;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.util.Log;

public class FragmentTabListener implements TabListener {

    private static final String TAG = FragmentTabListener.class.getName();

    private final Fragment mFragment;

    private final int mFragmentContainer;

    public FragmentTabListener(Fragment fragment, int fragmentContainer) {
        mFragment = fragment;
        mFragmentContainer = fragmentContainer;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Log.d(TAG, "Tab selected: " + tab.getText());
        ft.replace(mFragmentContainer, mFragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(mFragment);
    }

}
