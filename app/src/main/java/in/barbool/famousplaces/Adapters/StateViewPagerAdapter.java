package in.barbool.famousplaces.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.barbool.famousplaces.Models.StateViewPagerModel;
import in.barbool.famousplaces.R;

public class StateViewPagerAdapter extends PagerAdapter {
    private Context context;
    LayoutInflater layoutInflater;
    private List<StateViewPagerModel> models;

    public StateViewPagerAdapter(Context context, List<StateViewPagerModel> models){
        this.context = context;
        this.models =models;
    }

//    public int[] slide_images = {
//
//            R.drawable.hotel1,
//            R.drawable.diet1,
//            R.drawable.palace1
//
//    };

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.state_viewpager_item,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.stateimageView);

        Picasso.get().load(models.get(position).getImageView()).into(slideImageView);
      // slideImageView.setImageResource(slide_images[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
