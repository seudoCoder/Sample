package com.example.white;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
public interface InterfaceApi {
    @POST("user.account/checkLogin")
    Call<String> checkLogin(@Header("Authorisation")String authToken);

    String url="http://192.168.16.216:4400/getActiveUserList/";
}
