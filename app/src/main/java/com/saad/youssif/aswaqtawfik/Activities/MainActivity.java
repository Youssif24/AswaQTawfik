package com.saad.youssif.aswaqtawfik.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Adapter.ProductAdapter;
import com.saad.youssif.aswaqtawfik.Model.Product;
import com.saad.youssif.aswaqtawfik.Presenter.ProductPresenter;
import com.saad.youssif.aswaqtawfik.R;
import com.saad.youssif.aswaqtawfik.View.CartView;
import com.saad.youssif.aswaqtawfik.View.ProductView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements ProductView,CartView {

    private GridView gridView;
    private List<Product> productList =new ArrayList<Product>();
    List<Product>cartList=new ArrayList<>();

    ProductPresenter productPresenter;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.fruits_tab:
                    productPresenter.getFruits();
                    ////mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.veg_tab:
                    productPresenter.showVegetables();
                    //Toast.makeText(MainActivity.this,"خضروات",Toast.LENGTH_LONG).show();
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.spices_tab:
                    productPresenter.showSpices();
                    //Toast.makeText(MainActivity.this,"توابل",Toast.LENGTH_LONG).show();
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        gridView=(GridView)findViewById(R.id.gridView);
        productPresenter=new ProductPresenter(this,this);
        productPresenter.getFruits();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent showItemIntent=new Intent(MainActivity.this,SingleItemActivity.class);
                showItemIntent.putExtra("p_name",productList.get(i).getName());
                showItemIntent.putExtra("p_price",productList.get(i).getPrice());
                showItemIntent.putExtra("p_photo",productList.get(i).getPhoto());
                startActivity(showItemIntent);

            }
        });
    }




    @Override
    public void showProductList(List<Product> productList) {
        this.productList=productList;
        ProductAdapter myAdapter = new ProductAdapter(MainActivity.this, productList);
        gridView.setAdapter(myAdapter);
        gridView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.cart_tab:
                startActivity(new Intent(MainActivity.this,CartActivity.class));
                return true;

            case R.id.setting_tab:
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                return true;

           case R.id.about:
                return true;
            case R.id.rate:
                startActivity(new Intent(MainActivity.this,RateActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showTotal(List<Product> cartProducts) {
        this.cartList=cartProducts;
    }
}
