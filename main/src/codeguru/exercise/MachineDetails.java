package codeguru.exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import codeguru.machinelib.FragmentTabListener;

public class MachineDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Bundle args = getIntent().getExtras();
        String nameKey = getString(R.string.machine_name);
        String machineName = args.getString(nameKey);
        setTitle(machineName);

        String[] tabTitles = getResources().getStringArray(R.array.tab_titles);

        Fragment[] fragments = new Fragment[tabTitles.length];
        fragments[0] = new DescriptionFragment();
        fragments[1] = new ImagesFragment();
        fragments[2] = new MachineList();

        for (int i = 0; i < tabTitles.length; ++i) {
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(tabTitles[i]);
            tab.setTabListener(new FragmentTabListener(fragments[i],
                    R.id.fragment_container));
            actionBar.addTab(tab);

            fragments[i].setArguments(args);
        }
    }

}
