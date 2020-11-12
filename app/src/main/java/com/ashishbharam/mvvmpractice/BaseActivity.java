package com.ashishbharam.mvvmpractice;
/* 
Created by Ashish Bharam on 11-Nov-20 at 10:23 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.app.Activity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.function.ToDoubleBiFunction;

public class BaseActivity extends AppCompatActivity {

    public void showLoader(){
        Toast.makeText(this, "Testing Function", Toast.LENGTH_SHORT).show();
    }

}
