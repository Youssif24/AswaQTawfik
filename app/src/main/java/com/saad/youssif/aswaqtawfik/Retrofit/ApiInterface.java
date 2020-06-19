package com.saad.youssif.aswaqtawfik.Retrofit;

import com.saad.youssif.aswaqtawfik.Model.LoginResult;
import com.saad.youssif.aswaqtawfik.Model.ProductResult;
import com.saad.youssif.aswaqtawfik.Model.ProfileResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("user_login.php")
    Call<LoginResult> getUser(@QueryMap Map<String , String > querymap);
    @POST("user_register.php")
    @FormUrlEncoded
    Call<String> insertUser(@Field("name") String name,
                            @Field("password") String password,
                            @Field("phone") String phone,
                            @Field("address") String address,
                            @Field("age") String age,
                            @Field("gender") String gender,
                            @Field("userImageProfileName") String userImageProfileName,
                            @Field("userImageProfilePath") String userImageProfilePath
                            );
    @GET("get_fruits.php")
    Call<ProductResult> getFruits();
    @GET("get_vegetables.php")
    Call<ProductResult> getVegetables();
    @GET("get_spices.php")
    Call<ProductResult> getSpices();
    @GET("get_profile.php")
    Call<ProfileResult> getProfile(@QueryMap Map<String , Integer > querymap);
    @POST("update_profile.php")
    @FormUrlEncoded
    Call<String> updateUser(@Field("user_id") int user_id,
                            @Field("name") String name,
                            @Field("phone") String phone,
                            @Field("address") String address,
                            @Field("password") String password
                            );


   /* @GET("GetcategoryItems")
    Call<CategoryResponse> getItems(@QueryMap Map<String , String > querymap);
    //@POST("Login")
    //Call<LoginResponse> login(@QueryMap Map<String , String > querymap);*/
   @POST("send_private_request.php")
   @FormUrlEncoded
   Call<String> uploadRequest(@Field("user_id") int user_id,
                           @Field("req_details") String req_details,
                           @Field("req_time") String req_time
   );


}
