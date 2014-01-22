package codeguru.exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {

    private int imageId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        imageId = args.getInt(getActivity().getString(R.string.image_id));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.image, parent, false);
        ImageView imageView = (ImageView) fragmentView.findViewById(R.id.image);
        imageView.setImageResource(imageId);

        return fragmentView;
    }

}
