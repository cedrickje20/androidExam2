package com.example.anexam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Login>> getLogin();

    @GET
    Call<List<Questions>> getQuestion();


    @FormUrlEncoded
    @POST("posts")
    Call<LoginResponse> getLoginInfo(@Field("email") String email, @Field("password") String password);

}
