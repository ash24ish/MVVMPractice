package com.ashishbharam.mvvmpractice.adapter;
/* 
Created by Ashish Bharam on 10-Nov-20 at 12:20 AM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishbharam.mvvmpractice.R;
import com.ashishbharam.mvvmpractice.roomdatabase.EntityNews;

import java.util.List;

public class NewsRecycler extends RecyclerView.Adapter<NewsRecycler.NewsViewHolder> {

    private View view;
    private OnNewsClickListener onNewsClickListener;
    private List<EntityNews> newsList;

    public void setAllNews(List<EntityNews> list){
        this.newsList = list;
    }

    public interface OnNewsClickListener{
        void onNewsClick(int position);
    }

    public NewsRecycler(OnNewsClickListener onNewsClickListener, List<EntityNews> newsList) {
        this.onNewsClickListener = onNewsClickListener;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_rv,parent,false);
        return new NewsViewHolder(view, onNewsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        EntityNews newsResponse = newsList.get(position);

        holder.newsTitle.setText(newsResponse.getNews_title());
        holder.newsAuthor.setText(newsResponse.getNews_author());
        holder.newsDate.setText(newsResponse.getNews_date());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView newsTitle, newsAuthor, newsDate;
        OnNewsClickListener newsClickListener;

        public NewsViewHolder(@NonNull View itemView, OnNewsClickListener onClickListener) {
            super(itemView);

            newsClickListener = onClickListener;
            newsTitle = itemView.findViewById(R.id.tvNewsTitle);
            newsAuthor = itemView.findViewById(R.id.tvNewsAuthor);
            newsDate = itemView.findViewById(R.id.tvNewsDate);

            itemView.setOnClickListener(v ->{
                if (newsClickListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        newsClickListener.onNewsClick(position);
                    }
                }
            });
        }
    }
}
