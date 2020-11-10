package com.ashishbharam.mvvmpractice.roomdatabase;
/* 
Created by Ashish Bharam on 08-Nov-20 at 04:39 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {EntityNews.class}, version = 1)
public abstract class NewsRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "NewsDatabase";
    private static NewsRoomDatabase mInstance;
    public abstract NewsDao newsDao();


    public static synchronized NewsRoomDatabase getInstance(final Context context){
        if (mInstance == null){
                    mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    NewsRoomDatabase.class,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return mInstance;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(mInstance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void,Void,Void> {
        private NewsDao newsDao;

        public PopulateAsyncTask(NewsRoomDatabase database) {
            newsDao = database.newsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.deleteAll();
            return null;
        }
    }
}
