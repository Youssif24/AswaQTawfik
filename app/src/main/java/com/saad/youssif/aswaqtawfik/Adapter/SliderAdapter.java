package com.saad.youssif.aswaqtawfik.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saad.youssif.aswaqtawfik.R;

import java.util.zip.Inflater;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context)
    {
        this.context=context;
    }

    public String[] sliderHeadings=
    {
            "Fruits","Vegetables","Spices"
    };

    public int[] slideImages=
    {
            R.drawable.fruits1,R.drawable.vegetables,R.drawable.spices_slider
    };

    public String[] sliderDescription=
    {
            "This is fruits details",
            "this is vegetables details",
            "this is spices details"
    };

    @Override
    public int getCount() {
        return slideImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view== (ConstraintLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView sliderImg=view.findViewById(R.id.imgSlider);
        TextView headingTv=view.findViewById(R.id.headingTv);
        TextView descTv=view.findViewById(R.id.detailsTv);

        sliderImg.setImageResource(slideImages[position]);
        headingTv.setText(sliderHeadings[position]);
        descTv.setText(sliderDescription[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
