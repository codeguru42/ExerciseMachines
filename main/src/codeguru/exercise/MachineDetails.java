package codeguru.exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class MachineDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        String[] tabTitles = getResources().getStringArray(R.array.tab_titles);

        Fragment[] fragments = new Fragment[tabTitles.length];
        fragments[0] = new DescriptionFragment();

        for (int i = 0; i < tabTitles.length; ++i) {
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(tabTitles[i]);
            tab.setTabListener(new MachineTabListener(fragments[i]));
            actionBar.addTab(tab);
        }
    }

}
