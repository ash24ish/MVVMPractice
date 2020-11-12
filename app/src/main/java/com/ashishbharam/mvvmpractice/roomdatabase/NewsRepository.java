package com.ashishbharam.mvvmpractice.roomdatabase;
/* 
Created by Ashish Bharam on 08-Nov-20 at 04:57 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ashishbharam.mvvmpractice.MainActivity;
import com.ashishbharam.mvvmpractice.api.RetrofitClient;
import com.ashishbharam.mvvmpractice.pojo.DefaultResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static final String TAG = "mylog NewsRepository";
    private NewsRoomDatabase database;
    private LiveData<List<EntityNews>> getAllNewsList;
    private List<EntityNews> getNewsListServer;
    private RetrofitClient retrofitClient;

    public NewsRepository(Application application) {
        database = NewsRoomDatabase.getInstance(application);
        //getAllNewsList = database.newsDao().getAllNewsFromRoom();
        retrofitClient = RetrofitClient.getInstance();
    }

    public LiveData<List<EntityNews>> getNewsFromServer(){

        MutableLiveData<List<EntityNews>> data = new MutableLiveData<>();
        Call<DefaultResponse> call = retrofitClient.getRetrofitApi().getResponse();

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                assert response.body() != null;

                data.setValue(response.body().getResponse());

                getNewsListServer = response.body().getResponse();

                Log.d(TAG, "onResponse: " + getNewsListServer.get(3).getNews_title());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.setValue(null);
            }
        });
    return data;
    }

    public LiveData<List<EntityNews>> getGetAllNewsList(){
        return getAllNewsList;
    }

    public void insert(List<EntityNews> newsList){
        new InsertAsyncTask(database).execute(newsList);
    }

    public void insertOne(EntityNews news){
        new InsertSingleAsyncTask(database).execute(news);
    }

    public LiveData<List<EntityNews>> getNewsFromRoom() {
        return database.newsDao().getAllNewsFromRoom();
    }

    static class InsertAsyncTask extends AsyncTask<List<EntityNews>, Void, Void>{
        private NewsDao newsDao;

        public InsertAsyncTask (NewsRoomDatabase database){
            newsDao = database.newsDao();
        }

        @Override
        protected Void doInBackground(List<EntityNews>... lists) {
            newsDao.insert(lists[0]);
            return null;
        }
    }

    private class InsertSingleAsyncTask extends AsyncTask<EntityNews,Void,Void> {
        private NewsDao newsDao;
        public InsertSingleAsyncTask(NewsRoomDatabase database) {
            newsDao = database.newsDao();
        }

        @Override
        protected Void doInBackground(EntityNews... entityNews) {
            newsDao.insertOne(entityNews[0]);
            return null;
        }
    }
}
