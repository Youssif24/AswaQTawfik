package com.saad.youssif.aswaqtawfik.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Model.Product;
import com.saad.youssif.aswaqtawfik.R;
import com.saad.youssif.aswaqtawfik.View.CartView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SingleItemActivity extends AppCompatActivity {
    ImageView itemImg;
    TextView itemName,itemPrice,quantityTv;
    public static List<Product>cartProductList=new ArrayList<>();
    ImageButton minsBtn,plusBtn;
    int quantity=1;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);
        itemImg=findViewById(R.id.singleItemImg);
        itemName=findViewById(R.id.singleItemName);
        itemPrice=findViewById(R.id.singleItemPrice);
       // addToCartBtn=findViewById(R.id.addToCart);
        floatingActionButton=findViewById(R.id.btnCart);
       minsBtn=findViewById(R.id.minus_btn);
       plusBtn=findViewById(R.id.plus_btn);
       toolbar=findViewById(R.id.toolbar);
        quantityTv=findViewById(R.id.quantity_tv);
        getIntentData();


       floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product cartProduct=new Product();
                cartProduct.setName(itemName.getText().toString());
                cartProduct.setPrice(itemPrice.getText().toString());
                cartProduct.setQuantity(quantityTv.getText().toString());
                int total= Integer.valueOf(quantityTv.getText().toString())*Integer.valueOf(itemPrice.getText().toString());
                cartProduct.setTotal(total);
                if (cartProductList.contains(cartProduct.getName()))
                {
                    Toast.makeText(SingleItemActivity.this,"هذا العنصر موجود",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cartProductList.add(cartProduct);
                    Toast.makeText(SingleItemActivity.this,"Done",Toast.LENGTH_SHORT).show();

                }
                //cartView.showTotal(cartProductList);
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                quantityTv.setText(String.valueOf(quantity));
            }
        });

        minsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity!=1)
                {
                    quantity--;
                    quantityTv.setText(String.valueOf(quantity));
                }


            }
        });



    }



    public void getIntentData()
    {
        Intent intent=getIntent();
        toolbar.setTitle(intent.getExtras().getString("p_name"));
        itemName.setText(intent.getExtras().getString("p_name"));
        itemPrice.setText(intent.getExtras().getString("p_price"));
        String photo_url=intent.getExtras().getString("p_photo");
        Picasso.with(this).load(photo_url).into(itemImg);
    }
}
