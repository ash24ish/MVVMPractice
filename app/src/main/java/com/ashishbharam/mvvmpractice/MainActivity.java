package com.ashishbharam.mvvmpractice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishbharam.mvvmpractice.adapter.NewsRecycler;
import com.ashishbharam.mvvmpractice.roomdatabase.EntityNews;
import com.ashishbharam.mvvmpractice.roomdatabase.NewsRepository;
import com.ashishbharam.mvvmpractice.viewmodel.NewsViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsRecycler.OnNewsClickListener {

    private static final String TAG = "mytag";
    private NewsViewModel newsViewModel;
    private RecyclerView recyclerView;
    private NewsRecycler adapter;
    private ContentLoadingProgressBar progressBar;
    private MaterialTextView materialTextView;
    private List<EntityNews> entityNewsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.newsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        materialTextView = findViewById(R.id.errorView);

        NewsRepository newsRepository = new NewsRepository(getApplication());
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        initRecyclerView();

        /*if (isNetworkConnected())
            getNewsFromServer();
        else*/
            getsNewsFromLocalDb();

    }

    private void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsRecycler(this, entityNewsList);
    }

    private void getsNewsFromLocalDb(){
        newsViewModel.fetchFromRoom().observe(this, entityNews -> {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            materialTextView.setVisibility(View.GONE);

            entityNewsList = entityNews;
            adapter.setAllNews(entityNews);
            recyclerView.setAdapter(adapter);
            Log.d(TAG, "Fetching from Room ");
        });
    }



    @Override
    public void onNewsClick(int position) {
        Toast.makeText(MainActivity.this, "" + entityNewsList.get(position).getNews_id(), Toast.LENGTH_SHORT).show();
    }
}