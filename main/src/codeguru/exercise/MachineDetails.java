package codeguru.exercise;

import android.os.Bundle;
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
        for (String tabTitle : tabTitles) {
            ActionBar.Tab tab = actionBar.newTab();

            tab.setText(tabTitle);
            tab.setTabListener(new MachineTabListener());
            actionBar.addTab(tab);
        }
    }

}
