package com.saad.youssif.aswaqtawfik.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.saad.youssif.aswaqtawfik.Adapter.CartAdapter;
import com.saad.youssif.aswaqtawfik.Model.Product;
import com.saad.youssif.aswaqtawfik.R;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.Slidetop;

public class CartActivity extends AppCompatActivity {

    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyclerLayoutManager;
    TextView total_all;
    Button send_request_btn;
    RelativeLayout relativeLayout;
    ImageView noDataImg;
    LinearLayout linearRecyclerLayout;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        relativeLayout=findViewById(R.id.cartAllLayout);
        recyclerView=findViewById(R.id.cartItemsRecycler);
        total_all=findViewById(R.id.total_all_tv);
        noDataImg=findViewById(R.id.noDataImg);
        send_request_btn=findViewById(R.id.send_request_btn);
        linearRecyclerLayout=findViewById(R.id.recyclerLayout);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        cartAdapter=new CartAdapter(SingleItemActivity.cartProductList,this);
        checkRequestBtn();
        recyclerView.setAdapter(cartAdapter);
        total_all.setText("Total : "+String.valueOf(getTotal()));
        swipeToDelete();




    }

    public int getTotal()
    {
        int total=0;
        for(int i=0;i<SingleItemActivity.cartProductList.size();i++)
        {
            total=total+SingleItemActivity.cartProductList.get(i).getTotal();
        }
        return total;
    }

    public void checkRequestBtn()
    {
        if(SingleItemActivity.cartProductList.size()==0)
        {
            //send_request_btn.setVisibility(View.GONE);
            //relativeLayout.setVisibility(View.GONE);
            linearRecyclerLayout.setVisibility(View.GONE);
            noDataImg.setVisibility(View.VISIBLE);
        }
    }


    public void swipeToDelete()
    {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                if (direction == ItemTouchHelper.LEFT) {//if swipe left

                    final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(CartActivity.this);

                    dialogBuilder
                            .withTitle("Deleting Item")
                            .withTitleColor("#FFFFFF")
                            .withDividerColor("#11000000")
                            .withMessage("Are you sure to delete ? ")
                            .withMessageColor("#FFFFFFFF")
                            .withDialogColor("#3D5069")
                            .withIcon(R.drawable.logo1)
                            .withDuration(700)
                            .withEffect(Slidetop)
                            .withButton1Text("Cancel")
                            .withButton2Text("Remove")
                            .isCancelableOnTouchOutside(false)

                            //.setCustomView(R.layout.alert_dialog_custom_view,v.getContext())         //.setCustomView(View or ResId,context)
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(v.getContext(),"i'm btn2",Toast.LENGTH_SHORT).show();
                                    cartAdapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                                    cartAdapter.notifyItemRangeChanged(position, cartAdapter.getItemCount());//notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                                    dialogBuilder.dismiss();
                                    return;
                                }
                            })
                            .setButton2Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    cartAdapter.notifyItemRemoved(position);    //item removed from recylcerview
                                    //sqldatabase.execSQL("delete from " + TABLE_NAME + " where _id='" + (position + 1) + "'"); //query for delete
                                    int item_price=SingleItemActivity.cartProductList.get(position).getTotal();
                                    SingleItemActivity.cartProductList.remove(position);//then remove item
                                    String split_total_string=total_all.getText().toString().replace("Total : ","");
                                    int new_total=Integer.valueOf(split_total_string)-item_price;
                                    total_all.setText("Total : "+String.valueOf(new_total));
                                    checkRequestBtn();
                                    dialogBuilder.dismiss();
                                    return;
                                }
                            })
                            .show();


                   /* AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this); //alert for confirm to delete
                    builder.setMessage("Are you sure to delete?");    //set message

                    builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cartAdapter.notifyItemRemoved(position);    //item removed from recylcerview
                            //sqldatabase.execSQL("delete from " + TABLE_NAME + " where _id='" + (position + 1) + "'"); //query for delete
                            int item_price=SingleItemActivity.cartProductList.get(position).getTotal();
                            SingleItemActivity.cartProductList.remove(position);//then remove item
                            String split_total_string=total_all.getText().toString().replace("Total : ","");
                            int new_total=Integer.valueOf(split_total_string)-item_price;
                            total_all.setText("Total : "+String.valueOf(new_total));

                            return;
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cartAdapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                            cartAdapter.notifyItemRangeChanged(position, cartAdapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                            return;
                        }
                    }).show();  //show alert dialog*/
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
