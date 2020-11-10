package com.ashishbharam.mvvmpractice.api;

/* 
Created by Ashish Bharam on 08-Nov-20 at 04:25 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import com.ashishbharam.mvvmpractice.pojo.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    @GET("allnews")
    Call<DefaultResponse> getResponse();
}
