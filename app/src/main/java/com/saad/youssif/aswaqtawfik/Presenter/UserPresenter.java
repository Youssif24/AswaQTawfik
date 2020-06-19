package com.saad.youssif.aswaqtawfik.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.aswaqtawfik.Model.LoginResult;
import com.saad.youssif.aswaqtawfik.Model.ProfileResult;
import com.saad.youssif.aswaqtawfik.Retrofit.ApiClient;
import com.saad.youssif.aswaqtawfik.Retrofit.ApiInterface;
import com.saad.youssif.aswaqtawfik.View.LoginView;
import com.saad.youssif.aswaqtawfik.View.ProfileView;
import com.saad.youssif.aswaqtawfik.View.RegisterView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    Context context;
    LoginView loginView;
    RegisterView registerView;
    ProfileView profileView;
    String status="False";


    public UserPresenter(Context context, LoginView loginView)
    {
        this.context=context;
        this.loginView=loginView;

    }
    public UserPresenter(Context context,RegisterView registerView)
    {
        this.context=context;
        this.registerView=registerView;

    }
    public UserPresenter(Context context,ProfileView profileView)
    {
        this.context=context;
        this.profileView=profileView;

    }

    public void checkUser(String name, final String password)
    {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("login_username",name.trim());
        queryMap.put("login_password",password.trim());


        /* ApiClient.getClient() ===== returns retrofit object
         * which generates an implementation of the ApiInterface interface.
         * */
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResult> call = apiInterface.getUser(queryMap);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {


                Log.e("LoginResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    loginView.showList(response.body().getUserResult());

                }
                else
                    {
                        Toast.makeText(context,"error",Toast.LENGTH_LONG);

                    }

                }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }

        });
    }

    public void registerNewUser(String name,String phone,String address,String password
            ,String age,String gender,String imgProfileName,String imgProfilePath)
    {
        /*Map<String, String> queryMap = new HashMap<>();
        queryMap.put("name",name);
        queryMap.put("password",password);
        queryMap.put("phone",phone);
        queryMap.put("address",address);
        queryMap.put("age",age);
        queryMap.put("gender",gender);
        queryMap.put("userImageProfileName",imgProfileName);
        queryMap.put("userImageProfilePath",imgProfilePath);*/

        // Toast.makeText(context,"Hello",Toast.LENGTH_LONG);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.insertUser(name,password,phone,address,age,gender,imgProfileName,imgProfilePath);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.d("RegisterResponse", response.body().toString());
                    registerView.getResponseString(response.body());
                    /*if(response.body().getRegisterResult().get(0).getStatus().toString().equals("success"))
                    {
                        registerView.getResponseString("Success");
                        Toast.makeText(context,"Register Success",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(context,response.body().toString(),Toast.LENGTH_LONG).show();

                    }*/

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_LONG);


            }

        });




    }


    public void getProfile(int id)
    {
        Map<String, Integer> queryMap = new HashMap<>();
        queryMap.put("user_id",id);
        //queryMap.put("login_password",password.trim());


        /* ApiClient.getClient() ===== returns retrofit object
         * which generates an implementation of the ApiInterface interface.
         * */
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileResult> call = apiInterface.getProfile(queryMap);
        call.enqueue(new Callback<ProfileResult>() {
            @Override
            public void onResponse(Call<ProfileResult> call, Response<ProfileResult> response) {


                Log.e("ProfileResponse", response.raw().request().toString());
                Toast.makeText(context,response.body().toString(),Toast.LENGTH_LONG);
                if (response.isSuccessful()) {
                    profileView.showList(response.body().getUserResult());

                }
                else
                {
                    Toast.makeText(context,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<ProfileResult> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_LONG);

            }

        });
    }


    public void updateUser(int id,String name,String phone,String address,String password)
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.updateUser(id,name,phone,address,password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.d("UpdateProfileResponse", response.body().toString());
                registerView.getResponseString(response.body());
                    /*if(response.body().getRegisterResult().get(0).getStatus().toString().equals("success"))
                    {
                        registerView.getResponseString("Success");
                        Toast.makeText(context,"Register Success",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(context,response.body().toString(),Toast.LENGTH_LONG).show();

                    }*/

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_LONG);


            }

        });


    }



}
