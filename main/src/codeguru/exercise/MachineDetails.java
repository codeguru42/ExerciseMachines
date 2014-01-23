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
        fragments[1] = new ImagesFragment();
        fragments[2] = new ExercisesFragment();

        Bundle descArgs = new Bundle();
        String descKey = getString(R.string.desc_file);
        descArgs.putString(descKey,
                "file:///android_asset/desc/InclinedBench.html");
        fragments[0].setArguments(descArgs);

        for (int i = 0; i < tabTitles.length; ++i) {
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(tabTitles[i]);
            tab.setTabListener(new MachineTabListener(fragments[i]));
            actionBar.addTab(tab);
        }
    }

}
