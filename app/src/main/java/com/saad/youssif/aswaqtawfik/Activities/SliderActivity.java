package com.saad.youssif.aswaqtawfik.Activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saad.youssif.aswaqtawfik.Adapter.SliderAdapter;
import com.saad.youssif.aswaqtawfik.R;

public class SliderActivity extends AppCompatActivity {

    public ViewPager slideViewPager;
    RelativeLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button haveAccountBtn,newAccountBtn;
    int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        /*slideViewPager=findViewById(R.id.sliderPager);
        dotsLayout=findViewById(R.id.mdotsLayout);
        sliderAdapter=new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });*/
        haveAccountBtn=findViewById(R.id.haveAccount);
        newAccountBtn=findViewById(R.id.newAccount);
        haveAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SliderActivity.this,LoginActivity.class));
            }
        });

        newAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SliderActivity.this,RegisterActivity.class));
            }
        });

    }
    ViewPager.OnPageChangeListener viewListener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage=i;
           /* if(i==0)
            {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(false);
                backBtn.setVisibility(View.INVISIBLE);
            }
            else if(i==dots.length-1)
            {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("Finish");

            }
            else
            {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("Next");
                backBtn.setText("Back");
            }*/
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void addDotsIndicator(int position)
    {
        dots=new TextView[3];
        for(int i=0; i<dots.length; i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparentWhite));
            dotsLayout.addView(dots[i]);
        }

        if(dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }

    }


}
