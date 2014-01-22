package codeguru.exercise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.util.Log;

public class MachineTabListener implements TabListener {

    private static final String TAG = MachineTabListener.class.getName();

    private final Fragment fragment;

    public MachineTabListener(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Log.d(TAG, "Tab selected: " + tab.getText());
        ft.replace(R.id.fragment_container, fragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }

}
