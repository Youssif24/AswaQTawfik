package com.saad.youssif.aswaqtawfik.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.saad.youssif.aswaqtawfik.Model.Product;
import com.saad.youssif.aswaqtawfik.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    Context mContext;
    private List<Product> productList = new ArrayList<>();
    LayoutInflater mInflater;

    public ProductAdapter(Context mContext, List<Product> imagesList) {
        this.mContext = mContext;
        this.productList = imagesList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view=mInflater.inflate(R.layout.product_items_layout,null);

        TextView name=(TextView)view.findViewById(R.id.itemNameTextView);
        TextView price=(TextView)view.findViewById(R.id.itemPriceTextView);
        ImageView img=(ImageView)view.findViewById(R.id.itemImageView);

        name.setText(productList.get(position).getName());
        price.setText(String.valueOf(productList.get(position).getPrice())+" $ ");
        Picasso.with(mContext).load(productList.get(position).getPhoto()).into(img);
        //img.setImageURI(productList.get(position).getImg());
        return view;

    }

}
