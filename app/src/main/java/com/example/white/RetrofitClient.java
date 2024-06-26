package com.example.white;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;

public class RetrofitClient {
    private static final String Api_URL="http://192.168.16.216:4400/getActiveUserList";
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofitInstance(){
        if (retrofit==null) {
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit= new Retrofit.Builder()
                    .baseUrl(Api_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
