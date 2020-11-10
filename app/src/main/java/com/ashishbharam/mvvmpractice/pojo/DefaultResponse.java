package com.ashishbharam.mvvmpractice.pojo;
/* 
Created by Ashish Bharam on 08-Nov-20 at 04:27 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ashishbharam.mvvmpractice.roomdatabase.EntityNews;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DefaultResponse {
    String error;
    @SerializedName("news")
    @Expose
    List<EntityNews> response;

    public String getError() {
        return error;
    }

    public List<EntityNews> getResponse() {
        return response;
    }
}
