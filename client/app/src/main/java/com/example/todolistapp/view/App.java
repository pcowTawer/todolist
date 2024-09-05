package com.example.todolistapp.view;
import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("APPLICATION_ID")
                .server("http://10.0.2.2:1337/parse/")
                .build()
        );
    }
}