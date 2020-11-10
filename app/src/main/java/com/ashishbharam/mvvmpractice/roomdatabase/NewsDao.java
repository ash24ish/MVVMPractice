package com.ashishbharam.mvvmpractice.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/*
Created by Ashish Bharam on 08-Nov-20 at 04:36 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/
@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(EntityNews news);

    @Query("SELECT * FROM news_table")
    LiveData<List<EntityNews>> getAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<EntityNews> allNewsList);

    @Query("DELETE FROM news_table")
    void deleteAll();

    @Query("SELECT * FROM news_table ORDER BY news_id desc")
    LiveData<List<EntityNews>> getAllNewsFromRoom();
}
