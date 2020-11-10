package com.ashishbharam.mvvmpractice.viewmodel;
/* 
Created by Ashish Bharam on 09-Nov-20 at 12:17 AM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ashishbharam.mvvmpractice.api.RetrofitClient;
import com.ashishbharam.mvvmpractice.pojo.DefaultResponse;
import com.ashishbharam.mvvmpractice.roomdatabase.EntityNews;
import com.ashishbharam.mvvmpractice.roomdatabase.NewsRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends AndroidViewModel {
    private static final String TAG = "mytag";
    private NewsRepository repository;
    private LiveData<List<EntityNews>> getAllNewsList;
    private LiveData<List<EntityNews>> getList;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        getAllNewsList = repository.getGetAllNewsList();
    }

    // public LiveData<List<EntityNews>> getGetAllNewsList(){ return getAllNewsList; }

    public LiveData<List<EntityNews>> fetchFromServer(){
        return repository.getNewsFromServer();
    }
    public LiveData<List<EntityNews>> fetchFromRoom(){
        return repository.getNewsFromRoom();
    }
    public void insert(List<EntityNews> entityNewsList) {
        repository.insert(entityNewsList);
    }
    public void insertOne(EntityNews news) {
        repository.insertOne(news);
    }
}
