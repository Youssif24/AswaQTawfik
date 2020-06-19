package com.saad.youssif.aswaqtawfik.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Model.LoginResult;
import com.saad.youssif.aswaqtawfik.Model.ProductResult;
import com.saad.youssif.aswaqtawfik.Retrofit.ApiClient;
import com.saad.youssif.aswaqtawfik.Retrofit.ApiInterface;
import com.saad.youssif.aswaqtawfik.View.ProductView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter {

    Context mContext;
    ProductView productView;

    public ProductPresenter (Context context,ProductView productView)
    {
        this.mContext=context;
        this.productView=productView;
    }

    public void getFruits()
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductResult> call = apiInterface.getFruits();
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {


                Log.e("ProductResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    //loginView.showList(response.body().getUserResult());
                    productView.showProductList(response.body().getProduct());

                }
                else
                {
                    Toast.makeText(mContext,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {

            }

        });
    }

    public void showVegetables()
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductResult> call = apiInterface.getVegetables();
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {


                Log.e("ProductResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    //loginView.showList(response.body().getUserResult());
                    productView.showProductList(response.body().getProduct());

                }
                else
                {
                    Toast.makeText(mContext,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {

            }

        });
    }

    public void showSpices()
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductResult> call = apiInterface.getSpices();
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {


                Log.e("ProductResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    //loginView.showList(response.body().getUserResult());
                    productView.showProductList(response.body().getProduct());

                }
                else
                {
                    Toast.makeText(mContext,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {

            }

        });
    }
}
