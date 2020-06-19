package com.saad.youssif.aswaqtawfik.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saad.youssif.aswaqtawfik.Model.Product;
import com.saad.youssif.aswaqtawfik.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartViewHolder> {
    private final List<Product> cartList;
    Context ctx;

    public CartAdapter(List<Product> cartList, Context context){
        this.cartList = cartList;
        this.ctx=context;
    }


    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_recycler_layout,viewGroup,false);
        cartViewHolder viewHolder = new cartViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewHolder cartViewHolder, int i) {
        Product cartProduct=cartList.get(i);
        cartViewHolder.cart_item_name.setText(cartProduct.getName());
        cartViewHolder.cart_item_price.setText("Unit Price : "+cartProduct.getPrice());
        cartViewHolder.cart_item_quant.setText( "Quantity : "+cartProduct.getQuantity());
        cartViewHolder.cart_item_quant_price.setText("Qty Price : "+String.valueOf(cartProduct.getTotal()));

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class cartViewHolder extends RecyclerView.ViewHolder {
        TextView cart_item_name,cart_item_price,cart_item_quant,cart_item_quant_price;

        public cartViewHolder(View item) {
            super(item);
           cart_item_name=item.findViewById(R.id.cart_item_name);
           cart_item_price=item.findViewById(R.id.cart_item_price);
           cart_item_quant=item.findViewById(R.id.cart_item_quant);
           cart_item_quant_price=item.findViewById(R.id.cart_item_quant_price);
        }


    }
}
