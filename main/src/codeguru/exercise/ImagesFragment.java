package codeguru.exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ImagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.images, parent, false);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new ImagePagerAdapter(getFragmentManager(),
                getActivity());
        pager.setAdapter(pagerAdapter);

        return view;
    }

}
