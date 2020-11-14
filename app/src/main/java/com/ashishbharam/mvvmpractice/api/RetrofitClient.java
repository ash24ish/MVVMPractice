package com.ashishbharam.mvvmpractice.api;
/* 
Created by Ashish Bharam on 08-Nov-20 at 04:18 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL ="http://192.168.1.3/newstest/public/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private OkHttpClient client;

    // Create a new object from HttpLoggingInterceptor

    /* //if your connection is slow then you just need to increase the timeout!
    OkHttpClient client = new OkHttpClient.Builder()
         .readTimeout(60, TimeUnit.SECONDS)
         .connectTimeout(60, TimeUnit.SECONDS)
         .addInterceptor(interceptor).build();
    */

    private RetrofitClient(){
        client =new OkHttpClient().newBuilder().build();
        //client.networkInterceptors();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
    }

public static synchronized RetrofitClient getInstance(){
        if (mInstance == null){
            mInstance =new RetrofitClient();
        }
        return mInstance;
}
public RetrofitApi getRetrofitApi(){
        return retrofit.create(RetrofitApi.class);
}

}
