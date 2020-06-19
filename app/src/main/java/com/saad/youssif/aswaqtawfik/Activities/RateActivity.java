package com.saad.youssif.aswaqtawfik.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.saad.youssif.aswaqtawfik.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RateActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    ImageButton clostBtn;
    Button submitBtn;
    LinearLayout rate_layout,done_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        this.setFinishOnTouchOutside(false);
        clostBtn=findViewById(R.id.close_rate_btn);
        submitBtn=findViewById(R.id.rate_submit_btn);
        rate_layout=findViewById(R.id.rate_layout);
        done_layout=findViewById(R.id.rate_done_layout);


        clostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RateActivity.this.finish();
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate_layout.setVisibility(View.GONE);
                done_layout.setVisibility(View.VISIBLE);
            }
        });
    }
}
