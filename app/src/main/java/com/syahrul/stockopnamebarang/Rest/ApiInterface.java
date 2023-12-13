package com.syahrul.stockopnamebarang.Rest;

import com.syahrul.stockopnamebarang.Model.ServerResponse;
import com.syahrul.stockopnamebarang.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("auth/login")
    Call<ServerResponse> login(@Body User user);

    @GET("auth/getuser")
    Call<ServerResponse> getUser(@Header("Authorization") String authorization);

    @POST("auth/logout")
    Call<ServerResponse> logout(@Header("Authorization") String accessToken);

    @GET("requester/category")
    Call<ServerResponse> getKategori(@Header("Authorization") String accessToken);

    @GET("admin/itemall")
    Call<ServerResponse> getItem(@Header("Authorization") String accessToken);
}
