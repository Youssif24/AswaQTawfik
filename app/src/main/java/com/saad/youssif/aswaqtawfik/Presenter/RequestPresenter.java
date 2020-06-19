package com.saad.youssif.aswaqtawfik.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Retrofit.ApiClient;
import com.saad.youssif.aswaqtawfik.Retrofit.ApiInterface;
import com.saad.youssif.aswaqtawfik.View.RequestView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestPresenter {
    Context mContext;
    RequestView requestView;

    public RequestPresenter(Context context,RequestView requestView)
    {
        this.mContext=context;
        this.requestView=requestView;
    }

    public void sendRequest(int id,String details,String req_time)
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.uploadRequest(id,details,req_time);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.d("UpdateProfileResponse", response.body().toString());
                requestView.getRequestResponse(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext,t.toString(),Toast.LENGTH_LONG);


            }

        });


    }
}
