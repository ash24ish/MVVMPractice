package com.ashishbharam.mvvmpractice.roomdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
Created by Ashish Bharam on 08-Nov-20 at 04:28 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/
@Entity(tableName = "news_table")
public class EntityNews {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private int news_id;

    @SerializedName("title")
    @Expose
    private String news_title;

    @SerializedName("author")
    @Expose
    private String news_author;

    @SerializedName("date")
    @Expose
    private String news_date;

    @SerializedName("content")
    @Expose
    private String news_content;

    public EntityNews(String news_title, String news_author, String news_date, String news_content) {
        this.news_title = news_title;
        this.news_author = news_author;
        this.news_date = news_date;
        this.news_content = news_content;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_author() {
        return news_author;
    }

    public void setNews_author(String news_author) {
        this.news_author = news_author;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    @Override
    public String toString() {
        return "EntityNews{" +
                "news_id=" + news_id +
                ", news_title='" + news_title + '\'' +
                ", news_author='" + news_author + '\'' +
                ", news_date='" + news_date + '\'' +
                ", news_content='" + news_content + '\'' +
                '}';
    }
}
